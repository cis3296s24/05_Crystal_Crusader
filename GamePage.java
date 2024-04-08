import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GamePage {
    private Pane rootPane;

    public GamePage(MainUserInterface mainApp) {
        setupGamePage(mainApp);
    }

    private void setupGamePage(MainUserInterface mainApp) {
        rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: black;"); // Set background color

        // Output label
        Label outputLabel = new Label("Game Output");
        outputLabel.setLayoutX(100); // Positioning
        outputLabel.setLayoutY(50);
        outputLabel.setTextFill(Color.WHITE); // Text color

        // Input field
        TextField inputField = new TextField();
        inputField.setLayoutX(100);
        inputField.setLayoutY(100);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(100);
        submitButton.setLayoutY(150);
        submitButton.setOnAction(event -> {
            // Example of handling input and updating the output
            String inputText = inputField.getText();
            outputLabel.setText("You entered: " + inputText);
        });

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setLayoutX(100);
        quitButton.setLayoutY(200);
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
