package main;

import java.awt.*;
import java.util.*;

import model.BiomeStatsModel;
import model.EnvironmentStatsModel;

public class Environment {
	public static int biomeCount = 3;
	
	public int biome;
	public Point coordinates;
	public ArrayList<Organism> organisms, incomingOrganisms;
	
	public int resourceCount, resourceRegenRate, resourceMax;
	
	World world;
	
	public Environment(World world, int x, int y) {
		this(world, x, y, (new Random()).nextInt(biomeCount));
	}
	
	public Environment(World world, int x, int y, int biomeType) {
		// All actual data
		this.world = world;
		coordinates = new Point(x, y);
		biome = biomeType;
		organisms = new ArrayList<Organism>();
		incomingOrganisms = new ArrayList<Organism>();
		
		resourceRegenRate = (new Random()).nextInt(9)+1;
		resourceCount = resourceRegenRate * 4;
		resourceMax = resourceCount*2;
		
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
	}
	
	public void update() {
		// Regenerate more resources
		resourceCount += resourceRegenRate;
		if(resourceCount > resourceMax) {
			resourceCount = resourceMax;
		}
		
		// Update all organisms, reproduce, sweep out old organisms with "isDead"
		Random rand = new Random();
		ArrayList<Organism> newOrganisms = new ArrayList<Organism>(),
				deadOrganisms = new ArrayList<Organism>(),
				outgoingOrganisms = new ArrayList<Organism>();
		
		Organism tempOrganism;
		for(int c=0; c<organisms.size(); c++) {
			tempOrganism = organisms.get(c);
			
			tempOrganism.update();
			
			// Resource Check
			if(resourceCount > 0) {
				tempOrganism.testSurvival(100 - Math.abs(biome - organisms.get(c).species)*50);
			}
			else {
				tempOrganism.kill();
			}
			
			// Add dead organisms to list to be removed later
			if(tempOrganism.isDead) {
				deadOrganisms.add(tempOrganism);
			}
			
			// Chance to reproduce/migrate
			if(rand.nextBoolean() && tempOrganism.isDead == false) {
				newOrganisms.add(tempOrganism.reproduce());
			}
			
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
	}
	
	public EnvironmentStatsModel getEnvironmentStats() {
		return (new EnvironmentStatsModel(this));
	}
}
