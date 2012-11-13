package main;

import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Singleton {
	public static ArrayList<Color> biomeColorTable;
	public static ArrayList<Color> organismColorTable;
	
	public static ArrayList<String> organismNameTable;
	public static ArrayList<String> biomeNameTable;
	
	public static ArrayList<String> behaviorTraitTable;
	public static ArrayList<String> physicalTraitTable;
	
	
	public static Font defaultFont;
	
	public static void main() {
		// Non-list statics
		defaultFont = new Font("Helvetica", Font.PLAIN, 16);
		
		// All the temporary lists
		// List of organism names
		ArrayList<String> nameList = new ArrayList<String>();
		readFileToList("organismNames.txt", nameList);
		
		// List of organism colors
		ArrayList<String> organismColorList = new ArrayList<String>();
		readFileToList("organismColors.txt", organismColorList);
		
		// List of biome colors
		ArrayList<String> biomeColorList = new ArrayList<String>();
		readFileToList("biomeColors.txt", biomeColorList);
	
		// List of biome names
		ArrayList<String> biomeNameList = new ArrayList<String>();
		readFileToList("biomeNames.txt", biomeNameList);
		
		ArrayList<String> behaviorTraitList = new ArrayList<String>();
		readFileToList("behaviorTraits.txt", behaviorTraitList);
		
		ArrayList<String> physicalTraitList = new ArrayList<String>();
		readFileToList("physicalTraits.txt", physicalTraitList);
		
		
		
		// All the static Hashtables
		// Table of Color per Biome
		biomeColorTable = new ArrayList<Color>();
		// randomizeListToColorArrayList(biomeColorList, biomeColorTable);
		for(int x = 0; x < biomeColorList.size(); x++) {
			if(stringToColor(biomeColorList.get(x)) != null) {
				biomeColorTable.add(stringToColor(biomeColorList.get(x)));
			}
		}
		
		organismColorTable = new ArrayList<Color>();
		randomizeListToColorArrayList(organismColorList, organismColorTable);
		
		organismNameTable = new ArrayList<String>();
		randomizeListToStringArrayList(nameList, organismNameTable);
		
		biomeNameTable = new ArrayList<String>();
		// randomizeListToStringArrayList(biomeNameList, biomeNameTable);
		biomeNameTable = biomeNameList;
		
		behaviorTraitTable = new ArrayList<String>();
		randomizeListToStringArrayList(behaviorTraitList, behaviorTraitTable);
		
		physicalTraitTable = new ArrayList<String>();
		randomizeListToStringArrayList(physicalTraitList, physicalTraitTable);
	}
	
	public static void randomizeListToStringArrayList(ArrayList<String> list, ArrayList<String> table) {
		Random rand = new Random();
		int randInt;
		
		while(list.size() > 0) {
			 randInt = rand.nextInt(list.size());
			 table.add(list.get(randInt));
			 list.remove(randInt);
		}
	}
	
	public static void randomizeListToColorArrayList(ArrayList<String> list, ArrayList<Color> table) {
		Random rand = new Random();
		int randInt;
		
		while(list.size() > 0) {
			 randInt = rand.nextInt(list.size());
			 table.add(stringToColor(list.get(randInt)));
			 list.remove(randInt);
		}
	}
	
	public static void readFileToList(String fileName, ArrayList<String> list) {
		try {
		    // BufferedReader in = new BufferedReader(new FileReader(Singleton.class.getResource("/data/").toString() + fileName));
			BufferedReader in = new BufferedReader(new InputStreamReader(Singleton.class.getResourceAsStream("/data/" + fileName)));
		    String str;
		    while ((str = in.readLine()) != null) {
		        list.add(str);
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + fileName + " - " + (e.getMessage()));
		}
	}
	
	
	public static Color stringToColor(String string) {
		Color color;
		try {
			if(Character.isLetter(string.charAt(0))) {
				Field field = Color.class.getField(string);
				color = (Color)field.get(null);
			}
			else if(Character.isDigit(string.charAt(0))) {
				int red = Integer.parseInt(string.substring(0, 3)),
						green = Integer.parseInt(string.substring(4, 7)),
						blue = Integer.parseInt(string.substring(8, 11));
				color = new Color(red, green, blue);
			}
			else {
				color = null;
			}
		} catch (Exception e) {
		    color = null;
		    System.out.println("Error processing stringToColor: " + string);
		}
		return color;
	}
}
