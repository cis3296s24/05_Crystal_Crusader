import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


public class PlayerObject {
    private String name;
    private int healthPoints;
    private int attack;
    private int defense;
    private int speed;
    private int skillPoints;
    private Item equippedWeapon;
    private List<Item> inventory;


    public PlayerObject(String name) {
        this.name = name;
        this.healthPoints = 100; //random number - we can change it later
        this.itemsFound = new ArrayList<>();
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

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getExperiencePoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void battle(Enemy enemy){
        System.out.println("A " + enemy.getName() + " appears!!");
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean battleOver = false;
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
                    double chance;
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

                //enemy turn

            }else{

            }
        }
    }

    public List<Item> getBattleItems(){
        
    }
}
