package controller;

import java.util.ArrayList;

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
	    	System.out.println("HandleStorageplaceController initiated!");
		
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
	       sto =storageplaceTable.getSelectionModel().getSelectedItem();
	       storageplaceKeyTxt.setText(String.valueOf(sto.getStorageplaceKey())); // Convert to String.
	       storageplaceNameTxt.setText(sto.getStorageplaceName());
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
			
		@FXML
	    void openStartView(ActionEvent event) {
	    	//System.out.println("Start view should open");
	    	ViewController.activate("StartView");
	    }
		
		void showAllStorageplaces() {
			System.out.println("showAllStorageplaces called");
			//TODO
		}
		
		@FXML
	    void showSelectedStorageplace(ActionEvent event) {
	    	System.out.println("showSelectedStorageplace called");
	    	String message = null;
	    	//Text fields should not be empty
	    	 if(!(storageplaceKeyTxt.getText().length() > 0)) {
	            	message = "Fyll i id f�r den f�rvaringsplats som ska visas.";
	            	messageTextArea.setText(message);
	                return;
	        }
	    	int storageplaceKeySelection = Integer.parseInt(storageplaceKeyTxt.getText());
	    	Storageplace storageplace = new Storageplace();
	    	storageplace = storageplaceRepo.getSelectedStorageplace(storageplaceKeySelection);
	    	int storageplaceKey = storageplace.getStorageplaceKey();
	    	String storageplaceName = storageplace.getStorageplaceName();
	    	messageTextArea.setText("Vald f�rvaringsplats: \n");
	    	messageTextArea.appendText("Id: " + storageplaceKey + "\n");
	    	messageTextArea.appendText("Namn: " + storageplaceName + "\n");   	
	    }

		
	    @FXML
	    void addStorageplace(ActionEvent event) {
	    	System.out.println("addStorageplace called");
	    	/*
	    	String message = null;
	        //Text fields cannot be empty
	        if(!(storageplaceNameTxt.getText().length() > 0)) {
	        	message = "Fyll i f�rvaringsplatsens namn innan du f�rs�ker l�gga till en f�rvaringsplats.";
	        	messageTextArea.setText(message);
	            return;
	        }
	        // New category instance			
	        Storageplace sto1 = new Storageplace();
			//sto1.setStorageplaceKey(Integer.parseInt(storageplaceKeyTxt.getText()));
			sto1.setStorageplaceName(storageplaceNameTxt.getText());
			message = storageplaceRepo.add(sto1); //TODO
	        messageTextArea.setText(message); //TODO
	        //update table
	        updateTable();
	        */
	    }
	    
	    @FXML
	    void updateStorageplace(ActionEvent event) {
	    	System.out.println("updateStorageplace called");
	    	/*
	    	Storageplace sto1 = new Storageplace();
	    	String message = null;
	    	//Text fields should not be empty
	    	if(!(storageplaceNameTxt.getText().length() > 0)) {
	        	message = "Fyll i nytt namn f�r den f�rvaringsplats som ska uppdateras.";
	        	messageTextArea.setText(message);
	            return;
	        }
	    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
	        	message = "Fyll i id f�r den f�rvaringsplats som ska uppdateras";
	        	messageTextArea.setText(message);
	            return;
	        }
	    	sto1.setStorageplaceKey(Integer.parseInt(storageplaceKeyTxt.getText()));
			sto1.setStorageplaceName(storageplaceNameTxt.getText());
	    	message = storageplaceRepo.update(sto1);
	        messageTextArea.setText(message);
	    	updateTable();
	    	*/
	    }
	    
	    
	    @FXML
	    void deleteStorageplace(ActionEvent event) {
	    	System.out.println("deleteStorageplace called");
	    	/*
	    	Storageplace sto = new Storageplace();
	    	String message = null;
	    	if(!(storageplaceKeyTxt.getText().length() > 0)) {
	        	message = "Fyll i id f�r den f�rvaringsplats som ska tas bort.";
	        	messageTextArea.setText(message);
	            return;
	        }
	    	sto.setStorageplaceKey(Integer.parseInt(storageplaceKeyTxt.getText()));
	    	sto.setStorageplaceName(storageplaceNameTxt.getText());
	        message = storageplaceRepo.delete(sto);
	        messageTextArea.setText(message); //TODO
	        //update table
	        updateTable();
	        */
	    }

	 

}