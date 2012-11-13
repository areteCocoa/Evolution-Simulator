package main;

import java.util.*;

import model.*;
import model.analytical.DayDataManager;
import model.analytical.WorldData;

public class World implements Runnable{
	public int height, width;
	
	private Thread worldThread;
	private ArrayList<DataListener> dataListeners;
	
	public Environment[][] environments;
	
	// Non-GUI data
	// shouldUpdate refers to threading, doneRunning is the duration of the world
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
		this.height = height;
		this.width = width;
		
		// shouldUpdate = true;
		doneRunning = false;
		
		day = 0;
		this.duration = duration;
		dayData = new DayDataManager();
		
		environments = new Environment[width][height];
		/*
        # Fill world with land
        while (world.LAND_COUNT > 0):
            randEnv = self.environments[random.randrange(0, 80)][random.randrange(0, 40)];
            if(randEnv.biome == 0):
                world.LAND_COUNT = world.LAND_COUNT - 1;
                randEnv.biome = 1;
                tempCount = random.randrange(10, 20);
                tempEnv = randEnv;
                
                # Create land off of existing land
                while(tempCount > 0):
                    for x in range(-1, 1, 1):
                        for y in range(-1, 1, 1):
                            tempEnv = self.environments[tempEnv.coordinates[0]+x][tempEnv.coordinates[1]+y];
                            if(tempEnv.getBiome() != 1):
                                if(random.randrange(1, 10) > 0):
                                    tempEnv.setBiome(1);
                                    world.LAND_COUNT = world.LAND_COUNT - 1;
                                    tempCount = tempCount - 1;
                    randEnv = self.environments[tempEnv.coordinates[0]+random.randrange(-1, 1)][tempEnv.coordinates[1]+random.randrange(-1, 1)];  
                # End
        */
		// Fill world with water
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(this, x, y, 0);
			}
		}
		
		// Add land
		int landCount = 50;
		while(landCount > 0) {
			int x = (new Random()).nextInt(width), y = (new Random()).nextInt(height);
			Environment randomEnvironment = environments[x][y];
			if(randomEnvironment.biome == 0) {
				landCount--;
				environments[x][y] = new Environment(this, x, y, 1);
			}
		}
		
		/*
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(this, x, y);
			}
		}
		*/
		
		dataListeners = new ArrayList<DataListener>();
		
		worldData = new WorldData();
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
			
			// Should I be done running?
			if(day == duration && duration != 0) {
				doneRunning = true;
			}
			
			try {
				Thread.sleep(dayDuration);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
		if(doneRunning) {
			updateWorldData();
		}
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
	
	private void updateWorldData() {
		worldData.daysRun = day;
		worldData.intendedDuration = duration;
		worldData.dayData = this.dayData;
	}
	public WorldData getWorldData() {
		updateWorldData();
		return worldData;
	}
	
	public boolean isDoneRunning() {
		return doneRunning;
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
	
	public boolean isRunning() {
		return shouldUpdate;
	}
	
	public void end() {
		doneRunning = true;
	}
}
