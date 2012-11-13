package graphics.start.load;

import java.awt.BorderLayout;

import javax.swing.*;

public class LoadViewController {
	private JDialog frame;
	
	public LoadViewController(JFrame owner) {
		frame = new JDialog(owner, "Load World", true);
		
		JLabel temporaryLabel = new JLabel("Implement me later! Please check back later for the Load window.");
		frame.getContentPane().add(temporaryLabel, BorderLayout.NORTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void showDialog(boolean shouldShow) {
		frame.setVisible(shouldShow);
	}
}
