package main;

import graphics.*;
import graphics.start.*;

public class Main {
	
	public static void main(String[] args) {
		// Initialize the Singleton and all the files
		Singleton.main();
		
		// Create a scenario file to lock (and possibly load) - no pun intended.
		WorldScenario scenario = new WorldScenario();
		
		// Create first window to see what the user wants
		StartViewController startWindow = new StartViewController(scenario);
		startWindow.showFrame(true);
		
		// Wait for Start Window to close
		synchronized(scenario) {
			System.out.println("Start Window Closed");
			scenario = startWindow.getScenario();
		}
		
		// Create new world
		World world = new World(scenario);
		world.addOrganisms(10);
		
		// Display the world
		WorldViewController mainController = new WorldViewController(world);
		mainController.showFrame();
		
		// Set up updating
		world.addDataListener(mainController);
		
		// Let the mainViewController draw before drawing the world
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {System.out.println("ERROR");}
		
		// Auto-start the world - possibly remove later
		world.startThread();
	}
}
