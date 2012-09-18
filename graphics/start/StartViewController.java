package graphics.start;

import graphics.start.create.CreateWorldViewController;

import java.awt.event.*;

import main.*;
import javax.swing.*;

public class StartViewController implements WindowListener, ActionListener {

	StartView startView;
	JFrame mainFrame;
	
	WorldScenario scenario;
	
	public boolean isShowing;
	
	public StartViewController(WorldScenario s) {
		isShowing = false;
		this.scenario = s;
		
		startView = new StartView();
		startView.addActionListener(this);
		
		mainFrame = new JFrame("Evolution Simulator");
		mainFrame.setContentPane(startView);
		mainFrame.addWindowListener(this);
	}
	
	public void showFrame(boolean should) {
		isShowing = should;
		mainFrame.setVisible(should);
		mainFrame.pack();
		scenarioLock();
	}
	
	private void scenarioLock() {
		while(isShowing) {
			synchronized(scenario) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {System.out.println("ERROR");}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("new".equalsIgnoreCase(e.getActionCommand())) {
			createNewWorldWindow();
		}
		else if("open".equalsIgnoreCase(e.getActionCommand())) {
			// comingSoon();
		}
		else if("scenario".equalsIgnoreCase(e.getActionCommand())) {
			// comingSoon();
		}
		else if("help".equalsIgnoreCase(e.getActionCommand())) {
			// comingSoon();
		}
		else {
			System.out.println("Invalid ActionEvent received");
		}
		
	}
	
	private void createNewWorldWindow() {
		CreateWorldViewController newWorld = new CreateWorldViewController(mainFrame);
		newWorld.showDialog();
		if(newWorld.getWorldScenario() != null) {
			scenario.cloneFromScenario(newWorld.getWorldScenario());
			showFrame(false);
		}
	}
	
	public WorldScenario getScenario() {
		return scenario;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		isShowing = false;
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
