package main;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ViewController {
	static int height = (int) ((Toolkit.getDefaultToolkit().getScreenSize().height)*.8);
	static int width = (int) ((Toolkit.getDefaultToolkit().getScreenSize().width)*.8);
	static int panelPadding = (int) (width*.01);
	static int panelHeight = (int) (height - (panelPadding*2) - 25);
	
	JFrame mainFrame;
	
	// Main Panel Class
	@SuppressWarnings("serial")
	class WorldPanel extends JPanel implements Runnable{
		private int envSize = 200;
		int cellPadding = 5;

		World world;
		Hashtable<Integer, Color> environmentTable;
		Hashtable<Integer, Color> organismTable;
		
		Thread worldPanelThread;
		
		public WorldPanel() {
			setBorder(BorderFactory.createLineBorder(Color.black));
			
			// Make Hashtable of all environments and color representations
			environmentTable = new Hashtable<Integer, Color>();
			environmentTable.put(0, Color.black);
			environmentTable.put(1, Color.gray);
			environmentTable.put(2, new Color(50, 50, 50));
			
			organismTable = new Hashtable<Integer, Color>();
			organismTable.put(0, Color.yellow);
			organismTable.put(1, Color.green);
			organismTable.put(2, Color.red);
			
			// Initialize Thread
			worldPanelThread = new Thread(this);
			worldPanelThread.start();
		}
		
		public void setWorld(World temp) {
			world = temp;
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			
			// Custom drawing
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			
			// Draw world cell by cell
			Rectangle tempRect;
			for(int x=0; x<world.width; x++) {
				for(int y=0; y<world.height; y++) {
					tempRect = new Rectangle((envSize*x) + cellPadding*(x+1), (envSize*y) + cellPadding*(y+1), envSize, envSize);
					
					g.setColor(environmentTable.get(world.environments[x][y].biome));
					g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
					
					// Draw organisms inside environment
					// Uses square root + 1 of total organisms to calculate sizes and max lengths
					int maxLength = (int)Math.sqrt(world.environments[x][y].organisms.size())+1;
					int cellSize = (int)((1.0/maxLength)*100);
					int cellPadding = (envSize - cellSize*maxLength)/(maxLength+1);
					int row = 1; int column = 1;
					Rectangle rect = new Rectangle(tempRect.x + row*cellPadding + cellSize*(row-1), tempRect.y + column*cellPadding + cellSize*(column-1), cellSize, cellSize);
					for(int c=0; c<world.environments[x][y].organisms.size(); c++) {
						g.setColor(organismTable.get(world.environments[x][y].organisms.get(c).species));
						g.fillRect(tempRect.x + row*cellPadding + cellSize*(row-1), tempRect.y + column*cellPadding + cellSize*(column-1), cellSize, cellSize);
						row++;
						if(row>maxLength) {
							row = 1;
							column++;
							if(column>maxLength) {
								System.out.println("ERROR: ENVIRONMENT EXCEEDED MAXIMUM Y SIZE");
							}
						}
					}
				}
			}
		}

		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {System.out.println("ERROR");}
			}
			
		}
	}
	
	// Information Panel Class
	class InfoPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public InfoPanel() {
			setBorder(BorderFactory.createLineBorder(Color.black));
		}
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			
			// Custom drawing
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			String resolution = new String(Integer.toString(width) + "x" + Integer.toString(height));
			g.drawString(resolution, 20, 20);
		}
	}

	WorldPanel worldPanel;
	InfoPanel infoPanel;
	
	public ViewController(World w) {
		// Handle all the panels before the main frame
		Rectangle mainRectangle = new Rectangle(panelPadding, panelPadding, (int)(width*.75), panelHeight);
		Rectangle infoRectangle = new Rectangle((panelPadding*2)+mainRectangle.width, panelPadding, (int)(width*.22), panelHeight);
		worldPanel = new WorldPanel();
		infoPanel = new InfoPanel();
		
		// Take care off World w as well as panel scaling
		worldPanel.setWorld(w);
		if(w.height > w.width) {
			
		}
		else if(w.height <= w.width) {
			
		}
		else {
			System.out.println("Error handling world height/width in ViewController.");
		}
		
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
