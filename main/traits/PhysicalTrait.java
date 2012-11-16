package main.traits;

import java.util.*;
import main.Singleton;
import model.Dice;

// Traits shown in physical form - Arms, legs, brain, bones
public class PhysicalTrait extends Trait {
	
	// Alleles with value 0 (aa), 1 (Aa), 2 (AA) instead of infinite range for simple implementation
	
	
	public PhysicalTrait() {
		super();
		
		name = Singleton.physicalTraitTable.get((new Random()).nextInt(Singleton.physicalTraitTable.size()-1));
		super.value = 2;
	}
	
	public Trait reproduceTrait() {
		PhysicalTrait temp = new PhysicalTrait();
		temp.name = this.getName();
		temp.value = this.getValue();
		
		// Chance for mutation after reproducing trait
		if(Dice.getPercentBoolean(1)) {
			temp.setValue(temp.getValue() + ( ((new Random()).nextInt(3))-1));
			if(temp.value < 0) {
				temp.value = 0;
			}
		}
		
		return temp;
	}
}
