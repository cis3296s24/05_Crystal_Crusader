import java.util.List;

/*
This is just the basic idea, will definitely have to add more methods/variables. Its kind of hard to write
the class without knowing what the other classes will look like, and what our main will look like. Here is
some other ideas for methods that I had.

enterArea() method for when the player initially arrives in the area
searchArea() displays anything in the area (items, enemies, idk)
solveChallenge() I guess this would be the battle so idk if that goes here?
interactWithArea() different commands like "search" (to find item) or "use item" (to use it)
add method that displays something after a certain time after the player entered the area, maybe the enemy appears

 */
public class Area {
    private String name; //each area needs a name to identify it like an ID
    private String description; //each area needs a description

    //crystal object?

    /*
    these may not be needed
     */
    private boolean hasCrystal; //check if area has crystal
    private boolean hasItems; //check if area has items
    private boolean hasEnemies; //check is area has enemy
    private boolean isFinalBossArea; //check if its final boss area

    /*
    The type should be the Item object or Enemy object, but I don't know how were doing those classes yet.
     */
    private List<String> items; //get the list of items in the area (item objects)
    private List<String> enemies; //get the list of enemies in the area (enemy objects)
    private List<String> paths; //get the lists of areas you can travel to from here?

    //constructor
    public Area(String name, String description, boolean hasCrystal, boolean hasWeapons, boolean hasItems, boolean hasEnemies,
              boolean isFinalBossArea, List<String> items, List<String> enemies, List<String> paths){
        this.name = name;
        this.description = description;
        this.hasCrystal = hasCrystal;
        this.hasItems = hasItems;
        this.hasEnemies = hasEnemies;
        this.isFinalBossArea = isFinalBossArea;
        this.items = items;
        this.enemies = enemies;
        this.paths = paths;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void displayAreaInfo() {
        System.out.println(description);
        //Could show other info here like hints, weather, time of day, etc
    }

    public boolean hasCrystal(){
        return hasCrystal;
    }

    //Method to remove a crystal
    public void removeCrystal() {
        this.hasCrystal = false;
    }

    public boolean hasItems(){
        return hasItems;
    }

    public boolean hasEnemies(){
        return hasEnemies;
    }

    public boolean isFinalBossArea(){
        return isFinalBossArea;
    }

    public List<String> getItems(){
        return items;
    }

    //just in case we want
    public void addItem(String item) {
        items.add(item);
    }

    //everytime the user finds an item
    public void removeItem(String item) {
        items.remove(item);
    }

    public List<String> getEnemies(){
        return enemies;
    }

    public void addEnemy(String enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(String enemy) {
        enemies.remove(enemy);
    }

    //also add a method to move to the different areas that user can call with the different area ids (name)
    public List<String> getPaths(){
        return paths;
    }

}

