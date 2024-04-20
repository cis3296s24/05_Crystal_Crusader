import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SettingsPage {
    private Pane rootPane;
    private Stage primaryStage;
    private MainUserInterface mainApp;
    public SettingsPage(Stage primaryStage, MainUserInterface mainApp) {
        this.primaryStage = primaryStage;
        this.mainApp = mainApp;
        setupSettingsPage(primaryStage, mainApp);
    }


    private void setupSettingsPage(Stage primaryStage, MainUserInterface mainApp) {
        rootPane = new Pane();

        // Initialize a ComboBox for selecting screen resolution from predefined options
        ComboBox<String> resolutionComboBox = createComboBox();

        // Set the ComboBox to the current actual resolution used by the primaryStage
        Platform.runLater(() -> {
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            String actualResolution = String.format("%d x %d",
                    (int) primaryStage.getWidth(),
                    (int) primaryStage.getHeight());
            resolutionComboBox.getSelectionModel().select(actualResolution);
        });

        Button quitButton = new Button("Quit");

        // Setup a CheckBox for enabling full screen mode
        CheckBox fullScreenCheckBox = createCheckBox(primaryStage);

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
        quitButton.setLayoutX(50);
        quitButton.setLayoutY(200);
        quitButton.setOnAction(e -> {
            mainApp.switchToMainPage();
            mainApp.setFullScreen(fullScreenCheckBox.isSelected());
        });

        // Add components to the root pane
        rootPane.getChildren().addAll(resolutionComboBox, fullScreenCheckBox, quitButton);
    }

    private static CheckBox createCheckBox(Stage primaryStage) {
        CheckBox fullScreenCheckBox = new CheckBox("Full Screen Mode");
        fullScreenCheckBox.setLayoutX(50);
        fullScreenCheckBox.setLayoutY(100);
        fullScreenCheckBox.setSelected(primaryStage.isFullScreen());
        return fullScreenCheckBox;
    }

    private static ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("800 x 600", "1024 x 768", "1280 x 720");
        comboBox.setLayoutX(50);
        comboBox.setLayoutY(50);
        return comboBox;
    }




    public Pane getRootPane() {
        Pane newRootPane = new Pane();
        setupSettingsPage(primaryStage, mainApp);
        return rootPane;
    }
}
