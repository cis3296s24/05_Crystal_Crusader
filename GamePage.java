import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GamePage {
    private VBox rootPane = new VBox(20); // spacing between children
    private StringProperty outputText = new SimpleStringProperty(""); // StringProperty for binding
    public String input;
    private boolean isFullScreen;

    public GamePage(MainUserInterface mainApp, boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        GamePlay gameplay = new GamePlay(this);
        Thread game = new Thread(gameplay);
        game.start();

        rootPane.setId("game-page"); // CSS ID for styling

        TextArea outputTextArea = new TextArea();
        outputTextArea.setId("output-text-area");
        outputTextArea.setEditable(false);  // Make TextArea non-editable
        outputTextArea.setWrapText(true);  // Enable text wrapping
        outputTextArea.textProperty().bind(outputText);  // Bind the TextArea's text property to the outputText StringProperty
        // Input field
        TextField inputField = new TextField();
        inputField.setId("input-field"); // CSS ID for styling

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setId("game-submit-button"); // CSS ID for styling
        submitButton.setOnAction(event -> {
            input = inputField.getText();
            inputField.clear();  // Clear input field after submitting
            gameplay.proceed();
        });

        // Quit button
        Button quitButton = new Button("Quit");
        quitButton.setId("game-quit-button"); // CSS ID for styling
        quitButton.setOnAction(e -> {
            mainApp.switchToMainPage();
            mainApp.setFullScreen(isFullScreen);
        });

        rootPane.getChildren().addAll(outputTextArea, inputField, submitButton, quitButton);

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
