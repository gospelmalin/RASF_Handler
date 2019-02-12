package repository;

import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Category;
import model.Item;
import model.Storageplace;


/**
 * The Class RESTClient handles the communication with the API.
 */
public class RESTClient {
	
	/** The client. */
	private Client client;
	
	// URLs as static strings for ease of use and more readable code.
	/** The  URL used to Search for categories in RASF. */
	private static String REST_SERVICE_URL_CAT = "http://localhost:8081/RASF/rest/CategoryService/categories"; 
	/** The  URL used to Search for items in RASF. */
	private static String REST_SERVICE_URL_IT = "http://localhost:8081/RASF/rest/ItemService/items"; 
	/** The  URL used to Search for storageplaces in RASF. */
	private static String REST_SERVICE_URL_STO = "http://localhost:8081/RASF/rest/StorageplaceService/storageplaces"; 
	
	/**
	 * Instantiates a new API client.
	 */
	// constructor
	public RESTClient() {
		client = ClientBuilder.newClient();
	}
	
	
	/**
	 * Query API for all categories.
	 *
	 * @return the string
	 */
	protected String getAllCategories() {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL_CAT)
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	
	/**
	 * Query API for selected category.
	 *
	 * @param categoryKey the category key
	 * @return the string
	 */
	protected String getSelectedCategory(int categoryKey) {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL_CAT)
			    .path("/{categoryKey}")
		        .resolveTemplate("categoryKey", Integer.toString(categoryKey))
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}

	/**
	 * Call API to add category.
	 *
	 * @param category the Category
	 * @return the string
	 */
	protected String addCategory(Category category) {
		Form form = new Form();
	    form.param("categoryName", category.getCategoryName());
	    System.out.println("this is categoryName param: " + category.getCategoryName()); //TODO TEMP
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL_CAT)
	       .request(MediaType.APPLICATION_XML)
	       .post(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Add category request returned: \n" + callResult;		
	    System.out.println(returnMessage);
	    return returnMessage;
	}


	/**
	 * Call API to update category.
	 *
	 * @param category the Category
	 * @return the string
	 */
	protected String updateCategory(Category category) {
		Form form = new Form();
	   form.param("categoryKey", Integer.toString(category.getCategoryKey()));
	    form.param("categoryName", category.getCategoryName());
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL_CAT)
	       .path("/{categoryKey}")
	       .resolveTemplate("categoryKey", Integer.toString(category.getCategoryKey()))
	       .request(MediaType.APPLICATION_XML)
	       .put(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Update category request returned: \n" + callResult;
	    System.out.println(returnMessage);	    
	return returnMessage;
	}
	
	/**
	 * Call API to delete category.
	 *
	 * @param category the Category
	 * @return the string returnMessage
	 */
	protected String deleteCategory(Category category) {
	 RESTClient rc = new RESTClient();
	 String callResult = rc.client
	         .target(REST_SERVICE_URL_CAT)
	         .path("/{categoryKey}")
	         .resolveTemplate("categoryKey", Integer.toString(category.getCategoryKey()))
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);
	 String returnMessage = "Delete category request returned: \n" + callResult;
	 System.out.println(returnMessage);	
	return returnMessage;
	}

	
	// ITEM QUERIES
	
	/**
	 * Query API for all items.
	 *
	 * @return the string
	 */
	protected String getAllItems() {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL_IT)
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	
	/**
	 * Query API for selected item.
	 *
	 * @param itemKey the item key
	 * @return the string
	 */
	protected String getSelectedItem(int itemKey) {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL_IT)
			    .path("/{itemKey}")
		        .resolveTemplate("itemKey", Integer.toString(itemKey))
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	
	/**
	 * Call API to add item.
	 *
	 * @param item the Item
	 * @return the string
	 */
	protected String addItem(Item item) {
		Form form = new Form();
	    form.param("categoryKey", Integer.toString(item.getCategoryKey()));
	    form.param("itemName",item.getItemName());
	    form.param("unitsAlways", Integer.toString(item.getUnitsAlways())); 
	    form.param("available",item.getAvailable()); 
	    form.param("numberOfUnits", Integer.toString(item.getNumberOfUnits()));
	    form.param("storageplaceKey", Integer.toString(item.getStorageplaceKey()));

	    System.out.println("this is itemName param: " + item.getItemName()); //TODO TEMP
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL_IT)
	       .request(MediaType.APPLICATION_XML)
	       .post(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Add item request returned: \n" + callResult;		
	    System.out.println(returnMessage);
	    return returnMessage;
	}

	//TODO update
	
	/**
	 * Call API to delete item.
	 *
	 * @param item the Item
	 * @return the string returnMessage
	 */
	protected String deleteItem(Item item) {
	 RESTClient rc = new RESTClient();
	 String callResult = rc.client
	         .target(REST_SERVICE_URL_IT)
	         .path("/{itemKey}")
	         .resolveTemplate("itemKey", Integer.toString(item.getItemKey()))
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);
	 String returnMessage = "Delete item request returned: \n" + callResult;
	 System.out.println(returnMessage);	
	return returnMessage;
	}
	
	
	
	// STORAGEPLACES QUERIES
	
	/**
	 * Query API for all storageplaces.
	 *
	 * @return the string
	 */
	protected String getAllStorageplaces() {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL_STO)
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	
	/**
	 * Query API for selected storageplace.
	 *
	 * @param storageplaceKey the storageplace key
	 * @return the string
	 */
	protected String getSelectedStorageplace(int storageplaceKey) {
		RESTClient rc = new RESTClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = rc.client
				.target(REST_SERVICE_URL_STO)
			    .path("/{storageplaceKey}")
		        .resolveTemplate("storageplaceKey", Integer.toString(storageplaceKey))
				.request(MediaType.APPLICATION_XML)
				.get(string); // get the XML representation
		//print the XML representation
		System.out.println(s); // Kept for reference only
		return s;
	}
	

	/**
	 * Call API to add storageplace.
	 *
	 * @param storageplace the Storageplace
	 * @return the string
	 */
	protected String addStorageplace(Storageplace storageplace) {
		Form form = new Form();
	    form.param("storageplaceName", storageplace.getStorageplaceName());
	    System.out.println("this is storageplaceName param: " + storageplace.getStorageplaceName()); //TODO TEMP
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL_STO)
	       .request(MediaType.APPLICATION_XML)
	       .post(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Add storageplace request returned: \n" + callResult;		
	    System.out.println(returnMessage);
	    return returnMessage;
	}


	/**
	 * Call API to update storageplace.
	 *
	 * @param storageplace the Storageplace
	 * @return the string
	 */
	protected String updateStorageplace(Storageplace storageplace) {
		Form form = new Form();
	   form.param("storageplaceKey", Integer.toString(storageplace.getStorageplaceKey()));
	    form.param("storageplaceName", storageplace.getStorageplaceName());
	    RESTClient rc = new RESTClient();
	    String callResult = rc.client
	       .target(REST_SERVICE_URL_STO)
	       .path("/{storageplaceKey}")
	       .resolveTemplate("storageplaceKey", Integer.toString(storageplace.getStorageplaceKey()))
	       .request(MediaType.APPLICATION_XML)
	       .put(Entity.entity(form,
	          MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	          String.class);
	    String returnMessage = "Update storageplace request returned: \n" + callResult;
	    System.out.println(returnMessage);	    
	return returnMessage;
	}
	
	/**
	 * Call API to delete storageplace.
	 *
	 * @param storageplace the Storageplace
	 * @return the string returnMessage
	 */
	protected String deleteStorageplace(Storageplace storageplace) {
	 RESTClient rc = new RESTClient();
	 String callResult = rc.client
	         .target(REST_SERVICE_URL_STO)
	         .path("/{storageplaceKey}")
	         .resolveTemplate("storageplaceKey", Integer.toString(storageplace.getStorageplaceKey()))
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);
	 String returnMessage = "Delete storageplace request returned: \n" + callResult;
	 System.out.println(returnMessage);	
	return returnMessage;
	}
	
}
