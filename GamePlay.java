import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class GamePlay implements Runnable{

    public GamePage UI;
    private String inputText;
    private boolean readyToProceed = false;  // Flag to control flow

    public GamePlay(GamePage UI){
        this.UI = UI;
    }

    // Input method
    //public StringProperty getOutput(String input) {
    //    inputText = input;

    //    return outputText;
    //}
    // Unlock method
    public void proceed() {
        synchronized (this) {
            readyToProceed = true;
            notifyAll();  // Wake up all waiting threads
        }
    }
    // Lock method
    public void waitForInput()
    {
        synchronized (this) {
            while (!readyToProceed) {
                try {
                    wait();  // Call wait() on the instance 'lock', not 'Thread' or statically
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Game was interrupted unexpectedly");
                    // Optionally handle the interrupt according to your needs
                }
            }
        }
        readyToProceed = false;  // Reset the flag for the next round
    }

    public void run(){
        UI.setOutput("Welcome to Crystal Crusader!!\n(Click submit to continue)");
        waitForInput();
        String testMessage = UI.input;
        UI.setOutput("You typed in: " + testMessage);
    }
}
