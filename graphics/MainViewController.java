package graphics;

import javax.swing.*;

import main.World;

import java.awt.*;

public class MainViewController {
	public static int height = (int) ((Toolkit.getDefaultToolkit().getScreenSize().height));
	public static int width = (int) ((Toolkit.getDefaultToolkit().getScreenSize().width));
	static int panelPadding = (int) (width*.01);
	// static int panelHeight = (int) (height - (panelPadding*2) - 25);
	
	JFrame mainFrame;
	
	WorldPanel worldPanel;
	StatsPanel statsPanel;
	
	public MainViewController(World world) {
		Rectangle mainRectangle, statsRectangle;
		
		// Handle all the panels before the main frame
		worldPanel = new WorldPanel(world);
		statsPanel = new StatsPanel(world);
		
		// Calculate envSize and mainWidth
		int workingWidth = width - (panelPadding*3), workingHeight = height - (panelPadding*2) - 25;
		int MPWidth = (int)(workingWidth*.7), MPHeight = (int)(workingHeight);
		if(world.height > world.width) {
			WorldPanel.envSize = (MPHeight - (WorldPanel.cellPadding*(world.height + 1)))/world.height;
			MPWidth = (WorldPanel.cellPadding*(world.width+1)) + (WorldPanel.envSize*world.width);
		}
		else if(world.height < world.width) {
			WorldPanel.envSize = (MPWidth - (WorldPanel.cellPadding*(world.width + 1)))/world.width;
			MPHeight = (WorldPanel.cellPadding*(world.height+1)) + (WorldPanel.envSize*world.height);
		}
		
		// WorldPanel.envSize = (int)(((Toolkit.getDefaultToolkit().getScreenSize().width)*(1200.0/1920.0))/world.width);
		// int mainPanelWidth = (WorldPanel.cellPadding*(world.width+1)) + (WorldPanel.envSize*world.width);
		
		// Set rectangles for the panels
		mainRectangle = new Rectangle(panelPadding, panelPadding, MPWidth, MPHeight);
		statsRectangle = new Rectangle((panelPadding*2)+mainRectangle.width, panelPadding, workingWidth - mainRectangle.width, MPHeight);
		
		// Resize frame to proper size
		width = (panelPadding*3) + mainRectangle.width + statsRectangle.width;
		height = (panelPadding*2) + mainRectangle.height + 25;
		
		// Resize panels to new size
		worldPanel.setBounds(mainRectangle);
		statsPanel.setBounds(statsRectangle);
		
		// Main Frame
		mainFrame = new JFrame("Evolution Simulation");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(width, height);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		
		// Configure SMListeners
		worldPanel.addSimplifiedMouseListener(statsPanel.getSMListeners()[0]);
		
		// Add panels to frame
		mainFrame.add(worldPanel);
		mainFrame.add(statsPanel);
	}
	
	public void defaultSize() {
		mainFrame.setSize(width, height);
	}
	
	public void showFrame() {
		mainFrame.setVisible(true);
	}
}