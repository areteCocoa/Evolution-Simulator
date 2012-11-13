package main.traits;

import java.util.Random;

import main.Singleton;

// Traits exhibited via behavior - Parental behavior, sexual behavior, etc
public class BehaviorTrait extends Trait {

	public BehaviorTrait() {
		super();
		
		name = Singleton.behaviorTraitTable.get((new Random()).nextInt(Singleton.behaviorTraitTable.size()-1));
	}
	
	public static BehaviorTrait getMaxAgeTrait(int age) {
		BehaviorTrait temp = new BehaviorTrait();
		temp.name = "Age";
		temp.value = 5;
		
		return temp;
	}

	public Trait reproduceTrait() {
		return null;
	}
}
