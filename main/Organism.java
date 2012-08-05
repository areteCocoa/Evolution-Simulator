package main;

import java.util.*;

public class Organism {
	public static int speciesCount=5;
	
	public int species;
	boolean isDead;
	
	public Organism() {
		species = (new Random().nextInt(speciesCount));
		isDead = false;
	}
	
	public Organism(int species) {
		this.species = species;
		isDead = false;
	}
	
	public String getName() {
		return Singleton.organismNameTable.get(species);
	}
	
	public Organism reproduce() {
		return new Organism(this.species);
	}
	
	public void testSurvival(int deathChance) {
		if((new Random()).nextInt(100)>deathChance) {
			isDead = true;
		}
	}
}
