package model;

public class Day {
	
	SpeciesStatsModel speciesStats;
	
	public Day() {
		speciesStats = SpeciesStatsModel.getTodayInstance();
	}
	
	public SpeciesStatsModel getSpeciesStatsModel() {
		return speciesStats;
	}
}
