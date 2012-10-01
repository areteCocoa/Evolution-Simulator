package model;

import main.Environment;

public class BiomeStatsModel {
	public static int biomeCount = Environment.biomeCount;
	public static int[] totalBiomes = new int[biomeCount];
	
	private int _biomeCount;
	private int[] _totalBiomes;
	
	public static void newBiomeCreated(int biome) {
		totalBiomes[biome]++;
	}
	
	public static BiomeStatsModel getInstance() {
		return (new BiomeStatsModel());
	}
	
	
	private BiomeStatsModel() {
		_biomeCount = BiomeStatsModel.biomeCount;
		_totalBiomes = BiomeStatsModel.totalBiomes;
	}
	
	public int getBiomeCount() {
		return _biomeCount;
	}
	
	public int getTotalBiomes(int index) {
		if(index < _totalBiomes.length) {
			return _totalBiomes[index];
		}
		return 0;
	}
}
