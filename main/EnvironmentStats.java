package main;

import java.awt.*;

public class EnvironmentStats {
	public String name;
	public Point coordinates;
	public int biome, resourceCount, resourceRate;
	
	public EnvironmentStats(Environment e) {
		this.coordinates = e.coordinates;
		this.biome = e.biome;
		this.name = Singleton.biomeNameTable.get(this.biome);
		this.resourceCount = e.resourceCount;
		this.resourceRate = e.resourceRegenRate;
	}
}
