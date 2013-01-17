package main;

import graphics.start.*;

public class Main {
	
	public static void main(String[] args) {
		// Initialize the Singleton and all the files
		Singleton.main();
		
		// Create first window to see what the user wants
		StartViewController startWindow = new StartViewController();
		startWindow.showFrame(true);
	}
}
