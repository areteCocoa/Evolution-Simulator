package main.traits;

// Superclass for all types of traits
public abstract class Trait {
	
	private String name;
	private int value;
	
	public Trait() {
		
	}
	
	public void mutate() {
		// Change value
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
}
