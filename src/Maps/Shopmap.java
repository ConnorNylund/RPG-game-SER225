package Maps;

import Game.ScreenCoordinator;
import Inventory.InventoryItem;
import Level.*;
// import NPCs.Bug;
import NPCs.Dinosaur;
import Scripts.HealthPickUpScript;
import Scripts.ItemPickUpScript;
// import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class Shopmap extends Map {

    public Shopmap(ScreenCoordinator screenCoordinator) {
        super("Shopmap.txt", new CommonTileset(), screenCoordinator);
        this.playerStartPosition = getMapTile(6, 11).getLocation();
        this.screenCoordinator = screenCoordinator;
        this.currentMap = 2;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        //PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        //enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        // walrus.setInteractScript(new WalrusScript());
        // npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(7, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        //Removing the NPCs we are not using

        // Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        // bug.setInteractScript(new BugScript());
        // npcs.add(bug);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    @Override
    public void loadScripts(ScreenCoordinator screenCoordinator) {
        //getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        //getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        //getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        //getMapTile(25, 18).setInteractScript(new SimpleTextScript("Eventually will be Shop"));

        getMapTile(1, 5).setInteractScript(new HealthPickUpScript());

        getMapTile(3, 3).setItemData(new InventoryItem("Pistol With Bayonet", "Weapons/download.png", 5));
        getMapTile(3, 3).setInteractScript(new ItemPickUpScript());
        getMapTile(4, 3).setItemData(new InventoryItem("Pistol With Bayonet", "Weapons/download.png", 5));
        getMapTile(4, 3).setInteractScript(new ItemPickUpScript());
        getMapTile(5, 3).setItemData(new InventoryItem("Pistol With Bayonet", "Weapons/download.png", 5));
        getMapTile(5, 3).setInteractScript(new ItemPickUpScript());

        getMapTile(3, 7).setItemData(new InventoryItem("Carrot Rocket Launcher", "Weapons/download (5).png", 20));
        getMapTile(3, 7).setInteractScript(new ItemPickUpScript());
        getMapTile(4, 7).setItemData(new InventoryItem("Carrot Rocket Launcher", "Weapons/download (5).png", 20));
        getMapTile(4, 7).setInteractScript(new ItemPickUpScript());
        getMapTile(5, 7).setItemData(new InventoryItem("Carrot Rocket Launcher", "Weapons/download (5).png", 20));
        getMapTile(5, 7).setInteractScript(new ItemPickUpScript());

        getMapTile(8, 3).setItemData(new InventoryItem("Small Machine Gun", "Weapons/download (6).png", 15));
        getMapTile(8, 3).setInteractScript(new ItemPickUpScript());
        getMapTile(9, 3).setItemData(new InventoryItem("Small Machine Gun", "Weapons/download (6).png", 15));
        getMapTile(9, 3).setInteractScript(new ItemPickUpScript());
        getMapTile(10, 3).setItemData(new InventoryItem("Small Machine Gun", "Weapons/download (6).png", 15));
        getMapTile(10, 3).setInteractScript(new ItemPickUpScript());

        getMapTile(8, 7).setItemData(new InventoryItem("Bloody Cleaver", "Weapons/download (1).png", 10));
        getMapTile(8, 7).setInteractScript(new ItemPickUpScript());
        getMapTile(9, 7).setItemData(new InventoryItem("Bloody Cleaver", "Weapons/download (1).png", 10));
        getMapTile(9, 7).setInteractScript(new ItemPickUpScript());
        getMapTile(10, 7).setItemData(new InventoryItem("Bloody Cleaver", "Weapons/download (1).png", 10));
        getMapTile(10, 7).setInteractScript(new ItemPickUpScript());

        getMapTile(12, 5).setItemData(new InventoryItem("Speed Potion", "Resources/speedpotion.png", 0));
        getMapTile(12, 5).setInteractScript(new ItemPickUpScript());

        getMapTile(7, 5).setInteractScript(new SimpleTextScript("Welcome to the Shop, take a look at the merchandise "));

        getMapTile(9, 8).setInteractScript(new SimpleTextScript("The Bow and Arrow; Cost 5 Coins "));
        getMapTile(9, 2).setInteractScript(new SimpleTextScript("The Machine Gun; Cost 15 Coins "));
        getMapTile(4, 2).setInteractScript(new SimpleTextScript("The Pistol Bayonet; Cost 5 Coins "));
        getMapTile(4, 8).setInteractScript(new SimpleTextScript("The Carrot Launcher; Cost 20 Coins "));
        getMapTile(12, 6).setInteractScript(new SimpleTextScript("Speed Potion Power up; Cost 20 Coins "));

        getMapTile(6, 11).setInteractScript(new ReturnScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }
}
