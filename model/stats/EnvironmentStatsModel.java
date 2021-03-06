package model.stats;

import java.awt.*;

import main.Environment;
import main.Singleton;

public class EnvironmentStatsModel {
	public String name;
	public Point coordinates;
	public int biome, resourceCount, resourceRate, resourceMax;
	
	public EnvironmentStatsModel(Environment e) {
		this.coordinates = e.coordinates;
		this.biome = e.biome;
		this.name = Singleton.biomeData[e.biome].name;
		this.resourceCount = e.resourceCount;
		this.resourceRate = e.resourceRegenRate;
		this.resourceMax = e.resourceMax;
	}
}
