package model;

public class DefaultSettings {
	public int sizeIndex, duration, dayDuration, organismCount, speciesCount,biomeCount;
	public boolean limitedDuration;
	
	public void printSettings() {
		System.out.println("Size Index: " + sizeIndex);
		System.out.println("Duration: " + duration);
		System.out.println("Day Duration: " + dayDuration);
		System.out.println("Organism Count: " + organismCount);
		System.out.println("Species Count: " + speciesCount);
		System.out.println("Biome Count: " + biomeCount);
		System.out.println("Limited Duration: " + limitedDuration);
	}
}
