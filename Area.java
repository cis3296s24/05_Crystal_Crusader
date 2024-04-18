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
        this.items = items;
        this.enemies = enemies;
    }

    /*
    More important methods
     */
    public String enterArea(){
        String shortDescription = "";
        switch(this.getName()) {
            case "Castle":
                shortDescription = "A looming castle with towering stone walls and heavy iron gates.";
                break;
            //other area names;
        }
        return shortDescription;
    }

    public String detailedDescription(){
        String detailedDescription = "";
        switch(this.getName()){
            case "Castle":
                detailedDescription = "Perched atop a craggy hill, the ancient Ravenhold Castle dominates the landscape, " +
                    "its imposing stone walls weathered by centuries of battle and storm. As you approach, the sheer " +
                    "scale of the fortress becomes apparent, with its battlements casting long shadows across the barren landscape. " +
                    "The massive iron gates are etched with the emblems of forgotten royal lines, and they creak ominously as they " +
                    "swing open to grant you entry Inside, the grand courtyard is bordered by towering walls and dotted with statues of " +
                    "past monarchs, each telling a silent tale of glory and despair. Beyond lies the main keep, where intricate tapestries " +
                    "and dusty velvet drapes line the halls, illuminated by flickering torches set in sconces along the walls. Echoes of " +
                    "footsteps resonate through the vast chambers, a reminder of the castle's eerie solitude. Secret passages hidden behind " +
                    "moving bookcases and under stairwells lead to forgotten rooms, each holding relics of the castle’s storied past. The air " +
                    "is cool and carries a faint mustiness, the scent of time that no amount of breeze through the arrow slits can displace. " +
                    "Each corner of the castle, from the lofty towers to the deep dungeons, whispers secrets of ancient intrigues and the " +
                    "echoes of long-lost battles";
                break;

            //other area names
        }
        return detailedDescription;
    }

    public static List<Area> initializeAreas() {
        List<Area> areas = new ArrayList<>();
        areas.add(new Area("Castle", true, true, true, false, Item.initializeItems(), Enemy.initializeEnemies()));
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

