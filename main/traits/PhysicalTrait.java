package main.traits;

import java.util.*;
import main.Singleton;

// Traits shown in physical form - Arms, legs, brain, bones
public class PhysicalTrait extends Trait {
	
	public PhysicalTrait() {
		super();
		
		name = Singleton.physicalTraitTable.get((new Random()).nextInt(Singleton.physicalTraitTable.size()-1));
		System.out.println(name);
	}
}
