package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColorTableCell extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int internalPadding = 3;
	
	Color cellColor;
	
	public ColorTableCell(Color cellColor) {
		this.cellColor = cellColor;
	}
	
	public void paint(Graphics g) {
		g.setColor(cellColor);
		g.fillRect(internalPadding, internalPadding, this.getWidth()-internalPadding, this.getHeight()-internalPadding);
	}
}
