package graphics.editor;

import graphics.WorldPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.Environment;
import main.Singleton;
import main.World;

public class ScenarioEditorViewController {
	// Fields
	private JFrame mainFrame;
	private WorldPanel worldPanel;
	
	// Popup used to get data from user
	private class SettingsPopup {
		private JDialog window;
		private JComboBox sizeMenu;
		
		SettingsData settingsData;
		
		public SettingsPopup(JFrame owner) {
			window = new JDialog(owner, "Scenario Editor Settings", true);
			((JPanel) window.getContentPane()).setBorder(BorderFactory.createLineBorder(window.getBackground(), 10));
			
			sizeMenu = new JComboBox(Environment.getSizesAsString());
			sizeMenu.getModel().setSelectedItem(Environment.getSizesAsString()[Singleton.defaultSettings.sizeIndex]);
			
			JButton done = new JButton("Done");
			done.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showDialog(false);
				}
			});
			
			window.getContentPane().add(sizeMenu);
			window.getContentPane().add(done, BorderLayout.SOUTH);
			
			window.getRootPane().setDefaultButton(done);
			
			window.pack();
			window.setLocationRelativeTo(null);
		}
		
		public SettingsData getSettingsData() {
			settingsData = new SettingsData(Environment.getDimensionFromIndex(sizeMenu.getSelectedIndex()));
			return settingsData;
		}
		
		public void showDialog(boolean visible) {
			window.setVisible(visible);
		}
	}
	
	private class SettingsData {
		private Dimension size;
		SettingsData(Dimension size) {
			this.size = size;
		}
		public Dimension getSize() {
			return size;
		}
	}

	
	
	// Constructors
	public ScenarioEditorViewController() {
		mainFrame = new JFrame("Scenario Editor");
		mainFrame.getContentPane().add(new JLabel("Scenario Editor Frame"));
		
		worldPanel = new WorldPanel(new World(5,5));
	}
	
	// Methods
	public void showDialog() {
		SettingsPopup temp = new SettingsPopup(mainFrame);
		temp.showDialog(true);
		
		worldPanel.setWorld(World.getBlankWorld((int)temp.getSettingsData().getSize().getHeight(), (int)temp.getSettingsData().getSize().getWidth()));
	}
	
	public void setVisible(boolean visible) {
		if(visible) {
			mainFrame.pack();
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setVisible(true);
		}
		else {
			mainFrame.setVisible(false);
		}
	}
}
