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

import model.Storageplace;

/**
 * The Class StorageplaceRepository separates the Controller from the RESTClient and handles the
 * parsing of the xmlString the client receives from the server.
 */
public class StorageplaceRepository {

			
	/**
	 * Get all storageplace call to the client.
	 *
	 * @return the array list of items
	 */
	public ArrayList<Storageplace> getAllStorageplaces() {
		RESTClient rc = new RESTClient();
		
		String xmlString = rc.getAllStorageplaces();
		System.out.println("jag har fått xml-strängen: " + xmlString);
		
		ArrayList<Storageplace> storageplacesList =  new ArrayList<Storageplace>();
		storageplacesList = jaxbXmlStringToObject(xmlString);
		
		return storageplacesList;
	}
	
	/**
	 * Get selected category call to the client.
	 *
	 * @return the user
	 */
	public Storageplace getSelectedStorageplace(int storageplaceKeyQuery) {
		RESTClient rc = new RESTClient();
		String xmlString = rc.getSelectedStorageplace(storageplaceKeyQuery);
		ArrayList<Storageplace> storageplacesList =  new ArrayList<Storageplace>();
		storageplacesList = jaxbXmlStringToObject(xmlString);
		int storageplaceKey = storageplacesList.get(0).getStorageplaceKey();
		String storageplaceName = storageplacesList.get(0).getStorageplaceName();
					
		Storageplace storageplace = new Storageplace(storageplaceKey, storageplaceName);
		return storageplace;
	}
	
	/**
	 * add storageplace call to the client.
	 *
	 * @return string message
	 */
	//TODO
	/*
	public String add(Storageplace s1) {
		RESTClient rc = new RESTClient();
		String message = rc.addStorageplace(s1);
		return message;	
	}
	*/
	
	/**
	 * Update storageplace call to the client.
	 *
	 * @return string message
	 */
	//TODO
	/*
	public String update(Storageplace s1) {
		RESTClient rc = new RESTClient();
		String message = rc.updateStorageplace(s1);
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
	public String delete(Storageplace storageplace) {
		RESTClient rc = new RESTClient();
		String message = rc.deleteStorageplace(storageplace);
		return message;	
	}
	*/
	
	
	private ArrayList<Storageplace> jaxbXmlStringToObject(String xmlString) { 
		ArrayList<Storageplace> storageplacesList =  new ArrayList<Storageplace>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nStorageplaces = doc.getElementsByTagName("storageplace"); // extract a list of element from the tag structure 
			System.out.println("length nStorageplaces: " + nStorageplaces.getLength()); // Printing lenght of nodelist
			for (int temp = 0; temp < nStorageplaces.getLength(); temp++) { // loop through the elements 
				Element element = (Element)nStorageplaces.item(temp); 
				int storageplaceKey = Integer.parseInt(element.getElementsByTagName("storageplaceKey").item(0).getTextContent());
				String storageplaceName = element.getElementsByTagName("storageplaceName").item(0).getTextContent(); 
				//String tray = element.getElementsByTagName("tray").item(0).getTextContent();  
				Storageplace storageplace = new Storageplace(storageplaceKey, storageplaceName); // Create a Storageplace object 
				System.out.println("Printing a storageplace: " + storageplace); // call the Storageplace object's toString-method and print 
				storageplacesList.add(storageplace);
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
		System.out.println("Done parsing item-xml!"); 
			return storageplacesList;		
		}
}
