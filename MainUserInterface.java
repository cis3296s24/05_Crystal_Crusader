import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainUserInterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox menu = new VBox(10); // Vertical box with spacing of 10
        menu.getStyleClass().add("menu"); // CSS class for styling
        // Creating Title
        Label title = new Label("Crystal Crusader");
        title.getStyleClass().add("menu-title");
        menu.getChildren().add(title);


        // Creating buttons
        Button startButton = new Button("Start");
        startButton.getStyleClass().add("menu-button");
        startButton.setOnAction(e -> {
            System.out.println("Game started!");
        });
        // Event handler for mouse hover

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("menu-button");
        saveButton.setOnAction(e -> {
            System.out.println("Game saved!");
        });

        Button settingsButton = new Button("Settings");
        settingsButton.getStyleClass().add("menu-button");
        settingsButton.setOnAction(e -> {
            System.out.println("Settings opened!");
        });

        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("menu-button");
        quitButton.setOnAction(e -> primaryStage.close()); // Close the application

        // Adding buttons to the layout
        menu.getChildren().addAll(startButton, saveButton, settingsButton, quitButton);

        Scene scene = new Scene(menu, 900, 500);
        scene.getStylesheets().add("style.css"); // Load the CSS stylesheet

        primaryStage.setTitle("Crystal Crusader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
