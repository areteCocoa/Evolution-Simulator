package main;

import java.awt.*;
import java.util.*;

import model.EnvironmentStatsModel;

public class Environment {
	public static int biomeCount = 3;
	
	public int biome;
	public Point coordinates;
	public ArrayList<Organism> organisms;
	
	public int resourceCount, resourceRegenRate, resourceMax;
	
	public Environment(int x, int y) {
		coordinates = new Point(x, y);
		biome = (new Random()).nextInt(biomeCount);
		organisms = new ArrayList<Organism>();
		
		initResources();
	}
	
	public Environment(int x, int y, int biomeType) {
		coordinates = new Point(x, y);
		biome = biomeType;
		organisms = new ArrayList<Organism>();
		
		initResources();
	}

	private void initResources() {
		resourceRegenRate = (new Random()).nextInt(9)+1;
		resourceCount = resourceRegenRate * 4;
		resourceMax = resourceCount*6;
	}
	
	public void addOrganism(Organism o) {
		organisms.add(o);
	}
	
	public void update() {
		// Regenerate more resources
		resourceCount += resourceRegenRate;
		
		// Update all organisms, reproduce, sweep out old organisms with "isDead"
		Random rand = new Random();
		ArrayList<Organism> newOrganisms = new ArrayList<Organism>();
		ArrayList<Organism> deadOrganisms = new ArrayList<Organism>();
		
		for(int c=0; c<organisms.size(); c++) {
			// Does the organism survive?
			// int resourcePressure = (int)(100*resourceCount/resourceMax);
			// System.out.println(resourcePressure);
			if(resourceCount > 0) {
				organisms.get(c).testSurvival(100 - Math.abs(biome - organisms.get(c).species)*50);
				resourceCount -= 1;
			}
			else {
				organisms.get(c).kill();
			}
			
			
			if(organisms.get(c).isDead) {
				deadOrganisms.add(organisms.get(c));
			}
			if(rand.nextBoolean() && organisms.get(c).isDead == false) {
				newOrganisms.add(organisms.get(c).reproduce());
			}
		}
		organisms.addAll(newOrganisms);
		organisms.removeAll(deadOrganisms);
	}
	
	public EnvironmentStatsModel getEnvironmentStats() {
		return (new EnvironmentStatsModel(this));
	}
}
