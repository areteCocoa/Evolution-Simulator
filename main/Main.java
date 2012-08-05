package main;

public class Main {
	
	public static void main(String[] args) {
		Singleton.main();
		
		World world = new World(5, 7);
		world.addOrganisms(500);
		
		ViewController mainController = new ViewController(world);
		mainController.showFrame();
	}
}
