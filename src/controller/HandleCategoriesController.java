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
import model.Category;
import repository.CategoryRepository;


public class HandleCategoriesController {
   @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, Integer> categoryKeyCol;

    @FXML
    private TableColumn<Category, String> categoryNameCol;

    @FXML
    private TextField categoryKeyTxt;

    @FXML
    private TextField categoryNameTxt;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private Button showCategoryBtn;

    @FXML
    private Button addCategoryBtn;

    @FXML
    private Button updateCategoryBtn;

    @FXML
    private Button deleteCategoryBtn;

    @FXML
    private Button homeBtn;
    
    
    ArrayList<Category> categoriesList;
    Category cat;
    CategoryRepository categoryRepo = new CategoryRepository();

    
    /**
     * Initializing the controller class.
     */
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	System.out.println("HandleCategoriesController initiated!");
	
    	// mouseclick eventhandler
    	categoryTable.setOnMouseClicked(this::TableClicked);
    	// Match column with property
    	categoryKeyCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("categoryKey"));
    	categoryNameCol.setCellValueFactory(new PropertyValueFactory<Category, String>("categoryName"));
    	updateTable();
    }

    
    /**
     * Table clicked.
     *
     * @param event the event
     */
    @FXML
    private void TableClicked(MouseEvent event) {
     	if (categoryTable.getSelectionModel().getSelectedItem()== null) {
    		return;
    	}
     	else {
       cat = categoryTable.getSelectionModel().getSelectedItem();
       categoryKeyTxt.setText(String.valueOf(cat.getCategoryKey())); // Convert to String.
       categoryNameTxt.setText(cat.getCategoryName());
     	}
    }
    
    /**
	  * Update table.
	  */
	 // Updating table with result from Db search
		private void updateTable() {
	    	categoriesList = new ArrayList<Category>();
	    	categoriesList = categoryRepo.getAllCategories();
			ObservableList<Category> list = FXCollections.observableArrayList(categoriesList);
			categoryTable.setItems((ObservableList<Category>) list);
		}
		
	@FXML
    void openStartView(ActionEvent event) {
    	//System.out.println("Start view should open");
    	ViewController.activate("StartView");
    }
	
	void showAllCategories() {
		System.out.println("showAllCategories called");
		//TODO
	}
	
	@FXML
    void showSelectedCategory(ActionEvent event) {
    	System.out.println("showSelectedCategory called");
    	String message = null;
    	//Text fields should not be empty
    	 if(!(categoryKeyTxt.getText().length() > 0)) {
            	message = "Fyll i id för den kategori som ska visas.";
            	messageTextArea.setText(message);
                return;
        }
    	 boolean isInteger = CommonUtil.isInteger(categoryKeyTxt.getText());
    	if (!isInteger) {
    		message = "Id måste vara en siffra.\nFyll i en siffra eller välj kategori att visa från tabellen.";
        	messageTextArea.setText(message);
            return;
    	}
    	int categoryKeySelection = Integer.parseInt(categoryKeyTxt.getText());
    	boolean categoryExists = checkCategoryExistance(categoryKeySelection);
    	if (!categoryExists) {
			message = "Det finns ingen kategori med det id:t. \nVälj kategori att visa från tabellen.";
			messageTextArea.setText(message);
            return;
    	}
		else {
			Category category = new Category();
			category = categoryRepo.getSelectedCategory(categoryKeySelection);
			int categoryKey = category.getCategoryKey();
			String categoryName = category.getCategoryName();
			messageTextArea.setText("Vald kategori: \n");
			messageTextArea.appendText("Id: " + categoryKey + "\n");
			messageTextArea.appendText("Namn: " + categoryName + "\n");  
		}
    }

    @FXML
    void addCategory(ActionEvent event) {
    	System.out.println("addCategory called");
    	String message = null;
    	// id (categoryKey) should be empty to avoid confusion as database will generate it
    	categoryKeyTxt.setText("");
        //Text fields cannot be empty
        if(!(categoryNameTxt.getText().length() > 0)) {
        	message = "Ange namn innan du försöker lägga till en kategori.";
        	messageTextArea.setText(message);
            return;
        }
        // Want only one of each category name
        String existingCategoryName = null;
        categoriesList = new ArrayList<Category>();
    	categoriesList = categoryRepo.getAllCategories();
        for(Category cat : categoriesList) {
        	existingCategoryName = cat.getCategoryName();
        	if(categoryNameTxt.getText().equalsIgnoreCase(existingCategoryName)) {
        		message = "Kategorin finns redan. Ange ett annat kategorinamn.";
        		messageTextArea.setText(message);
        		return;
        	}
        }
        // New category instance			
		Category c1 = new Category();
		//c1.setCategoryKey(Integer.parseInt(categoryKeyTxt.getText()));
		// Categories should be uppercase
		c1.setCategoryName(categoryNameTxt.getText().toUpperCase());
		message = categoryRepo.add(c1); //TODO
        messageTextArea.setText(message); //TODO
        //update table
        updateTable();
        categoryTable.refresh();
    }
    
    @FXML
    void updateCategory(ActionEvent event) {
    	System.out.println("updateCategory called");
    	Category c1 = new Category();
    	String message = null;
    	//Text fields should not be empty
    	if(!(categoryNameTxt.getText().length() > 0)) {
        	message = "Fyll i nytt namn för den kategori som ska uppdateras.";
        	messageTextArea.setText(message);
            return;
        }
    	if(!(categoryKeyTxt.getText().length() > 0)) {
        	message = "Fyll i id för den kategori som ska uppdateras";
        	messageTextArea.setText(message);
            return;
        }
   	 	boolean isInteger = CommonUtil.isInteger(categoryKeyTxt.getText());
   	 	if (!isInteger) {
	   		message = "Id måste vara en siffra.\nFyll i en siffra eller välj kategori från tabellen.";
	       	messageTextArea.setText(message);
           return;
   	 	}
    	int categoryKeySelection = Integer.parseInt(categoryKeyTxt.getText());
    	boolean categoryExists = checkCategoryExistance(categoryKeySelection);
    	if (!categoryExists) {
			message = "Det finns ingen kategori med det id:t. \nVälj kategori för uppdatering i tabellen.";
			messageTextArea.setText(message);
            return;
    	}
		else {
	    	c1.setCategoryKey(categoryKeySelection);
			c1.setCategoryName(categoryNameTxt.getText().toUpperCase());
	    	message = categoryRepo.update(c1);
	        messageTextArea.setText(message);
	    	updateTable();
	    	categoryTable.refresh();
		}
    }
    
    
    @FXML
    void deleteCategory(ActionEvent event) {
    	System.out.println("deleteCategory called");
    	Category cat = new Category();
    	String message = null;
    	if(!(categoryKeyTxt.getText().length() > 0)) {
        	message = "Fyll i id för den kategori som ska tas bort, \neller välj kategori i tabellen.";
        	messageTextArea.setText(message);
            return;
        }
   	 	boolean isInteger = CommonUtil.isInteger(categoryKeyTxt.getText());
   	 	if (!isInteger) {
	   		message = "Id måste vara en siffra.\nFyll i en siffra eller välj kategori från tabellen.";
	       	messageTextArea.setText(message);
           return;
   	 	}
    	int categoryKeySelection = Integer.parseInt(categoryKeyTxt.getText());
    	boolean categoryExists = checkCategoryExistance(categoryKeySelection);
    	if (!categoryExists) {
			message = "Det finns ingen kategori med det id:t. \nVälj kategori i tabellen.";
			messageTextArea.setText(message);
            return;
    	}
		else {
    	cat.setCategoryKey(categoryKeySelection);
    	cat.setCategoryName(categoryNameTxt.getText().toUpperCase());
        message = categoryRepo.delete(cat);
        messageTextArea.setText(message); //TODO
        //update table
        updateTable();
		}
    }

    /**
	 * The check category existance method.
	 *
	 * @param catKey the category Key
	 * @return the boolean
	 */
	private boolean checkCategoryExistance(int catKey) {
		ArrayList<Category> allCategories = categoryRepo.getAllCategories();
		boolean categoryExists = false;
		for (Category cat : allCategories) {
			if (cat.getCategoryKey() == catKey) {
				categoryExists = true;
				break;
			}
		}
		return categoryExists;
	}

}
