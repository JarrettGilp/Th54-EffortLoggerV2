// Made by: Jonathan De La O

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoleManagementController {
    @FXML
    private TextField userIDField;
    @FXML
    private TextField roleField;
    
    @FXML public Button close;

    // This method is invoked when the "Save" button is clicked in the Role Management window.
    @FXML
    private void saveRole(ActionEvent event) throws IOException {
        // Get the user's input from the fields
        String userID = userIDField.getText();
        String role = roleField.getText();

        // Perform validation and save the role
        if (isValidUserID(userID) && isValidRole(role)) {
            // Logic to save the role
            System.out.println("UserID: " + userID + ", Role: " + role + " saved.");

        } else {
            // Handle validation errors or show a message to the user
            System.out.println("Validation failed. Please enter valid UserID and Role.");
        }
    }
    
    @FXML
    private void close(ActionEvent event) {
    	Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    // Validation methods.
    private boolean isValidUserID(String userID) {
        // Implementation of UserID validation logic
        return !userID.isEmpty();
    }

    private boolean isValidRole(String role) {
        // Implementation of role validation logic
        return !role.isEmpty();
    }
}
