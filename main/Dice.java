package main;

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
}
