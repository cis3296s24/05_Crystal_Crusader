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
                shortDescription = "The gates of Cragrock Keep loom before you, cloaked in an unnatural fog. A chilling silence hangs in the air, " +
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
                detailedDescription = "As you venture into the maw of Cragrock Keep, the air grows dense with a palpable darkness that " +
                        "seems to pulse with the keep's ancient and dark history. Just a few paces in, your eyes catch a glint of something unusual—a skeletal figure, " +
                        "half-buried in the debris. Grasping your small sword tightly, you steel your nerves and proceed deeper into the castle." +
                        " A chatter of eerie sounds begins to swell around you. The chillingly rhythmic scrape of bone on stone mingles with a low, jarring growl—the " +
                        "kind that could only belong to a creature corrupted beyond its natural form. Suddenly, a fleeting warmth washes over you, as if a great flame has " +
                        "briefly flickered to life and then died down. Yet, undeterred, you continue on. The crystal must be reclaimed, not just " +
                        "for your own sake, but for the fate of Eldoria itself.\n(Click submit to continue)";
                break;
        }
        return detailedDescription;
    }

    public String interactWithArea(){
        String interaction = "";
        switch (this.getName()){
            case "Cragrock Keep":
                String items = "";
                List<Item> itemList = getAreaItems();
                if (!itemList.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    int size = itemList.size();
                    for (int i = 0; i < size; i++) {
                        if (i > 0) {
                            if (i == size - 1) {
                                sb.append(", and ");
                            } else {
                                sb.append(", ");
                            }
                        }
                        sb.append(itemList.get(i).getName());
                    }
                    items = sb.toString();
                }

                String enemies = "";
                List<Enemy> enemyList = getEnemies();
                if (!getEnemies().isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    int size = enemyList.size();
                    for (int i = 0; i < size; i++) {
                        if (i > 0) {
                            if (i == size - 1) {
                                sb.append(", and ");
                            } else {
                                sb.append(", ");
                            }
                        }
                        sb.append(enemyList.get(i).getName());
                    }
                    enemies = sb.toString();
                }
                interaction = "As you venture deeper into the shadows of Cragrock Keep, a ghostly figure begins to manifest " +
                        "before you. It's barely visible at first, like a wisp of smoke caught in the dim light, but gradually, " +
                        "the figure becomes more distinct, revealing a knight clad in spectral armor.\n\n" +
                        "'Brave soul, heed my words,' the apparition speaks with a voice that resonates through the cold air. " +
                        "'I am Gustus, once a guardian of the Crystal. My failure has condemned me to this ethereal existence. " +
                        "But you, you have the power to set things right.'\n\n" +
                        "You listen intently as Gustus continues, 'To reclaim the crystal and restore balance to Eldoria, you " +
                        "must overcome formidable guardians left by Valrak. A " + enemies + ".’\n\n" +
                        "His form flickering like a candle in the wind, Gaius imparts one last piece of advice, 'Within this " +
                        "castle lie artifacts of great power. A " + items + ". You must seek them out, they are essential to overcoming " +
                        "the foes that await you.’. With that, the figure of Gaius dissolves into the air, leaving behind a trail " +
                        "of mist that leads you onward.\n(Click submit to continue)";
                break;

        }
        return interaction;
    }

    public static List<Area> initializeAreas() {
        List<Area> areas = new ArrayList();
        areas.add(new Area("Cragrock Keep", true, true, true, true, Item.initializeItems(), Enemy.initializeEnemies()));
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

