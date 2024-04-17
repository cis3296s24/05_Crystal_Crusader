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

    public Item(String name, String description, int itemID, int itemHealth, int attackPwr, List<String> abilities) {
        this.name = name;
        this.description = description;
        this.itemID = itemID;
        this.itemHealth = itemHealth;
        this.attackPwr = attackPwr;
        this.isEquipped = false;
        this.abilities = new ArrayList<>();
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
        if(this.abilities == null) {
            this.abilities = new ArrayList<>();
        }
        this.abilities.add(ability);
    }
    public static List<Item> initializeItems() {
        //function for adding items

        List<Item> items = new ArrayList<>();
        //item id, item health, attack pwr numbers are placeholders for now
        items.add(new Item("Sword", "A sharp blade suitable for combat.", 101, 100, 15, Arrays.asList("Slice", "Dice")));
        items.add(new Item("Shield", "Protects the user from attacks.", 102, 150, 5, Arrays.asList("Block", "Reflect")));
        items.add(new Item("Healing Potion", "Restores health when used.", 103, 1, 0, Arrays.asList("Heal")));
        items.add(new Item("Magic Wand", "A mystical wand that emits magical energies.", 104, 80, 25, Arrays.asList("Cast Spell", "Magic Boost")));
        items.add(new Item("Battle Armor", "Provides excellent protection during combat.", 105, 200, 10, Arrays.asList("Increase Defense", "Reflect Damage")));
        return items;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void use(PlayerObject player, Enemy enemy){
        //have a series of if() statements checking the name of the item and doing the effect for that one
        //is passed the player and enemy in the fight so it can manipulate their stats if needed
        //(Example: healing, raising attack, defense, or speed, etc)
    }
}