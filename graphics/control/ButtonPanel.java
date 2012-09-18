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
	
	public ButtonPanel(ConsoleController console) {
		this.consoleController = console;
		
		// setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		
		this.setLayout(new GridLayout(2, 2, 10, 10));
		
		JButton startButton = new JButton("Start");
		startButton.setActionCommand("start");
		startButton.addActionListener(this);
		this.add(startButton);
		
		JButton stopButton = new JButton("Stop");
		stopButton.setActionCommand("stop");
		stopButton.addActionListener(this);
		this.add(stopButton);
		
		JButton insertButton = new JButton("Insert");
		insertButton.setActionCommand("insert");
		insertButton.addActionListener(this);
		this.add(insertButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public void addActionListener(ActionListener a) {
		actionListener = a;
	}
}
