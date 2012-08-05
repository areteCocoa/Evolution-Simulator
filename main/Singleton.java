package main;

import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Singleton {
	public static Hashtable<Integer, Color> biomeColorTable;
	public static Hashtable<Integer, String> organismColorTable;
	public static Hashtable<Integer, String> organismNameTable;
	public static Hashtable<Integer, String> biomeNameTable;
	
	public static void main() {
		// All the temporary lists
		// List of organism names
		ArrayList<String> nameList = new ArrayList<String>();
		readFileToList("organismNames.txt", nameList);
		
		// List of organism colors
		ArrayList<String> organismColorList = new ArrayList<String>();
		readFileToList("organismColors.txt", organismColorList);
		
		// List of biome colors
		ArrayList<Color> biomeColorList = new ArrayList<Color>();
		biomeColorList.add(Color.black);
		biomeColorList.add(Color.gray);
		biomeColorList.add(new Color(25, 25, 25));
	
		// List of biome names
		ArrayList<String> biomeNameList = new ArrayList<String>();
		readFileToList("biomeNames.txt", biomeNameList);
		
		// All the static Hashtables
		// Table of Color per Biome
		biomeColorTable = new Hashtable<Integer, Color>();
		biomeColorTable.put(0, Color.black);
		biomeColorTable.put(1, Color.gray);
		biomeColorTable.put(2, new Color(50, 50, 50));
		
		organismColorTable = new Hashtable<Integer, String>();
		randomizeListToHashtable(organismColorList, organismColorTable);
		
		organismNameTable = new Hashtable<Integer, String>();
		randomizeListToHashtable(nameList, organismNameTable);
		
		biomeNameTable = new Hashtable<Integer, String>();
		randomizeListToHashtable(biomeNameList, biomeNameTable);
	}
	
	public static void randomizeListToHashtable(ArrayList<String> list, Hashtable<Integer, String> table) {
		Random rand = new Random();
		int randInt;
		int count = 0;
		
		while(list.size() > 0) {
			 randInt = rand.nextInt(list.size());
			 table.put(count, list.get(randInt));
			 list.remove(randInt);
			 count++;
		}
	}
	
	public static void readFileToList(String fileName, ArrayList<String> list) {
		try {
		    BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/data/" + fileName));
		    String str;
		    while ((str = in.readLine()) != null) {
		        list.add(str);
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + (e.getMessage()));
		}
	}
	
	
	public static Color stringToColor(String string) {
		Color color;
		try {
			Field field = Color.class.getField(string);
			color = (Color)field.get(null);
		} catch (Exception e) {
		    color = null;
		    System.out.println("Error processing stringToColor");
		}
		return color;
	}
}
