package main.traits;

// Superclass for all types of traits
public abstract class Trait {
	
	protected String name;
	protected int value;
	
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
	
	protected void setValue(int value) {
		this.value = value;
	}
	
	abstract public Trait reproduceTrait();
}
