package graphics;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import simplifiedMouseListener.*;

import main.*;

// Main Panel Class
@SuppressWarnings("serial")
public class WorldPanel extends JPanel implements Runnable, MouseListener, DataListener{
	public static int envSize = 150;
	public static int cellPadding = 5;
	private int updateCount = 0;
	private static int UPDATE_LIMIT = 0;
	
	ArrayList<SimplifiedMouseListener> SMListeners;
	
	private EnvironmentGraphics[][] worldGraphics;
	private World world;
	
	Thread thread;
	
	public WorldPanel(World w) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		world = w;
		worldGraphics = new EnvironmentGraphics[w.width][w.height];
		for(int x=0; x<w.width; x++) {
			for(int y=0; y<w.height; y++) {
				worldGraphics[x][y] = new EnvironmentGraphics(w.environments[x][y]);
			}
		}
		
		
		this.addMouseListener(this);
		SMListeners = new ArrayList<SimplifiedMouseListener>();
		
		// Initialize Thread
		thread = new Thread(this, "World-GUI");
		thread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Fill screen
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		
		// Declare variables
		EnvironmentGraphics graphics;
		
		// Draw world cell by cell
		Rectangle tempRect;
		for(int x=0; x<worldGraphics.length; x++) {
			for(int y=0; y<worldGraphics[0].length; y++) {
				graphics = worldGraphics[x][y];
				
				tempRect = new Rectangle((envSize*x) + cellPadding*(x+1), (envSize*y) + cellPadding*(y+1), envSize, envSize);
				g.setColor(Color.blue);
				g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
				
				g.setColor(graphics.color);
				
				// Draw properly shaped tile
				/*
				if(graphics.isSquare) {
					g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
				} else if(graphics.isCircle) {
					g.fillOval(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
				} else if(graphics.drawAngle == 90) {
					g.fillArc(tempRect.x, tempRect.y, tempRect.width, tempRect.height, graphics.startingAngle, graphics.drawAngle);
					
				} else if(graphics.drawAngle == 180) {
					g.fillArc(tempRect.x, tempRect.y, tempRect.width, tempRect.height, graphics.startingAngle, graphics.drawAngle);
				}
				*/
				
				g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
				
				// Draw organisms inside environment
				// Uses square root + 1 of total organisms to calculate sizes and max lengths
				int maxLength = (int)Math.sqrt(world.environments[x][y].organisms.size())+1;
				int cellSize = (int)((1.0/maxLength)*(envSize/2));
				int cellPadding = (envSize - cellSize*maxLength)/(maxLength+1);
				int row = 1; int column = 1;
				for(int c=0; c<world.environments[x][y].organisms.size(); c++) {
					g.setColor(Singleton.organismColorTable.get(world.environments[x][y].organisms.get(c).species));
					g.fillRect(tempRect.x + row*cellPadding + cellSize*(row-1), tempRect.y + column*cellPadding + cellSize*(column-1), cellSize, cellSize);
					row++;
					if(row>maxLength) {
						row = 1;
						column++;
					}
				}
			}
		}
	}

	public void addSimplifiedMouseListener(SimplifiedMouseListener l) {
		SMListeners.add(l);
	}
	
	private void mouseEventHappened(MouseEvent e) {
		int tempX = e.getX()/(envSize + cellPadding), tempY = e.getY()/(envSize + cellPadding);
		// FocusPanel.setEnv(world.environments[tempX][tempY]);
		for(int count=0; count<SMListeners.size(); count++) {
			SMListeners.get(count).fireMouseEvent(new SimplifiedMouseEvent("Environment Update", 0, new Point(tempX, tempY)));
		}
	}
	
	@Override
	public void run() {
		repaint();
	}

	@Override
	public void fireDataUpdate() {
		updateCount++;
		if(updateCount>WorldPanel.UPDATE_LIMIT) {
			thread = new Thread(this, "World-GUI");
			thread.start();
			updateCount = 0;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Creates weird interface interactions
		// mouseEventHappened(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseEventHappened(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Creates weird interface interactions
		// mouseEventHappened(e);
	}
}