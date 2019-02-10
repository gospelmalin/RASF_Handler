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
        alert.setHeaderText("RASF Handler - Håll koll på dina matvaror på ett smart sätt - en app av Malin");
        alert.setContentText("En JavaFX-applikation för att visa och uppdatera data om matvaror och deras förvaring gjord av Malin");
        alert.show();
    }
}
