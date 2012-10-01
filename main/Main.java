package main;

import javax.swing.JOptionPane;

import graphics.*;
import graphics.start.*;
import graphics.end.*;

public class Main {
	
	public static void main(String[] args) {
		// Initialize the Singleton and all the files
		Singleton.main();
		
		// Create a scenario file to lock (and possibly load) - no pun intended.
		Scenario scenario = new Scenario();
		
		// Create first window to see what the user wants
		StartViewController startWindow = new StartViewController(scenario);
		startWindow.showFrame(true);
		
		// Wait for Start Window to close
		synchronized(scenario) {
			scenario = startWindow.getScenario();
		}
		
		// Create new world
		World world = new World(scenario);
		// world.addOrganisms(10);
		
		// Display the world
		WorldViewController mainController = new WorldViewController(world);
		mainController.showFrame(true);
		
		// Set up updating
		world.addDataListener(mainController);
		
		// Auto-start the world - possibly remove later
		// world.startThread();
		
		while(!world.isDoneRunning()) {
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
		
		if(world.isDoneRunning()) {
			JOptionPane.showConfirmDialog(mainController.getMainFrame(), "View Data?", "End of Simulation",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			mainController.showFrame(false);
			
			EndViewController endWindow = new EndViewController(world.getWorldData());
			endWindow.showFrame();
		}
	}
}
