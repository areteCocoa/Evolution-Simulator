package main;

import java.util.*;

public class World implements Runnable{
	public int height, width;
	
	private Thread worldThread;
	private ArrayList<DataListener> dataListeners;
	
	public Environment[][] environments;
	
	// Non-GUI data
	public int day;
	// Day[] dayDatabase;
	
	public World(int h, int w) {
		height = h;
		width = w;
		
		day = 0;
		
		environments = new Environment[width][height];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(this, x, y);
			}
		}
		
		worldThread = new Thread(this, "World-Engine");
		dataListeners = new ArrayList<DataListener>();
	}

	public void addOrganisms(int count) {
		Environment tempEnv;
		for(int x=0; x<count; x++) {
			tempEnv = environments[(new Random().nextInt(this.width))][(new Random().nextInt(this.height))];
			tempEnv.addRandomOrganism();
		}
	}
	
	public void addDataListener(DataListener d) {
		if(!dataListeners.contains(d)) {
			dataListeners.add(d);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			// Tell Environments to update
			for(int x=0; x<width; x++) {
				for(int y=0; y<height; y++) {
					environments[x][y].update();
				}
			}
			
			// Add to stats models
			day++;
			
			// Update other classes listening for updates in this class
			for(int x=0; x<dataListeners.size(); x++) {
				dataListeners.get(x).fireDataUpdate();
			}
			
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
	}
	
	public void startThread() {
		worldThread.start();
	}
}
