package main;

import java.util.*;

public class World implements Runnable{
	public int height, width;
	
	private Thread worldThread;
	private ArrayList<DataListener> dataListeners;
	
	public Environment[][] environments;
	
	// Non-GUI data
	private boolean shouldUpdate;
	
	public int day;
	// Day[] dayDatabase;
	
	public World(int h, int w) {
		height = h;
		width = w;
		
		shouldUpdate = true;
		
		day = 0;
		
		environments = new Environment[width][height];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(this, x, y);
			}
		}
		dataListeners = new ArrayList<DataListener>();
	}
	
	public World(WorldScenario scenario) {
		this(scenario.size.height, scenario.size.width);
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
		while(shouldUpdate) {
			// Tell Environments to update
			for(int x=0; x<width; x++) {
				for(int y=0; y<height; y++) {
					environments[x][y].update();
				}
			}
			
			// Add to stats models
			day++;
			
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
