package graphics.start.create;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.Environment;
import main.Scenario;

public class CustomCreateView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	int sizeIndex, duration, dayDuration, organismCount, speciesCount, biomeCount;
	boolean limitedDuration;
	
	ActionListener actionListener;
	
	JTextArea nameInput;
	JSpinner durationSpinner, dayDurationSpinner,
		startingOrganism, startingSpecies,
		biomeCountSpinner;
	JCheckBox setDurationBox;
	JComboBox sizeMenu;
	
	public CustomCreateView() {
		setBorder(BorderFactory.createLineBorder(getBackground(), 15));
		
		this.setLayout(new GridLayout(6, 3, 10, 10));
		
		// Labels on the left hand column
		JLabel nameLabel = new JLabel("Name: "),
				sizeLabel = new JLabel("Size: "),
				durationLabel = new JLabel("Limited Duration: "),
				dayDurationLabel = new JLabel("Day Duration (ms):"),
				startingOrganismLabel = new JLabel("Starting Organism Count: "),
				startingSpeciesLabel = new JLabel("Different Starting Species: "),
				biomeCountLabel = new JLabel("Different Biomes: ");
		
		nameInput = new JTextArea("Earth");
		
		durationSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		dayDurationSpinner = new JSpinner(new SpinnerNumberModel(5, 5, 1000, 25));
		startingOrganism = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		startingSpecies = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		biomeCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 18, 1));
		
		setDurationBox = new JCheckBox("Enabled");
		setDurationBox.setSelected(limitedDuration);
		
		sizeMenu = new JComboBox(Environment.getSizesAsString());
		sizeMenu.getModel().setSelectedItem(Environment.getSizesAsString()[0]);
		
		/*
		this.add(nameLabel);
		this.add(getEmptyComp());
		this.add(nameInput);
		*/
		
		this.add(sizeLabel);
		this.add(getEmptyComp());
		this.add(sizeMenu);
		
		this.add(durationLabel);
		this.add(setDurationBox);
		this.add(durationSpinner);
		
		this.add(dayDurationLabel);
		this.add(getEmptyComp());
		this.add(dayDurationSpinner);
		
		this.add(startingOrganismLabel);
		this.add(getEmptyComp());
		this.add(startingOrganism);
		
		this.add(startingSpeciesLabel);
		this.add(getEmptyComp());
		this.add(startingSpecies);
		
		this.add(biomeCountLabel);
		this.add(getEmptyComp());
		this.add(biomeCountSpinner);
	}
	
	private JComponent getEmptyComp() {
		return new JLabel();
	}
	
	public void defaultSpinnerValues() {
		durationSpinner.setValue(duration);
		dayDurationSpinner.setValue(dayDuration);
		startingOrganism.setValue(organismCount);
		startingSpecies.setValue(speciesCount);
		biomeCountSpinner.setValue(biomeCount);
		
		setDurationBox.setSelected(limitedDuration);
		
		sizeMenu.getModel().setSelectedItem(Environment.getSizesAsString()[sizeIndex]);
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
		s.size = Environment.getDimensionFromIndex(sizeMenu.getSelectedIndex());
		if(setDurationBox.isSelected()) {
			s.duration = Integer.parseInt(durationSpinner.getValue().toString());
		}
		else {
			s.duration = 0;
		}
		s.dayDuration = Integer.parseInt(dayDurationSpinner.getValue().toString());
		s.startingOrganismCount = Integer.parseInt(startingOrganism.getValue().toString());
		s.startingSpeciesCount = Integer.parseInt(startingSpecies.getValue().toString());
		s.biomeCount = Integer.parseInt(biomeCountSpinner.getValue().toString());
		
		// Set static fields
		s.setStatics();
		
		return s;
	}
}
