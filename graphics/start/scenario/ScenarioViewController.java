package graphics.start.scenario;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScenarioViewController {

	private JDialog frame;
	
	public ScenarioViewController(JFrame owner) {
		frame = new JDialog(owner, "Scenario Editor", true);
		
		JLabel temporaryLabel = new JLabel("This powerful tool will be available in the future.");
		frame.getContentPane().add(temporaryLabel, BorderLayout.NORTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void showDialog(boolean shouldShow) {
		frame.setVisible(shouldShow);
	}
}
