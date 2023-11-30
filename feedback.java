package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ASUHelloWorldJavaFX extends Application {

    private TextArea feedbackbox;
    private TextField nameBox;
    private TextField empBox;
    private TextField departBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageone) {
        stageone.setTitle("Feedback Hub Prototype");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

   
        CheckBox feedbackCheckBox = new CheckBox("Submit Feedback");
        CheckBox suggestionsCheckBox = new CheckBox("Submit Suggestions");
        CheckBox issuesCheckBox = new CheckBox("Report Issues");

 
        GridPane.setConstraints(feedbackCheckBox, 0, 0);
        GridPane.setConstraints(suggestionsCheckBox, 1, 0);
        GridPane.setConstraints(issuesCheckBox, 2, 0);

        gridPane.getChildren().addAll(feedbackCheckBox, suggestionsCheckBox, issuesCheckBox);

        Label namebox1 = new Label("Name:");
        GridPane.setConstraints(namebox1, 0, 1);
        nameBox = new TextField();
        GridPane.setConstraints(nameBox, 1, 1);

        Label positionbox2 = new Label("Position:");
        GridPane.setConstraints(positionbox2, 0, 2);
        empBox = new TextField();
        GridPane.setConstraints(empBox, 1, 2);

        Label departbox3 = new Label("Department:");
        GridPane.setConstraints(departbox3, 0, 3);
        departBox = new TextField();
        GridPane.setConstraints(departBox, 1, 3);

        Label feedbackbox4 = new Label("Enter Feedback:");
        GridPane.setConstraints(feedbackbox4, 0, 4);
        feedbackbox = new TextArea();
        feedbackbox.setPrefRowCount(5);
        feedbackbox.setPrefColumnCount(20);
        GridPane.setConstraints(feedbackbox, 1, 4);

        Button submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 1, 5);

        submitButton.setOnAction(e -> {
            if (feedbackCheckBox.isSelected()) {
                
                String name = nameBox.getText();
                String position = empBox.getText();
                String department = departBox.getText();
                String feedback = feedbackbox.getText();

                System.out.println("Name: " + name);
                System.out.println("Position: " + position);
                System.out.println("Department: " + department);
                System.out.println("Feedback submitted: " + feedback);

                nameBox.clear();
                empBox.clear();
                departBox.clear();
                feedbackbox.clear();
            }

            if (suggestionsCheckBox.isSelected()) {

            }

            if (issuesCheckBox.isSelected()) {

            }
        });

        gridPane.getChildren().addAll(
                namebox1, nameBox, positionbox2, empBox, departbox3, departBox,
                feedbackbox4, feedbackbox, submitButton);

        Scene scene = new Scene(gridPane, 600, 400);
        stageone.setScene(scene);
        stageone.show();
    }
}
