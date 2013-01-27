package main.traits;

import java.util.*;
import main.Singleton;
import model.Dice;

// Traits shown in physical form - Arms, legs, brain, bones
public class PhysicalTrait extends Trait {
	
	// Alleles with value 0 (aa), 1 (Aa), 2 (AA) instead of infinite range for simple implementation
	
	private int ID, biomeID;
	private TraitEffect traitEffect;
	
	protected enum TraitEffect {
		ENVIRONMENTAL, COMPETITIVE
	}
	
	public PhysicalTrait(int ID) {
		super();
		
		this.ID = ID;
		name = Singleton.traitData[ID].name;
		biomeID = Singleton.traitData[ID].biomeID;
		traitEffect = TraitEffect.ENVIRONMENTAL;
	}
	
	public PhysicalTrait reproduceTrait() {
		PhysicalTrait temp = new PhysicalTrait(this.ID);
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
	
	public int getBiomeID() {
		if(TraitEffect.ENVIRONMENTAL == traitEffect) {
			return biomeID;
		}
		return -1;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
