package graphics.stats;

import java.awt.*;

import javax.swing.*;

import main.Environment;
import main.Singleton;

@SuppressWarnings("serial")
public class FocusPanel extends JPanel {

	private Environment lastClickedEnvironment;
	
	private static final int envPadding = 2;
	
	public FocusPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// Custom drawing
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Draw environment
		if(lastClickedEnvironment != null) {
			Rectangle tempRect = new Rectangle(envPadding, envPadding, this.getWidth() - envPadding*2, this.getWidth() - envPadding*2);
			
			g.setColor(Singleton.biomeData[lastClickedEnvironment.biome].color);
			g.fillRect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
			
			int maxLength = (int)Math.sqrt(lastClickedEnvironment.organisms.size())+1;
			int cellSize = (int)((1.0/maxLength)*(tempRect.getWidth()/2));
			int cellPadding = (int) (tempRect.getWidth() - cellSize*maxLength)/(maxLength+1);
			int row = 1; int column = 1;
			for(int c=0; c<lastClickedEnvironment.organisms.size(); c++) {
				g.setColor(Singleton.organismColorTable.get(lastClickedEnvironment.organisms.get(c).species));
				g.fillRect(tempRect.x + row*cellPadding + cellSize*(row-1), tempRect.y + column*cellPadding + cellSize*(column-1), cellSize, cellSize);
				row++;
				if(row>maxLength) {
					row = 1;
					column++;
					if(column>maxLength) {
						System.out.println("Focus Panel Error: Environment exceeded max y size");
					}
				}
			}
		}
	}
	
	public void setLastClickedEnvironment(Environment e) {
		lastClickedEnvironment = e;
		repaint();
	}
}
