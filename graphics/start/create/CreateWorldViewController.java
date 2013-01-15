package graphics.start.create;

import javax.swing.*;

import main.Scenario;
import main.Singleton;
import model.DefaultSettings;

import java.awt.BorderLayout;
import java.awt.event.*;

public class CreateWorldViewController implements ActionListener{

	JDialog window;
	
	CustomCreateView createWorldView;
	ScenarioCreateView autoView;
	
	private CreateWorldRadioView createWorldRadioView;
	
	private Scenario scenario;
	
	public CreateWorldViewController(JFrame owner) {
		createWorldView = new CustomCreateView();
		createWorldView.setActionListener(this);
		
		this.loadDefaultSettingsToView(createWorldView);
		
		createWorldRadioView = new CreateWorldRadioView();
		createWorldRadioView.addActionListener(this);
		
		autoView = new ScenarioCreateView();
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(this);
		
		window = new JDialog(owner, "Create New World", true);
		
		window.getContentPane().add(createWorldRadioView, BorderLayout.NORTH);
		window.getContentPane().add(createWorldView, BorderLayout.CENTER);
		window.getContentPane().add(doneButton, BorderLayout.SOUTH);
		
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.getRootPane().setDefaultButton(doneButton);
	}
	
	public void showDialog() {
		window.setVisible(true);
	}
	
	public Scenario getWorldScenario() {
		return scenario;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("done")) {
			scenario = createWorldView.getWorldScenario();
			window.setVisible(false);
		}
		else if(e.getActionCommand().equalsIgnoreCase("scenario")) {
			window.getContentPane().remove(createWorldView);
			window.getContentPane().add(autoView, BorderLayout.CENTER);
		}
		else if(e.getActionCommand().equalsIgnoreCase("custom")) {
			window.getContentPane().remove(autoView);
			window.getContentPane().add(createWorldView, BorderLayout.CENTER);
		}
		window.pack();
		window.repaint();
	}
	
	// Load settings from .txt file
	private void loadDefaultSettingsToView(CustomCreateView view) {
		DefaultSettings settings = Singleton.defaultSettings;
		view.sizeIndex = 		 settings.sizeIndex;
		view.duration = 		 settings.duration;
		view.dayDuration =		 settings.dayDuration;
		view.organismCount =	 settings.organismCount;
		view.speciesCount =		 settings.speciesCount;
		view.biomeCount =		 settings.biomeCount;
		view.limitedDuration =	 settings.limitedDuration;
				
		view.defaultSpinnerValues();
	}
}
