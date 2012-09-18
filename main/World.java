package main;

import java.util.*;
import model.WorldData;

public class World implements Runnable{
	public int height, width;
	
	private Thread worldThread;
	private ArrayList<DataListener> dataListeners;
	
	public Environment[][] environments;
	
	// Non-GUI data
	private boolean shouldUpdate;
	
	public int day, duration;
	// Day[] dayDatabase;
	
	public WorldData worldData;
	
	public World(int height, int width) {
		this(height, width, 0);
	}
	
	// Main Constructor - All others default to this
	public World(int height, int width, int duration) {
		this.height = height;
		this.width = width;
		
		shouldUpdate = true;
		
		day = 0;
		this.duration = duration;
		
		environments = new Environment[width][height];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(this, x, y);
			}
		}
		dataListeners = new ArrayList<DataListener>();
		
		worldData = new WorldData();
	}
	
	public World(Scenario scenario) {
		this(scenario.size.height, scenario.size.width, scenario.duration);
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
		while(shouldUpdate && (day < duration || duration == 0)) {
			// Tell Environments to update
			for(int x=0; x<width; x++) {
				for(int y=0; y<height; y++) {
					environments[x][y].update();
				}
			}
			
			// Add to stats models
			day++;
			// Day[].update();
			
			// Update other classes listening for updates in this class
			fireDataUpdate();
			
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
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
	
	public WorldData getWorldData() {
		return worldData;
	}
	
	public boolean isDoneRunning() {
		if(day < duration) {
			return false;
		}
		else {
			return true;
		}
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
}
