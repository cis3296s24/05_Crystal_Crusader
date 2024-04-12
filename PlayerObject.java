import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class PlayerObject {
    private String name;
    private int healthPoints;
    private int currentHealth;
    private int attack;
    private int defense;
    private int speed;
    private int skillPoints;
    private Item equippedWeapon;
    private List<Item> inventory;


    public PlayerObject(String name) {
        this.name = name;
        this.healthPoints = 100; //random number - we can change it later
        this.currentHealth = healthPoints;
        this.inventory = new ArrayList<>();
        this.skillPoints = 0; //starting experience points (can increment in program)

    }
    public static void main(String[]args) {
        //main class
        PlayerObject player = new PlayerObject(""); //empty string for now

        // call other methods here later and then display class
        player.displayStatus(); //will display name, points etc
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
    public int battle(Enemy enemy){
        System.out.println("A " + enemy.getName() + " appears!!");
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean battleOver = false;
        double chance;
        while(!battleOver){
            if(this.speed >= enemy.getSpeed()){
                
                //player turn

                int playerChoice = 0;
                while(playerChoice != 1 && playerChoice != 2 && playerChoice != 3){
                    System.out.println("What will you do?");
                    System.out.println("1: Attack");
                    System.out.println("2: Item");
                    System.out.println("3: Run");
                    playerChoice = scan.nextInt();
                    switch(playerChoice){
                        case 1:
                            System.out.println("You attack with your " + equippedWeapon.getName() + "!");
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
                            System.out.println("You did " + convertedDamage + " damage!!");
                            enemy.takeDamage(convertedDamage);
                            if(enemy.checkIfDefeated()){
                                battleOver = true;
                            }
                        case 2:
                            int itemChoice = 0;
                            List<Item> battleItems = getBattleItems();
                            if(battleItems.size() == 0){
                                System.out.println("You dont have any items you can use right now");
                                playerChoice = 0;
                            }else{
                                while(itemChoice == 0){
                                    System.out.println("Choose an item:");
                                    for(int i = 0; i < battleItems.size(); i++){
                                        System.out.println((i + 1) + ": " + battleItems.get(i).getName());
                                    }
                                    System.out.println((battleItems.size() + 1) + ": Back");
                                    itemChoice = scan.nextInt();
                                    if(itemChoice != 0 && itemChoice < battleItems.size() + 1){
                                        battleItems.get(itemChoice).use(this, enemy);
                                    }else if(itemChoice == battleItems.size() + 1){
                                        playerChoice = 0;
                                    }else{
                                        System.out.println("Thats not an option");
                                    }
                                }
                            }
                        case 3:
                            if(enemy.isBoss){
                                System.out.println("You can't run from this fight.");
                                playerChoice = 0;
                            }else{
                                System.out.println("You try to run away!!");
                                chance = rand.nextInt(1);
                                if(chance == 1){
                                    System.out.println("You got away!!");
                                    battleOver = true;
                                }else{
                                    System.out.println("You couldn't get away!!");
                                }
                            }
                            break;
                        default:
                            System.out.println("Thats not an option!");
                            break;
                    }
                }

                // check if enemy is defeated or if player ran
                
                if(battleOver == true){
                    break;
                }

                //enemy turn
                
                System.out.println("The " + enemy.getName() + " attacks!!");
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
                setCurrentHealth(getCurrentHealth() - convertedDamage);

                if(getCurrentHealth() == 0){
                    battleOver = true;
                }
            }else{

                //enemy turn

                System.out.println("The " + enemy.getName() + " attacks!!");
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
                    System.out.println("What will you do?");
                    System.out.println("1: Attack");
                    System.out.println("2: Item");
                    System.out.println("3: Run");
                    playerChoice = scan.nextInt();
                    switch(playerChoice){
                        case 1:
                            System.out.println("You attack with your " + equippedWeapon.getName() + "!");
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
                            System.out.println("You did " + convertedDamage + " damage!!");
                            enemy.takeDamage(convertedDamage);
                            if(enemy.checkIfDefeated()){
                                battleOver = true;
                            }
                        case 2:
                            int itemChoice = 0;
                            List<Item> battleItems = getBattleItems();
                            if(battleItems.size() == 0){
                                System.out.println("You dont have any items you can use right now");
                                playerChoice = 0;
                            }else{
                                while(itemChoice == 0){
                                    System.out.println("Choose an item:");
                                    for(int i = 0; i < battleItems.size(); i++){
                                        System.out.println((i + 1) + ": " + battleItems.get(i).getName());
                                    }
                                    System.out.println((battleItems.size() + 1) + ": Back");
                                    itemChoice = scan.nextInt();
                                    if(itemChoice != 0 && itemChoice < battleItems.size() + 1){
                                        battleItems.get(itemChoice).use(this, enemy);
                                    }else if(itemChoice == battleItems.size() + 1){
                                        playerChoice = 0;
                                    }else{
                                        System.out.println("Thats not an option");
                                    }
                                }
                            }
                        case 3:
                            if(enemy.isBoss){
                                System.out.println("You can't run from this fight.");
                                playerChoice = 0;
                            }else{
                                System.out.println("You try to run away!!");
                                chance = rand.nextInt(1);
                                if(chance == 1){
                                    System.out.println("You got away!!");
                                    battleOver = true;
                                }else{
                                    System.out.println("You couldn't get away!!");
                                }
                            }
                            break;
                        default:
                            System.out.println("Thats not an option!");
                            break;
                    }
                }
            }
        }

        if(currentHealth == 0){
            System.out.println("You won!!");
            System.out.println("You got " + enemy.getXpWorth() + " exp");
            skillPoints += enemy.getXpWorth();
            int rewardChance = 0;
            if(enemy.getItemDrops().size() > 0){
                rewardChance = rand.nextInt(enemy.getItemDrops().size() + 1);
            }
            Item reward = enemy.getItemDrops().get(rewardChance);
            System.out.println("You found a " + reward.getName());
            inventory.add(reward);
            return 2;
        }else if (enemy.getCurrentHealth() == 0){
            System.out.println("You were defeated...");
            return 1;
        }else{
            System.out.println("You got away safely!");
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
}
