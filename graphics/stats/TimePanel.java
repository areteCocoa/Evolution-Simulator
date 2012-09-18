package graphics.stats;

import java.awt.Color;
import javax.swing.*;

import main.World;

public class TimePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	World world;
	
	private JLabel dayLabel;
	
	public TimePanel(World world) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		
		dayLabel = new JLabel();
		dayLabel.setText(Integer.toString(world.day));
		this.add(dayLabel);
		
		this.world = world;
	}

	public void updateData() {
		dayLabel.setText(Integer.toString(world.day));
	}
}
