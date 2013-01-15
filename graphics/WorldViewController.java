package graphics;

import graphics.control.ControlPanel;
import graphics.stats.StatsPanel;

import javax.swing.*;

import main.*;

import java.awt.*;
import java.util.ArrayList;

public class WorldViewController implements DataListener {
	public static int height = (int) ((Toolkit.getDefaultToolkit().getScreenSize().height) * .95);
	public static int width = (int) ((Toolkit.getDefaultToolkit().getScreenSize().width));
	public static int panelPadding = (int) (height*.01);
	
	JFrame mainFrame;
	
	WorldPanel worldPanel;
	StatsPanel statsPanel;
	ControlPanel controlPanel;
	
	private ArrayList<DataListener> dataListeners;
	
	public WorldViewController(World world) {
		Rectangle mainRectangle, statsRectangle, controlRectangle;
		
		// Handle all the panels before the main frame
		worldPanel = new WorldPanel(world);
		statsPanel = new StatsPanel(world);
		controlPanel = new ControlPanel(world);
		
		// Calculate envSize and mainWidth
		int workingWidth = width - (panelPadding*3), workingHeight = height - (panelPadding*3) - 25;
		int MPWidth = (int)(workingWidth*.7), MPHeight = (int)(workingHeight*.8);
		if(world.height > world.width) {
			WorldPanel.envSize = (MPHeight - (WorldPanel.cellPadding*(world.height + 1)))/world.height;
		}
		else if(world.height < world.width) {
			WorldPanel.envSize = (MPWidth - (WorldPanel.cellPadding*(world.width + 1)))/world.width;
		}
		
		MPWidth = (WorldPanel.cellPadding*(world.width + 1)) + ((WorldPanel.envSize)*world.width);
		MPHeight = (WorldPanel.cellPadding*(world.height + 1)) + (WorldPanel.envSize*world.height);
		
		// Set rectangles for the panels
		mainRectangle = new Rectangle(panelPadding, panelPadding, MPWidth, MPHeight);
		statsRectangle = new Rectangle((panelPadding*2)+mainRectangle.width, panelPadding, workingWidth - mainRectangle.width, MPHeight);
		controlRectangle = new Rectangle(panelPadding, (panelPadding*2)+mainRectangle.height, mainRectangle.width + statsRectangle.width, workingHeight - mainRectangle.height);
		
		// Resize frame to proper size
		width = (panelPadding*3) + mainRectangle.width + statsRectangle.width;
		height = (panelPadding*3) + mainRectangle.height + controlRectangle.height + 25;
		
		// Resize panels to new size
		worldPanel.setBounds(mainRectangle);
		statsPanel.setBounds(statsRectangle);
		controlPanel.setBounds(controlRectangle);
		
		// Main Frame
		mainFrame = new JFrame("Evolution Simulation");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(width, height);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		
		// Configure SMListeners
		worldPanel.addSimplifiedMouseListener(statsPanel.getSMListeners()[0]);
		worldPanel.addSimplifiedMouseListener(controlPanel);
		
		// Add panels to frame
		mainFrame.add(worldPanel);
		mainFrame.add(statsPanel);
		mainFrame.add(controlPanel);
		
		// Add data listeners
		dataListeners = new ArrayList<DataListener>();
		dataListeners.add(worldPanel);
		dataListeners.add(statsPanel);
	}
	
	public void showFrame(boolean should) {
		mainFrame.setVisible(should);
		if(should) {
			this.fireDataUpdate();
		}
	}
	
	@Override
	public void fireDataUpdate() {
		for(int x=0; x<dataListeners.size(); x++) {
			dataListeners.get(x).fireDataUpdate();
		}
	}
	
	public JFrame getMainFrame() {
		return mainFrame;
	}
}