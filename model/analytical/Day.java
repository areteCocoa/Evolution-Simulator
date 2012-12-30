package model.analytical;

import model.stats.SpeciesStatsModel;

public class Day {
	
	SpeciesStatsModel speciesStats;
	
	public Day() {
		speciesStats = SpeciesStatsModel.getTodayInstance();
	}
	
	public SpeciesStatsModel getSpeciesStatsModel() {
		return speciesStats;
	}
}
