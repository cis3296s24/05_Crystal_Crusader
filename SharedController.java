import javafx.stage.Stage;

public class SharedController {
    private boolean fullScreen = false;
    private String resolution = "800 x 600";
    private Stage primaryStage;

    public SharedController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
        primaryStage.setFullScreen(fullScreen);
        System.out.println("Full screen mode set to: " + fullScreen);
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
        // Assume resolution format "width x height"
        String[] dimensions = resolution.split(" x ");
        if (dimensions.length == 2) {
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
            primaryStage.centerOnScreen();
        }
    }

    public void triggerFunctionOnSwitch() {
        // This method can be called when switching interfaces
        System.out.println("Function triggered due to interface switch.");
    }
}
