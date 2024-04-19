import java.util.ArrayList;
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
public class Area{

   // Area castle = new Area("Castle", true, true, true, false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    private String name; //each area needs a name to identify it like an ID
    /*
    Basic Variables we could use
     */
    private boolean hasCrystal; //check if area has crystal
    private boolean hasItems; //check if area has items
    private boolean hasEnemies; //check is area has enemy
    private boolean isFinalBossArea; //check if its final boss area
    private List<Item> items; //get the list of items in the area (item objects)
    private List<Enemy> enemies; //get the list of enemies in the area (enemy objects)

    //constructor
    public Area(String name, boolean hasCrystal, boolean hasItems, boolean hasEnemies,
              boolean isFinalBossArea, List<Item> items, List<Enemy> enemies){
        this.name = name;
        this.hasCrystal = hasCrystal;
        this.hasItems = hasItems;
        this.hasEnemies = hasEnemies;
        this.isFinalBossArea = isFinalBossArea;
        this.items = items != null ? items : new ArrayList<>();  // Ensure items is never null
        this.enemies = enemies != null ? enemies : new ArrayList<>();  // Ensure enemies is never null
    }

    /*
    More important methods
     */
    public String enterArea(){
        String shortDescription = "";
        switch(this.getName()) {
            case "Cragrock Keep":
                shortDescription = "The gates of Cragrock Keep loom before you, cloaked in an unnatural fog. A chilling silence hangs in the air,\n " +
                        "broken only by your determined steps.\n";
                break;
            //other area names;
        }
        return shortDescription;
    }

    public String detailedDescription() {
        String detailedDescription = "";
        switch (this.getName()) {
            case "Cragrock Keep":
                String itemDescription = "";
                if (!getAreaItems().isEmpty()) {
                    Item firstItem = getAreaItems().get(0);
                    itemDescription = firstItem.getName();
                }

                String enemyDescription = "";
                if (!getEnemies().isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Enemy enemy : getEnemies()) {
                        if (sb.length() > 0) sb.append(", ");
                        sb.append(enemy.getName());
                    }
                    enemyDescription = sb.toString();
                }

                detailedDescription = "As you venture into the maw of Cragrock Keep, the air grows dense with a palpable darkness that seems to pulse with the keep's ancient\n" +
                        "and malevolent history. A few paces in, your eyes catch a glint of a" +  itemDescription + ". With cautious reverence, \n " +
                        "you pick it up, knowing it could be of use later on.\n\n" +
                        "A cacophony of eerie sounds begins to swell around you. There's the scrape of bone on stone, chillingly rhythmic. This is mingled\n" +
                        "with a low, guttural growl, the kind that could only belong to a creature corrupted beyond its natural form. A sudden, fleeting \n" +
                        "warmth washes over you, as if a great flame has briefly flickered to life and then died down. Then it hits you, you finally put\n" +
                        "it together—these sounds must be coming from" + enemyDescription + "They must be protecting the crystal, lurking in the darkness, each \n" +
                        "creature a sentinel set by the sorcerer to thwart any who dare to reclaim Eldoria's lost light\n";
                break;

            // Add other cases for different areas as necessary
        }
        return detailedDescription;
    }

    public static List<Area> initializeAreas() {
        List<Area> areas = new ArrayList();
        areas.add(new Area("Cragrock Keep", true, true, false, true, null, null));
        return areas;
    }

    /*
    Basic methods that we could use if helpful
     */
    public String getName(){
        return name;
    }
    public boolean hasCrystal(){
        return hasCrystal;
    }
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
    public List<Item> getAreaItems(){
        return items;
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
    public List<Enemy> getEnemies(){
        return enemies;
    }
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
}

