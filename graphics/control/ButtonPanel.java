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
		
		setBackground(Color.white);
		
		this.setLayout(new GridLayout(2, 3, 10, 10));
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(this);
		this.add(startButton);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(this);
		this.add(stopButton);
		
		JButton insertButton = new JButton("Insert");
		insertButton.addActionListener(this);
		this.add(insertButton);
		
		JButton disasterButton = new JButton("Disaster");
		disasterButton.addActionListener(this);
		this.add(disasterButton);
		
		JButton stepButton = new JButton("Step");
		stepButton.addActionListener(this);
		this.add(stepButton);
		
		JButton endButton = new JButton("End");
		endButton.addActionListener(this);
		this.add(endButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public void addActionListener(ActionListener a) {
		actionListener = a;
	}
}
