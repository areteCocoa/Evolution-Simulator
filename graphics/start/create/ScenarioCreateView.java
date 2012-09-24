package graphics.start.create;

import javax.swing.*;

public class ScenarioCreateView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JLabel comingSoonDotCom;
	
	public ScenarioCreateView() {
		setBorder(BorderFactory.createLineBorder(getBackground(), 20));
		
		comingSoonDotCom = new JLabel("Coming Soon! Load from presets for faster, more accurate tests!");
		
		this.add(comingSoonDotCom);
	}
}
