package graphics.start;

import java.awt.event.ActionListener;

import javax.swing.*;

public class StartView extends JPanel {
	static final long serialVersionUID = 1L;

	JButton newButton, openButton, editorButton, helpButton, dataEditorButton;
	
	public StartView() {
		this.setBorder(BorderFactory.createLineBorder(getBackground(), 10));
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		newButton = new JButton("Create New World");
		newButton.setActionCommand("new");
		this.add(newButton);
		
		openButton = new JButton("Open World");
		openButton.setActionCommand("open");
		// this.add(openButton);
		
		editorButton = new JButton("Scenario Editor");
		editorButton.setActionCommand("scenario");
		// this.add(editorButton);
		
		helpButton = new JButton("Help");
		helpButton.setActionCommand("help");
		// this.add(helpButton);
		
		dataEditorButton = new JButton("Data Editor");
		dataEditorButton.setActionCommand("data");
		// this.add(this.dataEditorButton);
	}
	
	public void addActionListener(ActionListener a) {
		newButton.addActionListener(a);
		openButton.addActionListener(a);
		editorButton.addActionListener(a);
		helpButton.addActionListener(a);
	}
}
