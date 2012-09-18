package graphics.start.create;

import javax.swing.*;

import main.Scenario;

import java.awt.event.*;

public class CreateWorldViewController implements ActionListener{

	JDialog window;
	CreateWorldView createWorldView;
	
	private Scenario scenario;
	
	public CreateWorldViewController(JFrame owner) {
		createWorldView = new CreateWorldView();
		createWorldView.setActionListener(this);
		
		window = new JDialog(owner, "Create New World", true);
		window.setContentPane(createWorldView);
		window.pack();
		window.setLocationRelativeTo(null);
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
	}
}
