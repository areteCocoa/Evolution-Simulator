package graphics.end;

import javax.swing.*;

public class EndView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JLabel nameLabel;
	
	public EndView(String name) {
		nameLabel = new JLabel("Name: " + name);
		
		this.add(nameLabel);
	}
	
}
