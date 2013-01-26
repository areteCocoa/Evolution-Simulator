package model.analytical;

import main.Environment;
import model.stats.BiomeStatsModel;
import model.stats.SpeciesStatsModel;

public class WorldData {
	
	public String name;
	public int daysRun, intendedDuration;
	
	public BiomeStatsModel biomeStats;
	public SpeciesStatsModel speciesStats;
	
	public DayDataManager dayData;
	
	public WorldData() {
		biomeStats = new BiomeStatsModel(Environment.biomeCount);
		speciesStats = SpeciesStatsModel.getInstance();
	}
	
	public BiomeStatsModel getBiomeStatsModel() {
		return biomeStats;
	}
	
	public SpeciesStatsModel getSpeciesStatsModel() {
		return speciesStats;
	}
}
