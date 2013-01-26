package model.stats;

public class BiomeStatsModel {
	// public static int biomeCount = Environment.biomeCount;
	// public static int[] totalBiomes = new int[biomeCount];
	
	private int biomeCount;
	private int[] totalBiomes;
	
	public BiomeStatsModel(int biomeCount) {
		this.biomeCount = biomeCount;
		totalBiomes = new int[this.biomeCount];
	}
	
	public void addBiome(int biome) {
		totalBiomes[biome]++;
	}
	
	public void removeBiome(int biome) {
		totalBiomes[biome]--;
	}
	
	public int getBiomeCount() {
		return this.biomeCount;
	}
	
	public int getTotalBiomes(int index) {
		if(index < totalBiomes.length) {
			return totalBiomes[index];
		}
		return 0;
	}
}
