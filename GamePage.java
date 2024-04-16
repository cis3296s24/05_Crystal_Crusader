import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GamePage {
    private Pane rootPane;

    public GamePage(MainUserInterface mainApp) {
        setupGamePage(mainApp);
    }

    private void setupGamePage(MainUserInterface mainApp) {
        rootPane = new Pane();
        PlayerObject player = new PlayerObject("Player");
        Thread battleThread = new Thread(player);
        battleThread.start();

        rootPane.setStyle("-fx-background-color: black;"); // Set background color

        // Output label
        Label outputLabel = new Label("Game Output");
        outputLabel.setLayoutX(100); // Positioning
        outputLabel.setLayoutY(50);
        outputLabel.setTextFill(Color.WHITE); // Text color
        outputLabel.textProperty().bind(player.getOutput(""));  // Bind directly to player's outputText

        // Input field
        TextField inputField = new TextField();
        inputField.setLayoutX(100);
        inputField.setLayoutY(200);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(100);
        submitButton.setLayoutY(250);
        submitButton.setOnAction(event -> {
            // Example of handling input and updating the output
            String inputText = inputField.getText();
            outputLabel.textProperty().bind(player.getOutput(inputText));  // Bind directly to player's outputText

            player.proceedWithBattle();
        });

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setLayoutX(100);
        quitButton.setLayoutY(300);
        quitButton.setOnAction(event -> {
            // Quit the game
            mainApp.switchToMainPage();
        });


        rootPane.getChildren().addAll(outputLabel, inputField, submitButton, quitButton);
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
