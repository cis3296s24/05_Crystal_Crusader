import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GamePage {
    private Pane rootPane;
    private StringProperty outputText = new SimpleStringProperty(""); // StringProperty for binding
    public String input;

    public GamePage(MainUserInterface mainApp) {
        rootPane = new Pane();
        GamePlay gameplay = new GamePlay(this);
        Thread game = new Thread(gameplay);
        game.start();

        rootPane.setStyle("-fx-background-color: black;"); // Set background color

        // Output label
        Label outputLabel = new Label("Game Output");
        outputLabel.setLayoutX(100); // Positioning
        outputLabel.setLayoutY(50);
        outputLabel.setTextFill(Color.WHITE); // Text color
        outputLabel.textProperty().bind(outputText);  // Bind the label's text property to the outputText StringProperty

        // Input field
        TextField inputField = new TextField();
        inputField.setLayoutX(100);
        inputField.setLayoutY(200);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(100);
        submitButton.setLayoutY(250);
        submitButton.setOnAction(event -> {
            input = inputField.getText();
            inputField.clear();  // Clear input field after submitting
            gameplay.proceed();
        });

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setLayoutX(100);
        quitButton.setLayoutY(300);
        quitButton.setOnAction(event -> mainApp.switchToMainPage());  // Quit the game

        rootPane.getChildren().addAll(outputLabel, inputField, submitButton, quitButton);

    }

    public Pane getRootPane() {
        return rootPane;
    }

    public void setOutput(String inputText) {
        outputText.set(inputText);  // Update the StringProperty, which automatically updates the label's text
    }

    public Runnable runnableSetOutput(String inputText){
        Runnable runnable = new Runnable(){
            public void run() {
                outputText.set(inputText);  // Update the StringProperty, which automatically updates the label's text
            }
        };
        return runnable;
    }
}
