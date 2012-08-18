package model;

import java.awt.*;

import main.Environment;
import main.Singleton;

public class EnvironmentStatsModel {
	public String name;
	public Point coordinates;
	public int biome, resourceCount, resourceRate;
	
	public EnvironmentStatsModel(Environment e) {
		this.coordinates = e.coordinates;
		this.biome = e.biome;
		this.name = Singleton.biomeNameTable.get(this.biome);
		this.resourceCount = e.resourceCount;
		this.resourceRate = e.resourceRegenRate;
	}
}
