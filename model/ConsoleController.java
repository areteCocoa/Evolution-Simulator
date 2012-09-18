package model;

import main.Organism;
import main.World;

public class ConsoleController {
	
	World world;
	
	public ConsoleController(World world) {
		this.world = world;
	}
	
	public String processInput(String input) {
		String output = "Invalid command.";
		
		if(input.equalsIgnoreCase("stop")) {
			if(world.isRunning()) {
				output = "Stopping world.";
				world.stop();
			}
			else {
				output = "World already stopped.";
			}
		}
		else if(input.equalsIgnoreCase("start")) {
			if(world.isRunning()) {
				output = "World already running.";
			}
			else {
				output = "Starting world.";
				world.start();
			}
		}
		else if(input.equalsIgnoreCase("insert")) {
			Organism temp = world.addOrganism();
			output = ("Inserting organism at: " + temp.containingEnvironment.coordinates.x + " x " + temp.containingEnvironment.coordinates.y +
					" with species type " + temp.species);
		}
		
		return output;
	}
	
	
}
