package model;

import main.Organism;

public class SpeciesStatsModel {
	private static int speciesCount = Organism.speciesCount;
	public static int[] totalDead = new int[speciesCount];
	public static int[] totalAlive = new int[speciesCount];
	public static int[] totalEver = new int[speciesCount];
	
	public static void newOrganism(int species) {
		totalAlive[species]++;
		totalEver[species]++;
	}
	
	public static void deadOrganism(int species) {
		totalDead[species]++;
		totalAlive[species]--;
	}
}
