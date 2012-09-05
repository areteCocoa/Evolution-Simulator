package main;

import java.util.*;

import model.SpeciesStatsModel;

public class Organism {
	public static int speciesCount=5;
	
	// Static attributes - Only change once or twice
	public Environment containingEnvironment;
	public int species;
	boolean isDead, wantsMigration;
	
	// Other attributes - Change often
	private int feed, maxFeed;
	// Characteristic characteristics[]
	
	// Clone Constructor
	public Organism(Organism o) {
		this.containingEnvironment = o.containingEnvironment;
		this.species = o.species;
		this.isDead = o.isDead;
		this.wantsMigration = o.wantsMigration;
		
		this.feed = o.feed;
		this.maxFeed = o.maxFeed;
	}
	
	public Organism(Environment env) {
		this(env, (new Random().nextInt(speciesCount)));
	}
	
	public Organism(Environment env, int species) {
		this.species = species;
		this.isDead = false;
		this.containingEnvironment = env;
		this.wantsMigration = false;
		
		feed = 1;
		maxFeed = feed*5;
		
		SpeciesStatsModel.newOrganism(species);
	}
	
	public String getName() {
		return Singleton.organismNameTable.get(species);
	}
	
	public Organism reproduce() {
		return new Organism(this);
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
	
	public void update() {
		// Try to collect food if not full
		if(feed < maxFeed) {
			for(int x=0; x<3; x++) {
				if(Dice.getPercentBoolean(Dice.getPercentage(containingEnvironment.resourceCount, containingEnvironment.resourceMax))) {
					feed++;
					containingEnvironment.resourceCount--;
				}
			}
		}
		
		// Eat
		if(feed>0) {
			feed--;
		}
		else {
			kill();
		}
		
		if(Dice.getPercentage(feed, maxFeed) < 30 && Dice.getPercentBoolean(10)) {
			this.wantsMigration = true;
		}
		else {
			this.wantsMigration = false;
		}
	}
	
	public void kill() {
		SpeciesStatsModel.deadOrganism(species);
		isDead = true;
	}
}
