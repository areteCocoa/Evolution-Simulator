package main;

public class Main {
	
	public static void main(String[] args) {
		World world = new World(3, 5);
		world.addOrganisms(100);
		
		ViewController mainController = new ViewController(world);
		mainController.showFrame();
	}
}
