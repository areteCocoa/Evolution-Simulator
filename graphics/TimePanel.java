package graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TimePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public TimePanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(400, 100);
	}
}
