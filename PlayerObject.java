import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerObject {
   private int amount;
    private String name;
    private int healthPoints;
    private int currentHealth;
    private int attack;
    private int defense;
    private int speed;
    private int skillPoints;
    public Item equippedWeapon;
    public List<Item> inventory;
    // Input and output texts for UI
    private final StringProperty outputText = new SimpleStringProperty("Initial text ready...");
    private String inputText;
    private boolean readyToProceed = false;  // Flag to control flow

    public final GamePlay game;

    public int level;


    public PlayerObject(String name, GamePlay game) {
        this.name = name;
        this.healthPoints = 20; //random number - we can change it later
        this.currentHealth = healthPoints;
        this.attack = 5;
        this.defense = 5;
        this.speed = 5;
        this.inventory = new ArrayList<>();
        this.skillPoints = 0; //starting experience points (can increment in program)
        this.game = game;
        level = 1;
    }
    public void displayStatus() {
        System.out.println("player name: " + name);
        System.out.println("player's health points: " + healthPoints);
        System.out.println("player's beginning points: " + skillPoints);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense(){
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
        System.out.println("Defense set to: " + this.defense);
    }


    public int getAttack(){
        return attack;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

    public void setCurrentHealth(int hp){
        int x = currentHealth + hp;
        if(x < 0){
            currentHealth = 0;
        }else{
            currentHealth = x;
        }
    }

    public int getExperiencePoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    //returns 0 if player ran away, 1 if player won, and 2 if player lost
    public int battle(Enemy enemy, GamePage UI){
        Platform.runLater(UI.runnableSetOutput("You encountered a " + enemy.getName() + "!!\n(Click submit to continue)"));
        System.out.println("check");
        game.waitForInput();
        System.out.println("check");
        Random rand = new Random();
//        Scanner scan = new Scanner(System.in);
        boolean battleOver = false;
        double chance;
        while(!battleOver){
            if(this.speed >= enemy.getSpeed()){

                //player turn

                int playerChoice = 0;
                while(playerChoice != 1 && playerChoice != 2 && playerChoice != 3){
                    Platform.runLater(UI.runnableSetOutput("What will you do?\n1. Attack\n2. Item\n3. Run"));
                    game.waitForInput();

//                    playerChoice = scan.nextInt();
                    playerChoice = Integer.parseInt(UI.input);
                    switch(playerChoice){
                        case 1:
                            Platform.runLater(UI.runnableSetOutput("You attack with your " + equippedWeapon.getName() + "!\n(Click submit to continue)"));
                            game.waitForInput();
                            int enemyDefense = enemy.getDefense();
                            chance = rand.nextInt(19);
                            chance++;
                            if(attack >= enemyDefense * 2){
                                chance += 4;
                            }else if (attack > enemyDefense){
                                chance += 2;
                            }else if(attack <= enemyDefense / 2){
                                chance -= 4;
                            }else if(attack < enemyDefense){
                                chance -= 2;
                            }
                            double attackPercent = chance / 20.0;
                            double damage = attackPercent * equippedWeapon.getAttackPwr();
                            int convertedDamage = (int) damage;
                            Platform.runLater(UI.runnableSetOutput("You did " + convertedDamage + " damage!!\n(Click submit to continue)"));
                            game.waitForInput();
                            enemy.takeDamage(convertedDamage);
                            if(enemy.checkIfDefeated()){
                                battleOver = true;
                            }
                            break;
                        case 2:
                            int itemChoice = 0;
                            List<Item> battleItems = getBattleItems();
                            if(battleItems.size() == 0){
                                Platform.runLater(UI.runnableSetOutput("You dont have any items you can use right now\n(CLick submit to continue)"));
                                game.waitForInput();
                                playerChoice = 0;
                            }else{
                                while(itemChoice == 0){
                                    String itemList = "";
                                    itemList += "Choose an item:\n";
                                    for(int i = 0; i < battleItems.size(); i++){
                                        itemList += (i + 1) + ": " + battleItems.get(i).getName() + "\n";
                                    }
                                    itemList += (battleItems.size() + 1) + ": Back";
//                                    itemChoice = scan.nextInt();
                                    Platform.runLater(UI.runnableSetOutput(itemList));
                                    game.waitForInput();
                                    itemChoice = Integer.parseInt(UI.input);
                                    if(itemChoice != 0 && itemChoice < battleItems.size() + 1){
                                        Platform.runLater(UI.runnableSetOutput(battleItems.get(itemChoice - 1).use(this, enemy)));
                                        game.waitForInput();
                                    }else if(itemChoice == battleItems.size() + 1){
                                        playerChoice = 0;
                                    }else{
                                        Platform.runLater(UI.runnableSetOutput("That's not an option\n(Click submit to continue)"));
                                        game.waitForInput();
                                        itemChoice = 0;
                                    }
                                }
                            }
                            break;
                        case 3:
                            if(enemy.isBoss){
                                Platform.runLater(UI.runnableSetOutput("You can't run from this fight\n(Click submit to continue)"));
                                game.waitForInput();
                                playerChoice = 0;
                            }else{
                                Platform.runLater(UI.runnableSetOutput("You try to run away!!\n(Click submit to continue)"));
                                game.waitForInput();
                                chance = rand.nextInt(10);
                                if(chance <= 7){
                                    battleOver = true;
                                }else{
                                    Platform.runLater(UI.runnableSetOutput("You couldn't get away!!\n(Click submit to continue)"));
                                    game.waitForInput();
                                }
                            }
                            break;
                        default:
                            Platform.runLater(UI.runnableSetOutput("That's not an option!\n(Click submit to continue)"));
                            game.waitForInput();
                            break;
                    }
                }

                // check if enemy is defeated or if player ran

                if(battleOver == true){
                    break;
                }

                //enemy turn
                Platform.runLater(UI.runnableSetOutput("The " + enemy.getName() + " attacks!!\n(Click submit to continue)"));
                game.waitForInput();
                chance = rand.nextInt(19) + 1;
                if(enemy.getAttack() >= defense * 2){
                    chance += 4;
                }else if (enemy.getAttack() > defense){
                    chance += 2;
                }else if(enemy.getAttack() <= defense / 2){
                    chance -= 4;
                }else if(enemy.getAttack() < defense){
                    chance -= 2;
                }
                double attackPercent = chance / 20.0;
                double damage = attackPercent * enemy.getDamage();
                int convertedDamage = (int) damage;
                System.out.println("You took " + convertedDamage + " damage!!");
                Platform.runLater(UI.runnableSetOutput("You took " + convertedDamage + " damage!!\n(Click submit to continue)"));
                game.waitForInput();
                setCurrentHealth(getCurrentHealth() - convertedDamage);

                if(getCurrentHealth() <= 0){
                    battleOver = true;
                }
            }else{

                //enemy turn

                Platform.runLater(UI.runnableSetOutput("The " + enemy.getName() + " attacks!!\n(Click submit to continue)"));
                game.waitForInput();
                chance = rand.nextInt(19) + 1;
                if(enemy.getAttack() >= defense * 2){
                    chance += 4;
                }else if (enemy.getAttack() > defense){
                    chance += 2;
                }else if(enemy.getAttack() <= defense / 2){
                    chance -= 4;
                }else if(enemy.getAttack() < defense){
                    chance -= 2;
                }
                double attackPercent = chance / 20.0;
                double damage = attackPercent * enemy.getDamage();
                int convertedDamage = (int) damage;
                System.out.println("You took " + convertedDamage + " damage!!");
                Platform.runLater(UI.runnableSetOutput("You took " + convertedDamage + " damage!!\n(Click submit to continue)"));
                game.waitForInput();
                setCurrentHealth(getCurrentHealth() - convertedDamage);

                if(getCurrentHealth() == 0){
                    battleOver = true;
                }

                //check if player has been defeated

                if(battleOver == true){
                    break;
                }

                //player turn

                int playerChoice = 0;
                while(playerChoice != 1 && playerChoice != 2 && playerChoice != 3){
                    Platform.runLater(UI.runnableSetOutput("What will you do?\n1. Attack\n2. Item\n3. Run"));
                    game.waitForInput();

//                    playerChoice = scan.nextInt();
                    playerChoice = Integer.parseInt(UI.input);
                    switch(playerChoice){
                        case 1:
                            Platform.runLater(UI.runnableSetOutput("You attack with your " + equippedWeapon.getName() + "!\n(Click submit to continue)"));
                            game.waitForInput();
                            int enemyDefense = enemy.getDefense();
                            chance = rand.nextInt(19);
                            chance++;
                            if(attack >= enemyDefense * 2){
                                chance += 4;
                            }else if (attack > enemyDefense){
                                chance += 2;
                            }else if(attack <= enemyDefense / 2){
                                chance -= 4;
                            }else if(attack < enemyDefense){
                                chance -= 2;
                            }
                            attackPercent = chance / 20.0;
                            damage = attackPercent * equippedWeapon.getAttackPwr();
                            convertedDamage = (int) damage;
                            Platform.runLater(UI.runnableSetOutput("You did " + convertedDamage + " damage!!\n(Click submit to continue)"));
                            game.waitForInput();
                            enemy.takeDamage(convertedDamage);
                            if(enemy.checkIfDefeated()){
                                battleOver = true;
                            }
                            break;
                        case 2:
                            int itemChoice = 0;
                            List<Item> battleItems = getBattleItems();
                            if(battleItems.size() == 0){
                                Platform.runLater(UI.runnableSetOutput("You dont have any items you can use right now\n(CLick submit to continue)"));
                                game.waitForInput();
                                playerChoice = 0;
                            }else{
                                while(itemChoice == 0){
                                    String itemList = "";
                                    itemList += "Choose an item:\n";
                                    for(int i = 0; i < battleItems.size(); i++){
                                        itemList += (i + 1) + ": " + battleItems.get(i).getName() + "\n";
                                    }
                                    itemList += (battleItems.size() + 1) + ": Back";
//                                    itemChoice = scan.nextInt();
                                    Platform.runLater(UI.runnableSetOutput(itemList));
                                    game.waitForInput();
                                    itemChoice = Integer.parseInt(UI.input);
                                    if(itemChoice != 0 && itemChoice < battleItems.size() + 1){
                                        Platform.runLater(UI.runnableSetOutput(battleItems.get(itemChoice - 1).use(this, enemy)));
                                        game.waitForInput();
                                    }else if(itemChoice == battleItems.size() + 1){
                                        playerChoice = 0;
                                    }else{
                                        Platform.runLater(UI.runnableSetOutput("That's not an option\n(Click submit to continue)"));
                                        game.waitForInput();
                                        itemChoice = 0;
                                    }
                                }
                            }
                            break;
                        case 3:
                            if(enemy.isBoss){
                                Platform.runLater(UI.runnableSetOutput("You can't run from this fight\n(Click submit to continue)"));
                                game.waitForInput();
                                playerChoice = 0;
                            }else{
                                Platform.runLater(UI.runnableSetOutput("You try to run away!!\n(Click submit to continue)"));
                                game.waitForInput();
                                chance = rand.nextInt(10);
                                if(chance <= 7){
                                    battleOver = true;
                                }else{
                                    Platform.runLater(UI.runnableSetOutput("You couldn't get away!!\n(Click submit to continue)"));
                                    game.waitForInput();
                                }
                            }
                            break;
                        default:
                            Platform.runLater(UI.runnableSetOutput("That's not an option!\n(Click submit to continue)"));
                            game.waitForInput();
                            break;
                    }

                }
            }
        }

        if(enemy.getCurrentHealth() <= 0){
            Platform.runLater(UI.runnableSetOutput("You won!!\nYou got " + enemy.getXpWorth() + " exp\n(Click submit to continue)"));
            game.waitForInput();
            skillPoints += enemy.getXpWorth();
            int rewardChance = 0;
            if(enemy.getItemDrops().size() > 0){
                rewardChance = rand.nextInt(enemy.getItemDrops().size());
            }
            Item reward = enemy.getItemDrops().get(rewardChance);
            Platform.runLater(UI.runnableSetOutput("You found a " + reward.getName() + "\n(Click submit to continue)"));
            game.waitForInput();
            inventory.add(reward);
            return 2;
        }else if (currentHealth <= 0){
            return 1;
        }else{
            Platform.runLater(UI.runnableSetOutput("You got away safely!\n(Click submit to continue)"));
            game.waitForInput();
            return 0;
        }
    }

    public List<Item> getBattleItems(){
        List<Item> battleItems = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getAttackPwr() == 0){
                battleItems.add(inventory.get(i));
            }
        }
        return battleItems;
    }

    public List<Item> getWeapons(){
        List<Item> weapons = new ArrayList<>();
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getAttackPwr() != 0){
                weapons.add(inventory.get(i));
            }
        }
        return weapons;
    }

    // Adds health to the player up to their maximum health points.
    /// preet functions for implementing use() function in item.java
    public void heal(int amount) {
        this.currentHealth = Math.min(this.healthPoints, this.currentHealth + amount);
        System.out.println(this.name + " heals for " + amount + " HP.");
    }
    // Temporarily increases player's defense.
    public void increaseDefense(int amount) {
        this.defense += amount;
        System.out.println(this.name + " increases defense by " + amount);
    }

    public void increaseAttack(int amount) {
        this.attack += amount;
        System.out.println(this.name + " increases attack by " + amount);
    }

    // Temporarily increases player's speed.
    public void increaseSpeed(int amount) {
        this.speed += amount;
        System.out.println(this.name + " increases speed by " + amount);
    }

    public void levelUp(GamePage UI){
        if(skillPoints >= level * 10){
            skillPoints -= level * 10;
            level++;
            int playerChoice = 0;
            while(playerChoice == 0) {
                Platform.runLater(UI.runnableSetOutput("Level up!!\nChoose a stat to increase:\n1. attack\n2. defense\n3. speed"));
                game.waitForInput();
                playerChoice = Integer.parseInt(UI.input);
                switch(playerChoice){
                    case 1:
                        increaseAttack(5);
                        Platform.runLater(UI.runnableSetOutput("Your attack increased!"));
                        game.waitForInput();
                        break;
                    case 2:
                        increaseDefense(5);
                        Platform.runLater(UI.runnableSetOutput("Your defense increased!"));
                        game.waitForInput();
                        break;
                    case 3:
                        increaseSpeed(5);
                        Platform.runLater(UI.runnableSetOutput("Your speed increased!"));
                        game.waitForInput();
                        break;
                    default:
                        Platform.runLater(UI.runnableSetOutput("That's not an option."));
                        playerChoice = 0;
                        game.waitForInput();
                        break;
                }
            }
        }
    }





}



