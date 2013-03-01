package graphics.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import graphics.WorldPanel;
import graphics.WorldViewController;

import javax.swing.*;

import main.Organism;
import main.World;
import model.ConsoleController;

public class WorldSettingsPopup implements ActionListener {
	
	JDialog mainWindow;
	
	private World world;
	private WorldPanel worldPanel;
	
	private JCheckBox biomeTypes, organismTypes, displayGrid;
	private JTextField dayDurationField;
	
	public WorldSettingsPopup(World world, WorldPanel panel, WorldViewController owner) {
		this.world = world;
		this.worldPanel = panel;
		
		if(owner != null) {
			mainWindow = new JDialog(owner.getMainFrame(), "World Settings", true);
		} else {
			mainWindow = new JDialog();
			mainWindow.setTitle("World Settings");
		}
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0,1,10,10));
		container.setBorder(BorderFactory.createLineBorder(container.getBackground(), 10));
		
		biomeTypes = new JCheckBox("Biome Types", worldPanel.shouldDisplayBiomeType());
		biomeTypes.addActionListener(this);
		container.add(biomeTypes);
		
		organismTypes = new JCheckBox("Organism Types", worldPanel.shouldDisplayOrganismType());
		organismTypes.addActionListener(this);
		container.add(organismTypes);
		
		displayGrid = new JCheckBox("Display Grid", worldPanel.isDisplayingGrid());
		displayGrid.addActionListener(this);
		container.add(displayGrid);
		
		JPanel durationPanel = new JPanel();
		durationPanel.add(new JLabel("Day Duration"));
		dayDurationField = new JTextField(4);
		dayDurationField.addActionListener(this);
		durationPanel.add(dayDurationField);
		container.add(durationPanel);
		
		container.add(new JSeparator(SwingConstants.HORIZONTAL));
		container.add(new JLabel("Display Organisms:"));
		
		JCheckBox temp;
		for(int count=0; count<Organism.speciesCount; count++) {
			temp = new JCheckBox("Organism " + count);
			temp.setActionCommand("O" + count);
			temp.addActionListener(this);
			temp.setSelected(worldPanel.shouldDisplayOrganism(count));
			
			container.add(temp);
		}
		
		mainWindow.getContentPane().add(container);
		
		mainWindow.setAlwaysOnTop(true);
		mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
	}
	
	public WorldSettingsPopup(ConsoleController console) {
		this(console.getWorld(), console.getWorldPanel(), null);
	}

	public void showDialog() {
		mainWindow.setVisible(true);
		this.dayDurationField.setText(String.valueOf(world.getDayDuration()));
	}
	
	public boolean isShowing() {
		return mainWindow.isVisible();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("biome types")) {
			worldPanel.setDisplayBiomeTypes(biomeTypes.isSelected());
		} else if(e.getActionCommand().equalsIgnoreCase("organism types")) {
			worldPanel.setDisplayOrganismTypes(organismTypes.isSelected());
		} else if(e.getActionCommand().equalsIgnoreCase("display grid")) {
			worldPanel.setShouldDisplayGrid(displayGrid.isSelected());
		} else if(e.getActionCommand().substring(0, 1).equalsIgnoreCase("O")) {
			// Change display of organism
			for(int type=0; type<Organism.speciesCount; type++) {
				if(e.getActionCommand().equalsIgnoreCase("O" + type)) {
					worldPanel.setDisplayOrganism(type, !worldPanel.shouldDisplayOrganism(type));
				}
			}
		} else if(e.getSource() == dayDurationField) {
			dayDurationField.select(0, dayDurationField.getText().length());
			int newDuration = world.getDayDuration();
			try {
				newDuration = Integer.parseInt(dayDurationField.getText());
				world.setDayDuration(newDuration);
			} catch(NumberFormatException exception) {
				dayDurationField.setText(String.valueOf(newDuration));
			}
		}
		else {
			System.out.println("Unhandled event in WorldSettingsPopup: " + e.getActionCommand() + " with ID: " + e.getID());
		}
	}
	
}
