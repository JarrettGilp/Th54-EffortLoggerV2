// Made by Ritesh Puttanmadam

package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeScreen extends Application {

    Stage stage;
    Scene welcomeScene;
    
    // runs the program
    public static void main(String[] args) {
        launch(args);
    }

    // start method where the button from the main is displayed over the button
    public void start(Stage primaryStage) {
        stage = primaryStage;

        // Welcome Screen 
        Label welcomeLabel = new Label("Welcome to Effortlogger V2 Onboarding Tutorial");
        welcomeLabel.setOnMouseClicked(e -> goToOnboardingTutorial());

        StackPane welcomeLayout = new StackPane();
        welcomeLayout.getChildren().add(welcomeLabel);
        welcomeScene = new Scene(welcomeLayout, 400, 300);

        stage.setScene(welcomeScene);
        stage.show();
    }
    // this method goes to onboardingtutorial page
    private void goToOnboardingTutorial() {
        Main main = new Main();
        main.start(stage);
    }
}

