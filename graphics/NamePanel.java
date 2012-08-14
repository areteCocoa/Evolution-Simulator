package graphics;

import java.awt.*;

import javax.swing.*;

import main.*;
import model.EnvironmentTableModel;

public class NamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	World world;
	
	JTable environmentTable;
	
	public NamePanel(World w) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		environmentTable = new JTable(new EnvironmentTableModel());
		this.add(environmentTable);
		
		environmentTable.setRowHeight(25);
		environmentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		environmentTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		environmentTable.setFont(new Font("Helvetica", Font.PLAIN, 16));
		environmentTable.setEnabled(false);
		
		world = w;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Fill background
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Display Environment Biomes
		/* for(int x=0; x<Environment.biomeCount; x++) {
			g.setColor(Color.black);
			g.drawString("Biome #" + Integer.toString(x) + ": " + Singleton.biomeNameTable.get(x), 20, 30*(x+1));
			g.setColor(Singleton.biomeColorTable.get(x));
			g.fillRect(this.getWidth()-50, 30*(x)+16, 16, 16);
		} */
		
		// Display Species
		for(int x=0; x<Organism.speciesCount; x++) {
			g.setColor(Color.black);
			g.drawString(("Species #" + Integer.toString(x)) + ": " + Singleton.organismNameTable.get(x), 20, 30*(x+1)+150);
			g.setColor(Singleton.stringToColor(Singleton.organismColorTable.get(x)));
			g.fillRect(this.getWidth()-50, 30*(x)+16+150, 16, 16);
		}
		
		// Display resolution vs window size
		/* g.setColor(Color.black);
		g.drawString("Screen resolution:" + Integer.toString(Toolkit.getDefaultToolkit().getScreenSize().height) + " x " + Integer.toString(Toolkit.getDefaultToolkit().getScreenSize().width), 20, 500);
		g.drawString("Window Size: " + MainViewController.height + " x " + MainViewController.width, 20, 525); */
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((int)(StatsPanel.width*.6), 500);
	}
}
