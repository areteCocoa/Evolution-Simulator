package model;

import java.util.Random;

public class Dice {
	
	public static boolean getPercentBoolean(int trueChance) {
		if((new Random()).nextInt(100)<trueChance) {
			return true;
		}
		else {return false;}
	}
	
	public static int getPercentage(int numerator, int denominator) {
		return (int)((int)((100.0*numerator/denominator)));
	}
	
	public static float getFloatPercentage(float numerator, float denominator) {
		return 100*numerator/denominator;
	}
}
