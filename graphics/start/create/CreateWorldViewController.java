package graphics.start.create;

import javax.swing.*;

import main.Scenario;
import main.Singleton;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		// autoView.addActionListener(this);
		
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
		ArrayList<String> stringList = new ArrayList<String>();
		
		// Load file to list
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(Singleton.class.getResourceAsStream("/settings/createWorldSettings.txt")));
		    String string;
		    while ((string = in.readLine()) != null) {
		        stringList.add(string);
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + "createWorldSettings.txt" + " - " + (e.getMessage()));
		}
		
		String currentString = "", propertyString = "", valueString = "";
		int value = 0;
		for(int count=0; count<stringList.size(); count++) {
			currentString = stringList.get(count);
			int equalIndex = currentString.indexOf('=');
			if(equalIndex != -1) {
				propertyString = currentString.substring(0, equalIndex);
				valueString = currentString.substring(equalIndex+1);
				value = Integer.parseInt(valueString);
				
				// Analyze data and set settings
				if(propertyString.equalsIgnoreCase("size_index")) {
					view.sizeIndex = value;
				} else if(propertyString.equalsIgnoreCase("duration")) {
					view.duration = value;
				} else if(propertyString.equalsIgnoreCase("day_duration")) {
					view.dayDuration = value;
				} else if(propertyString.equalsIgnoreCase("organism_count")) {
					view.organismCount = value;
				} else if(propertyString.equalsIgnoreCase("species_count")) {
					view.speciesCount = value;
				} else if(propertyString.equalsIgnoreCase("biome_count")) {
					view.biomeCount = value;
				} else if(propertyString.equalsIgnoreCase("limited_duration")) {
					// Parse string to boolean
					view.limitedDuration = true;
				}
			}
		}
		view.defaultSpinnerValues();
	}
}
