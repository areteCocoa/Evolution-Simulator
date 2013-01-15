package main;

import graphics.start.*;

public class Main {
	
	public static void main(String[] args) {
		// Initialize the Singleton and all the files
		Singleton.main();
		
		// Create a scenario file to lock (and possibly load)
		Scenario scenario = new Scenario();
		
		// Create first window to see what the user wants
		StartViewController startWindow = new StartViewController(scenario);
		startWindow.showFrame(true);
		
		// Easy lazy loop
		// Main.main(args);
	}
}
