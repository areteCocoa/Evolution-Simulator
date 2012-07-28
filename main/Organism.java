package main;

import java.util.*;

public class Organism {
	int species;
	boolean isDead;
	
	public Organism() {
		species = (new Random().nextInt(3));
		isDead = false;
	}
	
	public Organism(int species) {
		this.species = species;
		isDead = false;
	}
	
	public String getName() {
		return "NAME NOT IMPLEMENTED, SEE FUTURE UPDATES!";
	}
	
	public Organism reproduce() {
		return new Organism(this.species);
	}
}
