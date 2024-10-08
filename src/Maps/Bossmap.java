package Maps;

import EnhancedMapTiles.PushableRock;
import Game.ScreenCoordinator;
import Level.*;
// import NPCs.Bug;
// import NPCs.Dinosaur;
// import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class Bossmap extends Map {

    public Bossmap(ScreenCoordinator screenCoordinator) {
        super("Bossmap.txt", new CommonTileset(), screenCoordinator);
        this.playerStartPosition = getMapTile(5, 5).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        // enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        // walrus.setInteractScript(new WalrusScript());
        // npcs.add(walrus);

        // Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        // dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        // dinosaur.setInteractScript(new DinoScript());
        // npcs.add(dinosaur);
        
        //Removing the Npcs we are not using

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

        //getMapTile(22, 34).setInteractScript(new SimpleTextScript("Eventually will be NPC's area"));

        getMapTile(5, 0).setInteractScript(new ReturnScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }    
}

