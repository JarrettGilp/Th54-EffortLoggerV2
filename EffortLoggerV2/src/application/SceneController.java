// --------------------------
// Made by: Jarrett Gilpatric
// ASU ID: 1216797582
// --------------------------

/* -------USAGE NOTES--------
 * 
 *  USER NEEDS TO REGISTER A LOGIN AND PASSWORD BEFORE BEING ABLE TO LOGIN TO AN ACCOUNT
 *  -- UNLESS THEY KNOW THE USERNAME AND PASSWORD USED FOR TESTING FOR EITHER REGULAR USERS OR SUPERVISORS
 * 
 */
 

package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
	private static String supervisorUsername;
	private static Boolean loggedIn = false;
	
	// LOGIN VARIABLES
	@FXML
	TextField usernameLoginTextField;
	@FXML
	TextField passwordLoginTextField;
	
	// REGISTRATION VARIABLES AND PAGE ELEMENTS
	@FXML
	TextField usernameRegisterTextField;
	@FXML
	TextField passwordRegisterTextField;
	@FXML
	TextField supervisorCodeTextField;
	@FXML
	Label usernameLabel;
	@FXML
	Label supervisorLabel;
	@FXML
	private Button logoutButton;
	@FXML
	private AnchorPane userPagePane;
	
	// TEST VARIABLES
	private static String testUsername = "testuser";
	private static String testPassword = "testpass";
	private static String superTestUsername = "superuser";
	private static String superTestPassword = "superpass";
	private static String supervisorTestCode = "1234";
	
	// DATA STRUCTURES
		private static ArrayList<String> userList = new ArrayList<String>(100) {{
			add(testUsername);
			add(testPassword);
		}};
		private static ArrayList<String> supervisorList = new ArrayList<String>(100) {{
			add(superTestUsername);
			add(superTestPassword);
		}};
		private static ArrayList<String> superCodes = new ArrayList<String>(20) {{ 
			add(supervisorTestCode);
		}};
		private static String buffer[] = new String[] {"0", "0"};
		
	// WHEN LOGIN BUTTON IS CLICKED - CHECK IF USERNAME AND PASSWORD COMBO EXISTS
	public void authentication(ActionEvent event) throws IOException {
		
		String username = usernameLoginTextField.getText();
		String password = passwordLoginTextField.getText();
		
		// CHECK IF USER NAME AND PASSWORD CREDENTIALS ARE WITHIN USERLIST OR SUPERVISORLIST
		for( int i = 0; i <= userList.size() - 2; i+=2) {
			
			System.out.println("\nTEST:\n");
			System.out.println("UserList size is: ");
			System.out.println(userList.size());
			System.out.println(userList.get(0));
			System.out.println(userList.get(1));
			
			if ( (username.equals(userList.get(i)) && password.equals(userList.get(i + 1))) )
			{
				System.out.println("User info was seen.");
				loggedIn = true;
				userLogin(event);
				
			}
		}
		
		for( int j = 0; j <= supervisorList.size() - 2; j+=2) {
			
			if ( (username.equals(supervisorList.get(j)) && password.equals(supervisorList.get(j + 1))) )
			{
				System.out.println("User info was seen.");
				loggedIn = true;
				setSuperUsername(username);
				supervisorLogin(event);
			}
		}
		
		if ( loggedIn == false ){
			System.out.println("\nInvalid username or password.");
		}
			
	}
	
	// GOES TO LOGIN PAGE (HOME PAGE)
	public void switchToHomePage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/EffortLoggerLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// GOES TO USER PAGE
	public void switchToUserPage(ActionEvent event) throws IOException {
				
		Parent root = FXMLLoader.load(getClass().getResource("/UserPage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// GOES TO SUPERVISOR PAGE
	public void switchToSupervisorPage(ActionEvent event) throws IOException {
		
		//Parent root = FXMLLoader.load(getClass().getResource("/SupervisorPage.fxml"));
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorPage.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// GOES TO REGISTRATION PAGE
	public void switchToRegisterPage(ActionEvent event) throws IOException {
					
			Parent root = FXMLLoader.load(getClass().getResource("/RegisterPage.fxml"));
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
	}
	
	// GOES TO SUPERVISOR CODE PROMPT PAGE
	public void switchToSupervisorCodePage(ActionEvent event) throws IOException {
		
		//Parent root = FXMLLoader.load(getClass().getResource("/SupervisorCodePage.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorCodePage.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// DISPLAYS NAME ON USER PAGE AFTER USER LOGS IN
	public void displayName(String username) {
		
		usernameLabel.setText("Welcome User: " + username);
		
	}
	
	// DISPLAYS NAME ON SUPERVISOR PAGE AFTER SUPERVISOR LOGS IN
	public void displaySupervisorName(String username) {
		
		supervisorLabel.setText("Welcome Supervisor: " + username);
		
	}
	
	// PRINTS ELEMENTS OF A SPECIFIED LIST
	public void printList(ArrayList<String> list) {
		System.out.println("List items: ");
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i));
		}
		System.out.println();
	}
	
	// PRINTS ELEMENTS OF A SPECIFIED ARRAY
	public void printArray(String[] arr) {
		System.out.println("Array Items: ");
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	// ADD ELEMENTS TO A SPECIFIED LIST
	public void listAdd(ArrayList<String> list, String username, String password)
	{
		list.add(username);
		list.add(password);
	}
	
	// SETTER FOR SUPERVISOR USERNAME - USED FOR LABEL
	public void setSuperUsername(String username) {
		this.supervisorUsername = username;
	}
	
	// GETTER FOR SUPERVISOR USERNAME
	public String getSuperUsername() {
		return supervisorUsername;
	}
	
	// OCCURS AFTER CLICKING REGISTER BUTTON ON LOGIN PAGE
	public void registerButton(ActionEvent event) throws IOException {
		
		switchToRegisterPage(event);
		System.out.println("\nYou have switched to the register page!");
		
	}
	
	// REGISTERS LOGIN INFORMATION FOR A USER
	public void registerUserLogin(ActionEvent event) throws IOException {
	
		// ADD USERNAME AND PASSWORD TO ARRAYLIST THAT CONTAINS LOGIN INFO OF USERS
		String username = usernameRegisterTextField.getText();
		String password = passwordRegisterTextField.getText();
		
		// PRINTS OUT FOR TESTING
		listAdd(userList, username, password);
		System.out.print("\nUserList - ");
		printList(userList);
		
		System.out.println("\nYou have successfully registered your login information!\n");
		
		// LOGS IN USER AFTER REGISTRATION
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
		root = loader.load();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(username);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// OCCURS AFTER CLICKING REGISTER AS SUPERVISOR BUTTON
	public void registerSupervisorLogin(ActionEvent event) throws IOException {
		//ADD USERNAME AND PASSWORD TO ARRAYLIST THAT CONTAINS LOGIN INFO OF USERS
		String username = usernameRegisterTextField.getText();
		String password = passwordRegisterTextField.getText();
		
		// PRINTING OUT FOR TESTING
		System.out.println("\nLogin info has been added to the buffer!\n");
		buffer[0] = username;
		buffer[1] = password;
		System.out.print("\nBuffer - ");
		printArray(buffer);
				
		//switchToSupervisorCodePage(event);
		setSuperUsername(username);
				
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorCodePage.fxml"));
		root = loader.load();
				
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("\nYou have switched to supervisor code page.");
	}
	
	// LOGS USER IN
	public void userLogin(ActionEvent event) throws IOException {
		
		// CAPTURES USERNAME ENTRY FIELD FOR DISPLAYING IN USER PAGE
		String username = usernameLoginTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
		root = loader.load();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(username);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("\nYou have successfully logged in!");
		
	}
	
	// LOGS SUPERVISOR IN
	public void supervisorLogin(ActionEvent event) throws IOException {
		
		String user = getSuperUsername();
		
		// IF SUPERVISOR IS LOGGING IN REGULARLY
		if ( this.supervisorCodeTextField == null ) {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorPage.fxml"));
			root = loader.load();
			System.out.println("\nYou have successfully logged in as supervisor!");	
			
			SceneController sceneController = loader.getController();
			sceneController.displaySupervisorName(user);
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		// LOGS SUPERVISOR IN IF THEY PROVIDE A CORRECT SUPERVISOR CODE IN REGISTRATION
		else {
			// CAPTURES CODE ENTERED BY USER
			String code = supervisorCodeTextField.getText();
			
			// IF THE SUPERVISOR CODE IS A CORRECT ONE, LOG SUPERVISOR IN AFTER REGISTERING
			for (int i = 0; i < superCodes.size(); i++) {
				
			  	if( code.equals(superCodes.get(i)) ) {
			  	// ADDS SUPERVISOR LOGIN INFO TO SUPERVISOR LIST
					listAdd(supervisorList, buffer[0], buffer[1]);
					
					// PRINTING FOR TESTING
					System.out.print("\nSupervisorList - ");
					printList(supervisorList);
					
					// EMPTY BUFFER ARRAY
					buffer[0] = "0";
					buffer[1] = "0";
					
					// PRINTING FOR TESTING
					System.out.print("\nBuffer - ");
					printArray(buffer);
					
					// LOGGED IN TO SUPERVISOR PAGE
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorPage.fxml"));
					root = loader.load();
					System.out.println("\nYou have successfully logged in as supervisor!");
					
					SceneController sceneController = loader.getController();
					sceneController.displaySupervisorName(user);
					
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();	
			 
			  	} // IF CODE WAS NOT A CORRECT CODE
				else {
					// EMPTY BUFFER ARRAY
					buffer[0] = "0";
					buffer[1] = "0";
					System.out.println("\nThe supervisor code entered does not exist!");	
					
					break;
				}
			}		
		} 	
	}
	
	// LOGS USER OUT
	public void logout(ActionEvent event) throws IOException {
		
		// LOGS OUT USER TO LOGIN PAGE
		switchToHomePage(event);
		System.out.println("\nYou have successfully logged out!\n");
		
		// CONTROL VARIABLES SET BACK TO DEFAULT STATES
		setSuperUsername("");
		loggedIn = false;
		
		// PRINTING OUT FOR TESTING
		System.out.println("UserList - ");
		printList(userList);
		System.out.println("SupervisorList - ");
		printList(supervisorList);
		System.out.println("Buffer - ");
		printArray(buffer);
		
	}
	
	// QUITS THE PROGRAM
	public void exit(ActionEvent event) {
		
		stage = (Stage) userPagePane.getScene().getWindow();
		System.out.println("\nYou have successfully exited the program!");
		stage.close();
		
	}
	
}
