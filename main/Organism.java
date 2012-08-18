package main;

import java.util.*;

import model.SpeciesStatsModel;

public class Organism {
	public static int speciesCount=5;
	
	
	public int species;
	boolean isDead;
	
	public Organism() {
		species = (new Random().nextInt(speciesCount));
		isDead = false;
		SpeciesStatsModel.newOrganism(species);
	}
	
	public Organism(int species) {
		this.species = species;
		isDead = false;
		SpeciesStatsModel.newOrganism(species);
	}
	
	public String getName() {
		return Singleton.organismNameTable.get(species);
	}
	
	public Organism reproduce() {
		return new Organism(this.species);
	}
	
	public boolean testSurvival(int deathChance) {
		if((new Random()).nextInt(100)>deathChance) {
			kill();
			return false;
		}
		else {
			return true;
		}
	}
	
	public void kill() {
		SpeciesStatsModel.deadOrganism(species);
		isDead = true;
	}
}
