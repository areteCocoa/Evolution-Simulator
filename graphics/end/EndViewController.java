package graphics.end;

import javax.swing.*;

import main.Singleton;
import model.analytical.WorldData;

public class EndViewController {
	
	JFrame mainFrame;
	EndView endView;
	
	public EndViewController(WorldData worldData) {
		endView = new EndView(worldData);
		
		mainFrame = new JFrame("End of Simulation Analysis");
		mainFrame.setContentPane(endView);
		mainFrame.setSize((int)(Singleton.width/1.4), (int)(Singleton.height/1.15));
		
		// mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
	}
	
	public void showFrame() {
		mainFrame.setVisible(true);
	}
	
	public boolean isVisible() {
		return mainFrame.isVisible();
	}
}
