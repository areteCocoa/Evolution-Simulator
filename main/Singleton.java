package main;

import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import javax.xml.parsers.*;

import model.DefaultSettings;

import org.w3c.dom.*;

public class Singleton {
	public static ArrayList<Color> 	biomeColorTable;
	public static ArrayList<Color> 	organismColorTable;
	
	public static ArrayList<String> organismNameTable;
	public static ArrayList<String> biomeNameTable;
	
	public static ArrayList<String> behaviorTraitTable;
	public static ArrayList<String> physicalTraitTable;
	
	public static DefaultSettings 	defaultSettings;
	
	public static Font 				defaultFont;
	
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
		readFileToList("biomeData/biomeColors.txt", biomeColorList);
	
		// List of biome names
		ArrayList<String> biomeNameList = new ArrayList<String>();
		readFileToList("biomeData/biomeNames.txt", biomeNameList);
		
		ArrayList<String> behaviorTraitList = new ArrayList<String>();
		readFileToList("behaviorTraits.txt", behaviorTraitList);
		
		ArrayList<String> physicalTraitList = new ArrayList<String>();
		readFileToList("physicalTraits.txt", physicalTraitList);
		
		// readXMLToDictionary("biomes.xml");
		// readBiomeXMLFileToSettings("biomes.xml");
		
		// Get settings from XML File
		defaultSettings = new DefaultSettings();
		readSettingsXMLToSettings("settings.xml", defaultSettings);
		
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
	
	private static void randomizeListToStringArrayList(ArrayList<String> list, ArrayList<String> table) {
		Random rand = new Random();
		int randInt;
		
		while(list.size() > 0) {
			 randInt = rand.nextInt(list.size());
			 table.add(list.get(randInt));
			 list.remove(randInt);
		}
	}
	
	private static void randomizeListToColorArrayList(ArrayList<String> list, ArrayList<Color> table) {
		Random rand = new Random();
		int randInt;
		
		while(list.size() > 0) {
			 randInt = rand.nextInt(list.size());
			 table.add(stringToColor(list.get(randInt)));
			 list.remove(randInt);
		}
	}
	
	private static void readFileToList(String fileName, ArrayList<String> list) {
		try {
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
	
	private static Color stringToColor(String string) {
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
	
	private static void readXMLToDictionary(String fileName) {
		try {
			Document document = getDocumentWithFileName(fileName);
			
			NodeList list = document.getElementsByTagName("biome");
			Node node = list.item(0);
			System.out.println(list.getLength());
			for(int count=0; count<list.getLength(); count++) {
				node = list.item(count);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.print(element.getAttributes().item(0).getNodeValue());
					System.out.println(element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readBiomeXMLFileToSettings(String fileName) { // ADD: Load data to list
		try {
			Document document = getDocumentWithFileName(fileName);
			
			NodeList list = document.getElementsByTagName("biome");
			Node node;
			
			for(int count=0; count<list.getLength(); count++) {
				node = list.item(count);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					System.out.print(element.getAttributes().item(0).getNodeValue());
					System.out.println(element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void readSettingsXMLToSettings(String fileName, DefaultSettings settings) {
		try {
			Element e = (Element) getDocumentWithFileName(fileName).getFirstChild();
			
			settings.sizeIndex = Integer.parseInt(e.getElementsByTagName("sizeIndex").item(0).getChildNodes().item(0).getNodeValue());
			settings.duration = Integer.parseInt(e.getElementsByTagName("duration").item(0).getChildNodes().item(0).getNodeValue());
			settings.dayDuration = Integer.parseInt(e.getElementsByTagName("dayDuration").item(0).getChildNodes().item(0).getNodeValue());
			settings.organismCount = Integer.parseInt(e.getElementsByTagName("organismCount").item(0).getChildNodes().item(0).getNodeValue());
			settings.speciesCount = Integer.parseInt(e.getElementsByTagName("speciesCount").item(0).getChildNodes().item(0).getNodeValue());
			settings.biomeCount = Integer.parseInt(e.getElementsByTagName("biomeCount").item(0).getChildNodes().item(0).getNodeValue());
			
			String limit = e.getElementsByTagName("limitedDuration").item(0).getChildNodes().item(0).getNodeValue();
			settings.limitedDuration = !(limit.equalsIgnoreCase("0"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Document getDocumentWithFileName(String fileName) {
		try {
			// Get URL for current directory and add data/fileName and convert to String
			File file = new File((Singleton.class.getResource("/data/" + fileName)).getPath());
			
			// Create document from file to read data to
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			document.getDocumentElement().normalize();
			
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
