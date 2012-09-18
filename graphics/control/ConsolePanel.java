package graphics.control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

import main.World;
import model.ConsoleController;

public class ConsolePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	JTextField inputField;
	JTextArea outputField;
	
	ConsoleController controller;
	
	public ConsolePanel(ConsoleController controller) {
		this.controller = controller;
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		
		this.setLayout(new BorderLayout(5, 5));
		
		inputField = new JTextField();
		inputField.addActionListener(this);
		
		outputField = new JTextArea();
		outputField.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(outputField);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
		}});  
		
		this.add(inputField, BorderLayout.PAGE_END);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = inputField.getText();
		inputField.setText("");
		
		processString(input);
	}
	
	public void processString(String string) {
		outputField.append(string + "\n");
		outputField.append(controller.processInput(string));
		outputField.append("\n");
	}
}
