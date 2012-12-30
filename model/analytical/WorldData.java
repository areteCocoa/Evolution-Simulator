package model.analytical;

import model.stats.BiomeStatsModel;
import model.stats.SpeciesStatsModel;

public class WorldData {
	
	public String name;
	public int daysRun, intendedDuration;
	
	public BiomeStatsModel biomeStats;
	public SpeciesStatsModel speciesStats;
	
	public DayDataManager dayData;
	
	public WorldData() {
		biomeStats = BiomeStatsModel.getInstance();
		speciesStats = SpeciesStatsModel.getInstance();
	}
}
