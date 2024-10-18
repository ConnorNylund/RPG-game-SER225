package Maps;

import EnhancedMapTiles.DestroyableWall;
import EnhancedMapTiles.PushableRock;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
// import NPCs.Bug;
// import NPCs.Dinosaur;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

import Engine.Screen;

// Represents a test map to be used in a level
public class TestMap extends Map {

    protected ScreenCoordinator screenCoordinator;

    public TestMap(ScreenCoordinator screenCoordinator, int currentMap) {
        super("test_map.txt", new CommonTileset(), screenCoordinator);
        this.playerStartPosition = getMapTile(38, 24).getLocation();
        this.screenCoordinator = screenCoordinator;
        this.currentMap = 0;
    }
    
    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        //Brady's broken code dump
        DestroyableWall destroyableWallV1 = new DestroyableWall(getMapTile(21, 25).getLocation(), "GateVertical.png");
        DestroyableWall destroyableWallV2 = new DestroyableWall(getMapTile(21, 26).getLocation(), "GateVertical.png");
        DestroyableWall destroyableWallV3 = new DestroyableWall(getMapTile(21, 27).getLocation(), "GateVertical.png");

        DestroyableWall destroyableWallH1 = new DestroyableWall(getMapTile(4, 13).getLocation(), "GateHorizontal.png");
        DestroyableWall destroyableWallH2 = new DestroyableWall(getMapTile(5, 13).getLocation(), "GateHorizontal.png");
        DestroyableWall destroyableWallH3 = new DestroyableWall(getMapTile(6, 13).getLocation(), "GateHorizontal.png");
        DestroyableWall destroyableWallH4 = new DestroyableWall(getMapTile(7, 13).getLocation(), "GateHorizontal.png");
        DestroyableWall destroyableWallH5 = new DestroyableWall(getMapTile(8, 13).getLocation(), "GateHorizontal.png");

        enhancedMapTiles.add(destroyableWallV1);
        enhancedMapTiles.add(destroyableWallV2);
        enhancedMapTiles.add(destroyableWallV3);

        enhancedMapTiles.add(destroyableWallH1);
        enhancedMapTiles.add(destroyableWallH2);
        enhancedMapTiles.add(destroyableWallH3);
        enhancedMapTiles.add(destroyableWallH4);
        enhancedMapTiles.add(destroyableWallH5);
        System.out.println("DEBUG: SUCCESFULLY LOADED ALL ENHANCED TILES TO ARRAYLIST");



        return enhancedMapTiles;
    }

    

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(26, 19).getLocation().subtractY(40));
        npcs.add(walrus);

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

        getMapTile(25, 18).setInteractScript(new SimpleTextScript("Eventually will be Shop"));

        getMapTile(22, 34).setInteractScript(new SimpleTextScript("Eventually will be NPC's area"));

        getMapTile(2, 6).setInteractScript(new TreeScript());

        getMapTile(26, 18).setInteractScript(new BossChallengeScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }

}