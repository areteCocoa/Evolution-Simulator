package graphics;

import java.awt.*;

import javax.swing.*;

public class NameInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public NameInfoPanel() {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((int)(StatsPanel.width*.4)-10, 500);
	}
}
