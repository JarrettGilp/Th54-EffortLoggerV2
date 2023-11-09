// --------------------------
// Made by: Jarrett Gilpatric
// ASU ID: 1216797582
// --------------------------

package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private static ArrayList<String> userList = new ArrayList<String>(2);
	private static ArrayList<String> supervisorList = new ArrayList<String>(2);
	
	// FXML FIELDS
	@FXML
	TextField usernameLoginTextField;
	@FXML
	TextField passwordLoginTextField;
	@FXML
	TextField usernameRegisterTextField;
	@FXML
	TextField passwordRegisterTextField;
	@FXML
	TextField supervisorCodeTextField;
	@FXML
	Label usernameLabel;
	@FXML
	private Button logoutButton;
	@FXML
	private AnchorPane userPagePane;
	
	// TESTING VARIABLES
	private String testUsername = "testuser";
	private String testPassword = "testpass";
	private String superTestUsername = "supertest";
	private String superTestPassword = "superpass";
	private String supervisorTestCode = "1234";
	
	
	// WHEN LOGIN BUTTON IS CLICKED - CHECK IF USERNAME AND PASSWORD COMBO EXISTS
	public void authentication(ActionEvent event) throws IOException {
		
		String username = usernameLoginTextField.getText();
		String password = passwordLoginTextField.getText();
		
		// CHECK CREDENTIALS
		if ( (testUsername.equals(username) && (testPassword.equals(password))) || (username.equals(userList.get(0)) && password.equals(userList.get(1))) )
		{
			userLogin(event);
		}
		else if ( (superTestUsername.equals(username) && (superTestPassword.equals(password))) || (username.equals(supervisorList.get(0)) && password.equals(supervisorList.get(1))) )
		{
			supervisorLogin(event);
		}
		else
		{
			System.out.println("Invalid username or password.");
		}
		
	}
	
	// Goes to Login Page
	public void switchToHomePage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/EffortLoggerLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// Goes to the User's Page
	public void switchToUserPage(ActionEvent event) throws IOException {
				
		Parent root = FXMLLoader.load(getClass().getResource("/UserPage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchToSupervisorPage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/SupervisorPage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// Goes to the Register Page
	public void switchToRegisterPage(ActionEvent event) throws IOException {
					
			Parent root = FXMLLoader.load(getClass().getResource("/RegisterPage.fxml"));
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
	}
	
	public void switchToSupervisorCodePage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/SupervisorCodePage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// Displays name after logging in at top of screen
	public void displayName(String username) {
		
		usernameLabel.setText("Welcome User: " + username);
		
	}
	
	public void displaySupervisorName(String username) {
		
		usernameLabel.setText("Welcome Supervisor: " + username);
		
	}
	
	public void listAdd(ArrayList<String> list, String username, String password)
	{
		list.add(username);
		list.add(password);
		
		System.out.println("\nList items: ");
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
		System.out.println();
		
	}
	
	public void registerButton(ActionEvent event) throws IOException {
		
		switchToRegisterPage(event);
		System.out.println("You have switched to the register page!");
		
	}
	
	public void registerUserLogin(ActionEvent event) throws IOException {
	
		//ADD USERNAME AND PASSWORD TO ARRAYLIST THAT CONTAINS LOGIN INFO OF USERS
		String username = usernameRegisterTextField.getText();
		String password = passwordRegisterTextField.getText();
		
		listAdd(userList, username, password);
		
		System.out.println("You have successfully registered your login information!\n");
		
		// Login after Registering
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
		root = loader.load();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(username);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void registerSupervisor(ActionEvent event) throws IOException {
		
		//ADD USERNAME AND PASSWORD TO ARRAYLIST THAT CONTAINS LOGIN INFO OF USERS
				String username = usernameRegisterTextField.getText();
				String password = passwordRegisterTextField.getText();
				
				listAdd(supervisorList, username, password);
				
				System.out.println("You have successfully registered your supervisor login information!\n");
				
				// Login after Registering
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorPage.fxml"));
				root = loader.load();
				
				SceneController sceneController = loader.getController();
				sceneController.displaySupervisorName(username);
				
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
		
	}
	
	// Logs user in
	public void userLogin(ActionEvent event) throws IOException {
		
		String username = usernameLoginTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
		root = loader.load();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(username);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("You have successfully logged in!");
		
	}
	
	public void supervisorLogin(ActionEvent event) throws IOException {
		
		String username = usernameLoginTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorPage.fxml"));
		root = loader.load();
		
		SceneController sceneController = loader.getController();
		sceneController.displaySupervisorName(username);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("You have successfully logged in as supervisor!");		
		
	}
	
	// Logs user out / Switches to home page
	public void logout(ActionEvent event) throws IOException {
		
		switchToHomePage(event);
		System.out.println("You have successfully logged out!");
		
	}
	
	// Quits the program
	public void exit(ActionEvent event) {
		
		stage = (Stage) userPagePane.getScene().getWindow();
		System.out.println("You have successfully exited the program!");
		stage.close();
		
	}
	
	
}
