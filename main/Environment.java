package main;

import java.awt.*;
import java.util.*;

public class Environment {
	public int biome;
	Dimension coordinates;
	public ArrayList<Organism> organisms;
	
	public static int biomeCount = 3;
	
	public Environment(int x, int y) {
		coordinates = new Dimension(x, y);
		biome = (new Random()).nextInt(biomeCount);
		organisms = new ArrayList<Organism>();
	}
	public Environment(int x, int y, int biomeType) {
		coordinates = new Dimension(x, y);
		// ADD IF BIOME IS IN BIOME DICTIONARY/SET
		biome = biomeType;
		organisms = new ArrayList<Organism>();
	}
	
	public void addOrganism(Organism o) {
		organisms.add(o);
	}
	
	public void update() {
		// Update all organisms, reproduce, sweep out old organisms with "isDead"
		Random rand = new Random();
		ArrayList<Organism> newOrganisms = new ArrayList<Organism>();
		ArrayList<Organism> deadOrganisms = new ArrayList<Organism>();
		
		for(int c=0; c<organisms.size(); c++) {
			// Does the organism survive?
			organisms.get(c).testSurvival(100 - Math.abs(biome - organisms.get(c).species)*50);
			
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
}
