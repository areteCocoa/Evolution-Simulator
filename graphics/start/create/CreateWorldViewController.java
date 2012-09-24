package graphics.start.create;

import javax.swing.*;

import main.Scenario;

import java.awt.BorderLayout;
import java.awt.event.*;

public class CreateWorldViewController implements ActionListener{

	JDialog window;
	CustomCreateView createWorldView;
	// ManualCreateView manualView;
	ScenarioCreateView autoView;
	
	CreateWorldRadioView createWorldRadioView;
	
	private Scenario scenario;
	
	public CreateWorldViewController(JFrame owner) {
		createWorldView = new CustomCreateView();
		createWorldView.setActionListener(this);
		
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
}
