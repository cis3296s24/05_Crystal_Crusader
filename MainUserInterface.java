import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class MainUserInterface extends Application implements SettingsCallback {
    // Define the triangle and blink animation globally so they can be used in the setupButton method
    Polygon triangleLeft;
    Polygon triangleRight;

    Timeline blinkAnimation;
    private Pane mainPageRoot;
    private Scene mainScene;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Create a Pane which allows for absolute positioning
        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;"); // Set the background color of the Pane

        // Initialize the triangle and animation
        initTriangles();

        VBox menu = new VBox(10); // Vertical box with spacing of 10
        menu.getStyleClass().add("menu"); // CSS class for styling
        root.getChildren().add(menu); // Add the VBox to the Pane

        // Creating Title
        Label title = new Label("Crystal Crusader");
        title.getStyleClass().add("menu-title");
        menu.getChildren().add(title);

        // Creating buttons and adding them to the menu
        Button startButton = createButton("Start ", menu);
        startButton.getStyleClass().add("menu-button");
        Button saveButton = createButton("Save ", menu);
        saveButton.getStyleClass().add("menu-button");
        Button settingsButton = createButton("Settings ", menu);
        settingsButton.getStyleClass().add("menu-button");
        Button quitButton = createButton("Quit ", menu);
        quitButton.getStyleClass().add("menu-button");
        menu.getChildren().addAll(startButton, saveButton, settingsButton, quitButton);

        // Define actions for buttons
        startButton.setOnAction(e -> {
            GamePage gamePage = new GamePage(this); // Create an instance of GamePage
            Scene gameScene = new Scene(gamePage.getRootPane(), 800, 600); // Use the GamePage's root pane
            // Loading the stylesheet
            URL stylesheetURL = getClass().getResource("/style.css"); // Make sure the path is correct
            if (stylesheetURL != null) {
                gameScene.getStylesheets().add(stylesheetURL.toExternalForm());
            } else {
                System.out.println("ERROR: Failed to load style.css");
            }

            primaryStage.setScene(gameScene); // Switch to the game scene
            System.out.println("Game started!");
        });

        saveButton.setOnAction(e -> System.out.println("Game saved!"));

        settingsButton.setOnAction(e -> {

            SettingsPage settingsPage = new SettingsPage(primaryStage, this); // 'this' refers to an instance of MainUserInterface
            Scene settingsScene = new Scene(settingsPage.getRootPane(), 800, 600); // Create a scene with the settings page, size can be adjusted
            primaryStage.setScene(settingsScene); // Apply the settings scene to the primary stage
            System.out.println("Settings opened!");
        });

        quitButton.setOnAction(e -> primaryStage.close()); // Close the application

        // Add the triangles to the Pane for absolute positioning
        root.getChildren().addAll(triangleLeft, triangleRight);
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Center menu horizontally
            menu.setLayoutX((newVal.doubleValue() - menu.getWidth()) / 2);
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            // Center menu vertically
            menu.setLayoutY((newVal.doubleValue() - menu.getHeight()) / 2);
        });
        menu.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Adjust menu position if its width changes
            menu.setLayoutX((root.getWidth() - newVal.doubleValue()) / 2);
        });
        menu.heightProperty().addListener((obs, oldVal, newVal) -> {
            // Adjust menu position if its height changes
            menu.setLayoutY((root.getHeight() - newVal.doubleValue()) / 2);
        });


        // Setup and show the main scene
        this.mainPageRoot = setupMainPage();
        this.mainScene = new Scene(root, 800, 600); // Initialize mainScene with root
        this.mainScene.getStylesheets().add("style.css"); // Load the CSS stylesheet
        primaryStage.setTitle("Crystal Crusader");
        primaryStage.setScene(this.mainScene);
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
            double yPos = button.getLayoutY() + button.getHeight() / 2 + menu.getLayoutY();

            triangleLeft.setLayoutY(yPos - triangleLeft.getLayoutBounds().getHeight() / 2);
            triangleLeft.setLayoutX(menu.getLayoutX() + button.getLayoutX() - triangleLeft.getLayoutBounds().getWidth() - 3);

            triangleRight.setLayoutY(yPos - triangleRight.getLayoutBounds().getHeight() / 2);
            triangleRight.setLayoutX(menu.getLayoutX() + button.getLayoutX() + button.getWidth() + 5);

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
    @Override
    public void switchToMainPage() {
        primaryStage.setScene(mainScene); // Switch back to the main scene
    }
    @Override
    public void setFullScreen(boolean fullScreen) {
        System.out.println("Full screen requested: " + fullScreen);
        primaryStage.setFullScreen(fullScreen);// TODO: Needs to be fixed
    }
    private Pane setupMainPage() {
        // Set up your main page layout here and return the root pane
        Pane rootPane = new Pane();
        // Your main page setup...
        return rootPane;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
