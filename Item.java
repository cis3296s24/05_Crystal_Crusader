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

    private boolean readyToProceed = false;  // Flag to control flow
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
        items.add(new Item("Shining Sword", "A sharp blade that deals extra damage.", 103, 0, 20, Arrays.asList("Increase Attack")));
        items.add(new Item("Speed Boots", "Increases the player's speed.", 104, 0, 0, Arrays.asList("Increase Speed")));
        items.add(new Item("Weakening Dagger", "Lowers the enemy's defense.", 105, 0, 0, Arrays.asList("Decrease Enemy Defense")));

        return items;
    }


    public List<String> getAbilities() {
        return abilities;
    }

    public String use(PlayerObject player, Enemy enemy) {
        //have a series of if() statements checking the name of the item and doing the effect for that one
        //is passed the player and enemy in the fight so it can manipulate their stats if needed
        //(Example: healing, raising attack, defense, or speed, etc)

        //replace sout with platform.runlater
        for (String ability : abilities) {
            switch (ability.toLowerCase()) {
                case "heal": // Associated with the Potion
                    player.heal(itemHealth); // Assuming a heal method exists that adds health up to max health
                    return player.getName() + " uses " + name + " and recovers " + itemHealth + " health points.";

                case "increase defense": // Associated with the Shield
                    player.increaseDefense(5); // Assuming method that temporarily increases player defense
                    return player.getName() + " uses " + name + " to increase defense.";

                case "increase attack": // Associated with the Sword
                    player.increaseAttack(10); // Assuming method that temporarily increases player attack
                    return player.getName() + " uses " + name + " to increase attack power.";

                case "increase speed": // Associated with the Speed Boots
                    player.increaseSpeed(5); // Assuming a method that temporarily increases speed
                    return player.getName() + " uses " + name + " to increase speed.";

                case "decrease enemy defense": // Associated with the Weakening Dagger
                    enemy.decreaseDefense(5); // Assuming method that temporarily decreases enemy defense
                    return player.getName() + " uses " + name + " to decrease " + enemy.getName() + "'s defense.";

                default:
                    return name + "does not have any effect";

            }
        }
        return "invalid item ability";
    }
}