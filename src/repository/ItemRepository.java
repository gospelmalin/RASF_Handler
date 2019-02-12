package repository;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import model.Item;

/**
 * The Class ItemRepository separates the Controller from the RESTClient and handles the
 * parsing of the xmlString the client receives from the server.
 */
public class ItemRepository {
			
	/**
	 * Get all items call to the client.
	 *
	 * @return the array list of items
	 */
	public ArrayList<Item> getAllItems() {
		RESTClient rc = new RESTClient();
		
		String xmlString = rc.getAllItems();
		System.out.println("jag har fått xml-strängen: " + xmlString);
		
		ArrayList<Item> itemsList =  new ArrayList<Item>();
		itemsList = jaxbXmlStringToObject(xmlString);
		
		return itemsList;
	}
	
	/**
	 * Get selected category call to the client.
	 *
	 * @return the user
	 */
	public Item getSelectedItem(int itemKeyQuery) {
		RESTClient rc = new RESTClient();
		String xmlString = rc.getSelectedItem(itemKeyQuery);
		ArrayList<Item> itemsList =  new ArrayList<Item>();
		itemsList = jaxbXmlStringToObject(xmlString);
		String available = itemsList.get(0).getAvailable();
		int categoryKey = itemsList.get(0).getCategoryKey();
		String categoryName = itemsList.get(0).getCategoryName();
		int itemKey = itemsList.get(0).getItemKey();
		String itemName = itemsList.get(0).getItemName();
		int numberOfUnits = itemsList.get(0).getNumberOfUnits();		
		int storageplaceKey = itemsList.get(0).getStorageplaceKey();
		String storageplaceName = itemsList.get(0).getStorageplaceName();
		int unitsAlways = itemsList.get(0).getUnitsAlways();				
		Item item = new Item(itemKey, categoryKey, itemName, unitsAlways, available, numberOfUnits, categoryName, storageplaceKey, storageplaceName);
		return item;
	}
	
	/**
	 * add category call to the client.
	 *
	 * @return string message
	 */
	public String add(Item i1) {
		RESTClient rc = new RESTClient();
		String message = rc.addItem(i1);
		return message;	
	}

	
	/**
	 * Update category call to the client.
	 *
	 * @return string message
	 */
	//TODO
	/*
	public String update(Item i1) {
		RESTClient rc = new RESTClient();
		String message = rc.updateItem(i1);
		return message;	
	}
	*/
	
	/**
	 * Delete user call to the client.
	 *
	 * @return string message
	 */
	//TODO
	/*
	public String delete(Item item) {
		RESTClient rc = new RESTClient();
		String message = rc.deleteItem(item);
		return message;	
	}
	*/
	
	
	private ArrayList<Item> jaxbXmlStringToObject(String xmlString) { 
		ArrayList<Item> itemsList =  new ArrayList<Item>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nItems = doc.getElementsByTagName("item"); // extract a list of element from the tag structure 
			System.out.println("length nItems: " + nItems.getLength()); // Printing lenght of nodelist
			for (int temp = 0; temp < nItems.getLength(); temp++) { // loop through the elements 
				Element element = (Element)nItems.item(temp); 
				String available = element.getElementsByTagName("available").item(0).getTextContent(); 
				int categoryKey = Integer.parseInt(element.getElementsByTagName("categoryKey").item(0).getTextContent());
				String categoryName = element.getElementsByTagName("categoryName").item(0).getTextContent(); 
				int itemKey = Integer.parseInt(element.getElementsByTagName("itemKey").item(0).getTextContent());
				String itemName = element.getElementsByTagName("itemName").item(0).getTextContent();  
				int numberOfUnits = Integer.parseInt(element.getElementsByTagName("numberOfUnits").item(0).getTextContent()); 
				int storageplaceKey = Integer.parseInt(element.getElementsByTagName("storageplaceKey").item(0).getTextContent());
				String storageplaceName = element.getElementsByTagName("storageplaceName").item(0).getTextContent(); 
				int unitsAlways = Integer.parseInt(element.getElementsByTagName("unitsAlways").item(0).getTextContent()); 
				
				int numberToBuy = 0;
				if (unitsAlways>0 && numberOfUnits<=unitsAlways) {
					numberToBuy = unitsAlways - numberOfUnits;
				}
				Item item = new Item(itemKey, categoryKey, itemName, unitsAlways, available, numberOfUnits, categoryName, storageplaceKey, storageplaceName, numberToBuy); // Create an Item object 
				
				// System.out.println("Printing an item: " + item); // call the Item object's toString-method and print 
				itemsList.add(item);
			}
		} catch (NumberFormatException e1) {
			System.err.println("A number format exception occured: " + e1.getMessage());
		} catch (DOMException e2) {
			System.err.println("Parsing problem. A DOM exception occured: " + e2.getMessage());
		} catch (ParserConfigurationException e3) {
			System.err.println("Parsing problem. A parser configuration exception occured: " + e3.getMessage());
		} catch (SAXException e4) {
			System.err.println("Parsing problem. A SAX exception occured: " + e4.getMessage());
		} catch (IOException e5) {
			System.err.println("A IO exception occured: " + e5.getMessage());
		} 
	 // System.out.println("Done parsing item-xml!"); 
		return itemsList;		
	}



}
