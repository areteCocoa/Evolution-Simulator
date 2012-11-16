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
		Organism newOrganism = new Organism(this);
		newOrganism.traits = this.copyTraits();
		
		return newOrganism;
	}
	
	public void testSurvival(int deathChance) {
		if((new Random()).nextInt(100)>deathChance && !isDead) {
			kill();
		}
	}
	
	public void testBiomeSurvival() {
		// Test physical traits compared to the biome
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
			// More traits requires more food
			for(int x=0; x<traits.size(); x++) {
				feed--;
			}
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
		if(Dice.getPercentBoolean(1)) {
			traits.add(new PhysicalTrait());
		}
		age++;
		if(age==maxAge.getValue() && !this.isDead) {
			kill();
		}
		
		// Test water survivability
		// Temporary implementation, eventually work towards dynamic implementations for different biomes/traits
		boolean canSwim = false;
		for(int x=0; x<traits.size(); x++) {
			if(traits.get(x).getName() == Singleton.physicalTraitTable.get(0) || traits.get(x).getName() == Singleton.physicalTraitTable.get(1)) {
				canSwim = true;
				
				if(containingEnvironment.biome == 0) {
					// Temporary reward for having well adapted traits
					if(traits.get(x).getValue() > 1) {
						feed++;
					}
				}
			}
			
			// Handle the physical traits
			/* if(traits.get(x).getClass() == PhysicalTrait.class) {
				PhysicalTrait tempTrait = (PhysicalTrait)traits.get(x);
				
			} else if(traits.get(x).getClass() == BehaviorTrait.class) { // Handle the behavioral traits
				
			} */
			
		}
		if(canSwim == false && containingEnvironment.biome == 0) { 
			kill();
		}
		
		// Chance to reproduce/migrate
		if((new Random()).nextBoolean() && !isDead) {
			containingEnvironment.addReproducedOrganism(this.reproduce());
		}
	}
	
	// Marks organism as dead and notifies stats models
	public void kill() {
		if(!isDead) {
			SpeciesStatsModel.deadOrganism(species);
			isDead = true;
			containingEnvironment.resourceCount+=feed;
		}
		else {
			System.out.println("Error: kill() called on organism already dead: " + this);
		}
	}
	
	// Used for possible mutation of traits
	private ArrayList<Trait> copyTraits() {
		ArrayList<Trait> traits = new ArrayList<Trait>();
		for(int x=0; x<this.traits.size(); x++) {
			traits.add(this.traits.get(x).reproduceTrait());
		}
		
		return traits;
	}
}
