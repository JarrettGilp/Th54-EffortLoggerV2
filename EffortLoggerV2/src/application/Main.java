// Made by: Ritesh Puttanmadam

package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {

	
	Stage stage;
    Scene welcomeScene, onBoardingScene, teamJoiningScene, planningPokerScene;

    public static void main(String[] args) {
        launch(args);
    }

    // This start method contains interactions when user clicks on the button 
    public void start(Stage primaryStage) {
        stage = primaryStage;

        stage.getIcons().add(new Image("EL.png"));
        stage.setTitle("EffortLogger");
        Label effortLoggerTutorial = new Label("EffortLogger Tutorial");
        effortLoggerTutorial.setTranslateY(-60);
        effortLoggerTutorial.setFont(Font.font(30));
        
        // Welcome Screen button name
        Button welcomeButton = new Button("Welcome to Effortlogger V2 Onboarding Tutorial");
        welcomeButton.setOnAction(e -> goToOnboardingTutorial());
        
        Button goToLogin = new Button("Go To Login");
        goToLogin.setTranslateX(0);
        goToLogin.setTranslateY(40);
        goToLogin.setOnAction(e -> {
        	SceneController controller = new SceneController();
        	controller.initiateHomePage(stage); 
        	}
        );
        
        // layout of the button 
        StackPane welcomeLayout = new StackPane();
        welcomeLayout.getChildren().add(welcomeButton);
        welcomeLayout.getChildren().add(goToLogin);
        welcomeLayout.getChildren().add(effortLoggerTutorial);
        welcomeScene = new Scene(welcomeLayout, 400, 300);
        
        stage.setScene(welcomeScene);
        stage.show();
    }
    
    // This goToOnboardingTutorial method contains interactions when user clicks on the button 
    private void goToOnboardingTutorial() {
        VBox onBoardingLayout = new VBox(10);
        onBoardingLayout.setAlignment(Pos.CENTER);

        Button joinTeamButton = new Button("Join your Team");
        joinTeamButton.setOnAction(e -> goToTeamJoiningScreen());

        Button createTeamButton = new Button("Create a Team");
        createTeamButton.setOnAction(e -> {
            // Logic for creating a team
        	// Create team page
        	goToTeamJoiningScreen();
        });
        // layout of the button 
        onBoardingLayout.getChildren().addAll(joinTeamButton, createTeamButton);
        onBoardingScene = new Scene(onBoardingLayout, 400, 300);

        stage.setScene(onBoardingScene);
    }
    
    // This goToTeamJoiningScreen method is where user can enter team names and member
    private void goToTeamJoiningScreen() {
        VBox teamJoiningLayout = new VBox(10);

        // Input for team name
        TextField teamNameInput = new TextField();
        teamNameInput.setPromptText("Enter Team Name");

        ListView<String> teamMembersList = new ListView<>();

        TextField userNameInput = new TextField();
        userNameInput.setPromptText("Enter your name");

        Button addMemberButton = new Button("Add Member");
        addMemberButton.setOnAction(e -> {
            String userName = userNameInput.getText();
            if (!userName.isEmpty()) {
                teamMembersList.getItems().add(userName);
                userNameInput.clear();
            }
        });
        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> {
            createPlanningPokerPage(); // Create Planning Poker Page
            stage.setScene(planningPokerScene); // Set the Planning Poker Scene
        });

        teamJoiningLayout.getChildren().addAll(teamNameInput, userNameInput, addMemberButton, nextButton, teamMembersList);
        teamJoiningScene = new Scene(teamJoiningLayout, 400, 300);

        stage.setScene(teamJoiningScene);
    }
    // This createProductBacklogPage method contains layout of the planning poker page 
    private void createProductBacklogPage() {
        VBox productBacklogLayout = new VBox(10);
        productBacklogLayout.setAlignment(Pos.CENTER);

        Button viewStoriesButton = new Button("View Stories");
        viewStoriesButton.setOnAction(e -> {
            // Logic for viewing stories
        });

        Button createStoriesButton = new Button("Create Stories");
        createStoriesButton.setOnAction(e -> {
            // Logic for creating stories
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(planningPokerScene); // Go back to the Planning Poker Page
        });

        productBacklogLayout.getChildren().addAll(viewStoriesButton, createStoriesButton, backButton);
        Scene productBacklogScene = new Scene(productBacklogLayout, 400, 300);

        stage.setScene(productBacklogScene);
    }

    // This createEstimateEffortPage method contains layout for EstimateEffort where the buttons are there 
    private void createEstimateEffortPage() {
        VBox estimateEffortLayout = new VBox(10);
        estimateEffortLayout.setAlignment(Pos.CENTER);

        Button feedbackHubButton = new Button("Feedback Hub");
        feedbackHubButton.setOnAction(e -> {
            // Logic for feedback hub functionality
        });

        Button userProfileButton = new Button("User Profile Customization");
        userProfileButton.setOnAction(e -> {
            // Logic for user profile customization functionality
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            stage.setScene(welcomeScene); // Go back to the Welcome Screen
        });

        estimateEffortLayout.getChildren().addAll(feedbackHubButton, userProfileButton, quitButton);
        Scene estimateEffortScene = new Scene(estimateEffortLayout, 400, 300);

        stage.setScene(estimateEffortScene);
    }

    // Modification in createPlanningPokerPage()
    private void createPlanningPokerPage() {
        VBox planningPokerLayout = new VBox(10);
        planningPokerLayout.setAlignment(Pos.CENTER);

        Button productBacklogButton = new Button("Product Backlog");
        productBacklogButton.setOnAction(e -> createProductBacklogPage());

        Button estimateEffortButton = new Button("Estimate Effort");
        estimateEffortButton.setOnAction(e -> createEstimateEffortPage());

        planningPokerLayout.getChildren().addAll(productBacklogButton, estimateEffortButton);
        planningPokerScene = new Scene(planningPokerLayout, 400, 300);

        stage.setScene(planningPokerScene);
    }
    // when run from main class it will display this 
    private void createTeam() {
        System.out.println("Team created!");
    }
	
}
