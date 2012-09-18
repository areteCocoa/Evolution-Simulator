package graphics.end;

import javax.swing.*;

import main.*;

public class EndViewController {
	
	JFrame mainFrame;
	EndView endView;
	
	public EndViewController(World world) {
		endView = new EndView();
		
		mainFrame = new JFrame("End of Simulation Analysis");
		mainFrame.setContentPane(endView);
		// mainFrame.addWindowListener(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
	}
	
	public void showFrame() {
		mainFrame.setVisible(true);
	}
}