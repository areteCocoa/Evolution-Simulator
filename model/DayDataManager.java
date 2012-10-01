package model;

import java.util.ArrayList;

public class DayDataManager {

	ArrayList<Day> dayData;
	int day;
	
	public DayDataManager() {
		dayData = new ArrayList<Day>();
		day = 0;
	}
	
	public void update() {
		dayData.add(new Day());
	}
	
	public Day getDayData(int day) {
		return dayData.get(day);
	}
}
