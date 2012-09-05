package main;

import java.util.*;

public class World implements Runnable{
	public int height, width;
	Thread worldThread;
	
	public Environment[][] environments;
	
	// Non-GUI data
	public int day;
	
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
	}

	public void addOrganisms(int count) {
		Environment tempEnv;
		for(int x=0; x<count; x++) {
			tempEnv = environments[(new Random().nextInt(this.width))][(new Random().nextInt(this.height))];
			tempEnv.addRandomOrganism();
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
			
			day++;
			
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
	}
	
	public void startThread() {
		worldThread.start();
	}
}
