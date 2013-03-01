package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.xml.parsers.*;

import model.*;
import model.TraitData.TraitType;

import org.w3c.dom.*;

public class Singleton {
	// Temporary Lists
	public static ArrayList<Color> 	organismColorTable;
	public static ArrayList<String> organismNameTable;
	
	// Images
	public static BufferedImage	startWindowImage;
	
	// Standards
	public static BiomeData[]		biomeData;
	public static TraitData[]		traitData;
	
	public static DefaultSettings 	defaultSettings;
	public static Font 				defaultFont;
	
	// Graphics Constants
	public static int height = (int) ((Toolkit.getDefaultToolkit().getScreenSize().height) * .95);
	public static int width = (int) ((Toolkit.getDefaultToolkit().getScreenSize().width));
	
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
		
		// Get settings from XML File
		defaultSettings = new DefaultSettings();
		readSettingsXMLToSettings("settings.xml", defaultSettings);
		
		// Load Images
		startWindowImage = null;
		startWindowImage = getImageWithFileName("start_logo.png");
		
		// Load biome data from biomes.xml
		biomeData = new BiomeData[19];
		loadBiomeDataFromXML("biomes.xml", biomeData);
		
		traitData = new TraitData[9];
		loadTraitDataFromXML("traits.xml", traitData);
		
		organismColorTable = new ArrayList<Color>();
		randomizeListToColorArrayList(organismColorList, organismColorTable);
		
		organismNameTable = new ArrayList<String>();
		randomizeListToStringArrayList(nameList, organismNameTable);
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
	
	private static void loadBiomeDataFromXML(String fileName, BiomeData[] data) {
		try {
			Document document = getDocumentWithFileName(fileName);
			
			NodeList list = document.getElementsByTagName("biome");
			Node node = list.item(0);
			// data = new BiomeData[list.getLength()];	Dynamically allocate the size of the list to the amount of biomes
			for(int count=0; count<list.getLength(); count++) {
				node = list.item(count);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					data[count] = new BiomeData();
					Element rootElement = (Element) node,
							resourceElement = (Element) rootElement.getElementsByTagName("resources").item(0),
							colorElement = (Element) rootElement.getElementsByTagName("color").item(0);
					
					// Set attributes to biomes
					data[count].ID = Integer.parseInt(rootElement.getAttribute("id"));
					data[count].name = rootElement.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
					data[count].minimumResources = Integer.parseInt(resourceElement.getElementsByTagName("minimum").item(0).getChildNodes().item(0).getNodeValue());
					data[count].maximumResources = Integer.parseInt(resourceElement.getElementsByTagName("maximum").item(0).getChildNodes().item(0).getNodeValue());
					
					int red = Integer.parseInt(colorElement.getElementsByTagName("red").item(0).getChildNodes().item(0).getNodeValue()),
							green = Integer.parseInt(colorElement.getElementsByTagName("green").item(0).getChildNodes().item(0).getNodeValue()),
							blue = Integer.parseInt(colorElement.getElementsByTagName("blue").item(0).getChildNodes().item(0).getNodeValue());
					
					data[count].harshness = Integer.parseInt(rootElement.getElementsByTagName("harshness").item(0).getChildNodes().item(0).getNodeValue());
					data[count].color = new Color(red, green, blue);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadTraitDataFromXML(String fileName, TraitData[] data) {
		try {
			Document document = getDocumentWithFileName(fileName);
			
			NodeList list = document.getElementsByTagName("physicalTrait");
			Node node = list.item(0);
			// data = new BiomeData[list.getLength()];	Dynamically allocate the size of the list to the amount of biomes
			for(int count=0; count<list.getLength(); count++) {
				node = list.item(count);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					data[count] = new TraitData();
					Element rootElement = (Element) node;
					
					// Set attributes to traits
					data[count].biomeID = Integer.parseInt(rootElement.getElementsByTagName("biomeID").item(0).getChildNodes().item(0).getNodeValue());
					data[count].name = rootElement.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue();
					data[count].traitType = TraitType.PHYSICAL;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Document getDocumentWithFileName(String fileName) {
		try {
			// Get URL for current directory and add data/fileName and convert to String
			URL url = (Singleton.class.getResource("/data/" + fileName));
			
			// Create document from file to read data to
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openStream());
			document.getDocumentElement().normalize();
			
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static BufferedImage getImageWithFileName(String fileName) {
		BufferedImage image;
		
		URL url = (Singleton.class.getResource("/images/" + fileName));
		try {
			image = ImageIO.read(url);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
