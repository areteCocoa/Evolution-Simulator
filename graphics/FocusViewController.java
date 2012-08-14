package graphics;

import java.awt.Toolkit;

import javax.swing.*;

public class FocusViewController implements Runnable{
	static int width = (Toolkit.getDefaultToolkit().getScreenSize().width) - MainViewController.width - 10;
	static int height = 400;
	
	JFrame mainFrame;
	Thread mainThread;
	FocusPanel focusPanel;
	
	public FocusViewController() {
		focusPanel = new FocusPanel();
		focusPanel.setBounds(5, 5, width-10, height-10-25);
		
		mainFrame = new JFrame("Biome Inspection");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(width, height);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.setLocation(MainViewController.width + 5, 0);
		mainFrame.add(focusPanel);
		
		mainThread = new Thread(this);
	}

	public void showFrame() {
		mainThread.start();
	}
	
	@Override
	public void run() {
		mainFrame.setVisible(true);
		while(true) {
			focusPanel.repaint();
		}
	}
}
