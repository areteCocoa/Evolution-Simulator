package main;

import java.awt.*;
import java.util.*;

public class Singleton {
	public static Hashtable<Integer, Color> biomeColorTable;
	public static Hashtable<Integer, Color> organismTable;
	public static Hashtable<Integer, String> organismNameTable;
	public static Hashtable<Integer, String> biomeNameTable;
	
	public static ArrayList<String> nameList;
	public static ArrayList<Color> biomeColorList;
	
	public static void main() {
		// variables for random placement later
		int count = 0;
		Random rand = new Random();
		
		// Lists of organism names
		nameList = new ArrayList<String>();
		nameList.add("Cat");
		nameList.add("Dog");
		nameList.add("Butterfly");
		nameList.add("Dinosaur");
		nameList.add("Sim");
		
		// List of biome colors
		biomeColorList = new ArrayList<Color>();
		biomeColorList.add(Color.black);
		biomeColorList.add(Color.gray);
		biomeColorList.add(new Color(25, 25, 25));
	
		// Tables of names, colors, etc
		biomeColorTable = new Hashtable<Integer, Color>();
		biomeColorTable.put(0, Color.black);
		biomeColorTable.put(1, Color.gray);
		biomeColorTable.put(2, new Color(50, 50, 50));
		
		organismTable = new Hashtable<Integer, Color>();
		organismTable.put(0, Color.yellow);
		organismTable.put(1, Color.green);
		organismTable.put(2, Color.red);
		organismTable.put(3, Color.blue);
		organismTable.put(4, Color.cyan);
		
		
		organismNameTable = new Hashtable<Integer, String>();
		while(nameList.size() > 0) {
			int randInt = rand.nextInt(nameList.size());
			organismNameTable.put(count, nameList.get(randInt));
			nameList.remove(randInt);
			count++;
		}
	}
}
