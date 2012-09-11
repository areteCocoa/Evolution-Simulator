package model;

import main.Environment;

public class BiomeStatsModel {
	public static int biomeCount = Environment.biomeCount;
	public static int[] totalBiomes = new int[biomeCount];
	
	public static void newBiomeCreated(int biome) {
		totalBiomes[biome]++;
	}
}
