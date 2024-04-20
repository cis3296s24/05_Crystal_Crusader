import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

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
        // Load the audio file
        URL resource = getClass().getResource("/submit.wav");
        if (resource == null) {
            System.out.println("File not found");
            return; // Stop if the file isn't found
        }
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1); // Play the audio exactly once




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
            mediaPlayer.stop();  // Stop any currently playing audio
            mediaPlayer.seek(Duration.ZERO);  // Seek to the beginning
            mediaPlayer.play(); // Start playing the audio
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
