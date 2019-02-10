package repository;

import java.util.ArrayList;

import model.Category;

public class CategoryRepository {

	
	/**
	 * Get all users call to the client.
	 *
	 * @return the array list of users
	 */
	public ArrayList<Category> getAllCategories() {
		//TODO

		//RESTClient rc = new RESTClient();
		//String xmlString = rc.getAllUsers();
		ArrayList<Category> categoriesList =  new ArrayList<Category>();
		//categoriesList = jaxbXmlStringToObject(xmlString);
		//TODO TEMP from here
		Category c = new Category(1, "FISK"); //TODO TEMP dev data
		categoriesList.add(c); //TODO TEMP dev data
		c = new Category(2, "GRÖNSAKER"); //TODO TEMP dev data
		categoriesList.add(c);		//TODO TEMP dev data
		//Until here
		return categoriesList;
	}
}
