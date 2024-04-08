import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingsPage {
    private Pane rootPane;

    public SettingsPage(Stage primaryStage, MainUserInterface mainApp) {
        setupSettingsPage(primaryStage, mainApp);
    }


    private void setupSettingsPage(Stage primaryStage, MainUserInterface mainApp) {
        rootPane = new Pane();


        // Create a ComboBox for resolution selection with predefined options
        ComboBox<String> resolutionComboBox = new ComboBox<>(
                FXCollections.observableArrayList(
                        "800 x 600",
                        "1024 x 768",
                        "1280 x 720",
                        "1920 x 1080"
                )
        );
        resolutionComboBox.setLayoutX(50); // Set X position
        resolutionComboBox.setLayoutY(50); // Set Y position

        // Optionally, set a default selection or use a user preference
        resolutionComboBox.getSelectionModel().select("800 x 600");

        // Add a listener to change the application window size based on the selected resolution
        resolutionComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String[] dimensions = newValue.split(" x ");
            if (dimensions.length == 2) {
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                primaryStage.setWidth(width);
                primaryStage.setHeight(height);
            }
        });

        rootPane.getChildren().add(resolutionComboBox);
        Button quitButton = new Button("Quit");
        quitButton.setLayoutX(50);
        quitButton.setLayoutY(200);
        quitButton.setOnAction(e -> mainApp.switchToMainPage());

        rootPane.getChildren().add(quitButton);
    }

    public Pane getRootPane() {
        return rootPane;
    }
}
