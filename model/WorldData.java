package model;

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
