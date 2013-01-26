package graphics.stats;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.World;

public class OverviewViewController extends JPanel implements MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	OverviewPanel overviewPanel;
	OverviewInfoPanel overviewInfoPanel;
	
	GridBagLayout grid;
	
	public OverviewViewController(World world) {
		grid = new GridBagLayout();
		setLayout(grid);
		GridBagConstraints c = new GridBagConstraints();
		
		overviewPanel = new OverviewPanel();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, StatsPanel.inset);
		this.add(overviewPanel, c);
		
		overviewInfoPanel = new OverviewInfoPanel(world);
		c.gridx = 1;
		c.insets = new Insets(0, StatsPanel.inset, 0, 0);
		this.add(overviewInfoPanel, c);
		
		overviewPanel.addMouseListener(this);
		overviewPanel.addKeyListener(this);
	}
	
	public void updateData() {
		overviewInfoPanel.updateData();
	}

	public void setDimensions(int[] columnWidths, int height) {
		int[] rowHeights = {height};
		
		grid.columnWidths = columnWidths;
		grid.rowHeights = rowHeights;
		
		overviewPanel.resizeTable(columnWidths[0]);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		overviewInfoPanel.setTableView(overviewPanel.selectedTable);
		overviewInfoPanel.updateTableInfo(overviewPanel.selectedTable, overviewPanel.selectedIndex);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {
		overviewInfoPanel.updateTableInfo(overviewPanel.selectedTable, overviewPanel.selectedIndex);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
