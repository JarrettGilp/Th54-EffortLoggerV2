/*
 *  EffortLogger V2 - Team Th54
 *
 *  This file edited by: Jarrett Gilpatric
 *
 */ 

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			// SET ROOT PAGE AND CREATE SCENE
			Parent root = FXMLLoader.load(getClass().getResource("/EffortLoggerLogin.fxml"));
			Scene homeScene = new Scene(root);
			
			// ICON
			//Image icon = new Image("<something>.png");
			//stage.getIcons().add(icon);
						
			// WINDOW TITLE
			stage.setTitle("EffortLogger");
						
			// WINDOW SIZING
			stage.setX(450);
			stage.setY(225);
						
			// DISPLAY SCENE
			stage.setScene(homeScene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
