package graphics;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Selector extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	JButton[] buttons;
	int selectedButton;
	int selectorSize;
	
	ActionListener actionListener;
	
	private class SelectorChanged extends ActionEvent {
		private static final long serialVersionUID = 1L;

		public SelectorChanged(Object source, int id, String command) {
			super(source, id, command);
			// TODO Auto-generated constructor stub
		}
	}
	
	public Selector(String[] buttonNames) {
		setBackground(Color.white);
		
		GridLayout layout = new GridLayout(1, buttonNames.length);
		layout.setHgap(0);
		layout.setVgap(0);
		this.setLayout(layout);
		
		buttons = new JButton[buttonNames.length];
		selectedButton = 0;
		selectorSize = buttonNames.length;
		
		for(int x=0; x<buttonNames.length; x++) {
			buttons[x] = new JButton(buttonNames[x]);
			
			this.add(buttons[x]);
			
			/* if(x != buttonNames.length-1) {
				JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
				this.add(separator);
			} */
		}
		
		this.addButtonActionListener(this);
	}
	
	private void addButtonActionListener(ActionListener a) {
		for(int x=0; x<buttons.length; x++) {
			buttons[x].addActionListener(a);
		}
	}
	
	public void addActionListener(ActionListener a) {
		actionListener = a;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int x=0; x<buttons.length; x++) {
			if(buttons[x].getText() == e.getActionCommand()) {
				selectedButton = x;
			}
		}
		actionListener.actionPerformed(new SelectorChanged(this, e.getID(), Integer.toString(selectedButton)));
	}
}
