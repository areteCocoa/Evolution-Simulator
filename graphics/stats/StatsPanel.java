package graphics.stats;

import graphics.WorldViewController;

import java.awt.*;
import javax.swing.*;

import simplifiedMouseListener.SimplifiedMouseListener;
import main.*;

public class StatsPanel extends JPanel implements Runnable, DataListener {
	private static final long serialVersionUID = 1L;
	
	private TimePanel timePanel;
	private OverviewViewController overviewPanel;
	private FocusViewController focusPanel;
	
	private GridBagLayout gridBag;
	private int height, width;
	
	private SimplifiedMouseListener[] SMListeners;
	
	public static int inset = (int)(WorldViewController.panelPadding/2);
	
	Thread thread;
	
	public StatsPanel(World world) {
		// setBorder(BorderFactory.createLineBorder(Color.black));
		
		gridBag = new GridBagLayout();
		this.setLayout(gridBag);
		GridBagConstraints c = new GridBagConstraints();
		
		inset = (int)(WorldViewController.panelPadding/2);
		c.fill = GridBagConstraints.BOTH;
		
		timePanel = new TimePanel(world);
		c.insets = new Insets(0, 0, inset, 0);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(timePanel, c);
		
		overviewPanel = new OverviewViewController(world);
		c.insets = new Insets(inset, 0, inset, 0);
		c.gridy = 1;
		this.add(overviewPanel, c);
		
		JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
		c.insets = new Insets(inset, 0, inset, 0);
		c.gridx = 0;
		c.gridy = 2;
		this.add(separator, c);
		
		focusPanel = new FocusViewController(world);
		c.gridy = 3;
		c.insets = new Insets(inset, 0, 0, 0);
		this.add(focusPanel, c);
		
		SMListeners = new SimplifiedMouseListener[1];
		SMListeners[0] = focusPanel;
	}
	
	public void setBounds(Rectangle rect) {
		super.setBounds(rect);
		
		width = rect.width;
		height = rect.height;
		
		int workingWidth = width;
		int workingHeight = height - (int)(workingWidth*.6);
		
		int[] columnWidths = {height - workingHeight, (int)(workingWidth*.4)};
		int[] rowHeights = {(int)(workingHeight*.10), (int)(workingHeight*.8), (int)(workingHeight*.05), height - workingHeight};
		
		gridBag.columnWidths = columnWidths;
		gridBag.rowHeights = rowHeights;
		
		overviewPanel.setDimensions(columnWidths, rowHeights[1]);
		focusPanel.setDimensions(columnWidths, rowHeights[3]);
	}
	
	public SimplifiedMouseListener[] getSMListeners() {
		// Add panels that implement SMListener
		return SMListeners;
	}
	
	@Override
	public void run() {
		timePanel.updateData();
		// overviewPanel.updateData();
		focusPanel.updateData();
	}

	@Override
	public void fireDataUpdate() {
		thread = new Thread(this, "StatsPanel");
		thread.start();
	}
}
