package main;

import java.util.*;

public class World implements Runnable{
	public int height, width;
	Environment[][] environments;
	
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
		
		worldThread = new Thread(this);
		worldThread.start();
	}

	public void addOrganisms(int count) {
		for(int x=0; x<count; x++) {
			environments[(new Random().nextInt(5))][(new Random().nextInt(3))].addOrganism(new Organism());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.println("Updating World...");
			
			// Tell Environments to update
			for(int x=0; x<width; x++) {
				for(int y=0; y<height; y++) {
					environments[x][y].update();
				}
			}
			
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
	}
}
