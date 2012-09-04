package graphics;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import main.World;

public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public ControlPanel(World w) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
	}
}
