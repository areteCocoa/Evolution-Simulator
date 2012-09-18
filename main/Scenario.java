package main;

import java.awt.Dimension;

public class Scenario {
	
	public String name;
	public Dimension size;
	public int duration;
	
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
	}
	
	public void cloneFromScenario(Scenario s) {
		this.name = s.name;
		this.size = s.size;
		this.duration = s.duration;
	}
}
