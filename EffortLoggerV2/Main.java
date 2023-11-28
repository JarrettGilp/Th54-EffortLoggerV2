package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Main extends Application {

    Stage stage;
    Scene welcomeScene, onBoardingScene, teamJoiningScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        // Welcome Screen button which welcomes the user 
        Button welcomeButton = new Button("Welcome to Effortlogger V2 Onboarding Tutorial");
        welcomeButton.setOnAction(e -> goToOnboardingTutorial());
        
        // welcome button layout 
        StackPane welcomeLayout = new StackPane();
        welcomeLayout.getChildren().add(welcomeButton);
        welcomeScene = new Scene(welcomeLayout, 400, 300);

        // Onboarding Tutorial Screen in which Join your Team is displayed 
        Button joinTeamButton = new Button("Join your Team");
        joinTeamButton.setOnAction(e -> goToTeamJoiningScreen());

        // Onboarding Tutorial Screen in which create a Team is also displayed 
        Button createTeamButton = new Button("Create a Team");
        createTeamButton.setOnAction(e -> createTeam());

        // layout for both the buttons 
        StackPane onBoardingLayout = new StackPane();
        onBoardingLayout.getChildren().addAll(joinTeamButton, createTeamButton);
        onBoardingScene = new Scene(onBoardingLayout, 400, 300);
        
        // welcome screen displayed
        stage.setScene(welcomeScene);
        stage.show();
    }
    
    // go to next screen page
    private void goToOnboardingTutorial() {
        stage.setScene(onBoardingScene);
    }
    
    // go to Joining Team Screen page 
    private void goToTeamJoiningScreen() {
        VBox teamJoiningLayout = new VBox(10);
        teamJoiningLayout.setPadding(new Insets(20));

        teamJoiningScene = new Scene(teamJoiningLayout, 400, 300);
        stage.setScene(teamJoiningScene);
    }
    // Team created displayed if run from the Main class 
    private void createTeam() {
        System.out.println("Team created!");
    }
}
