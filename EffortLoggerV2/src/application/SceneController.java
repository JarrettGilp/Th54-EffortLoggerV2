// --------------------------
// Made by: Jarrett Gilpatric
// ASU ID: 1216797582
// --------------------------
// KEY FUNCTIONS WORKED ON: 
// - PLANNING POKER AND USER STORY FUNCTIONALITY
// - LOGIN / REGISTRATION FUNCTIONALITY
//
/* -------USAGE NOTES--------
 * 
 *  FUNCTIONALITY CAN BE TESTED IN CONSOLE OUTPUT WITH REF. TO WHAT IS CURRENTLY WITHIN EACH DATA STRUCTURE
 * 
 *  USER NEEDS TO REGISTER A LOGIN AND PASSWORD BEFORE BEING ABLE TO LOGIN TO AN ACCOUNT
 *  -- UNLESS THEY KNOW THE USERNAME AND PASSWORD USED FOR TESTING FOR EITHER REGULAR USERS OR SUPERVISORS (USERNAME: testuser and PASSWORD: testpass)
 *  
 *  USER CAN NAVIGATE TO USER STORIES PAGE AND PLANNING POKER PAGE
 *  -- USER STORIES PAGE: USER CAN CREATE A NEW USER STORY AND VIEW ALREADY CREATED STORIES
 *  -- PLANNING POKER PAGE: USER CAN CHOOSE A USER STORY THEN CREATE/SELECT AN ITEM ASSOCIATED WITH SAID STORY.
 *  		USER CAN VOTE ON THE ITEM SELECTED THROUGH BUTTONS LABELED 0 - 4. 0 BEING LESS RELEVANT TO THE STORY
 *  		AND 4 HAVING HIGH RELEVANCE TO THE STORY.
 * 
 */

//

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

	// SCENE VARIABLES
	private Stage stage;
	private Scene scene;
	private Parent root;
	// STATES
	private static Boolean loggedIn = false;
	private static Boolean superLoggedIn = false;
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
	@FXML TextArea userStoryItemDescription;
	// LABELS
	@FXML Label usernameLabel;
	@FXML Label supervisorLabel;
	// CHOICE BOXES
	@FXML ChoiceBox<String> userStoryChoiceBox = new ChoiceBox<>();
	@FXML ChoiceBox<String> userStoryItemChoiceBox = new ChoiceBox<>();
	// LIST VIEW AND OBSERVABLE LISTS
	@FXML ListView<String> recentUserStoriesList = new ListView<String>();
	@FXML ListView<String> userStoryDataListView;
	// BUTTONS AND PANES
	@FXML private Button logoutButton;
	@FXML private Button confirmScoreButton = new Button();
	@FXML private Button createUserStoryButton = new Button();
	@FXML private Button userStoriesButton = new Button();
	@FXML private AnchorPane userPagePane;
	// TEST VARIABLES
	private static String supervisorUsername;
	private static String capUsername;
	private static String testUsername = "testuser";
	private static String testPassword = "testpass";
	private static String superTestUsername = "superuser";
	private static String superTestPassword = "superpass";
	private static String supervisorTestCode = "1234";
	// PLANNING POKER VARIABLES
	private static String selectedItem;
	private int scoreCount = 0;
	private int totalScore = 0;
	private static ArrayList<String> choices = new ArrayList<String>();
	private static ArrayList<String> itemChoices = new ArrayList<String>();
	public static ArrayList<String> obsItemArrList = new ArrayList<String>();
	public static ArrayList<ObservableList<String>> itemListList = new ArrayList<ObservableList<String>>();
	
	// might not need
	@FXML ObservableList<String> userStoryChoices = FXCollections.observableArrayList();
	
	@FXML ObservableList<String> storyList = FXCollections.observableArrayList(choices);
	@FXML ObservableList<String> itemList = FXCollections.observableArrayList(itemChoices);
	@FXML ObservableList<String> obsItemsList = FXCollections.observableArrayList(obsItemArrList);
	
	private UserStory tempStory;
	private static String storyBoxValue;
	private static int userStoryID = 0;
	
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
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/EffortLoggerLogin.fxml"));
		root = loader.load();
		//Parent root = FXMLLoader.load(getClass().getResource("/EffortLoggerLogin.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// GOES TO USER PAGE
	public void switchToUserPage(ActionEvent event) throws IOException {
			
		//Parent root = FXMLLoader.load(getClass().getResource("/UserPage.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		/*
		String cappedUserName = getUsername();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(cappedUserName);
		*/
		
		if ( superLoggedIn == true ) {
			String cappedUserName = getSuperUsername();
			
			SceneController sceneController = loader.getController();
			sceneController.displaySupervisorName(cappedUserName);
		} else {
			String cappedUserName = getUsername();
			
			SceneController sceneController = loader.getController();
			sceneController.displayName(cappedUserName);
		}
	}
	
	// GOES TO SUPERVISOR PAGE
	public void switchToSupervisorPage(ActionEvent event) throws IOException {
		
		//Parent root = FXMLLoader.load(getClass().getResource("/SupervisorPage.fxml"));	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
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
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/UserStoriesPage.fxml"));
		Parent root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	// GOES TO PLANNING POKER PAGE
	public void switchToPlanningPokerPage(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/PlanningPokerPage.fxml"));
		Parent root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	// GOES TO CREATE USER STORY PAGE
	public void switchToCreateUserStoryItemPage(ActionEvent event) throws IOException {
		
		// CAPTURE SELECTED USER STORY
		String selectedStory = userStoryChoiceBox.getValue();		
		setStoryBoxValue(selectedStory);

		// IF A USER STORY IS NOT SELECTED
		if( userStoryChoiceBox.getValue().equals("-- Select a Story --") ) {
			System.out.println("\nNo User story is selected.");
		} // IF A USER STORY IS SELECTED
		else {
			// IF THE USERSTORY IN CHOICEBOX IS SELECTED, FIND THE STORY WITH THE SAME VALUE IN THE CHOICEBOX IN 'STORIES' AND SET THAT STORY TO A DATA STRUCTURE 'tempStory' SO IT CAN HAVE THE NEW ITEM ADDED TO IT
			System.out.println(userStoryChoiceBox.getValue());
			for(int i = 0; i < stories.size(); i++) {
				if (userStoryChoiceBox.getValue().equals(stories.get(i).getTitle())) {
					setTemp(stories.get(i));
				}
			}
			
			// LOAD CREATE USER STORY ITEM PAGE
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/CreateUserStoryItemPage.fxml"));
			Parent root = loader.load();
			
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	// DISPLAYS NAME ON USER PAGE AFTER USER LOGS IN
	public void displayName(String username) {
		
		usernameLabel.setText("Welcome User: " + username);
		
	}
	
	// DISPLAYS NAME ON SUPERVISOR PAGE AFTER SUPERVISOR LOGS IN
	public void displaySupervisorName(String username) {
		
		usernameLabel.setText("Welcome Supervisor: " + username);
		//supervisorLabel.setText("Welcome Supervisor: " + username);
		
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
	
	// SET USERNAME FOR LABEL ON USER PAGE
	public void setUsername(String capUsername) {
		this.capUsername = capUsername;
	}
	
	// GET USERNAME FOR LABEL ON USER PAGE
	public String getUsername() {
		return capUsername;
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
		
		setUsername(username);
		String cappedUserName = getUsername();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(cappedUserName);
		
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
		
		setUsername(username);
		String cappedUserName = getUsername();
		
		SceneController sceneController = loader.getController();
		sceneController.displayName(cappedUserName);
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("\nYou have successfully logged in!");
		
		
	}
	
	// LOGS SUPERVISOR IN
	public void supervisorLogin(ActionEvent event) throws IOException {
		
		String user = getSuperUsername();
		setSuperUsername(user);
		
		// IF SUPERVISOR IS LOGGING IN REGULARLY
		if ( this.supervisorCodeTextField == null ) {
			
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("/SupervisorPage.fxml"));
			superLoggedIn = true;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
			root = loader.load();
			System.out.println("\nYou have successfully logged in as supervisor!");	
			
			SceneController sceneController = loader.getController();
			sceneController.displaySupervisorName(getSuperUsername());
			
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
					superLoggedIn = true;
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserPage.fxml"));
					root = loader.load();
					System.out.println("\nYou have successfully logged in as supervisor!");
					
					SceneController sceneController = loader.getController();
					sceneController.displaySupervisorName(getSuperUsername());
					
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
		superLoggedIn = false;
		
		// PRINTING OUT FOR TESTING
		System.out.println("UserList - ");
		printList(userList);
		System.out.println("SupervisorList - ");
		printList(supervisorList);
		System.out.println("Buffer - ");
		printArray(buffer);
		
	}
	
	// CREATES A USER STORY WITH DATE, TITLE, AND DESCRIPTION FIELDS
	public void createUserStory(ActionEvent event) throws IOException {
		
		int counter = 0;
		
		// CAPTURE USER INPUT AND CREATE EMPTY USER STORY ITEM
		String title = createUserStoryTitleField.getText();
		String description = createUserStoryDescriptionTestArea.getText();
		ArrayList<UserStoryItem> userStoryItem = new ArrayList<UserStoryItem>();
		//ObservableList<String> obsItemList = FXCollections.observableArrayList(obsItemArrList);
		ArrayList<String> arrList = new ArrayList<String>();
		ObservableList<String> obsItemList = FXCollections.observableArrayList(arrList);

		int id = getUserID();
		setUserID(id + 1);
		
		UserStory newStory = new UserStory(getDate(), title, description, userStoryItem, id);
		
		// IF STORIES LIST IS NOT EMPTY
		if (stories.size() != 0) {
			// CHECK IF TITLE EXISTS IN USER STORIES ALREADY
			for(int i = 0; i < stories.size(); i++) {
				if ( stories.get(i).getTitle().equals(newStory.getTitle()) ) {
					counter++;
				}
			}
			
			// IF TITLE DOES NOT EXIST ALREADY IN STORIES LIST
			if ( counter == 0 ) {
				stories.add(newStory);
				
				// ADD NEW STORY TO PLANNING POKER USER STORY CHOICEBOX
				choices.add(newStory.getTitle());
				
				arrList.add(newStory.getTitle());
				obsItemList.addAll(arrList);
				itemListList.add(obsItemList);
				
				
				
				// ADD STORY ITEMS INTO USERSTORYCHOICES
				for(int j = 0; j < stories.size(); j++) {
					userStoryChoices.add(stories.get(j).getDate() + " " + stories.get(j).getTitle() + "; " + stories.get(j).getDescription());
				}
				// EMPTY THE ALREADY EXISTING LIST ELEMENTS IN USERSTORYCHOICES
				for(int k = 0; k < stories.size() - 1; k++) {
					userStoryChoices.remove(0);
				}
				// POPULATE THE LIST WITH USERSTORYCHOICES
				recentUserStoriesList.setItems(userStoryChoices);
				printStories(stories);
			} else {
				System.out.println("User Story already exists!\n");
			}
		} // IF LIST IS EMPTY TO BEGIN WITH
		else {
			stories.add(newStory);
			userStoryChoices.add(stories.get(0).getDate() + " " + stories.get(0).getTitle() + "; " + stories.get(0).getDescription());
			recentUserStoriesList.setItems(userStoryChoices);
			
			// ADD NEW STORY TO PLANNING POKER USER STORY CHOICEBOX
			choices.add(newStory.getTitle());
			
			
			arrList.add(newStory.getTitle());
			obsItemList.addAll(arrList);
			itemListList.add(obsItemList);
			

			printStories(stories);
		}
		
		for(int i = 0; i < stories.size(); i++) {
			System.out.print("\nStory " + (i + 1) + " ID is: ");
			System.out.println(stories.get(i).getID());
		}
		
		
	}
	
	// GET USER ID NUMBER
	public int getUserID() {
		return userStoryID;
	}
	
	// SET USER ID NUMBER
	public void setUserID(int userStoryID) {
		this.userStoryID = userStoryID;
	}
	
	// GETS TEMPSTORY
	public UserStory getTemp() {
		return tempStory;
	}
	
	// SETS TEMPSTORY
	public void setTemp(UserStory tempStory) {
		this.tempStory = tempStory;
	}
	
	// GETS THE VALUE PRESENTLY SELECTED IN USER STORY CHOICEBOX WITHIN PLANNING POKER PAGE
	public String getStoryBoxValue() {
		return storyBoxValue;
	}
	
	// SETS THE VALUE PRESENTLY SELECTED IN USER STORY CHOICEBOX WITHIN PLANNING POKER PAGE
	public void setStoryBoxValue(String storyBoxValue) {
		this.storyBoxValue = storyBoxValue;
	}
	
	// CREATES A NEW USER STORY ITEM
	public void createUserStoryItem(ActionEvent event) throws IOException {
				
		String itemTitle = newUserStoryItemTitle.getText();
		String itemDesc = newUserStoryItemDescription.getText();
		String capturedStory = getStoryBoxValue();	
		System.out.println("Selected Story is: " + capturedStory);
		
		UserStoryItem newItem = new UserStoryItem(itemTitle, itemDesc, 0);

		for(int i = 0; i < stories.size(); i++ )  {
			
			for(int j = 0; j < stories.get(i).userStoryItems.size() + 1; j++) {
				
				if ( capturedStory.equals(stories.get(i).getTitle()) )
				{
					stories.get(i).userStoryItems.add(newItem);
					itemChoices.add(newItem.getStoryTitle());
					obsItemArrList.add(newItem.getStoryTitle());
					
					
					int storyID = stories.get(i).getID();
					itemListList.get(storyID).add(newItem.getStoryTitle());
					
					if (itemListList.get(storyID).get(0).equals(stories.get(i).getTitle()))
					{
						itemListList.get(storyID).remove(0);
					}
					
					break;
				}
				
			}
		}
		//TEST
		System.out.println("\nItems are: ");
		for(int h = 0; h < obsItemArrList.size(); h++) {
			System.out.print(obsItemArrList.get(h) + " ");
		}
	
		
		switchToPlanningPokerPage(event);
	}
	
	// GETS DATE IN FORMAT (YYYY-MM-DD)
 	public String getDate() {
		String date = Instant.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
				
		return date;
	}
	
	// CHANGE SCORE FIELD TO 0
	public void change0() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("0");
 	}
	
	// CHANGE SCORE FIELD TO 1
	public void change1() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("1");
 	}

	// CHANGE SCORE FIELD TO 2
 	public void change2() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("2");
 	}
	
 	// CHANGE SCORE FIELD TO 3
 	public void change3() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("3");
 	}

 	// CHANGE SCORE FIELD TO 4
 	public void change4() {
 		itemScoreTextField.setText("");
		itemScoreTextField.setText("4");
 	}

 	// GET SELECTED USER STORY ITEM
 	public String getSelectedItem() {
 		return selectedItem;
 	}

 	// SET SELECTED USER STORY ITEM
 	public void setSelectedItem(String selectedItem) {
 		this.selectedItem = selectedItem;
 	}
 	
 	// GET SCORE COUNT
 	public int getScoreCount() {
 		return scoreCount;
 	}

 	// SET SCORE COUNT
 	public void setScoreCount(int scoreCount) {
 		this.scoreCount = scoreCount;
 	}

 	// GET TOTAL SCORE
 	public int getTotalScore() {
 		return totalScore;
 	}
	
 	// SET TOTAL SCORE
 	public void setTotalScore(int totalScore) {
 		this.totalScore = totalScore;
 	}

 	// CONFIRM SCORE BUTTON IS CLICKED
 	public void confirmScore(ActionEvent event) {
 		 		
 		int score = 0;
 		int totalScore = getTotalScore();
 		int scoreCount = getScoreCount(); 
 		
 		// IF NO ITEM SCORE GIVEN AND CONFIRM SCORE BUTTON IS CLICKED
 		if ( itemScoreTextField == null) {
 			System.out.println("Item score field is empty.");
 		} else { 			
 			// IF STMT'S CHECK WHICH POINT WAS CHOSEN AND DESIGNATES THAT TO SELECTED ITEM
 			String selectedItem = getSelectedItem();
 			
 			if( selectedItem == null) {
 				System.out.println("There is no item selected.");
 			}
 			
 			else if( itemScoreTextField.getText().equals("0") ) {
 				
 				if( selectedItem != null ) {
 					score = 0;
 					setTotalScore(totalScore + score);
 					setScoreCount(scoreCount + 1);
 					
 					userStoryDataListView.getItems().add("Item: " + selectedItem + " - Pt: " + score);
 					itemScoreTextField.setText("");
 					 				}
 				else {
 					System.out.println("\nThere is no item selected.");
 					itemScoreTextField.setText("");
 				}
 			}
	 		else if (itemScoreTextField.getText().equals("1"))
	 		{
	 			score = 1;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - Pt: " + score);
				itemScoreTextField.setText("");
				
				// CALCULATES AVERAGE OF STORY POINTS AND DISPLAYS IN LISTVIEW
		 		int average = getTotalScore() / getScoreCount();
				userStoryDataListView.getItems().add("Points Average: " + average);
	 		}
	 		else if (itemScoreTextField.getText().equals("2"))
	 		{
	 			score = 2;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - Pt: " + score);
				itemScoreTextField.setText("");
				
				// CALCULATES AVERAGE OF STORY POINTS AND DISPLAYS IN LISTVIEW
		 		int average = getTotalScore() / getScoreCount();
				userStoryDataListView.getItems().add("Points Average: " + average);
	 		}
	 		else if (itemScoreTextField.getText().equals("3"))
	 		{
	 			score = 3;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - Pt: " + score);
				itemScoreTextField.setText("");
				
				// CALCULATES AVERAGE OF STORY POINTS AND DISPLAYS IN LISTVIEW
		 		int average = getTotalScore() / getScoreCount();
				userStoryDataListView.getItems().add("Points Average: " + average);
	 		}
	 		else if (itemScoreTextField.getText().equals("4"))
	 		{
	 			score = 4;
	 			setScoreCount(scoreCount + 1);
	 			setTotalScore(totalScore + score);

				userStoryDataListView.getItems().add("Item: " + selectedItem + " - Pt: " + score);
				itemScoreTextField.setText("");
				
				// CALCULATES AVERAGE OF STORY POINTS AND DISPLAYS IN LISTVIEW
		 		int average = getTotalScore() / getScoreCount();
				userStoryDataListView.getItems().add("Points Average: " + average);
	 		}
	 		else {
	 			System.out.println("\nNo score provided.");
	 		}

 		}
 		
 	}
    		
	// QUITS THE PROGRAM
 	public void exit(ActionEvent event) {
		stage = (Stage) userPagePane.getScene().getWindow();
		System.out.println("\nYou have successfully exited the program!");
		stage.close();
	}
	
 	// FILLS CHOICEBOXES WITH DATA VALUES WHEN PROGRAM RUNS / DETECTS USER INTERACTION WITH CHOICEBOXES
 	@Override	
 	public void initialize(URL arg0, ResourceBundle arg1) {
 		
 		// PLANNING POKER - USER STORY CHOICE BOX
 		userStoryChoiceBox.setValue("-- Select a Story --");
 		userStoryChoiceBox.setOnAction(this::populateStoryChoiceBox);
 		userStoryChoiceBox.getItems().addAll(storyList);
 		
 		// PLANNING POKER - USER STORY ITEM CHOICE BOX
 		userStoryItemChoiceBox.setOnAction(this::populateStoryItemChoiceBox);
 		
 		// SCORES FOR ITEMS
 		confirmScoreButton.setOnAction(this::confirmScore);
 	}
 	
 	// IF USER STORY CHOICEBOX IS CLICKED
 	private void populateStoryChoiceBox(ActionEvent event) {
 		
 		// IF USER STORY HAS BEEN PICKED - MAKE IT SO USER STORY ITEM CHOICEBOX NOW ASKS FOR AN ITEM
 		if ( !userStoryChoiceBox.getValue().equals("-- Select a Story --") ) {
 			userStoryItemChoiceBox.setValue("-- Select an Item --");
 			userStoryItemDescription.setText("");
 					
 		} // IF USER STORY HAS NOT BEEN PICKED - USER STORY ITEM CHOICEBOX DOES NOT ASK FOR ITEM
 		else {
 			userStoryItemDescription.setText("");
 		}
 		
 	}
 		
 	// IF USER STORY ITEM CHOICEBOX IS CLICKED
 	private void populateStoryItemChoiceBox(ActionEvent event) {
 		
 		// CAPTURE THE ITEM PRESENT IN USER STORY ITEM CHOICE BOX
 		String itemBoxValue = userStoryItemChoiceBox.getValue();
 		
 		// CHECK IF THERE IS A VALUE IN THE USER STORY ITEM CHOICEBOX
 		if( itemBoxValue != null ) {

 		  if( userStoryItemChoiceBox.getValue().equals("-- Select an Item --") ) {
 			// SEARCH THROUGH STORIES
 			for(int i = 0; i < stories.size(); i++) {
 							
 			// IF ANYTHING IN STORIES EQUALS WHATS WAS SELECTED IN STORY CHOICEBOX
 				if( stories.get(i).getTitle().equals(getStoryBoxValue()) ) {
 									
 					int storyID = stories.get(i).getID();					
 									
 					System.out.println("\n\nList " + (storyID + 1) + "'s List Contents: ");
 					for(int j = 0; j < itemListList.get(storyID).size(); j++) {
 						System.out.print(itemListList.get(storyID).get(j) + " ");
 					}
 					
 					userStoryItemChoiceBox.getItems().setAll( itemListList.get(storyID) );
 										
 					// BREAK FROM WHOLE FOR LOOP IF ALL ITEMS FROM THE STORY WERE ADDED TO THE ARRAYLIST obsItemArrList
 					break;
 									
 				}
 							
 			}	
 			
 		  } else {			
 			System.out.println("No story is selected.");
 		  }

 		}
 		
 		// FILL DESCRIPTION TEXT FIELD FOR SELECTED ITEM
 		if( itemBoxValue == null ) {
 			System.out.println("");
 		} else {
 		
 			// SET THE SELECTED ITEM TO THE ITEM USER CLICKED
 			setSelectedItem(userStoryItemChoiceBox.getValue());
 					
 			// SEARCH STORIES AND LOOK THROUGH THE USERSTORYITEMS IN EACH STORY - IF THE SELECTED ITEM IN ITEM CHOICE BOX EQUALS ANY EXISTING ITEM, DISPLAY THE DESCRIPTION FOR THAT ITEM IN DESC TEXTFIELD
 			for(int i = 0; i < stories.size(); i++) {
 						
 				for(int j = 0; j < stories.get(i).userStoryItems.size(); j++) {
 					
 					if (stories.get(i).userStoryItems.get(j).getStoryTitle().equals(getSelectedItem()) ) {
 						userStoryItemDescription.setText(stories.get(i).userStoryItems.get(j).getStoryDescription());
 						break;
 					}
 					
 				}
 	
 			}
 					
 		}
 		 		
 	}

// END OF SCENECONTROLLER
}
