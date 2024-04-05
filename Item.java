import java.util.List;

public class Item {

    private String name;
    private String description;
    private int itemID; // be able to distinguish between duplicate items
    private int itemHealth; // remaining health points
    private int attackPwr;
    private boolean isEquipped;
    private List<String> abilities;
    
    public Item(String name, String description, int itemID, int itemHealth, int attackPwr) {
        this.name = name;
        this.description = description;
        this.itemID = itemID;
        this.itemHealth = itemHealth;
        this.attackPwr = attackPwr;
        this.isEquipped = false;
        this.abilities = null;
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

    public String addAbilities(String ability) {
        this.abilities.add(ability);
        return ability;
    }

    public List<String> getAbilities() {
        return abilities;
    }
}