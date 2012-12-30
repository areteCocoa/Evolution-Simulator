package model.stats;

import main.Organism;

public class SpeciesStatsModel {
	private static int speciesCount = Organism.speciesCount;
	public static int[] totalDead = new int[speciesCount];
	public static int[] totalAlive = new int[speciesCount];
	public static int[] totalEver = new int[speciesCount];
	
	/*
	private static int[] todayDead = new int[speciesCount];
	private static int[] todayAlive = new int[speciesCount];
	private static int[] todayEver = new int[speciesCount];
	*/
	
	private int _speciesCount;
	private int[] _totalDead, _totalAlive, _totalEver;
	
	public static void newOrganism(int species) {
		totalAlive[species]++;
		totalEver[species]++;
		
		/*
		todayAlive[species]++;
		todayEver[species]++;
		*/
	}
	
	public static void deadOrganism(int species) {
		totalDead[species]++;
		totalAlive[species]--;
		
		/*
		todayDead[species]++;
		todayAlive[species]--;
		*/
	}
	
	public static SpeciesStatsModel getInstance() {
		return (new SpeciesStatsModel());
	}
	
	public static SpeciesStatsModel getTodayInstance() {
		SpeciesStatsModel temp = new SpeciesStatsModel();
		
		return temp;
	}
	
	public static void newDay() {
		// todayDead = new int[speciesCount];
		// todayAlive = new int[speciesCount];
		// todayEver = new int[speciesCount];
	}
	
	private SpeciesStatsModel() {
		_speciesCount = speciesCount;
		_totalDead = totalDead.clone();
		_totalAlive = totalAlive.clone();
		_totalEver = totalEver.clone();
	}
	
	public int getSpeciesCount() {
		return _speciesCount;
	}
	
	public int getTotalAlive(int index) {
		if(index < _totalAlive.length) {
			return _totalAlive[index];
		}
		return 0;
	}
	
	public int getTotalDead(int index) {
		if(index < _totalDead.length) {
			return _totalDead[index];
		}
		return 0;
	}
	
	public int getTotal(int index) {
		if(index < _totalEver.length) {
			return _totalEver[index];
		}
		return 0;
	}
}
