package graphics.start.create;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.Scenario;

public class CustomCreateView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	// JButton doneButton;
	ActionListener actionListener;
	
	JTextArea nameInput;
	JSpinner sizeInputX, sizeInputY,
		durationSpinner, dayDuration,
		startingOrganism;
	JCheckBox setDurationBox;
	
	public CustomCreateView() {
		setBorder(BorderFactory.createLineBorder(getBackground(), 15));
		
		this.setLayout(new GridLayout(5, 3, 10, 10));
		
		// v1: Name, size
		JLabel nameLabel = new JLabel("Name: "),
				sizeLabel = new JLabel("Size: "),
				// xLabel = new JLabel(" x ");
				durationLabel = new JLabel("Limited Duration: "),
				dayDurationLabel = new JLabel("Day Duration (ms):"),
				startingOrganismLabel = new JLabel("Starting Organism Count: ");
		
		nameInput = new JTextArea("Earth");
		sizeInputX = new JSpinner(new SpinnerNumberModel(7, 1, 10, 1));
		sizeInputY = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
		durationSpinner = new JSpinner(new SpinnerNumberModel(75, 1, 1000, 1));
		dayDuration = new JSpinner(new SpinnerNumberModel(100, 100, 1000, 25));
		startingOrganism = new JSpinner(new SpinnerNumberModel(20, 1, 50, 1));
		
		setDurationBox = new JCheckBox("Enabled");
		setDurationBox.setSelected(true);
		
		this.add(nameLabel);
		this.add(getEmptyComp());
		this.add(nameInput);
		
		this.add(sizeLabel);
		this.add(sizeInputX);
		this.add(sizeInputY);
		
		this.add(durationLabel);
		this.add(setDurationBox);
		this.add(durationSpinner);
		
		this.add(dayDurationLabel);
		this.add(getEmptyComp());
		this.add(dayDuration);
		
		this.add(startingOrganismLabel);
		this.add(getEmptyComp());
		this.add(startingOrganism);
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
		s.dayDuration = Integer.parseInt(dayDuration.getValue().toString());
		s.startingSpeciesCount = Integer.parseInt(startingOrganism.getValue().toString());
		
		return s;
	}
}
