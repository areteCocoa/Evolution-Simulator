package main;

import java.util.*;

import main.traits.*;
import model.Dice;
import model.stats.SpeciesStatsModel;

public class Organism {
	public static int speciesCount=9;
	
	// Static attributes - Only change once or twice
	public Environment containingEnvironment;
	public int species;
	
	// Booleans
	public boolean isDead, wantsMigration;
	private int nextOffspringSpecies;
	
	// Other attributes - Change often
	private int feed, maxFeed;
	private ArrayList<PhysicalTrait> traits;
	
	// Age
	private Trait maxAge;
	private int age;
	
	// Clone Constructor
	public Organism(Organism o) {
		this(o.containingEnvironment, o.species);
	}
	
	public Organism(Organism o, int species) {
		this(o.containingEnvironment, species);
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
		
		this.nextOffspringSpecies = species;
		
		feed = 1;
		maxFeed = feed*5;
		
		traits = new ArrayList<PhysicalTrait>();
		maxAge = new BehaviorTrait();
		age = 0;
		
		SpeciesStatsModel.newOrganism(species);
	}
	
	public String getName() {
		return Singleton.organismNameTable.get(species);
	}
	
	public Organism reproduce() {
		feed--;
		Organism newOrganism = new Organism(this, this.nextOffspringSpecies);
		newOrganism.traits = this.copyTraits();
		
		return newOrganism;
	}

	private void testSurvival(int deathChance) {
		if((new Random()).nextInt(100)<deathChance && !isDead) {
			kill();
		}
	}
	
	public void testBiomeSurvival() {
		// Test water survivability
		// Temporary implementation, eventually work towards dynamic implementations for different biomes/traits
				
		/* boolean canSwim = false;
		for(int x=0; x<traits.size(); x++) {
			if(traits.get(x).getName() == Singleton.physicalTraitTable.get(0) || traits.get(x).getName() == Singleton.physicalTraitTable.get(1)) {
			canSwim = true;
						
			if(containingEnvironment.biome == 0) {
				// A temporary reward for having well adapted traits
				// Or a detriment for being too adapted
				if(traits.get(x).getValue() > 2) {
					feed--;
				}
				} else {
					if(traits.get(x).getValue() >= 1) {
						kill();
					}
				}
			}
		// Handle the physical traits
		/* if(traits.get(x).getClass() == PhysicalTrait.class) {
			PhysicalTrait tempTrait = (PhysicalTrait)traits.get(x);
				
		} else if(traits.get(x).getClass() == BehaviorTrait.class) { // Handle the behavioral traits
						
		}
		}
		if(canSwim == false && containingEnvironment.biome == 0) { 
			kill();
		} */
		
		int deathChance = this.containingEnvironment.harshness/2;
		
		for(int count=0; count<traits.size(); count++) {
			if(traits.get(count).getBiomeID() == this.containingEnvironment.biome) {
				if(traits.get(count).getValue() != 0) {
					deathChance = deathChance / traits.get(count).getValue();
					System.out.println(containingEnvironment.coordinates + " " + deathChance);
					feed++;
				}
			}
		}
		
		// System.out.println(deathChance);
		testSurvival(deathChance);
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
		
		// Manipulate traits, possible speciation
		if(Dice.getPercentBoolean(2)) {
			if(Dice.getPercentBoolean(10)) {
				int traitID = (new Random()).nextInt(Organism.speciesCount);
				traits.add(new PhysicalTrait(traitID));
				this.nextOffspringSpecies = traitID;
				System.out.println(this + " Wow");
			} else {
				traits.add(new PhysicalTrait(species));
			}
		}
		age++;
		if(age==maxAge.getValue() && !this.isDead) {
			kill();
		}
		
		testBiomeSurvival();
		
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
	}
	
	// Used for possible mutation of traits
	private ArrayList<PhysicalTrait> copyTraits() {
		ArrayList<PhysicalTrait> traits = new ArrayList<PhysicalTrait>();
		for(int x=0; x<this.traits.size(); x++) {
			traits.add(this.traits.get(x).reproduceTrait());
		}
		
		return traits;
	}
}
