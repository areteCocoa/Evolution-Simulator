package graphics;

import javax.swing.*;

import main.World;

import java.awt.*;

public class MainViewController {
	public static int height = (int) ((Toolkit.getDefaultToolkit().getScreenSize().height)*.8);
	public static int width = (int) ((Toolkit.getDefaultToolkit().getScreenSize().width)*.8);
	static int panelPadding = (int) (width*.01);
	static int panelHeight = (int) (height - (panelPadding*2) - 25);
	
	JFrame mainFrame;
	
	WorldPanel worldPanel;
	StatsPanel statsPanel;
	
	public MainViewController(World w) {
		Rectangle mainRectangle, statsRectangle;
		
		// Handle all the panels before the main frame
		worldPanel = new WorldPanel(w);
		statsPanel = new StatsPanel(w);
		
		// Calculate envSize and mainWidth
		WorldPanel.envSize = (int)(((Toolkit.getDefaultToolkit().getScreenSize().width)*(1200.0/1920.0))/w.width);
		int mainPanelWidth = (WorldPanel.cellPadding*(w.width+1)) + (WorldPanel.envSize*w.width);
		panelHeight = (WorldPanel.cellPadding*(w.height + 1)) + (WorldPanel.envSize*w.height);
		
		// Set rectangles for the panels
		mainRectangle = new Rectangle(panelPadding, panelPadding, mainPanelWidth, panelHeight);
		statsRectangle = new Rectangle((panelPadding*2)+mainRectangle.width, panelPadding, (int)(width*.4), panelHeight);
		
		// Resize frame to proper size
		width = (panelPadding*4) + mainRectangle.width + statsRectangle.width;
		height = (panelPadding*2) + panelHeight + 25;
		
		// Resize panels to new size
		worldPanel.setBounds(mainRectangle);
		statsPanel.setBounds(statsRectangle);
		
		// Main Frame
		mainFrame = new JFrame("Evolution Simulation");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(width, height);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		
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