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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import model.Category;
import model.Item;
import repository.CategoryRepository;
import repository.ItemRepository;


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
    private ChoiceBox<String> categoryChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> categoryKeyChoiceBox;
    
    HashMap<String, Integer> categoryWithId = new HashMap<>(); //Will store "categoryName" and "categoryKey"
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
    	System.out.println("HandleItemsController initiated!");  //TODO något är galet här??
	
    	// mouseclick eventhandler
    	itemTable.setOnMouseClicked(this::TableClicked);
    	
    	// Match column with property
    	itemKeyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("itemKey"));
    	categoryKeyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("categoryKey"));
    	itemNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("itemName"));
    	categoryNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("categoryName"));
    	unitsAlwaysCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("unitsAlways"));
    	numberOfUnitsCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("numberOfUnit"));
    	storageplaceNameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("storageplaceName"));
    	storageplaceKeyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("storageplaceKey"));
    	availableCol.setCellValueFactory(new PropertyValueFactory<Item, String>("available"));    	
    	updateTable();
    	updateListOfCategories();

    	 
    }

    
    /**
     * Table clicked.
     *
     * @param event the event
     */
    @FXML
    private void TableClicked(MouseEvent event) {
       i = itemTable.getSelectionModel().getSelectedItem();
       
       itemKeyTxt.setText(String.valueOf(i.getItemKey())); // Convert to String.
       itemNameTxt.setText(i.getItemName());
       categoryNameTxt.setText(i.getCategoryName());
     //  categoryKeyTxt.setText(String.valueOf(i.getCategoryKey()));
       alwaysAtHomeTxt.setText(String.valueOf(i.getUnitsAlways()));
       numberOfUnitsTxt.setText(String.valueOf(i.getNumberOfUnits()));
       storageplaceKeyTxt.setText(String.valueOf(i.getStorageplaceKey()));
       availableTxt.setText(i.getAvailable());
       storageplaceNameTxt.setText(i.getStorageplaceName());
       
       
       categoryChoiceBox.setValue(i.getCategoryName()); //Set categorychoicebox to category name
       categoryKeyTxt.setText(String.valueOf(categoryWithId.get(categoryChoiceBox.getValue()))); //Konvertera detta till en String.
    }
    
    @FXML
    void categorySelected(ActionEvent event) {
    	categoryKeyTxt.setText(String.valueOf(categoryWithId.get(categoryChoiceBox.getValue()))); //Konvertera detta till en String.
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
	
	void showAllItems() {
		System.out.println("showAllItems called");
		updateTable();
	}

    @FXML
    void showSelectedItem(ActionEvent event) {
    	System.out.println("showSelectedItem called");
		//TODO
    }
	
	@FXML
	void addItem(ActionEvent event) {
		System.out.println("addItem called");
		//TODO
	}
	 
	@FXML
	void updateItem(ActionEvent event) {
		System.out.println("updateItem called");
	}
	
    @FXML
    void deleteItem(ActionEvent event) {
    	System.out.println("delete called");
		//TODO
    }


    private void updateListOfCategories() {

        //get repository
        CategoryRepository cr = new CategoryRepository();
        ArrayList<Category> categoryArray = cr.getAllCategories();

        //We loop through every category name in the category hashmap array.
        for(Category category : categoryArray) {
            categoryWithId.put(category.getCategoryName(), category.getCategoryKey());
        }

        // To set the items in the choice box:
        ObservableList<String> availableCategories = FXCollections.observableArrayList(categoryWithId.keySet());
        categoryChoiceBox.setItems(availableCategories);

    }
    
    
    @FXML
    void resetAllFields(ActionEvent event) {
    	itemKeyTxt.setText("");
        itemNameTxt.setText("");
        categoryNameTxt.setText(""); //TODO to be deleted?
        categoryKeyTxt.setText("");
        alwaysAtHomeTxt.setText("");
        numberOfUnitsTxt.setText("");
        storageplaceKeyTxt.setText("");
        availableTxt.setText("");
        storageplaceNameTxt.setText("");
    }
	   
    
    
}
