package graphics.control;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import simplifiedMouseListener.SimplifiedMouseEvent;
import simplifiedMouseListener.SimplifiedMouseListener;
import main.Environment;
import main.World;
import model.ConsoleController;

public class ControlPanel extends JPanel implements ActionListener, SimplifiedMouseListener {
	private static final long serialVersionUID = 1L;

	World world;
	
	ConsolePanel consolePanel;
	ButtonPanel buttonPanel;
	
	GridBagLayout gridBag;
	
	ConsoleController consoleController;
	
	Environment selectedEnvironment;
	
	public ControlPanel(World world) {
		// Non-GUI data
		this.world = world;
		consoleController = new ConsoleController(world);
		
		// GUI setup
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		
		gridBag = new GridBagLayout();
		this.setLayout(gridBag);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridy = 0;
		
		consolePanel = new ConsolePanel(consoleController);
		c.gridx = 0;
		this.add(consolePanel, c);
		
		buttonPanel = new ButtonPanel(consoleController);
		c.gridx = 1;
		buttonPanel.addActionListener(this);
		this.add(buttonPanel, c);
	}
	
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		
		int[] rowHeight = {rect.height},
				columnWidths = {(int)(rect.width*.7), (int)(rect.width*.3)};
		
		gridBag.columnWidths = columnWidths;
		gridBag.rowHeights = rowHeight;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		consolePanel.processString(e.getActionCommand());
	}

	@Override
	public void fireMouseEvent(SimplifiedMouseEvent s) {
		Point p;
		if(s.getCustomObject().getClass() == Point.class) {
			p = (Point) s.getCustomObject();
			selectedEnvironment = world.environments[p.x][p.y];
			consoleController.setSelectedEnvironment(selectedEnvironment);
		}
	}
}
