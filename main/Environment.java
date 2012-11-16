package main;

import java.awt.*;
import java.util.*;

import model.BiomeStatsModel;
import model.EnvironmentStatsModel;

public class Environment {
	public static int biomeCount;
	
	private static int[] heights = {5, 7, 9, 11, 13, 15},
			widths = {7, 9, 13, 16, 18, 21};
	
	public int biome;
	public Point coordinates;
	public ArrayList<Organism> organisms, incomingOrganisms, newOrganisms;
	
	public int resourceCount, resourceRegenRate, resourceMax;
	
	World world;
	
	public Environment(World world, int x, int y) {
		this(world, x, y, (new Random()).nextInt(biomeCount)+1);
	}
	
	public Environment(World world, int x, int y, int biomeType) {
		// All actual data
		this.world = world;
		coordinates = new Point(x, y);
		biome = biomeType;
		
		organisms = new ArrayList<Organism>();
		incomingOrganisms = new ArrayList<Organism>();
		newOrganisms = new ArrayList<Organism>();
		
		resourceRegenRate = (new Random()).nextInt(5)+5;
		resourceCount = resourceRegenRate * 2;
		resourceMax = resourceRegenRate*8;
		
		// All statistics data
		BiomeStatsModel.newBiomeCreated(biomeType);
	}
	
	public void addOrganism(Organism o) {
		organisms.add(o);
	}
	
	public Organism addRandomOrganism() {
		Organism tempOrganism = new Organism(this);
		organisms.add(tempOrganism);
		
		return tempOrganism;
	}
	
	public void addIncomingOrganism(Organism o) {
		incomingOrganisms.add(o);
		o.containingEnvironment = this;
	}
	
	public void addReproducedOrganism(Organism o) {
		newOrganisms.add(o);
	}
	
	public void update() {
		// TODO Add natural disasters
		// Disaster class
		// int killCount
		// String name
		// (Class?) EnvironmentalImpact
		
		// Regenerate more resources
		resourceCount += resourceRegenRate;
		if(resourceCount > resourceMax) {
			resourceCount = resourceMax;
		}
		
		// Update all organisms, reproduce, sweep out old organisms with "isDead"
		ArrayList<Organism> deadOrganisms = new ArrayList<Organism>(),
				outgoingOrganisms = new ArrayList<Organism>();
		
		Organism tempOrganism;
		for(int c=0; c<organisms.size(); c++) {
			tempOrganism = organisms.get(c);
			
			tempOrganism.update();
			
			// Test the survival
			// tempOrganism.testSurvival(200 - Math.abs(biome - organisms.get(c).species)*75);
			
			// Add dead organisms to list to be removed later
			if(tempOrganism.isDead) {
				deadOrganisms.add(tempOrganism);
			}
			
			// Does it want to migrate?
			if(tempOrganism.wantsMigration && !tempOrganism.isDead) {
				outgoingOrganisms.add(tempOrganism);
				
				int offset = 0;
				while(offset == 0) {
					offset = (new Random()).nextInt(3) - 1;
				}
				if((new Random()).nextBoolean()) {
					// Move vertically
					try{world.environments[this.coordinates.x + offset][this.coordinates.y].addIncomingOrganism(tempOrganism);}
					catch(ArrayIndexOutOfBoundsException e) {world.environments[this.coordinates.x - offset][this.coordinates.y].addIncomingOrganism(tempOrganism);}
				}
				else {
					// Move horizontally
					try{world.environments[this.coordinates.x][this.coordinates.y + offset].addIncomingOrganism(tempOrganism);}
					catch(ArrayIndexOutOfBoundsException e) {world.environments[this.coordinates.x][this.coordinates.y - offset].addIncomingOrganism(tempOrganism);}
				}
			}
		}
		organisms.addAll(newOrganisms);
		organisms.removeAll(deadOrganisms);
		
		organisms.addAll(incomingOrganisms);
		organisms.removeAll(outgoingOrganisms);
		
		incomingOrganisms.removeAll(incomingOrganisms);
		newOrganisms.removeAll(newOrganisms);
	}
	
	public EnvironmentStatsModel getEnvironmentStats() {
		return (new EnvironmentStatsModel(this));
	}
	
	// Statics
	// Return Dimension given index from sizeStrings array
	public static Dimension getDimensionFromIndex(int index) {
		if(index < widths.length) {
			return (new Dimension(widths[index], heights[index]));
		}
		return null;
	}
	
	public static String[] getSizesAsString() {
		String[] strings = new String[widths.length];
		for(int c = 0; c < widths.length; c++) {
			strings[c] = (String.valueOf(heights[c]) + " x " + String.valueOf(widths[c]));
		}
		return strings;
	}
}
