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

import model.Category;

/**
 * The Class CategoryRepository separates the Controller from the RESTClient and handles the
 * parsing of the xmlString the client receives from the server.
 */
public class CategoryRepository {
	
	/**
	 * Get all categories call to the client.
	 *
	 * @return the array list of categories
	 */
	public ArrayList<Category> getAllCategories() {
		RESTClient rc = new RESTClient();
		String xmlString = rc.getAllCategories();
		ArrayList<Category> categoriesList =  new ArrayList<Category>();
		categoriesList = jaxbXmlStringToObject(xmlString);
		//TODO TEMP from here
		/*
		Category c = new Category(1, "FISK"); //TODO TEMP dev data
		categoriesList.add(c); //TODO TEMP dev data
		c = new Category(2, "GRÖNSAKER"); //TODO TEMP dev data
		categoriesList.add(c);		//TODO TEMP dev data
		*/
		//Until here
		return categoriesList;
	}
	
	/**
	 * Get selected category call to the client.
	 *
	 * @return the category
	 */
	public Category getSelectedCategory(int categoryKeyQuery) {
		RESTClient rc = new RESTClient();
		String xmlString = rc.getSelectedCategory(categoryKeyQuery);
		ArrayList<Category> categoriesList =  new ArrayList<Category>();
		categoriesList = jaxbXmlStringToObject(xmlString);
		int categoryKey = categoriesList.get(0).getCategoryKey();
		String categoryName = categoriesList.get(0).getCategoryName();
		Category category = new Category(categoryKey, categoryName);
		return category;
	}
	
	/**
	 * add category call to the client.
	 *
	 * @return string message
	 */
	public String add(Category c1) {
		RESTClient rc = new RESTClient();
		String message = rc.addCategory(c1);
		return message;	
	}
	
	/**
	 * Update category call to the client.
	 *
	 * @return string message
	 */
	public String update(Category c1) {
		RESTClient rc = new RESTClient();
		String message = rc.updateCategory(c1);
		return message;	
	}
	
	/**
	 * Delete category call to the client.
	 *
	 * @return string message
	 */
	public String delete(Category c1) {
		RESTClient rc = new RESTClient();
		String message = rc.deleteCategory(c1);
		return message;	
	}
	
	
	private ArrayList<Category> jaxbXmlStringToObject(String xmlString) { 
		ArrayList<Category> categoriesList =  new ArrayList<Category>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); 
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlString)));
			NodeList nCategories = doc.getElementsByTagName("category"); // extract a list of element from the tag structure 
		//  System.out.println("length nCategories: " + nCategories.getLength()); // Printing lenght of nodelist
			for (int temp = 0; temp < nCategories.getLength(); temp++) { // loop through the elements 
				Element element = (Element)nCategories.item(temp); 
				int categoryKey = Integer.parseInt(element.getElementsByTagName("categoryKey").item(0).getTextContent());
				String categoryName = element.getElementsByTagName("categoryName").item(0).getTextContent();  
				Category category = new Category(categoryKey, categoryName); // Create a Category object 
		//		System.out.println("Printing a category: " + category); // call the Category object's toString-method and print 
				categoriesList.add(category);
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
		//System.out.println("Done!"); 
		return categoriesList;		
	}
}
