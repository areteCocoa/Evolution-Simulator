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
	
	JCheckBox biomeTypes, organismTypes;
	
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
		container.setLayout(new GridLayout(4 + Organism.speciesCount,1,10,10));
		container.setBorder(BorderFactory.createLineBorder(container.getBackground(), 10));
		
		biomeTypes = new JCheckBox("Biome Types", worldPanel.shouldDisplayBiomeType());
		biomeTypes.addActionListener(this);
		container.add(biomeTypes);
		
		organismTypes = new JCheckBox("Organism Types", worldPanel.shouldDisplayOrganismType());
		organismTypes.addActionListener(this);
		container.add(organismTypes);
		
		container.add(new JSeparator(SwingConstants.HORIZONTAL));
		container.add(new JLabel("Display Organism Types:"));
		
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
	}
	
	public boolean isShowing() {
		return mainWindow.isVisible();
	}
	
	public void setSelected() {
		mainWindow.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("biome types")) {
			worldPanel.setDisplayBiomeTypes(biomeTypes.isSelected());
		} else if(e.getActionCommand().equalsIgnoreCase("organism types")) {
			worldPanel.setDisplayOrganismTypes(organismTypes.isSelected());
		} else if(e.getActionCommand().substring(0, 1).equalsIgnoreCase("O")) {
			// Change display of organism
			for(int type=0; type<Organism.speciesCount; type++) {
				if(e.getActionCommand().equalsIgnoreCase("O" + type)) {
					worldPanel.setDisplayOrganism(type, !worldPanel.shouldDisplayOrganism(type));
				}
			}
		}
		else {
			System.out.println("Unhandled event in WorldSettingsPopup: " + e.getActionCommand());
		}
	}
	
}
