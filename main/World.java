package main;

import java.util.*;
import model.WorldData;

public class World implements Runnable{
	public int height, width;
	
	private Thread worldThread;
	private ArrayList<DataListener> dataListeners;
	
	public Environment[][] environments;
	
	// Non-GUI data
	// shouldUpdate refers to threading, doneRunning is the duration of the world
	private boolean shouldUpdate, doneRunning;
	
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
		
		// shouldUpdate = true;
		doneRunning = false;
		
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
		this.worldData.name = scenario.name;
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
			
			// Add to stats models
			day++;
			// Day[].update();
			
			// Update other classes listening for updates in this class
			fireDataUpdate();
			
			// Should I be done running?
			if(day == duration && duration != 0) {
				doneRunning = true;
			}
			
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
		if(doneRunning) {
			// initialize worldData for end data presentation
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
