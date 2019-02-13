package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Category;
import model.Item;
import model.Storageplace;
import repository.CategoryRepository;
import repository.ItemRepository;
import repository.StorageplaceRepository;


public class HandleItemsController {
   @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, Integer> itemKeyCol;
    
    @FXML
    private TableColumn<Item, Integer> categoryKeyCol;

    @FXML
    private TableColumn<Item, String> itemNameCol;

    @FXML
    private TableColumn<Item, String> categoryNameCol;

    @FXML
    private TableColumn<Item, Integer> unitsAlwaysCol;

    @FXML
    private TableColumn<Item, Integer> numberOfUnitsCol;

    @FXML
    private TableColumn<Item, String> storageplaceNameCol;

    @FXML
    private TableColumn<Item, Integer> storageplaceKeyCol;

    @FXML
    private TableColumn<Item, String> availableCol;
    
    @FXML
    private TextField categoryNameTxt;

    @FXML
    private TextField categoryKeyTxt;

    @FXML
    private TextField alwaysAtHomeTxt;

    @FXML
    private TextField numberOfUnitsTxt;

    @FXML
    private TextField storageplaceKeyTxt;

    @FXML
    private TextField availableTxt;

    @FXML
    private TextField storageplaceNameTxt;
    
    @FXML
    private TextField itemKeyTxt;

    @FXML
    private TextField itemNameTxt;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button showItemBtn;

    @FXML
    private Button addItemBtn;

    @FXML
    private Button updateItemBtn;

    @FXML
    private Button deleteItemBtn;

    @FXML
    private Button homeBtn;
    
    @FXML
    private Button resetFieldsBtn;
    
    @FXML
    private Button openShoppingListBtn;
   
    
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    
    @FXML
    private ChoiceBox<String> storageChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> categoryKeyChoiceBox;
    
    HashMap<String, Integer> categoryWithId = new HashMap<>(); //Will store "categoryName" and "categoryKey"
    HashMap<String, Integer> storageplaceWithId = new HashMap<>(); //Will store "storageplaceName" and "storageplaceKey"
    ArrayList<Item> itemsList;
    Item i;
    ItemRepository itemRepo = new ItemRepository();
    Category cat;
   
    
    /**
     * Initializing the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	System.out.println("HandleItemsController initiated!");
	
    	// mouseclick eventhandler
    	itemTable.setOnMouseClicked(this::TableClicked);
    	
    	// Match column with property
    	itemKeyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemKey"));
    	categoryKeyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("categoryKey"));
    	itemNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
    	categoryNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("categoryName"));
    	unitsAlwaysCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("unitsAlways"));
    	numberOfUnitsCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("numberOfUnits"));
    	storageplaceNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("storageplaceName"));
    	storageplaceKeyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("storageplaceKey"));
    	availableCol.setCellValueFactory(new PropertyValueFactory<Item, String>("available"));    	
    	updateTable();
    	updateListOfCategories();
    	updateListOfStorageplaces();

    	 
    }

    
    /**
     * Table clicked.
     *
     * @param event the event
     */
    @FXML
    private void TableClicked(MouseEvent event) {
    	if (itemTable.getSelectionModel().getSelectedItem()== null) {
    		return;
    	}
   	else {
       i = itemTable.getSelectionModel().getSelectedItem();      
       itemKeyTxt.setText(String.valueOf(i.getItemKey())); // Convert to String.
       itemNameTxt.setText(i.getItemName());
   //    categoryNameTxt.setText(i.getCategoryName()); //TODO TESTAR
     //  categoryKeyTxt.setText(String.valueOf(i.getCategoryKey()));
       alwaysAtHomeTxt.setText(String.valueOf(i.getUnitsAlways()));
       numberOfUnitsTxt.setText(String.valueOf(i.getNumberOfUnits()));
     //  storageplaceKeyTxt.setText(String.valueOf(i.getStorageplaceKey()));
       availableTxt.setText(i.getAvailable());
    //   storageplaceNameTxt.setText(i.getStorageplaceName()); //TODO TESTAR
       
       
       categoryChoiceBox.setValue(i.getCategoryName()); //Set categorychoicebox to category name
       categoryKeyTxt.setText(String.valueOf(categoryWithId.get(categoryChoiceBox.getValue()))); //Konvertera detta till en String.

       storageChoiceBox.setValue(i.getStorageplaceName()); //Set storagechoicebox to storageplace name
       storageplaceKeyTxt.setText(String.valueOf(storageplaceWithId.get(storageChoiceBox.getValue()))); //Konvertera detta till en String.
    	}
    }
    
    @FXML
    void categorySelected(ActionEvent event) {
    	categoryKeyTxt.setText(String.valueOf(categoryWithId.get(categoryChoiceBox.getValue()))); //Konvertera detta till en String.
    }
    
    @FXML
    void storageSelected(ActionEvent event) {
    	storageplaceKeyTxt.setText(String.valueOf(storageplaceWithId.get(storageChoiceBox.getValue()))); //Konvertera detta till en String.
    }
    
 
    
    /**
	  * Update table.
	  */
	 // Updating table with result from Db search
		private void updateTable() {
	    	itemsList = new ArrayList<Item>();
	    	itemsList = itemRepo.getAllItems();
			ObservableList<Item> list = FXCollections.observableArrayList(itemsList);
			itemTable.setItems((ObservableList<Item>) list);
		}
		
		
	@FXML
    void openStartView(ActionEvent event) {
    	//System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }
	
	@FXML
    void openShoppingList(ActionEvent event) {
    	ViewController.activate("ShoppingList");
    	System.out.println("ShoppingListView should be shown.");
    }
	
	void showAllItems() {
		System.out.println("showAllItems called");
		updateTable();
	}

    @FXML
    void showSelectedItem(ActionEvent event) {
    	System.out.println("showSelectedItem called");
    	String message = null;
    	//Text fields should not be empty
    	 if(!(itemKeyTxt.getText().length() > 0)) {
            	message = "Id saknas. Välj matvara i tabellen och klicka på Visa matvara.";
            	messageTextArea.setText(message);
                return;
        }
    	int itemKeySelection = Integer.parseInt(itemKeyTxt.getText());
    	Item item = new Item();
    	item = itemRepo.getSelectedItem(itemKeySelection);
    	int itemKey = item.getItemKey();
    	String itemName = item.getItemName();
    	int numberOfUnits = item.getNumberOfUnits();
    	int unitsAlways = item.getUnitsAlways();
    	int categoryKey = item.getCategoryKey();
    	String categoryName = item.getCategoryName();
    	String available = item.getAvailable();
    	int storageplaceKey = item.getStorageplaceKey();
    	String storageplaceName = item.getStorageplaceName();
    	int numberToBuy = item.getNumberToBuy();
    	
    	messageTextArea.setText("Vald matvara: \n");
    	messageTextArea.appendText("Id: " + itemKey + "\n");
    	messageTextArea.appendText("Namn: " + itemName + "\n");  
    	messageTextArea.appendText("Antal: " + numberOfUnits + "\n");
    	messageTextArea.appendText("Antal alltid hemma: " + unitsAlways + "\n");
    	messageTextArea.appendText("Finns hemma: " + available + "\n");
    	messageTextArea.appendText("Kategori id: " + categoryKey + "\n");
    	messageTextArea.appendText("Kategori: " + categoryName + "\n");
    	messageTextArea.appendText("Förvaringsid: " + storageplaceKey + "\n");
    	messageTextArea.appendText("Förvaringsplats: " + storageplaceName + "\n"); 	
    	messageTextArea.appendText("Antal att köpa: " + numberToBuy + "\n");
    }
	
	@FXML
	void addItem(ActionEvent event) {
		System.out.println("addItem called");
    	String message = null;
        //Text fields cannot be empty
        if(!(itemNameTxt.getText().length() > 0)) {
        	message = "Ange namn innan du försöker lägga till en matvara.";
        	messageTextArea.setText(message);
            return;
        }
        if(!(categoryKeyTxt.getText().length() > 0)) {
        	message = "Kategori-id saknas. Välj kategori i dropdown-listan för den matvara som ska läggas till.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(alwaysAtHomeTxt.getText().length() > 0)) {
        	message = "Antal alltid hemma saknas. Ange hur många förpackningar av matvaran som alltid ska finnas hemma.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(numberOfUnitsTxt.getText().length() > 0)) {
        	message = "Antal förpackningar saknas. Ange hur många förpackningar som finns av matvaran efter uppdatering.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
        	message = "Förpackningsid saknas. Välj förvaringsplats för matvaran i dropdown-listan";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(availableTxt.getText().length() > 0)) {
    		if(Integer.parseInt(numberOfUnitsTxt.getText())>0) {
    			availableTxt.setText("YES");
    		}
    		else {
    			availableTxt.setText("NO");
    		}
        }
    	//available must have appropriate values
    	if(Integer.parseInt(numberOfUnitsTxt.getText())>0) {
			availableTxt.setText("YES");
		}
		else {
			availableTxt.setText("NO");
		}
    	
        // New item instance			
		Item i1 = new Item();
		//i1.setItemKey(Integer.parseInt(itemKeyTxt.getText()));
		i1.setCategoryKey(Integer.parseInt(categoryKeyTxt.getText()));
		i1.setItemName(itemNameTxt.getText());
		i1.setUnitsAlways(Integer.parseInt(alwaysAtHomeTxt.getText()));
		i1.setAvailable(availableTxt.getText());
		i1.setNumberOfUnits(Integer.parseInt(numberOfUnitsTxt.getText()));
		i1.setStorageplaceKey(Integer.parseInt(storageplaceKeyTxt.getText()));
		message = itemRepo.add(i1); //TODO
        messageTextArea.setText(message); //TODO
        //update table
        updateTable();
        itemTable.refresh();
        resetFields(); // empty text fields
		//TODO
	}
	 
	@FXML
	void updateItem(ActionEvent event) {
		System.out.println("updateItem called");
		//Item i1 = new Item();
    	String message = null;
    	//Text fields should not be empty
    	if(!(categoryKeyTxt.getText().length() > 0)) {
        	message = "Kategori-id saknas. Välj kategori för den matvara som ska uppdateras i dropdown-listan";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(itemNameTxt.getText().length() > 0)) {
        	message = "Fyll i nytt namn för den matvara som ska uppdateras.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(itemKeyTxt.getText().length() > 0)) {
        	message = "Id saknas. Välj i tabellen den matvara som ska uppdateras";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(alwaysAtHomeTxt.getText().length() > 0)) {
        	message = "Antal alltid hemma saknas. Ange hur många förpackningar av matvaran som alltid ska finnas hemma.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(numberOfUnitsTxt.getText().length() > 0)) {
        	message = "Antal förpackningar saknas. Ange hur många förpackningar som finns av matvaran efter uppdatering.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
        	message = "Förpackningsid saknas. Välj förvaringsplats för matvaran i dropdown-listan";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(availableTxt.getText().length() > 0)) {
    		if(Integer.parseInt(numberOfUnitsTxt.getText())>0) {
    			availableTxt.setText("YES");
    		}
    		else {
    			availableTxt.setText("NO");
    		}
        }
    	//available must have appropriate values
    	if(Integer.parseInt(numberOfUnitsTxt.getText())>0) {
			availableTxt.setText("YES");
		}
		else {
			availableTxt.setText("NO");
		}
    	i.setItemKey(Integer.parseInt(itemKeyTxt.getText()));
    	i.setCategoryKey(Integer.parseInt(categoryKeyTxt.getText()));
		i.setItemName(itemNameTxt.getText());
		i.setUnitsAlways(Integer.parseInt(alwaysAtHomeTxt.getText()));
		i.setAvailable(availableTxt.getText());
		i.setNumberOfUnits(Integer.parseInt(numberOfUnitsTxt.getText()));
		i.setStorageplaceKey(Integer.parseInt(storageplaceKeyTxt.getText()));
    	message = itemRepo.update(i);
        messageTextArea.setText(message);
        resetFields(); // Empty input text fields
    	updateTable();
    	itemTable.refresh();
	}
	
    @FXML
    void deleteItem(ActionEvent event) {
    	System.out.println("delete called - delete items not allowed");
    }

    private void updateListOfCategories() {

        //get repository
        CategoryRepository cr = new CategoryRepository();
        ArrayList<Category> categoryArray = cr.getAllCategories();

        //loop through every category name in the category hashmap array.
        for(Category category : categoryArray) {
            categoryWithId.put(category.getCategoryName(), category.getCategoryKey());
        }

        // To set the items in the choice box:
        ObservableList<String> availableCategories = FXCollections.observableArrayList(categoryWithId.keySet());
        categoryChoiceBox.setItems(availableCategories);

    }
    
    private void updateListOfStorageplaces() {

        //get repository
        StorageplaceRepository sr = new StorageplaceRepository();
        ArrayList<Storageplace> storageplaceArray = sr.getAllStorageplaces();

        // loop through every storageplace name in the storageplace hashmap array.
        for(Storageplace storageplace : storageplaceArray) {
            storageplaceWithId.put(storageplace.getStorageplaceName(), storageplace.getStorageplaceKey());
        }

        // To set the items in the choice box:
        ObservableList<String> availableStorageplaces = FXCollections.observableArrayList(storageplaceWithId.keySet());
        storageChoiceBox.setItems(availableStorageplaces);

    }
    
    
    @FXML
    void resetAllFields(ActionEvent event) {
    	resetFields();
    }
	   
    private void resetFields() {
    	itemKeyTxt.setText("");
        itemNameTxt.setText("");
      //  categoryNameTxt.setText(""); //TODO to be deleted?
       // categoryKeyTxt.setText("");
        alwaysAtHomeTxt.setText("");
        numberOfUnitsTxt.setText("");
       // storageplaceKeyTxt.setText("");
        availableTxt.setText("");
      //  storageplaceNameTxt.setText(""); //TODO to be deleted?
    }
    
}
