package main;

import java.util.*;

import main.traits.Trait;
import model.SpeciesStatsModel;

public class Organism {
	public static int speciesCount=5;
	
	// Static attributes - Only change once or twice
	public Environment containingEnvironment;
	public int species;
	boolean isDead, wantsMigration;
	
	// Other attributes - Change often
	private int feed, maxFeed;
	private ArrayList<Trait> traits;
	
	// Clone Constructor
	public Organism(Organism o) {
		this(o.containingEnvironment, o.species);
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
		
		// TODO Add traits to organism
		traits = new ArrayList<Trait>();
		
		SpeciesStatsModel.newOrganism(species);
	}
	
	public String getName() {
		return Singleton.organismNameTable.get(species);
	}
	
	public Organism reproduce() {
		feed--;
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
		else if(!isDead) {
			kill();
		}
		
		if(Dice.getPercentage(feed, maxFeed) < 40 && Dice.getPercentBoolean(5) && !isDead) {
			// System.out.println(this + " " + isDead);
			this.wantsMigration = true;
		}
		else {
			this.wantsMigration = false;
		}
		
		// TODO Mutate traits
		// For all the traits
		// Chance to mutate
		// If mutate == true
		// trait.mutate
	}
	
	public void kill() {
		SpeciesStatsModel.deadOrganism(species);
		isDead = true;
	}
}
