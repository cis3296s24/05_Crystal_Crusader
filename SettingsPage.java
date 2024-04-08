import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        ComboBox<String> resolutionComboBox = createComboBox();
        resolutionComboBox.getSelectionModel().select("800 x 600");
        Button quitButton = new Button("Quit");

        // Create a CheckBox for toggling full screen mode
        CheckBox fullScreenCheckBox = createCheckBox(primaryStage);
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

        // Add an action listener to the checkbox
        fullScreenCheckBox.setOnAction(e -> {
            primaryStage.setFullScreen(fullScreenCheckBox.isSelected());
        });

        quitButton.setLayoutX(50);
        quitButton.setLayoutY(200);
        quitButton.setOnAction(e ->{ 
            mainApp.switchToMainPage();
        });
        rootPane.getChildren().add(resolutionComboBox);
        rootPane.getChildren().add(fullScreenCheckBox);
        rootPane.getChildren().add(quitButton);
    }

    private static CheckBox createCheckBox(Stage primaryStage) {
        CheckBox fullScreenCheckBox = new CheckBox("Full Screen Mode");
        fullScreenCheckBox.setLayoutX(50); // Adjust layout as needed
        fullScreenCheckBox.setLayoutY(100); // Adjust layout as needed

        // Set the checkbox to match the current full-screen status of the primary stage
        fullScreenCheckBox.setSelected(primaryStage.isFullScreen());


        return fullScreenCheckBox;
    }

    private static ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "800 x 600",
                "1024 x 768",
                "1280 x 720",
                "1920 x 1080"
        );

        comboBox.setLayoutX(50); // Adjust layout as needed
        comboBox.setLayoutY(50); // Adjust layout as needed
        return comboBox;
    }


    public Pane getRootPane() {
        return rootPane;
    }
}
