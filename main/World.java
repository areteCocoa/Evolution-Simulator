package main;

import java.util.*;
import model.analytical.DayDataManager;
import model.analytical.WorldData;
import model.stats.SpeciesStatsModel;

public class World implements Runnable{
	public int height, width;
	
	private Thread worldThread;
	private ArrayList<DataListener> dataListeners;
	
	public Environment[][] environments;
	
	// Non-GUI data
	// shouldUpdate deals with threading, doneRunning deals with duration of world
	private boolean shouldUpdate, doneRunning;
	private int dayDuration;
	
	public int day, duration;
	private DayDataManager dayData;
	
	public WorldData worldData;
	
	public World(int height, int width) {
		this(height, width, 0);
	}
	
	// Main Constructor - All others default to this
	public World(int height, int width, int duration) {
		worldData = new WorldData();
		
		this.height = height;
		this.width = width;
		
		// shouldUpdate = true;
		doneRunning = false;
		
		day = 0;
		this.duration = duration;
		dayData = new DayDataManager();
		
		environments = new Environment[width][height];
		
		// Fill world with water
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(this, x, y, 0);
				worldData.biomeStats.addBiome(0);
			}
		}
		
		
		// Add islands of varying sizes
		// Declare all variables
		int landCount = (int)(width*height)/3;
		int landSquareCount, biome, biomesPlaced;
		Random random = new Random();
		Environment randomEnvironment;
		
		// Place land and build around it
		while(landCount > 0) {
			int x = random.nextInt(width), y = random.nextInt(height);
			randomEnvironment = environments[x][y];
			
			biome = random.nextInt(Environment.biomeCount);
			if(randomEnvironment.biome == 0) {
				landSquareCount = random.nextInt(17)+3;
				while(landCount - landSquareCount < 0) {
					landSquareCount = random.nextInt(16);
				}
				biomesPlaced = buildIsland(x, y, biome, landSquareCount);
				
				landCount -= biomesPlaced;
				for(int count=0; count<biomesPlaced; count++) {
					worldData.biomeStats.addBiome(biome);
					worldData.biomeStats.removeBiome(0);
				}
			}
		}
		
		dataListeners = new ArrayList<DataListener>();
	}
	
	// Builds island and returns amount of squares used
	private int buildIsland(int x, int y, int biome, int landSquareCount) {
		int environmentsPlaced = 0;
		
		try{
			Environment specifiedEnvironment = environments[x][y], nearbyEnvironment;
			
			if(specifiedEnvironment.biome != biome) {
				specifiedEnvironment.changeBiome(biome);
			}
			
			for(int xOffset=-1; xOffset<=1; xOffset++) {
				for(int yOffset=-1; yOffset<=1; yOffset++) {
					try{
						nearbyEnvironment = environments[x + xOffset][y + yOffset];
						
						if(landSquareCount > 0 && nearbyEnvironment.biome == 0) {
							nearbyEnvironment.changeBiome(biome);
							landSquareCount--;
							environmentsPlaced++;
						}
					}
					catch(ArrayIndexOutOfBoundsException e) {} // Don't do anything if it's out of the world's view
				}
			}
			
			if(landSquareCount > 0 ) {
				int nextLandCount = buildIsland(x + (new Random()).nextInt(3)-1, y + (new Random()).nextInt(3)-1, biome, landSquareCount);
				landSquareCount -= nextLandCount;
				environmentsPlaced += nextLandCount;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {} // Don't do anything if it's out of the world's view
		
		return environmentsPlaced;
	}
	
	public Environment getRandomEnvironment() {
		Random random = new Random();
		return environments[random.nextInt(this.width)][random.nextInt(this.height)];
	}
	
	public World(Scenario scenario) {
		this(scenario.size.height, scenario.size.width, scenario.duration);
		this.worldData.name = scenario.name;
		this.dayDuration = scenario.dayDuration;
		this.addOrganisms(scenario.startingOrganismCount);
	}

	public void addOrganisms(int count) {
		Environment tempEnv;
		for(int x=0; x<count; x++) {
			tempEnv = environments[(new Random().nextInt(this.width))][(new Random().nextInt(this.height))];
			tempEnv.addRandomOrganism();
		}
	}
	
	public Organism addOrganism() {
		Organism organism = environments[(new Random().nextInt(this.width))][(new Random().nextInt(this.height))].addRandomOrganism();
		fireDataUpdate();
		return organism;
	}
	
	public void addDataListener(DataListener d) {
		if(!dataListeners.contains(d)) {
			dataListeners.add(d);
		}
	}
	
	@Override
	public void run() {
		while(shouldUpdate && !doneRunning) {
			update();
			
			// Should I be done running?
			if(day == duration && duration != 0) {
				doneRunning = true;
			}
			
			try {
				Thread.sleep(dayDuration);
			}
			catch (InterruptedException e) {System.out.println("Threading error in world.");}
		}
		if(doneRunning) {
			updateWorldData();
		}
	}
	
	public void update() {
		// Tell Environments to update
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y].update();
			}
		}
					
		// Update DayData and reset speciesStatsModel
		day++;
		dayData.update();
		SpeciesStatsModel.newDay();
					
		// Update other classes listening for updates in this class
		fireDataUpdate();
	}
	
	public void fireDataUpdate() {
		for(int x=0; x<dataListeners.size(); x++) {
			dataListeners.get(x).fireDataUpdate();
		}
	}
	
	public void startThread() {
		shouldUpdate = true;
		worldThread = new Thread(this, "World-Engine");
		worldThread.start();
	}
	
	public WorldData getWorldData() {
		updateWorldData();
		return worldData;
	}
	
	public boolean isDoneRunning() {
		return doneRunning;
	}
	
	// Private methods
	private void updateWorldData() {
		worldData.daysRun = day;
		worldData.intendedDuration = duration;
		worldData.dayData = this.dayData;
	}
	
	// All controller methods
	public void stop() {
		shouldUpdate = false;
	}
	
	public void start() {
		if(!shouldUpdate) {
			this.startThread();
		}
	}
	
	public void step(int count) {
		for(int x=0; x<count; x++) {
			this.update();
		}
	}
	
	public boolean isRunning() {
		return shouldUpdate;
	}
	
	public void end() {
		doneRunning = true;
	}
	
	// Static methods
	
	public static World getBlankWorld(int height, int width) {
		World world = new World(height, width);
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				world.environments[x][y] = new Environment(world, x, y, 0);
			}
		}
		
		return world;
	}
}
