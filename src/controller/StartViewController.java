package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * The Class StartViewController handles the start view where the user can choose
 * what to do.
 */
public class StartViewController {

    @FXML
    private Button categoryBtn;

    @FXML
    private Button itemsBtn;

    @FXML
    private Button storageBtn;

    @FXML
    private Button shoppingListBtn;
    
    /**
     * Initialize the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
      @FXML
      private void initialize () {
      	
      }

    @FXML
    void openHandleCategories(ActionEvent event) {
    	ViewController.activate("HandleCategories");
    	//System.out.println("HandleCategoriesView should be shown.");
    }

    @FXML
    void openHandleItems(ActionEvent event) {
    	ViewController.activate("HandleItems");
    	//System.out.println("HandleItemsView should be shown.");
    }

    @FXML
    void openHandleStorage(ActionEvent event) {
    	ViewController.activate("HandleStorage");
    	//System.out.println("HandleStorageView should be shown.");
    }

    @FXML
    void openShoppingList(ActionEvent event) {
    	ViewController.activate("ShoppingList");
    	//System.out.println("ShoppingListView should be shown.");
    }

}

