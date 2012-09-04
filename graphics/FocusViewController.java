package graphics;

import java.awt.*;
import javax.swing.JPanel;

import main.*;
import simplifiedMouseListener.*;

public class FocusViewController extends JPanel implements SimplifiedMouseListener {
	private static final long serialVersionUID = 1L;
	
	GridBagLayout grid;
	
	FocusPanel focusPanel;
	FocusInfoPanel focusInfoPanel;
	
	World world;
	Environment lastClickedEnvironment;
	
	public FocusViewController(World world) {
		this.world = world;
		
		grid = new GridBagLayout();
		this.setLayout(grid);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		
		focusPanel = new FocusPanel();
		c.insets = new Insets(0, 0, StatsPanel.inset, StatsPanel.inset);
		c.gridx = 0;
		this.add(focusPanel, c);
		
		focusInfoPanel = new FocusInfoPanel();
		c.insets = new Insets(0, StatsPanel.inset, StatsPanel.inset, 0);
		c.gridx = 1;
		this.add(focusInfoPanel, c);
	}
	
	public void setDimensions(int[] columnWidths, int height) {
		int[] rowHeights = {height};
		
		grid.columnWidths = columnWidths;
		grid.rowHeights = rowHeights;
	}

	@Override
	public void fireMouseEvent(SimplifiedMouseEvent s) {
		Point p;
		if(s.getCustomObject().getClass() == Point.class) {
			p = (Point) s.getCustomObject();
			lastClickedEnvironment = world.environments[p.x][p.y];
			focusPanel.lastClickedEnvironment = this.lastClickedEnvironment;
			focusInfoPanel.setLastClickedEnvironment(lastClickedEnvironment);
		}
	}
}
