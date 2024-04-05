import java.util.ArrayList;
import java.util.List;


public class PlayerObject {
    private String name;
    private int healthPoints;
    private int skillPoints;
    private List<String> itemsFound;


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


}
