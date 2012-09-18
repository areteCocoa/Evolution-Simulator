package graphics.start.create;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.Scenario;

public class CreateWorldView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	JButton doneButton;
	ActionListener actionListener;
	
	JTextArea nameInput;
	JSpinner sizeInputX, sizeInputY,
		durationSpinner;
	JCheckBox setDurationBox;
	
	public CreateWorldView() {
		this.setLayout(new GridLayout(4, 3, 10, 10));
		
		// v1: Name, size
		JLabel nameLabel = new JLabel("Name: "),
				sizeLabel = new JLabel("Size: "),
				// xLabel = new JLabel(" x ");
				durationLabel = new JLabel("Limited Duration: ");
		
		nameInput = new JTextArea();
		sizeInputX = new JSpinner(new SpinnerNumberModel(7, 1, 10, 1));
		sizeInputY = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
		durationSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 20, 1));
		
		setDurationBox = new JCheckBox("");
		
		doneButton = new JButton("Done");
		doneButton.setActionCommand("done");
		doneButton.addActionListener(this);
		
		this.add(nameLabel);
		this.add(getEmptyComp());
		this.add(nameInput);
		
		this.add(sizeLabel);
		this.add(sizeInputX);
		this.add(sizeInputY);
		
		this.add(durationLabel);
		this.add(setDurationBox);
		this.add(durationSpinner);
		
		this.add(doneButton);
	}
	
	private JComponent getEmptyComp() {
		return new JLabel();
	}
	
	public void setActionListener(ActionListener l) {
		actionListener = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public Scenario getWorldScenario() {
		Scenario s = new  Scenario();
		if(nameInput != null) {s.name = nameInput.getText();}
		int width = Integer.parseInt(sizeInputX.getValue().toString()),
				height = Integer.parseInt(sizeInputY.getValue().toString());
		s.size = new Dimension(width, height);
		if(setDurationBox.isSelected()) {
			s.duration = Integer.parseInt(durationSpinner.getValue().toString());
		}
		else {
			s.duration = 0;
		}
		
		return s;
	}
}
