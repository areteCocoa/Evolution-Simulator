package model.analytical;

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
		// System.out.println(dayData.get(day).getSpeciesStatsModel().getTotal(0));
		day++;
	}
	
	public Day getDayData(int day) {
		return dayData.get(day);
	}
}
