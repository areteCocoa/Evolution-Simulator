package graphics.start.create;

import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateWorldRadioView extends JPanel {
	private static final long serialVersionUID = 1L;

	ButtonGroup buttonGroup;
	JRadioButton manualButton, automaticButton;
	
	public CreateWorldRadioView() {
		JLabel loadFromLabel = new JLabel("Load from:");
		manualButton = new JRadioButton("Custom", true);
		automaticButton = new JRadioButton("Scenario");
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(manualButton);
		buttonGroup.add(automaticButton);
		
		this.add(loadFromLabel);
		this.add(automaticButton);
		this.add(manualButton);
	}
	
	public void addActionListener(ActionListener l) {
		manualButton.addActionListener(l);
		automaticButton.addActionListener(l);
	}
}
