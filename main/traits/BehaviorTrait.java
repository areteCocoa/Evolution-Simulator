package main.traits;

import java.util.Random;

import main.Singleton;

// Traits exhibited via behavior - Parental behavior, sexual behavior, etc
public class BehaviorTrait extends Trait {

	public BehaviorTrait() {
		super();
		
		name = Singleton.behaviorTraitTable.get((new Random()).nextInt(Singleton.behaviorTraitTable.size()-1));
	}
}
