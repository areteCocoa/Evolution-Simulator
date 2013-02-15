package graphics.start;

import graphics.ImagePanel;
import graphics.WorldViewController;
import graphics.editor.ScenarioEditorViewController;
import graphics.end.EndViewController;
import graphics.start.create.*;
import graphics.start.help.*;
import graphics.start.load.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import main.*;
import javax.swing.*;

public class StartViewController implements WindowListener, ActionListener, Runnable {

	StartView startView;
	JFrame mainFrame;
	
	Scenario scenario;
	
	public boolean isShowing;
	
	private StartWindow selection;
	
	public StartViewController() {
		isShowing = false;
		this.scenario = new Scenario();
		
		mainFrame = new JFrame("Evolution Simulator");
		
		startView = new StartView();
		startView.addActionListener(this);
		
		ImagePanel panel = new ImagePanel(Singleton.startWindowImage, new Dimension((int)Singleton.startWindowImage.getWidth(), (int)Singleton.startWindowImage.getHeight()));
		panel.setBorder(BorderFactory.createLineBorder(panel.getBackground(), 10));
		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		mainFrame.getContentPane().add(startView, BorderLayout.EAST);
		mainFrame.addWindowListener(this);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		
		selection = StartWindow.NONE;
	}
	
	public void showFrame(boolean should) {
		isShowing = should;
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(should);
		scenarioLock();
	}
	
	private void scenarioLock() {
		while(isShowing) {
			synchronized(scenario) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("new".equalsIgnoreCase(e.getActionCommand())) {
			selection = StartWindow.NEW;
		}
		else if("open".equalsIgnoreCase(e.getActionCommand())) {
			selection = StartWindow.OPEN;
		}
		else if("scenario".equalsIgnoreCase(e.getActionCommand())) {
			selection = StartWindow.SCENARIO;
		}
		else if("help".equalsIgnoreCase(e.getActionCommand())) {
			selection = StartWindow.HELP;
		}
		else {
			System.out.println("Invalid ActionEvent received in StartViewController");
		}
		Thread newThread = new Thread(this);
		newThread.start();
	}
	
	private enum StartWindow {
		NEW, OPEN, SCENARIO, HELP, NONE
	}
	
	// Dialog loading methods
	private void createNewWorldWindow() {
		CreateWorldViewController newWorld = new CreateWorldViewController(mainFrame);
		newWorld.showDialog();
		if(newWorld.getWorldScenario() != null) {
			scenario.cloneFromScenario(newWorld.getWorldScenario());
			showFrame(false);
			
			World world = new World(this.getScenario());
			WorldViewController worldController = new WorldViewController(world);
			worldController.showFrame(true);
			world.addDataListener(worldController);
			
			while(!world.isDoneRunning()) {
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException e) {e.printStackTrace();}
			}
			
			if(world.isDoneRunning() && world.day != 0) {
				int returnValue = JOptionPane.showConfirmDialog(worldController.getMainFrame(), "View Data?", "End of Simulation",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				System.out.println(returnValue);
				
				worldController.showFrame(false);
				
				if(returnValue == 0) {
					EndViewController endWindow = new EndViewController(world.getWorldData());
					endWindow.showFrame();
					while(endWindow.isVisible()) {
						try {
							Thread.sleep(100);
						}
						catch (InterruptedException e) {e.printStackTrace();}
					}
					this.showFrame(true);
				}
			} else {
				worldController.showFrame(false);
				this.showFrame(true);
			}
		}
	}
	
	@Override
	public void run() {
		switch(selection){
			case NEW:
				createNewWorldWindow();
				break;
			
			case OPEN:
				LoadViewController loadController = new LoadViewController(mainFrame);
				loadController.showDialog(true);
				break;
			
			case SCENARIO:
				createNewScenarioWindow();
				break;
			
			case HELP:
				HelpViewController helpController = new HelpViewController(mainFrame);
				helpController.showDialog(true);
				break;
			
			default:
				break;
		}
			
	}
	
	private void createNewScenarioWindow() {
		ScenarioEditorViewController scenarioWindow = new ScenarioEditorViewController();
		scenarioWindow.showDialog();
	}
	
	public Scenario getScenario() {
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
