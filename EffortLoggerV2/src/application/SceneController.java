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
import java.net.URL;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	private static String supervisorUsername;
	private static Boolean loggedIn = false;
	private String selectedItem;
	private int scoreCount = 0;
	private int totalScore = 0;
	
	// TEXT FIELDS
	@FXML TextField usernameLoginTextField;
	@FXML TextField passwordLoginTextField;
	@FXML TextField usernameRegisterTextField;
	@FXML TextField passwordRegisterTextField;
	@FXML TextField supervisorCodeTextField;
	@FXML TextField createUserStoryTitleField;
	@FXML TextField itemScoreTextField;
	@FXML TextField newUserStoryItemTitle;
	// TEXT AREAS
	@FXML TextArea createUserStoryDescriptionTestArea;
	@FXML TextArea newUserStoryItemDescription;
	@FXML ListView recentUserStoriesList;
	@FXML ListView userStoryDataListView;
	@FXML Label usernameLabel;
	@FXML Label supervisorLabel;
	// CHOICE BOXES
	@FXML ChoiceBox<String> userStoryChoiceBox = new ChoiceBox<>();
	@FXML ChoiceBox<String> userStoryItemChoiceBox = new ChoiceBox<>();
	@FXML ObservableList<String> userStoryChoices;
	// BUTTONS
	@FXML private Button logoutButton;
	@FXML private Button confirmScoreButton = new Button();
	@FXML private AnchorPane userPagePane;
	
	// TEST VARIABLES
	private static String testUsername = "testuser";
	private static String testPassword = "testpass";
	private static String superTestUsername = "superuser";
	private static String superTestPassword = "superpass";
	private static String supervisorTestCode = "1234";
	private String[] testData = {"User Story 1", "User Story 2", "User Story 3"};
	private String[] testItems1 = {"Story 1 Item 1", "Story 1 Item 2", "Story 1 Item 3"};
	private String[] testItems2 = {"Story 2 Item 1", "Story 2 Item 2", "Story 2 Item 3"};
	private String[] testItems3 = {"Story 3 Item 1", "Story 3 Item 2", "Story 3 Item 3"};

	
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
		private static ArrayList<UserStory> stories = new ArrayList<UserStory>();
		
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
	
	// GOES TO USER STORIES PAGE
	public void switchToUserStoriesPage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/UserStoriesPage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// GOES TO PLANNING POKER PAGE
	public void switchToPlanningPokerPage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/PlanningPokerPage.fxml"));
		
		// ADD ALL USER STORIES TO THE USER STORY CHOICE BOX ON PLANNING POKER PAGE
		/*for( int i = 0; i < stories.size(); i++) {
			//userStoryChoiceBox.getItems().add(stories.get(i).getTitle());
			userStoryChoiceBox.getItems().add("Hello");
		}*/
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// GOES TO CREATE USER STORY PAGE
	public void switchToCreateUserStoryItemPage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/CreateUserStoryItemPage.fxml"));
		
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
	
	// PRINTS EXISTING USER STORIES
	public void printStories(ArrayList<UserStory> storyList) {
		System.out.print("Data Log Content:\n");
		for(int i = 0; i < storyList.size(); i++) {
			System.out.println(storyList.get(i).getDate() + " " + storyList.get(i).getTitle() + "; " + storyList.get(i).getDescription());
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
	
	// CREATES A USER STORY WITH DATE, TITLE, AND DESCRIPTION FIELDS
	public void createUserStory(ActionEvent event) {
		
		String title = createUserStoryTitleField.getText();
		String description = createUserStoryDescriptionTestArea.getText();
		ArrayList<UserStoryItem> userStoryItem = new ArrayList<UserStoryItem>();
		
		UserStory newStory = new UserStory(getDate(), title, description, userStoryItem);
		
		if (stories.size() != 0) {
				
			for(int i = 0; i < stories.size(); i++) {
				
				if ( !stories.get(i).getTitle().equals(title) ) {
					stories.add(newStory);
					recentUserStoriesList.getItems().add(stories.get(i).getDate() + " " + stories.get(i).getTitle() + "; " + stories.get(i).getDescription());
					printStories(stories);
				}
				else {
					System.out.println("User Story already exists!\n");
					break;
				}
			}

		} 
		else {
			stories.add(newStory);
			recentUserStoriesList.getItems().add(stories.get(0).getDate() + " " + stories.get(0).getTitle() + "; " + stories.get(0).getDescription());
			printStories(stories);
		}
				
	}
	
	// CREATES A NEW USER STORY ITEM
	public void createUserStoryItem(ActionEvent event) throws IOException {
		
		// GET UserStory object from choice box in Planning poker page
		
		//UserStory selectedUserStory = userStoryChoiceBox.getItems();
		// UserStory userStory = effortData.get(index in choice box if possible);
		String itemTitle = newUserStoryItemTitle.getText();
		String itemDesc = newUserStoryItemDescription.getText();
		
		UserStoryItem newItem = new UserStoryItem(itemTitle, itemDesc, 0);
		//selectedUserStory.userStoryItems.add(newItem);
		
		switchToPlanningPokerPage(event);

	}
	
	// GETS DATE IN FORMAT (YYYY-MM-DD)
 	public String getDate() {
		
		String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
				
		return date;
	}
 	
 	// CHANGES USER STORY POINT VALUE TO 0
 	public void change0() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("0");
 	}
 	
 	// CHANGES USER STORY POINT VALUE TO 1
 	public void change1() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("1");
 	}
 	
 	// CHANGES USER STORY POINT VALUE TO 2
 	public void change2() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("2");
 	}
 	
 	// CHANGES USER STORY POINT VALUE TO 3
 	public void change3() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("3");
 	}
 	
 	// CHANGES USER STORY POINT VALUE TO 4
 	public void change4() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("4");
 	}
 	
 	public String getSelectedItem() {
 		return selectedItem;
 	}
 	
 	public void setSelectedItem(String selectedItem) {
 		this.selectedItem = selectedItem;
 	}
 	
 	public int getScoreCount() {
 		return scoreCount;
 	}
 	
 	public void setScoreCount(int scoreCount) {
 		this.scoreCount = scoreCount;
 	}
 	
 	public int getTotalScore() {
 		return totalScore;
 	}
 	
 	public void setTotalScore(int totalScore) {
 		this.totalScore = totalScore;
 	}
 	
 	// CONFIRM SCORE BUTTON IS CLICKED
 	public void confirmScore(ActionEvent event) {
 		 		
 		int score = 0;
 		int totalScore = getTotalScore();
 		int scoreCount = getScoreCount(); 
 		
 		if ( itemScoreTextField == null) {
 			System.out.println("Item score field is empty.");
 		} else {
	 		// GET USER STORY ITEM FROM CHOICE BOX ON PLANNING POKER PAGE
	 		//UserStoryItem item;
	 		//int score = Integer.parseInt(itemScoreTextField.getText());
 			
 			String selectedItem = getSelectedItem();
 			
	 		if (itemScoreTextField.getText().equals("0") && selectedItem.equals("Story 1 Item 1") )
	 		{
	 			score = 0;
	 			setTotalScore(totalScore + score);
	 			setScoreCount(scoreCount + 1);
	 		
				userStoryDataListView.getItems().add("Item: " + selectedItem + " - " + score);
	 		}
	 		if (itemScoreTextField.getText().equals("1"))
	 		{
	 			score = 1;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - " + score);

	 		}
	 		if (itemScoreTextField.getText().equals("2"))
	 		{
	 			score = 2;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - " + score);

	 		}
	 		if (itemScoreTextField.getText().equals("3"))
	 		{
	 			score = 3;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - " + score);

	 		}
	 		if (itemScoreTextField.getText().equals("4"))
	 		{
	 			score = 4;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - " + score);

	 		}
	 		
	 		int average = getTotalScore() / getScoreCount();
	 		
			userStoryDataListView.getItems().add("Points Average: " + average);

	 		//item.setScore(score);
 		}
 		
 	}
 		
	// QUITS THE PROGRAM
	public void exit(ActionEvent event) {
		
		stage = (Stage) userPagePane.getScene().getWindow();
		System.out.println("\nYou have successfully exited the program!");
		stage.close();
		
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		userStoryChoiceBox.getItems().addAll(testData);
		userStoryChoiceBox.setOnAction(this::getData);

		userStoryItemChoiceBox.setOnAction(this::getItemData);
		confirmScoreButton.setOnAction(this::confirmScore);
		//userStoryChoiceBox.getSelectionModel().selectLast();
		
		//userStoryChoiceBox.getItems().add(stories.get(0).getTitle());

		//userStoryChoiceBox.getItems().add("Hello");
	}
	
	private void getData(ActionEvent event) {
		String selectedStory = userStoryChoiceBox.getValue();
		String selectedItem = userStoryItemChoiceBox.getValue();
		
		if (selectedStory == "User Story 1") {
			// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
			userStoryItemChoiceBox.getItems().addAll(testItems1);
			userStoryDataListView.getItems().add("Story: " + selectedStory);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedStory == "User Story 2") {
			userStoryItemChoiceBox.getItems().addAll(testItems2);
			userStoryDataListView.getItems().add("Story: " + selectedStory);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedStory == "User Story 3") {
			userStoryItemChoiceBox.getItems().addAll(testItems3);
			userStoryDataListView.getItems().add("Story: " + selectedStory);

		}
	}
	
	private void getItemData(ActionEvent event) {
		String selectedItem = userStoryItemChoiceBox.getValue();
		//private String[] testItems1 = {"Story 1 Item 1", "Story 1 Item 2", "Story 1 Item 3"};
		//private String[] testItems2 = {"Story 2 Item 1", "Story 2 Item 2", "Story 2 Item 3"};
		//private String[] testItems3 = {"Story 3 Item 1", "Story 3 Item 2", "Story 3 Item 3"};
		if (selectedItem == "Story 1 Item 1") {
			setSelectedItem("Story 1 Item 1");
			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedItem == "Story 1 Item 2") {
			setSelectedItem("Story 1 Item 2");
			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedItem == "Story 1 Item 3") {
			setSelectedItem("Story 1 Item 3");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		
		
		if (selectedItem == "Story 2 Item 1") {
			setSelectedItem("Story 2 Item 1");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedItem == "Story 2 Item 2") {
			setSelectedItem("Story 2 Item 2");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedItem == "Story 2 Item 3") {
			setSelectedItem("Story 2 Item 3");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		
		
		if (selectedItem == "Story 3 Item 1") {
			setSelectedItem("Story 3 Item 1");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedItem == "Story 3 Item 2") {
			setSelectedItem("Story 3 Item 2");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);

		}
		// POPULATE ITEM CHOICE BOX AND ADD STORY TO LIST VIEW
		if (selectedItem == "Story 3 Item 3") {
			setSelectedItem("Story 3 Item 3");

			//userStoryDataListView.getItems().add("Item: " + selectedItem);
		}
	}
	
	
}
