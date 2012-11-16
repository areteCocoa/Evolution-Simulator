package graphics.end;

import javax.swing.*;

import model.analytical.WorldData;

public class EndViewController {
	
	JFrame mainFrame;
	EndView endView;
	
	public EndViewController(WorldData worldData) {
		endView = new EndView(worldData);
		
		mainFrame = new JFrame("End of Simulation Analysis");
		mainFrame.setContentPane(endView);
		
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
	}
	
	public void showFrame() {
		mainFrame.setVisible(true);
	}
	
	public boolean isVisible() {
		return mainFrame.isVisible();
	}
}
