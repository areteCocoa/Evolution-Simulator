package graphics;

import java.awt.*;

import javax.swing.*;

import main.Environment;
import main.Singleton;

@SuppressWarnings("serial")
public class FocusPanel extends JPanel {

	public static Environment lastClickedEnv;
	private static FocusPanel activePanel;
	
	private static final int envPadding = 2;
	
	public FocusPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		FocusPanel.activePanel = this;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Custom drawing
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw environment
		if(lastClickedEnv != null) {
			Rectangle tempRect = new Rectangle(envPadding, envPadding, this.getWidth() - envPadding*2, this.getWidth() - envPadding*2);
			
			g.setColor(Singleton.biomeColorTable.get(lastClickedEnv.biome));
			g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
			
			int maxLength = (int)Math.sqrt(lastClickedEnv.organisms.size())+1;
			int cellSize = (int)((1.0/maxLength)*(tempRect.getWidth()/2));
			int cellPadding = (int) (tempRect.getWidth() - cellSize*maxLength)/(maxLength+1);
			int row = 1; int column = 1;
			for(int c=0; c<lastClickedEnv.organisms.size(); c++) {
				g.setColor(Singleton.organismColorTable.get(lastClickedEnv.organisms.get(c).species));
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
	
	public Dimension getPreferredSize() {
		return new Dimension((MainViewController.panelHeight*2)/5, (MainViewController.panelHeight*2)/5);
	}
	
	public static void setEnv(Environment env) {
		lastClickedEnv = env;
		FocusPanel.activePanel.repaint();
	}
	
	public static void updateActivePanel() {
		FocusPanel.activePanel.repaint();
	}
	
	public static boolean hasActivePanel() {
		if(activePanel == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
