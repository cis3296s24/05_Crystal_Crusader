import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainUserInterface extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a Start Label object
        Label startLabel = new Label("Start");
        startLabel.setTextFill(Color.WHITE);
        startLabel.setStyle("-fx-font-size: 24px;");
        startLabel.setOnMouseClicked(e -> {
            System.out.println("Start button clicked");
        });

        // Create a Save Label object
        Label saveLabel = new Label("Start");
        startLabel.setTextFill(Color.WHITE);
        startLabel.setStyle("-fx-font-size: 24px;");
        startLabel.setOnMouseClicked(e -> {
            System.out.println("Save button clicked");
        });


        StackPane root = new StackPane();
        StackPane.setAlignment(startLabel, Pos.CENTER);
        StackPane.setAlignment(saveLabel, Pos.CENTER);

        // Set the background color of the StackPane to black
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        root.getChildren().add(startLabel);
        root.getChildren().add(saveLabel);
        Scene scene = new Scene(root, 900, 500);

        primaryStage.setTitle("Crystal Crusader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

