package controller;

import java.util.ArrayList;

import commonUtilities.CommonUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Storageplace;
import repository.StorageplaceRepository;

public class HandleStorageplacesController {

	   @FXML
	    private TableView<Storageplace> storageplaceTable;

	    @FXML
	    private TableColumn<Storageplace, Integer> storageplaceKeyCol;

	    @FXML
	    private TableColumn<Storageplace, String> storageplaceNameCol;

	    @FXML
	    private TextField storageplaceKeyTxt;

	    @FXML
	    private TextField storageplaceNameTxt;

	    @FXML
	    private TextArea messageTextArea;

	    @FXML
	    private Button showStorageplaceBtn;

	    @FXML
	    private Button addStorageplaceBtn;

	    @FXML
	    private Button updateStorageplaceBtn;

	    @FXML
	    private Button deleteStorageplaceBtn;

	    @FXML
	    private Button homeBtn;
	    
	    
	    ArrayList<Storageplace> storageplacesList;
	    Storageplace sto;
	    StorageplaceRepository storageplaceRepo = new StorageplaceRepository();
	    
	    /**
	     * Initializing the controller class.
	     */
	    //This method is automatically called after the fxml file has been loaded.
	    @FXML
	    private void initialize () {
	    //	System.out.println("HandleStorageplaceController initiated!");
		
	    	// mouseclick eventhandler
	    	storageplaceTable.setOnMouseClicked(this::TableClicked);
	    	// Match column with property
	    	storageplaceKeyCol.setCellValueFactory(new PropertyValueFactory<Storageplace, Integer>("storageplaceKey"));
	    	storageplaceNameCol.setCellValueFactory(new PropertyValueFactory<Storageplace, String>("storageplaceName"));
	    	updateTable();
	    }

	    
	    /**
	     * Table clicked.
	     *
	     * @param event the event
	     */
	    @FXML
	    private void TableClicked(MouseEvent event) {
	     	if (storageplaceTable.getSelectionModel().getSelectedItem()== null) {
	    		return;
	    	}
	     	else {
	       sto = storageplaceTable.getSelectionModel().getSelectedItem();
	       storageplaceKeyTxt.setText(String.valueOf(sto.getStorageplaceKey())); // Convert to String.
	       storageplaceNameTxt.setText(sto.getStorageplaceName());
	     	}
	    }
	    
	    /**
		  * Update table.
		  */
		 // Updating table with result from Db search
			private void updateTable() {
				storageplacesList = new ArrayList<Storageplace>();
		    	storageplacesList = storageplaceRepo.getAllStorageplaces();
				ObservableList<Storageplace> list = FXCollections.observableArrayList(storageplacesList);
				storageplaceTable.setItems((ObservableList<Storageplace>) list);
			}
		
		/**
		  * Open Start View.
		  * 
		  * @param event the event
		  */
		@FXML
	    void openStartView(ActionEvent event) {
	    	//System.out.println("Start view should open");
	    	ViewController.activate("StartView");
	    }
		
		/**
		  * Show all storageplaces.
		  * 
		  */
		void showAllStorageplaces() {
			//System.out.println("showAllStorageplaces called");
			//TODO
		}
		
		/**
		  * Show Selected Storageplace.
		  * 
		  * @param event the event
		  */
		@FXML
	    void showSelectedStorageplace(ActionEvent event) {
	    	//System.out.println("showSelectedStorageplace called");
	    	String message = null;
	    	//Text fields should not be empty
	    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
	            	message = "Fyll i id för den förvaringsplats som ska visas.";
	            	messageTextArea.setText(message);
	                return;
	        }
	    	boolean isInteger = CommonUtil.isInteger(storageplaceKeyTxt.getText());
    	 	if (!isInteger) {
 	   		message = "Id måste vara en siffra.\nFyll i en siffra eller välj förvaringsplats i tabellen.";
 	       	messageTextArea.setText(message);
            return;
    	 	}
	    	int storageplaceKeySelection = Integer.parseInt(storageplaceKeyTxt.getText());
    	 	boolean storageplaceExists = checkStorageplaceExistance(storageplaceKeySelection);
        	if (!storageplaceExists) {
    			message = "Det finns ingen förvaringsplats med det id:t. \nVälj förvaringsplats i tabellen.";
    			messageTextArea.setText(message);
                return;
        	}
    		else {
	    	Storageplace storageplace = new Storageplace();
	    	storageplace = storageplaceRepo.getSelectedStorageplace(storageplaceKeySelection);
	    	int storageplaceKey = storageplace.getStorageplaceKey();
	    	String storageplaceName = storageplace.getStorageplaceName();
	    	messageTextArea.setText("Vald förvaringsplats: \n");
	    	messageTextArea.appendText("Id: " + storageplaceKey + "\n");
	    	messageTextArea.appendText("Namn: " + storageplaceName + "\n");   
    		}
	    }

		/**
		  * Add storageplace.
		  * 
		  * @param event the event
		  */
	    @FXML
	    void addStorageplace(ActionEvent event) {
	    	System.out.println("addStorageplace called");
	    	String message = null;
	    	// id (storageplaceKey) should be empty to avoid confusion as database will generate it
	    	storageplaceKeyTxt.setText("");
	        //Text fields cannot be empty
	        if(!(storageplaceNameTxt.getText().length() > 0)) {
	        	message = "Fyll i förvaringsplatsens namn innan du försöker lägga till en förvaringsplats.";
	        	messageTextArea.setText(message);
	            return;
	        }
	        // Want only one of each storageplace name
	        String existingStorageplaceName = null;
	        storageplacesList = new ArrayList<Storageplace>();
	        storageplacesList = storageplaceRepo.getAllStorageplaces();
	        for(Storageplace sto : storageplacesList) {
	        	existingStorageplaceName = sto.getStorageplaceName();
	        	if(storageplaceNameTxt.getText().equalsIgnoreCase(existingStorageplaceName)) {
	        		message = "Det finns redan en förvaringsplats med det namnet. \nAnge ett annat förvaringsplatsnamn.";
	        		messageTextArea.setText(message);
	        		return;
	        	}
	        }
	        // New storageplace instance			
	        Storageplace sto1 = new Storageplace();
			//sto1.setStorageplaceKey(Integer.parseInt(storageplaceKeyTxt.getText()));
	        // Storageplaces should be uppercase
			sto1.setStorageplaceName(storageplaceNameTxt.getText().toUpperCase());
			message = storageplaceRepo.add(sto1); //TODO
	        messageTextArea.setText(message); //TODO
	        //update table
	        updateTable();
	        storageplaceTable.refresh();
	    }
	    
	    /**
		  * Update storageplace.
		  * 
		  * @param event the event
		  */
	    @FXML
	    void updateStorageplace(ActionEvent event) {
	    	System.out.println("updateStorageplace called");
	    	
	    	Storageplace sto1 = new Storageplace();
	    	String message = null;
	    	//Text fields should not be empty
	    	if(!(storageplaceNameTxt.getText().length() > 0)) {
	        	message = "Fyll i nytt namn för den förvaringsplats som ska uppdateras.";
	        	messageTextArea.setText(message);
	            return;
	        }
	    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
	        	message = "Fyll i id för den förvaringsplats som ska uppdateras eller välj förvaringsplats från tabellen.";
	        	messageTextArea.setText(message);
	            return;
	        }
	    	boolean isInteger = CommonUtil.isInteger(storageplaceKeyTxt.getText());
    	 	if (!isInteger) {
 	   		message = "Id måste vara en siffra.\nFyll i en siffra eller välj förvaringsplats från tabellen.";
 	       	messageTextArea.setText(message);
            return;
    	 	}
    	 	int storageplaceKeySelected = Integer.parseInt(storageplaceKeyTxt.getText());
    	 	boolean storageplaceExists = checkStorageplaceExistance(storageplaceKeySelected);
        	if (!storageplaceExists) {
    			message = "Det finns ingen förvaringsplats med det id:t. \nVälj förvaringsplats i tabellen.";
    			messageTextArea.setText(message);
                return;
        	}
    		else {
	    	sto1.setStorageplaceKey(storageplaceKeySelected);
	    	 // Storageplaces should be uppercase
			sto1.setStorageplaceName(storageplaceNameTxt.getText().toUpperCase());
	    	message = storageplaceRepo.update(sto1);
	        messageTextArea.setText(message);
	    	updateTable();
	    	storageplaceTable.refresh();
    		}
	    }
	    
	    /**
		  * Delete storageplace.
		  * 
		  * @param event the event
		  */
	    @FXML
	    void deleteStorageplace(ActionEvent event) {
	    	System.out.println("deleteStorageplace called");
	    	
	    	Storageplace sto = new Storageplace();
	    	String message = null;
	    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
	        	message = "Fyll i id för den förvaringsplats som ska tas bort.";
	        	messageTextArea.setText(message);
	            return;
	        }
	    	boolean isInteger = CommonUtil.isInteger(storageplaceKeyTxt.getText());
    	 	if (!isInteger) {
 	   		message = "Id måste vara en siffra.\nFyll i en siffra eller välj förvaringsplats från tabellen.";
 	       	messageTextArea.setText(message);
            return;
    	 	}
    	 	int storageplaceKeySelected = Integer.parseInt(storageplaceKeyTxt.getText());
    	 	boolean storageplaceExists = checkStorageplaceExistance(storageplaceKeySelected);
        	if (!storageplaceExists) {
    			message = "Det finns ingen förvaringsplats med det id:t. \nVälj förvaringsplats i tabellen.";
    			messageTextArea.setText(message);
                return;
        	}
    		else {
	    	sto.setStorageplaceKey(storageplaceKeySelected);
	    	sto.setStorageplaceName(storageplaceNameTxt.getText().toUpperCase());
	        message = storageplaceRepo.delete(sto);
	        messageTextArea.setText(message); //TODO
	        //update table
	        updateTable();
    		}
	        
	    }

	    /**
		 * The check storageplace existance method.
		 *
		 * @param storageplaceKey the storageplace Key
		 * @return the boolean
		 */
		private boolean checkStorageplaceExistance(int storageplaceKey) {
			ArrayList<Storageplace> allStorageplaces = storageplaceRepo.getAllStorageplaces();
			boolean storageplaceExists = false;
			for (Storageplace sto : allStorageplaces) {
				if (sto.getStorageplaceKey() == storageplaceKey) {
					storageplaceExists = true;
					break;
				}
			}
			return storageplaceExists;
		}

}
