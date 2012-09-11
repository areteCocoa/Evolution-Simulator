package main;

import graphics.*;

public class Main {
	
	public static void main(String[] args) {
		Singleton.main();
		
		World world = new World(2, 3);
		world.addOrganisms(10);
		
		MainViewController mainController = new MainViewController(world);
		
		mainController.showFrame();
		
		world.addDataListener(mainController);
		
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {System.out.println("ERROR");}
		world.startThread();
	}
}
