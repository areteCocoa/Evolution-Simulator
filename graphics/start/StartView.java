package graphics.start;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StartView extends JPanel {
	static final long serialVersionUID = 1L;

	JButton newButton, openButton, editorButton, helpButton;
	
	public StartView() {
		this.setBorder(BorderFactory.createLineBorder(getBackground(), 10));
		this.setLayout(new GridLayout(2, 2, 10, 10));
		
		newButton = new JButton("Create New World");
		newButton.setActionCommand("new");
		this.add(newButton);
		
		openButton = new JButton("Open World");
		openButton.setActionCommand("open");
		this.add(openButton);
		
		editorButton = new JButton("Scenario Editor");
		editorButton.setActionCommand("scenario");
		this.add(editorButton);
		
		helpButton = new JButton("Help");
		helpButton.setActionCommand("help");
		this.add(helpButton);
	}
	
	public void addActionListener(ActionListener a) {
		newButton.addActionListener(a);
		openButton.addActionListener(a);
		editorButton.addActionListener(a);
		helpButton.addActionListener(a);
	}
}
