package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import main.*;
import model.FocusTableModel;

// Main Panel Class
@SuppressWarnings("serial")
public class WorldPanel extends JPanel implements Runnable, MouseListener{
	public static int envSize = 150;
	public static int cellPadding = 5;

	World world;
	
	Thread worldPanelThread;
	
	public WorldPanel(World w) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		world = w;
		
		this.addMouseListener(this);
		
		// Initialize Thread
		worldPanelThread = new Thread(this, "World Panel Thread");
		worldPanelThread.start();
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
				
				g.setColor(Singleton.biomeColorTable.get(world.environments[x][y].biome));
				g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
				
				// Draw organisms inside environment
				// Uses square root + 1 of total organisms to calculate sizes and max lengths
				int maxLength = (int)Math.sqrt(world.environments[x][y].organisms.size())+1;
				int cellSize = (int)((1.0/maxLength)*(envSize/2));
				int cellPadding = (envSize - cellSize*maxLength)/(maxLength+1);
				int row = 1; int column = 1;
				for(int c=0; c<world.environments[x][y].organisms.size(); c++) {
					g.setColor(Singleton.stringToColor(Singleton.organismColorTable.get(world.environments[x][y].organisms.get(c).species)));
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
			
			if(FocusPanel.hasActivePanel()){
				FocusPanel.updateActivePanel();
			}
			if(FocusPanel.lastClickedEnv != null) {
				FocusTableModel.updateActiveModel();
			}
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		FocusPanel.setEnv(world.environments[e.getX()/(envSize + cellPadding)][e.getY()/(envSize + cellPadding)]);
		FocusTableModel.updateActiveModel();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}