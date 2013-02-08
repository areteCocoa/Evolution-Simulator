package graphics.stats;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import main.Singleton;
import main.World;

public class TimePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	World world;
	
	private JLabel dayLabel, monthLabel, yearLabel,
		durationLabel;
	private static final String[] MONTHS = {"January", "Feburary", "March", "April",
		"May", "June", "July", "August", "September", "October", "November", "December", "Labember"};
	
	public TimePanel(World world) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.white);
		
		if(world.duration > 0) {
			setLayout(new GridLayout(1, 1, 10, 10));
		} else {
			setLayout(new GridLayout(1, 2, 10, 10));
		}
		
		Font timeFont = Singleton.defaultFont.deriveFont((float) 21.0);
		
		dayLabel = new JLabel(Integer.toString(world.day));
		setupLabel(dayLabel, timeFont);
		
		monthLabel = new JLabel();
		setupLabel(monthLabel, timeFont);
		
		yearLabel = new JLabel();
		setupLabel(yearLabel, timeFont);
		
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(1,3, 5, 5));
		dayPanel.setBackground(this.getBackground());
		dayPanel.add(dayLabel);
		dayPanel.add(monthLabel);
		dayPanel.add(yearLabel);
		
		durationLabel = new JLabel();
		durationLabel.setText(Integer.toString(world.duration));
		durationLabel.setFont(Singleton.defaultFont);
		
		this.add(dayPanel);
		if(world.duration != 0) {
			this.add(durationLabel);
		}
		
		this.world = world;
	}

	public void updateData() {
		int yearCount = (int)(world.day/365),
				monthCount = (int)((world.day - (yearCount*365))/30),
				dayCount = (int)(world.day - (yearCount*365) - (monthCount*30));
		dayLabel.setText(Integer.toString(dayCount));
		monthLabel.setText(MONTHS[monthCount]);
		yearLabel.setText(Integer.toString(yearCount));
		
		durationLabel.setText(Integer.toString(world.duration));
	}
	
	private void setupLabel(JLabel l, Font f) {
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setFont(f);
	}
}
