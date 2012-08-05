package main;

import javax.swing.*;
import java.awt.*;

import graphics.*;

public class ViewController {
	static int height = (int) ((Toolkit.getDefaultToolkit().getScreenSize().height)*.8);
	static int width = (int) ((Toolkit.getDefaultToolkit().getScreenSize().width)*.8);
	static int panelPadding = (int) (width*.01);
	static int panelHeight = (int) (height - (panelPadding*2) - 25);
	
	JFrame mainFrame;
	
	WorldPanel worldPanel;
	InfoPanel infoPanel;
	
	public ViewController(World w) {
		Rectangle mainRectangle, infoRectangle;
		
		// Handle all the panels before the main frame
		worldPanel = new WorldPanel(w);
		infoPanel = new InfoPanel(w);
		
		// Calculate envSize and mainWidth
		worldPanel.envSize = (1100/w.width);
		int mainPanelWidth = (worldPanel.cellPadding*(w.width+1)) + (worldPanel.envSize*w.width);
		panelHeight = (worldPanel.cellPadding*(w.height + 1)) + (worldPanel.envSize*w.height);
		
		// Set rectangles for the two panels
		mainRectangle = new Rectangle(panelPadding, panelPadding, mainPanelWidth, panelHeight);
		infoRectangle = new Rectangle((panelPadding*2)+mainRectangle.width, panelPadding, (int)(width*.2), panelHeight);
		
		// Resize frame to proper size
		width = (panelPadding*3) + mainRectangle.width + infoRectangle.width;
		height = (panelPadding*2) + panelHeight + 25;
		
		// Resize panels to new size
		worldPanel.setBounds(mainRectangle);
		infoPanel.setBounds(infoRectangle);
		
		// Main Frame
		mainFrame = new JFrame("Evolution Simulation");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(width, height);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		
		// Add panels to frame
		mainFrame.add(worldPanel);
		mainFrame.add(infoPanel);
	}
	
	public void defaultSize() {
		mainFrame.setSize(width, height);
	}
	
	public void showFrame() {
		mainFrame.setVisible(true);
	}
}
