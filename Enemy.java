import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Ideas to maybe add later:
    -magic stat (possibly list of spells enemies can use
     if at certain magic stat or higher?)
    -resistance stats for physical or magical damage
    -special(), which would check the name of the enemy
     and have a different effect depending on which it is
     could be healing or affecting stats, would have limited
     # of uses (dragon's fire breathing, goblin sharpening
     their weapon, etc.)
*/

public class Enemy {
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private int speed;
    private int xpWorth;
    private List<Item> itemDrops;
    boolean isDefeated;
    boolean isBoss;


    //adding var for damage and physical/magical resistance attributes
    private int damage;
    private int physicalResistance;
    private int magicalResistance;


    public Enemy(String name, int maxHealth, int attack, int defense, int speed, int damage, int xpWorth, boolean isDefeated, List<Item> itemDrops, boolean isBoss) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = this.maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.xpWorth = xpWorth;
        this.itemDrops = itemDrops;
        this.damage = damage; //(adding damage stat)
        this.isDefeated = isDefeated;
        //this.isBoss = isBoss;
    }

    public int attack(PlayerObject player) {
        //how much damage enemy can do
        int baseDamage = this.damage;
        //to make attack random
        int variability = (int) (Math.random() * 20 + 1); //example number for now (1-20 for variability)
        if (attack >= player.getDefense() * 2) {
            variability += 4;
        } else if (attack > player.getDefense()) {
            variability += 2;
        } else if (attack <= player.getDefense() / 2) {
            variability -= 4;
        } else if (attack < player.getDefense()) {
            variability -= 2;
        }
        int adjustedDamage = baseDamage * variability / 20;
        return adjustedDamage; //for now (change it later)
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getXpWorth() {
        return xpWorth;
    }

    public List<Item> getItemDrops() {
        return itemDrops;
    }

    public boolean checkIfDefeated() {
        return isDefeated;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0) {
            isDefeated = true;
        }
    }

    public void heal(int heal) {
        currentHealth += heal;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void raiseAttack(int amount) {
        attack += amount;
    }

    public void raiseDefense(int amount) {
        defense += amount;
    }

    public void raiseSpeed(int amount) {
        speed += amount;
    }

    public void lowerAttack(int amount) {
        attack -= amount;
    }

    public void lowerDefense(int amount) {
        defense -= amount;
    }

    public void lowerSpeed(int amount) {
        speed -= amount;
    }



    public static List<Enemy> initializeEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        // Note: These values are only temporary placeholders only. I will fix it later
        // Note: The 'false' parameter is for the 'isBoss' boolean flag.
        //enemies.add(new Enemy("Goblin", 30, 10, 5, 2, 15, 10, false, null, false));

        //try to keep this method and these enemies please
        enemies.add(new Enemy("Zombie", 50, 8, 10, 1, 10, 20, false, null, false));
        enemies.add(new Enemy("Skeleton", 40, 12, 8, 3, 12, 15, false, null, false));
        enemies.add(new Enemy("Dragon", 100, 20, 15, 5, 30, 50, false, null, false));

        return enemies;
    }


    @Override

    //to display name and stats
    public String toString() {
        return name + ": Health=" + currentHealth + ", Attack=" + attack + ", Defense=" + defense +
                ", Speed=" + speed + ", Damage=" + damage + ", XP=" + xpWorth + ", Drops=" + itemDrops;
    }

    public int getDamage(){
        return damage;
    }




    // Reduces the defense of the enemy.
    //note: this is called in the item.java's use() function
    public void decreaseDefense(int amount) {
        this.defense = Math.max(0, this.defense - amount);
        System.out.println(this.name + " has defense reduced by " + amount);
    }

}