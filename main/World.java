package main;

import java.util.*;

public class World implements Runnable{
	public int height, width;
	public Environment[][] environments;
	
	Thread worldThread;
	
	public World(int h, int w) {
		height = h;
		width = w;
		
		environments = new Environment[width][height];
		for(int x=0; x<width; x++) {
			for(int y=0; y<height; y++) {
				environments[x][y] = new Environment(x, y);
			}
		}
		
		worldThread = new Thread(this, "World Engine Thread");
		worldThread.start();
	}

	public void addOrganisms(int count) {
		for(int x=0; x<count; x++) {
			environments[(new Random().nextInt(this.width))][(new Random().nextInt(this.height))].addOrganism(new Organism());
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
			
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
	}
}
