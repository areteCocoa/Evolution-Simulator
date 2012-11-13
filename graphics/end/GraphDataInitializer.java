package graphics.end;

import org.jfree.data.category.DefaultCategoryDataset;

import main.Singleton;
import model.analytical.WorldData;


public class GraphDataInitializer {
	
	public static final int TOTAL_ALIVE = 0,
			TOTAL_DEAD = 1,
			TOTAL_EVER = 2;
	
	public static void initDatasetFromWorldData(DefaultCategoryDataset dataset, WorldData data, int dataType) {
		if(dataType == TOTAL_ALIVE) {
			for(int y=0; y<data.speciesStats.getSpeciesCount(); y++) {
				for(int x=0; x<data.daysRun; x++) {
					String label = new String(Singleton.organismNameTable.get(y));
					// new String("Organism " + Integer.toString(y))
					dataset.addValue(data.dayData.getDayData(x).getSpeciesStatsModel().getTotalAlive(y),
							label, new String("Day " + Integer.toString(x)));
				}
			}
		}
		else if(dataType == TOTAL_DEAD) {
			for(int y=0; y<data.speciesStats.getSpeciesCount(); y++) {
				for(int x=0; x<data.daysRun; x++) {
					String label = new String(Singleton.organismNameTable.get(y));
					// new String("Organism " + Integer.toString(y))
					dataset.addValue(data.dayData.getDayData(x).getSpeciesStatsModel().getTotalDead(y),
							label, new String("Day " + Integer.toString(x)));
				}
			}
		}
		else if(dataType == TOTAL_EVER) {
			for(int y=0; y<data.speciesStats.getSpeciesCount(); y++) {
				for(int x=0; x<data.daysRun; x++) {
					String label = new String(Singleton.organismNameTable.get(y));
					// new String("Organism " + Integer.toString(y))
					dataset.addValue(data.dayData.getDayData(x).getSpeciesStatsModel().getTotal(y),
							label, new String("Day " + Integer.toString(x)));
				}
			}
		}		
	}
}
