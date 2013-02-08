	package graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private Dimension size;
	
	public ImagePanel(BufferedImage image, Dimension dim) {
		this.image = image;
		this.size = dim;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, size.width, size.height, null);     
    }
	
	public Dimension getPreferredSize() {
		return size;
	}
}
