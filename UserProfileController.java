package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UserProfileController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;

    @FXML
    private void saveChanges(ActionEvent event) {
        // Get the user's input from the fields
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();

        // Perform validation and save changes to the user's profile
        if (isValidName(name) && isValidEmail(email) && isValidAddress(address) && isValidPhone(phone)) {
            // Update the user's profile with the new values
            // Logic to save the changes
            System.out.println("Changes saved: Name=" + name + ", Email=" + email + ", Address=" + address + ", Phone=" + phone);
        } else {
            // Handle validation errors or show a message to the user
            System.out.println("Validation failed. Please enter valid information.");
        }
    }

    @FXML
    private void navigateToRoleManagement(ActionEvent event) {
        try {
            // Load the Role Management window FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RoleManagement.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Role Management window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Role Management");

            // Show the Role Management window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle loading error
        }
    }

    // Validation methods.
    private boolean isValidName(String name) {
        // Implementation of name validation logic
        return !name.isEmpty();
    }

    private boolean isValidEmail(String email) {
        // Implementation of email validation
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isValidAddress(String address) {
        // Implementation of address validation logic
        return !address.isEmpty();
    }

    private boolean isValidPhone(String phone) {
        // Implementation of phone validation logic
        return !phone.isEmpty();
    }
}
