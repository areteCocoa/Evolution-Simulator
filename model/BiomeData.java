package model;

import java.awt.Color;

public class BiomeData {
	public int ID;
	public String name;
	public Color color;
	public int minimumResources, maximumResources;
	public int harshness;
	public String notes;
	
	public String toString() {
		String temp = ID + " " + name + " " + color.toString() + " " + minimumResources + "-" + maximumResources + " " + harshness;
		return temp;
	}
	
	public Color getColor() {
		return color;
	}
}
