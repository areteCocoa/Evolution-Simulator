package graphics.control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.ConsoleController;

public class ButtonPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	ConsoleController consoleController;
	
	ActionListener actionListener;
	
	private WorldSettingsPopup settingsPopup;
	
	public ButtonPanel(final ConsoleController console) {
		this.consoleController = console;
		
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(this.getBackground(), 10));
		
		this.setLayout(new GridLayout(2, 3, 20, 20));
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		this.add(startButton);
		
		JButton stopButton = new JButton("Pause");
		stopButton.addActionListener(this);
		this.add(stopButton);
		
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		this.add(insertButton);
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(settingsPopup == null) {
					settingsPopup = new WorldSettingsPopup(console);
				}
				if(!settingsPopup.isShowing()) {
					settingsPopup.showDialog();
				} else {
					// settingsPopup.setSelected();
				}
			}
		});
		this.add(settingsButton);
		
		JButton disasterButton = new JButton("Disaster");
		disasterButton.addActionListener(this);
		// this.add(disasterButton);
		
		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(this);
		this.add(stepButton);
		
		JButton endButton = new JButton("End");
		endButton.addActionListener(this);
		this.add(endButton);
		
		JButton idkButton = new JButton("idk");
		idkButton.addActionListener(this);
		// this.add(idkButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public void addActionListener(ActionListener a) {
		actionListener = a;
	}
}
