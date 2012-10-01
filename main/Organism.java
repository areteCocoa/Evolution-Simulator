package main;

import java.util.*;

import main.traits.*;
import model.Dice;
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
	
	// TODO better implementation of this
	private Trait maxAge;
	private int age;
	
	// Clone Constructor
	public Organism(Organism o) {
		this(o.containingEnvironment, o.species);
	}
	
	public Organism(Environment env) {
		this(env, (new Random().nextInt(speciesCount)));
	}
	
	// Default Constructor
	public Organism(Environment env, int species) {
		this.species = species;
		this.isDead = false;
		this.containingEnvironment = env;
		this.wantsMigration = false;
		
		feed = 1;
		maxFeed = feed*5;
		
		traits = new ArrayList<Trait>();
		maxAge = new BehaviorTrait();
		age = 0;
		
		SpeciesStatsModel.newOrganism(species);
	}
	
	public String getName() {
		return Singleton.organismNameTable.get(species);
	}
	
	public Organism reproduce() {
		feed--;
		return new Organism(this);
	}
	
	public void testSurvival(int deathChance) {
		if((new Random()).nextInt(100)>deathChance && !isDead) {
			kill();
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
			this.wantsMigration = true;
		}
		else {
			this.wantsMigration = false;
		}
		
		// Manipulate traits
		// TODO Mutate traits
		if(Dice.getPercentBoolean(1)) {
			traits.add(new PhysicalTrait());
		}
		age++;
		if(age==maxAge.getValue() && !this.isDead) {
			kill();
		}
		// For all the traits
		// Chance to mutate
		// If mutate == true
		// trait.mutate
	}
	
	public void kill() {
		if(!isDead) {
			SpeciesStatsModel.deadOrganism(species);
			isDead = true;
			containingEnvironment.resourceCount+=feed;
		}
		else {
			System.out.println("kill() called on organism already dead: " + this);
		}
	}
}
