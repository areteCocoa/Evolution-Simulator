package main;

import java.awt.Dimension;

public class Scenario {
	
	public String name;
	public Dimension size;
	public int duration, startingOrganismCount, dayDuration;
	public int startingSpeciesCount, biomeCount;
	public double landPercentage;
	
	public Scenario() {
		this(new Dimension());
	}
	
	public Scenario(Dimension size) {
		this.size = size;
		name = "Earth";
	}
	
	public Scenario(Scenario s) {
		this.name = s.name;
		this.size = s.size;
		this.duration = s.duration;
		this.startingOrganismCount = s.startingOrganismCount;
		this.dayDuration = s.dayDuration;
		this.startingSpeciesCount = s.startingSpeciesCount;
		this.startingOrganismCount = s.startingOrganismCount;
		this.biomeCount = s.biomeCount;
		this.landPercentage = s.landPercentage;
	}
	
	public void cloneFromScenario(Scenario s) {
		this.name = s.name;
		this.size = s.size;
		this.duration = s.duration;
		this.startingOrganismCount = s.startingOrganismCount;
		this.dayDuration = s.dayDuration;
		this.startingSpeciesCount = s.startingSpeciesCount;
		this.startingOrganismCount = s.startingOrganismCount;
		this.biomeCount = s.biomeCount;
		this.landPercentage = s.landPercentage;
	}
	
	public void setStatics() {
		Environment.biomeCount = this.biomeCount;
		Organism.speciesCount = this.startingSpeciesCount;
	}
}
