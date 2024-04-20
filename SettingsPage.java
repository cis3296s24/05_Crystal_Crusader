import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SettingsPage {
    private VBox rootPane = new VBox(20); // spacing between children
    private Stage primaryStage;
    private MainUserInterface mainApp;
    public SettingsPage(Stage primaryStage, MainUserInterface mainApp) {
        this.primaryStage = primaryStage;
        this.mainApp = mainApp;
        rootPane.setId("settings-page"); // CSS ID for styling
        Label titleLabel = new Label("Settings");
        titleLabel.setId("settings-title-label"); // CSS ID for styling

        // Initialize a ComboBox for selecting screen resolution from predefined options
        ComboBox<String> resolutionComboBox = createComboBox();
        resolutionComboBox.setId("resolution-combo-box"); // CSS ID for styling

        // Set the ComboBox to the current actual resolution used by the primaryStage
        Platform.runLater(() -> {
            String actualResolution = String.format("%d x %d",
                    (int) primaryStage.getWidth(),
                    (int) primaryStage.getHeight());
            resolutionComboBox.getSelectionModel().select(actualResolution);
        });

        Button quitButton = new Button("Quit");
        quitButton.setId("settings-quit-button"); // CSS ID for styling

        // Setup a CheckBox for enabling full screen mode
        CheckBox fullScreenCheckBox = createCheckBox(primaryStage);
        fullScreenCheckBox.setId("full-screen-checkbox"); // CSS ID for styling

        // Define a listener to adjust the application window size based on the selected resolution
        resolutionComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String[] dimensions = newValue.split(" x ");
            if (dimensions.length == 2) {
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                primaryStage.setWidth(width);
                primaryStage.setHeight(height);
                primaryStage.centerOnScreen();  // Ensure the stage is centered after resizing
            }
        });

        // Configure the CheckBox to reflect the current full screen status and update on toggle
        fullScreenCheckBox.setSelected(primaryStage.isFullScreen());
        fullScreenCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            primaryStage.setFullScreen(newValue);
        });

        // Position the 'Quit' button and define its behavior

        quitButton.setOnAction(e -> {
            mainApp.switchToMainPage();
            mainApp.setFullScreen(fullScreenCheckBox.isSelected());
        });

        // Add components to the root pane
        rootPane.getChildren().addAll(titleLabel, resolutionComboBox, fullScreenCheckBox, quitButton);
    }

    private static CheckBox createCheckBox(Stage primaryStage) {
        CheckBox fullScreenCheckBox = new CheckBox("Full Screen Mode");
        fullScreenCheckBox.setSelected(primaryStage.isFullScreen());
        return fullScreenCheckBox;
    }

    private static ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("800 x 600", "1024 x 768", "1280 x 720");
        return comboBox;
    }




    public Pane getRootPane() {
        return rootPane;
    }
}
