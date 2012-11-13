package graphics.start.help;

import java.awt.*;
import javax.swing.*;

public class HelpViewController {
	private JDialog frame;
	
	public HelpViewController(JFrame owner) {
		frame = new JDialog(owner, "Help", true);
		
		JLabel temporaryLabel = new JLabel("Implement me later! Please check back later...");
		frame.getContentPane().add(temporaryLabel, BorderLayout.NORTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void showDialog(boolean shouldShow) {
		frame.setVisible(shouldShow);
	}
}
