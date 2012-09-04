package graphics;

import java.awt.Color;
import javax.swing.*;

import main.World;

public class TimePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	Thread thread;
	
	World world;
	
	private JLabel dayLabel;
	
	public TimePanel(World world) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		
		dayLabel = new JLabel();
		dayLabel.setText(Integer.toString(world.day));
		this.add(dayLabel);
		
		this.world = world;
		
		thread = new Thread(this, "Time Panel thread");
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			dayLabel.setText(Integer.toString(world.day));
			
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {System.out.println("ERROR");}
		}
	}
}
