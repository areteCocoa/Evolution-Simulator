package model;

import main.*;

public class ConsoleController {
	
	private static String DEFAULT_MESSAGE = "Invalid command.";
	
	World world;
	Environment selectedEnvironment;
	
	public ConsoleController(World world) {
		this.world = world;
	}
	
	public void setSelectedEnvironment(Environment environment) {
		selectedEnvironment = environment;
	}
	
	public Environment getSelectedEnvironment() {
		if(selectedEnvironment != null) {
			return selectedEnvironment;
		} else {
			selectedEnvironment = world.getRandomEnvironment();
		}
		return this.getSelectedEnvironment();
	}
	
	public String processInput(String input) {
		String output = DEFAULT_MESSAGE;
		if(world.isDoneRunning()) {
			return "The simulation has ended";
		} else if(input.equalsIgnoreCase("stop")) {
			if(world.isRunning()) {
				output = "Stopping world.";
				world.stop();
			}
			else {
				output = "World already stopped.";
			}
		} else if(input.equalsIgnoreCase("start")) {
			if(world.isRunning()) {
				output = "World already running.";
			}
			else {
				output = "Starting world.";
				world.start();
			}
		} else if(input.equalsIgnoreCase("insert")) {
			Organism randomOrganism = this.getSelectedEnvironment().addRandomOrganism();
			output = ("Inserting organism at: " + this.getSelectedEnvironment().coordinates.x + " x " + this.getSelectedEnvironment().coordinates.y +
					" with species type " + randomOrganism.species);
		} else if(input.equalsIgnoreCase("end")) {
			output = "Ending simulation.";
			world.end();
		} else if(input.equalsIgnoreCase("disaster")) {
			this.getSelectedEnvironment().organisms.removeAll(this.getSelectedEnvironment().organisms);
			
			output = "Disaster at " + this.getSelectedEnvironment().coordinates.x + " x " + this.getSelectedEnvironment().coordinates.y;
		} else if(input.equalsIgnoreCase("step")) {
			world.step(1);
			output = "Stepping to day " + world.day;
		}
		
		if(output != DEFAULT_MESSAGE) {
			world.fireDataUpdate();
		}
		
		return output;
	}
	
	
}
