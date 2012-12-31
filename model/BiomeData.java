package model;

import java.awt.Color;

public class BiomeData {
	public int ID;
	public String name;
	public Color color;
	public int minimumResources, maximumResources;
	
	public String toString() {
		String temp = ID + " " + name + " " + color.toString() + " " + minimumResources + " " + maximumResources;
		return temp;
	}
	
	public Color getColor() {
		System.out.println(color);
		return color;
	}
}
