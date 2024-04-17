/*
    I'm making this class as an example of what our main could look like. I don't understand
    the UI or the GamePage/GamePlay classes yet, so respectfully I don't know how to make a
    working main. But if we could just alter this code a little to fit the UI, then the code will
    be helpful. I also don't understand everyone else's classes, so obviously my main has to
    altered/enhanced significantly.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import java.util.List;
/*
public class Game {
    public static void gameStart(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PlayerObject player = new PlayerObject("Hero");
        /*
            I designed my area class in a way that this one call initializes the area,
            item, and enemy objects. It works like this rn since we only have one area.
            Could change it to work with multiple, but for now we may just have one area.
         */
/*
        List<Area> areas = Area.initializeAreas();

        //add the backstory
        System.out.println("Welcome...");
        System.out.println("Insane Backstory\n");

        //only have on area in the list anyways.
        Area currentArea = areas.get(0);

        //here's the loop
        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("You are currently in: " + currentArea.getName()); //only have one area
            System.out.println(currentArea.enterArea()); //prints short description of the area

            // Player choices
            System.out.println("Choose an action:");
            System.out.println("1: Search Area"); // Provide a detailed description
            System.out.println("2. Interact With Area");
            System.out.println("3: Battle");
            System.out.println("4: Exit Game");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            /*
                I'm aware that your classes have other methods we could call for each case that
                could enhance the experience/game. We can add them.
             */
/*
            switch (choice) {
                case 1: //needs work
                    System.out.println(currentArea.detailedDescription()); //more detailed description
                    //I'm going to add that it prints the current items/enemies in the area as well.
                    break;
                case 2:
                    //here is where you could collect the items you want (if thats a thing)
                    //here you could also talk to side characters
                case 3: //Here's the battle option. Idk how battle works that well.
                    if (currentArea.hasEnemies()){ //method I added in my area class
                        Enemy enemy = currentArea.getEnemies().get(0); //take first enemy (initalizeEnemies assigns all the enemies to one area currently)
                        int result = player.battle(enemy); //I know this takes the game page UI as well, but idk how that works
                        if (result == 1) {
                            System.out.println("You were defeated..."); //I think the battle method already has print statements
                            gameRunning = false; //end game if u loose I assume
                        } else if (result == 2) {
                            System.out.println("You won the battle!");
                        }
                    } else {
                        System.out.println("No enemies are here to fight.");
                    }
                    break;
                case 4: // Exit the game
                    System.out.println("Exiting game..."); //I know we may not need this, but just in case
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
*/