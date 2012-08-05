package graphics;

import java.awt.*;
import javax.swing.*;

import main.*;

public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	World world;
	
	public InfoPanel(World w) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		world = w;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Custom drawing
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Display Environment Biomes
		for(int x=0; x<Environment.biomeCount; x++) {
			g.setColor(Color.black);
			g.drawString("Biome #" + Integer.toString(x) + ": " + Singleton.biomeNameTable.get(x), 20, 30*(x+1));
			g.setColor(Singleton.biomeColorTable.get(x));
			g.fillRect(this.getWidth()-50, 30*(x)+16, 16, 16);
		}
		
		// Display Species
		for(int x=0; x<Organism.speciesCount; x++) {
			g.setColor(Color.black);
			g.drawString(("Species #" + Integer.toString(x)) + ": " + Singleton.organismNameTable.get(x), 20, 30*(x+1)+150);
			g.setColor(Singleton.stringToColor(Singleton.organismColorTable.get(x)));
			g.fillRect(this.getWidth()-50, 30*(x)+16+150, 16, 16);
		}
	}
}
