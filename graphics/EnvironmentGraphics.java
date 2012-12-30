package graphics;

import java.awt.*;

import main.Environment;
import main.Singleton;

public class EnvironmentGraphics {

	// Types of land relative to other land
	// 1 being top left, 9 being bottom right
	public int startingAngle, drawAngle, xOffset, yOffset;
	public Color color;
	public boolean isSquare, isCircle;
	
	public EnvironmentGraphics(Environment environment) {
		// Determine relativeLocation if not water
		if(environment.biome != 0) {
			boolean[] nearbyLandArray = new boolean[4];
			nearbyLandArray[0] = (environment.nearbyEnvironment(0, -1).biome != 0);
			nearbyLandArray[1] = (environment.nearbyEnvironment(-1, 0).biome != 0);
			nearbyLandArray[2] = (environment.nearbyEnvironment(1, 0).biome != 0);
			nearbyLandArray[3] = (environment.nearbyEnvironment(0, 1).biome != 0);
			
			int trues = 0;
			for(int item=0; item<nearbyLandArray.length; item++) {
				if(nearbyLandArray[item] == true) {
					trues++;
				}
			}
			
			// Switch for drawAngle
			switch(trues) {
			case 0:
				drawAngle = 0;
				isCircle = true;
				break;
			case 1:
				drawAngle = 180;
				break;
			case 2:
				drawAngle = 90;
				break;
			case 3:
				drawAngle = 0;
				isSquare = true;
				break;
			case 4:
				drawAngle = 0;
				isSquare = true;
				break;
			}
			
			// Translate shape to correct startingAngle
			for(int count=3; count>0; count--) {
				if(nearbyLandArray[count]) {
					startingAngle = 360 - (count*90);
					break;
				}
			}
			
		} else {
			isSquare = true;
		}
		
		// Set other variables
		color = Singleton.biomeColorTable.get(environment.biome);
	}
	
}
