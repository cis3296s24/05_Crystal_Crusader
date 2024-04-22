import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {

    private String name;
    private String description;
    private int itemID; // be able to distinguish between duplicate items
    private int itemHealth; // remaining health points
    private int attackPwr;
    private boolean isEquipped;
    private List<String> abilities;
    private int potion; //item

    public Item(String name, String description, int itemID, int itemHealth, int attackPwr, List<String> abilities) {
        this.name = name;
        this.description = description;
        this.itemID = itemID;
        this.itemHealth = itemHealth;
        this.attackPwr = attackPwr;
        this.isEquipped = false;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getItemID() {
        return itemID;
    }

    public int getHealth() {
        return itemHealth;
    }

    public void restoreHealth(int health) {
        this.itemHealth += health;
    }

    public void reduceHealth(int health) {
        this.itemHealth -= health;
    }

    public int getAttackPwr() {
        return attackPwr;
    }

    public boolean toggleEquip() {
        isEquipped = !isEquipped;
        return isEquipped;
    }

    public boolean equipStatus() {
        return isEquipped;
    }

    public void addAbilities(String ability) {
        if (this.abilities == null) {
            this.abilities = new ArrayList<>();
        }
        this.abilities.add(ability);
    }

    public static List<Item> initializeItems() {
        //function for adding items
        List<Item> items = new ArrayList<>();
        //item id, item health, attack pwr numbers are placeholders for now
        items.add(new Item("Potion", "Restores some amount of health.", 101, 50, 0, Arrays.asList("Heal")));
        items.add(new Item("Shield", "Provides temporary protection.", 102, 0, 0, Arrays.asList("Increase Defense")));
        items.add(new Item("Sword", "A sharp blade that deals extra damage.", 103, 0, 20, Arrays.asList("Increase Attack")));
        items.add(new Item("Speed Boots", "Increases the player's speed.", 104, 0, 0, Arrays.asList("Increase Speed")));
        items.add(new Item("Weakening Dagger", "Lowers the enemy's defense.", 105, 0, 0, Arrays.asList("Decrease Enemy Defense")));

        return items;
    }


    public List<String> getAbilities() {
        return abilities;
    }

    public void use(PlayerObject player, Enemy enemy) {
        //have a series of if() statements checking the name of the item and doing the effect for that one
        //is passed the player and enemy in the fight so it can manipulate their stats if needed
        //(Example: healing, raising attack, defense, or speed, etc)

        for (String ability : abilities) {
            switch (ability.toLowerCase()) {
                case "heal": // Associated with the Potion
                    player.heal(itemHealth); // Assuming a heal method exists that adds health up to max health
                    System.out.println(player.getName() + " uses " + name + " and recovers " + itemHealth + " health points.");
                    break;
                case "increase defense": // Associated with the Shield
                    player.increaseDefense(5); // Assuming method that temporarily increases player defense
                    System.out.println(player.getName() + " uses " + name + " to increase defense.");
                    break;
                case "increase attack": // Associated with the Sword
                    player.increaseAttack(10); // Assuming method that temporarily increases player attack
                    System.out.println(player.getName() + " uses " + name + " to increase attack power.");
                    break;
                case "increase speed": // Associated with the Speed Boots
                    player.increaseSpeed(5); // Assuming a method that temporarily increases speed
                    System.out.println(player.getName() + " uses " + name + " to increase speed.");
                    break;
                case "decrease enemy defense": // Associated with the Weakening Dagger
                    enemy.decreaseDefense(5); // Assuming method that temporarily decreases enemy defense
                    System.out.println(player.getName() + " uses " + name + " to decrease " + enemy.getName() + "'s defense.");
                    break;
                default:
                    System.out.println(name + " does not seem to have any effect.");
                    break;
            }
        }
    }
}