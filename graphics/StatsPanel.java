package graphics;

import java.awt.*;

import javax.swing.*;
import main.*;

public class StatsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public static int height = 400;
	public static int width = 400;
	
	TimePanel timePanel;
	NamePanel infoPanel;
	FocusPanel focusPanel;
	NameInfoPanel nameInfoPanel;
	FocusInfoPanel focusInfoPanel;
	
	public StatsPanel(World world) {
		// setBorder(BorderFactory.createLineBorder(Color.black));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		int inset = 4;
		c.insets = new Insets(inset, inset, inset, inset);
		
		/* timePanel = new TimePanel();
		c.insets = new Insets(0, 0, inset, 0);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy=0;
		this.add(timePanel, c); */
		
		infoPanel = new NamePanel(world);
		c.insets = new Insets(0, 0, inset, inset);
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		this.add(infoPanel, c);
		
		nameInfoPanel = new NameInfoPanel();
		c.insets = new Insets(0, inset, inset, 0);
		c.gridx = 1;
		this.add(nameInfoPanel, c);
		
		JSeparator midline = new JSeparator(SwingConstants.HORIZONTAL);
		c.insets = new Insets(inset, 0, inset, 0);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		this.add(midline, c);
		
		focusPanel = new FocusPanel();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(inset, 0, 0, inset);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 3;
		this.add(focusPanel, c);
		
		focusInfoPanel = new FocusInfoPanel();
		c.insets = new Insets(inset, inset, 0, 0);
		c.gridx = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(focusInfoPanel, c);
	}
	
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		
		width = rect.width;
		height = rect.height;
	}
}
