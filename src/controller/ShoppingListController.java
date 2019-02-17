package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Item;
import repository.ItemRepository;

public class ShoppingListController {
	
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
	    private TableColumn<Item, Integer> numberToBuyCol;
	    
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
	    private TextField numberToBuyTxt;

	    
	    @FXML
	    private Button refreshShoppingListBtn;
	    
	    @FXML
	    private Button homeBtn;
	    
	    ArrayList<Item> itemsList;
	    ArrayList<Item> itemsToBuyList;
	    Item i;
	    ItemRepository itemRepo = new ItemRepository();
	
	  /**
     * Initializing the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	// System.out.println("HandleShoppingListController initiated!");
	
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
    	numberToBuyCol.setCellValueFactory(new PropertyValueFactory<Item, Integer>("numberToBuy"));
    	updateTable();
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
           categoryKeyTxt.setText(String.valueOf(i.getCategoryKey()));
           alwaysAtHomeTxt.setText(String.valueOf(i.getUnitsAlways()));
           numberOfUnitsTxt.setText(String.valueOf(i.getNumberOfUnits()));
           storageplaceKeyTxt.setText(String.valueOf(i.getStorageplaceKey()));
           availableTxt.setText(i.getAvailable());
           storageplaceNameTxt.setText(i.getStorageplaceName());
           numberToBuyTxt.setText(String.valueOf(i.getNumberToBuy()));
           
            }
        
        /**
   	  * Update table.
   	  */
   	 // Updating table with result from Db search
   		private void updateTable() {
   	    	itemsList = new ArrayList<Item>();
   	    	itemsList = itemRepo.getAllItems();
   	    	//select appropriate items for shoppingList
   	    	itemsToBuyList = createShoppingList(itemsList);
   			ObservableList<Item> list = FXCollections.observableArrayList(itemsToBuyList);
   			itemTable.setItems((ObservableList<Item>) list);
   			itemTable.refresh(); //TODO check if it helps
   		}
   		
   		/**
      	  * Open startView.
      	  */
   		@FXML
   	    void openStartView(ActionEvent event) {
   	    	//System.out.println("Start view should open");
   	    	ViewController.activate("StartView");
   	    }
   		
   	  @FXML
      void refreshShoppingList(ActionEvent event) {
   		updateTable();
   		itemTable.refresh();
      }
   	
   	  /**
	  * Reset all text fields.
	  */
   	 @FXML
     void resetAllFields(ActionEvent event) {
     	itemKeyTxt.setText("");
         itemNameTxt.setText("");
         categoryNameTxt.setText("");
         categoryKeyTxt.setText("");
         alwaysAtHomeTxt.setText("");
         numberOfUnitsTxt.setText("");
         storageplaceKeyTxt.setText("");
         availableTxt.setText("");
         storageplaceNameTxt.setText("");
         numberToBuyTxt.setText("");
     }
   	 
     /**
   	  * Create shopping list
   	  * 
   	  * @param ArrayList<item>
   	  * @return itemsToBuyList Array list of items to buy
   	  */
   	 private ArrayList<Item> createShoppingList(ArrayList<Item> itemsList) {
   		 ArrayList<Item> itemsToBuyList = new ArrayList<Item>();
   		 for (Item item : itemsList) {
   			 if (item.getNumberToBuy()>0) {
   				 itemsToBuyList.add(item);
   			 }
   		 }
   		 return itemsToBuyList;
   	 }
    	
}
