package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * The Class RootLayoutController.
 */
public class RootLayoutController {
	
	 /**
 	 * Handle exit.
 	 *
 	 * @param actionEvent the action event
 	 */
 	//Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
 
    /**
     * Handle help.
     *
     * @param actionEvent the action event
     */
    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("RASF Handler - H�ll koll p� dina matvaror p� ett smart s�tt - en app av Malin");
        alert.setContentText("En JavaFX-applikation f�r att visa och uppdatera data om matvaror och deras f�rvaring gjord av Malin");
        alert.show();
    }
}
