import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainUserInterface extends Application {
    // Define the triangle and blink animation globally so they can be used in the setupButton method
    Polygon triangleLeft;
    Polygon triangleRight;

    Timeline blinkAnimation;
    @Override
    public void start(Stage primaryStage) {
        VBox menu = new VBox(10); // Vertical box with spacing of 10
        menu.getStyleClass().add("menu"); // CSS class for styling
        // Initialize the triangle and animation
        initTriangles();

        // Creating Title
        Label title = new Label("Crystal Crusader");
        title.getStyleClass().add("menu-title");
        menu.getChildren().add(title);

        // Creating buttons
        Button startButton = createButton("Start", menu);
        startButton.getStyleClass().add("menu-button");
        startButton.setOnAction(e -> {
            System.out.println("Game started!");
        });

        Button saveButton = createButton("Save", menu);
        saveButton.getStyleClass().add("menu-button");
        saveButton.setOnAction(e -> {
            System.out.println("Game saved!");
        });

        Button settingsButton = createButton("Settings", menu);
        settingsButton.getStyleClass().add("menu-button");
        settingsButton.setOnAction(e -> {
            System.out.println("Settings opened!");
        });

        Button quitButton = createButton("Quit", menu);
        quitButton.getStyleClass().add("menu-button");
        quitButton.setOnAction(e -> primaryStage.close()); // Close the application

        // Adding buttons to the layout
        menu.getChildren().addAll(startButton, saveButton, settingsButton, quitButton, triangleLeft, triangleRight);

        Scene scene = new Scene(menu, 900, 500);
        scene.getStylesheets().add("style.css"); // Load the CSS stylesheet

        primaryStage.setTitle("Crystal Crusader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void initTriangles() {
        triangleLeft = new Polygon(0.0, 0.0, 10.0, 5.0, 0.0, 10.0);
        triangleLeft.setFill(Color.WHITE);
        triangleLeft.setOpacity(0); // Initially invisible

        triangleRight = new Polygon(0.0, 0.0, -10.0, 5.0, 0.0, 10.0); // Note the negative X for the right triangle to make it point to the right
        triangleRight.setFill(Color.WHITE);
        triangleRight.setOpacity(0); // Initially invisible

        // Assuming blinkAnimation is applicable to both, you might need separate animations
        blinkAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> {
                    triangleLeft.setOpacity(0);
                    triangleRight.setOpacity(0);
                }),
                new KeyFrame(Duration.seconds(1.0), e -> {
                    triangleLeft.setOpacity(1);
                    triangleRight.setOpacity(1);
                })
        );
        blinkAnimation.setCycleCount(Timeline.INDEFINITE);
    }
    private Button createButton(String text, VBox menu) {
        Button button = new Button(text);

        button.setOnMouseEntered(e -> {
            // Get the position of the VBox in the scene to offset the triangle positions
            double menuX = menu.localToScene(menu.getBoundsInLocal()).getMinX();
            double menuY = menu.localToScene(menu.getBoundsInLocal()).getMinY();

            // Calculate the Y position common for both triangles (centered vertically on the button)
            double yPos = menuY + button.getLayoutY() + button.getHeight() / 2;

            // Set the positions for the left triangle
            triangleLeft.setLayoutY(yPos);
            // Align it to the left of the button, adjusting by its width and some padding
            triangleLeft.setLayoutX(menuX + button.getLayoutX() - triangleLeft.getLayoutBounds().getWidth() - 5);

            // Set the positions for the right triangle
            triangleRight.setLayoutY(yPos);
            // Align it to the right of the button, adjusting by some padding
            triangleRight.setLayoutX(menuX + button.getLayoutX() + button.getWidth() + 5);

            // Make the triangles visible and start the blinking animation
            triangleLeft.setOpacity(1);
            triangleRight.setOpacity(1);
            blinkAnimation.play();
        });


        button.setOnMouseExited(e -> {
            blinkAnimation.stop();
            triangleLeft.setOpacity(0);
            triangleRight.setOpacity(0);
        });
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
