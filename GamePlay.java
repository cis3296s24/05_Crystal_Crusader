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
        PlayerObject player = new PlayerObject("Hero");
        List<Area> areas = Area.initializeAreas();
        //add the backstory
        Platform.runLater(UI.runnableSetOutput("Welcome...\n(Insert backstory here)\n(Click submit to continue)"));

        waitForInput();
        //only have on area in the list anyways.

        Area currentArea = areas.get(0);

        //here's the loop
        boolean gameRunning = true;
        while (gameRunning) {
            Platform.runLater(UI.runnableSetOutput("You are currently in :" + currentArea.getName() + "\n" + currentArea.getName() + "\n(ClickSubmit to continue)"));
            waitForInput();
            // Player choices
            Platform.runLater(UI.runnableSetOutput("Choose an action:\n1: Search Area\n2. Interact With Area\n3: Battle\n4: Exit Game"));
            waitForInput();
            String choice = UI.input;

            /*
                I'm aware that your classes have other methods we could call for each case that
                could enhance the experience/game. We can add them.
             */
            switch (choice) {
                case "1": //needs work
                    Platform.runLater(UI.runnableSetOutput(currentArea.detailedDescription())); //more detailed description
                    //I'm going to add that it prints the current items/enemies in the area as well.
                    waitForInput();
                    break;
                case "2":
                    //here is where you could collect the items you want (if thats a thing)
                    //here you could also talk to side characters
                case "3": //Here's the battle option. Idk how battle works that well.
                    if (currentArea.hasEnemies()){ //method I added in my area class
                        Enemy enemy = currentArea.getEnemies().get(0); //take first enemy (initalizeEnemies assigns all the enemies to one area currently)
                        int result = player.battle(enemy, UI); //I know this takes the game page UI as well, but idk how that works
                        if (result == 1) {
                            Platform.runLater(UI.runnableSetOutput("You were defeated...")); //I think the battle method already has print statements
                            gameRunning = false; //end game if u loose I assume
                        } else if (result == 2) {
                            Platform.runLater(UI.runnableSetOutput("You won the battle!"));
                        }
                    } else {
                        Platform.runLater(UI.runnableSetOutput("No enemies are here to fight."));
                    }
                    waitForInput();
                    break;
                case "4": // Exit the game
                    System.out.println("Exiting game..."); //I know we may not need this, but just in case
                    gameRunning = false;
                    break;
                default:
                    Platform.runLater(UI.runnableSetOutput("Invalid choice. Please try again."));
                    waitForInput();
                    break;
            }
        }
    }
}
