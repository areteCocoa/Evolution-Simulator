package graphics.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import graphics.WorldPanel;
import graphics.WorldViewController;

import javax.swing.*;

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
		
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new GridLayout(2,1,10,10));
		checkBoxPanel.setBorder(BorderFactory.createLineBorder(checkBoxPanel.getBackground(), 10));
		
		biomeTypes = new JCheckBox("Biome Types", worldPanel.getShouldDisplayBiomeTypes());
		biomeTypes.addActionListener(this);
		checkBoxPanel.add(biomeTypes);
		
		organismTypes = new JCheckBox("Organism Types", worldPanel.getShouldDisplayOrganismTypes());
		organismTypes.addActionListener(this);
		checkBoxPanel.add(organismTypes);
		
		mainWindow.setAlwaysOnTop(true);
		mainWindow.getContentPane().add(checkBoxPanel);
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
		} else {
			System.out.println("Unhandled event in WorldSettingsPopup: " + e.getActionCommand());
		}
	}
	
}
