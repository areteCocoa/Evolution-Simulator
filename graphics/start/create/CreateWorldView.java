package graphics.start.create;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.WorldScenario;

public class CreateWorldView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	JButton doneButton;
	ActionListener actionListener;
	
	JTextArea nameInput;
	JSpinner sizeInputX, sizeInputY;
	
	public CreateWorldView() {
		this.setLayout(new GridLayout(3, 3, 10, 10));
		
		// v1: Name, size
		JLabel nameLabel = new JLabel("Name: "),
				sizeLabel = new JLabel("Size: ");
				// xLabel = new JLabel(" x ");
		JComponent emptyComp = new JLabel();
		
		nameInput = new JTextArea();
		sizeInputX = new JSpinner(new SpinnerNumberModel(7, 1, 10, 1));
		sizeInputY = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
		
		doneButton = new JButton("Done");
		doneButton.setActionCommand("done");
		doneButton.addActionListener(this);
		
		this.add(nameLabel);
		this.add(emptyComp);
		this.add(nameInput);
		
		this.add(sizeLabel);
		this.add(sizeInputX);
		this.add(sizeInputY);
		
		this.add(doneButton);
	}
	
	public void setActionListener(ActionListener l) {
		actionListener = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionListener.actionPerformed(e);
	}
	
	public WorldScenario getWorldScenario() {
		WorldScenario s = new  WorldScenario();
		if(nameInput != null) {s.name = nameInput.getText();}
		int width = Integer.parseInt(sizeInputX.getValue().toString()),
				height = Integer.parseInt(sizeInputY.getValue().toString());
		s.size = new Dimension(width, height);
		
		return s;
	}
}
