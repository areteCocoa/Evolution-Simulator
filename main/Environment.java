package main;

import java.awt.*;
import java.util.*;

public class Environment {
	int biome;
	Dimension coordinates;
	ArrayList<Organism> organisms;
	
	public Environment(int x, int y) {
		coordinates = new Dimension(x, y);
		biome = (new Random()).nextInt(3);
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
		// Update all organisms, reproduce, sweep out old deads
		Random rand = new Random();
		ArrayList<Organism> newOrganisms = new ArrayList<Organism>();
		
		for(int c=0; c<organisms.size(); c++) {
			// Does the organism survive?
			// Survival code here
			
			if(organisms.get(c).isDead) {
				organisms.remove(c);
			}
			if(rand.nextBoolean()) {
				newOrganisms.add(organisms.get(c).reproduce());
			}
		}
		organisms.addAll(newOrganisms);
	}
}
