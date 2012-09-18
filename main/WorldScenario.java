package main;

import java.awt.Dimension;

public class WorldScenario {
	
	public String name;
	public Dimension size;
	
	public WorldScenario() {
		this(new Dimension());
	}
	
	public WorldScenario(Dimension size) {
		this.size = size;
		name = "Earth";
	}
	
	public WorldScenario(WorldScenario s) {
		this.name = s.name;
		this.size = s.size;
	}
	
	public void cloneFromScenario(WorldScenario s) {
		this.name = s.name;
		this.size = s.size;
	}
}
