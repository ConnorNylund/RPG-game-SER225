package Maps;

import EnhancedMapTiles.DestroyableWall;
import EnhancedMapTiles.PushableRock;
import EnhancedMapTiles.Coin;
//import EnhancedMapTiles.TestingFarmer; // Import TestingFarmer class
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.*;
import NPCs.Walrus;
import NPCs.FishBunny;
import NPCs.PirateBunny;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

import Enemies.Enemy;
import Engine.ImageLoader;
import Engine.Screen;

// Represents a test map to be used in a level
public class TestMap extends Map {
    public DestroyableWall destroyableWallV1;
    public DestroyableWall destroyableWallV2;
    public DestroyableWall destroyableWallV3;
     

    public DestroyableWall destroyableWallV4;
    public DestroyableWall destroyableWallV5;
    public DestroyableWall destroyableWallV6;

    public DestroyableWall destroyableWallH1;
    public DestroyableWall destroyableWallH2;
    public DestroyableWall destroyableWallH3;
    public DestroyableWall destroyableWallH4;
    public DestroyableWall destroyableWallH5;

    public DestroyableWall destroyableWallG1, destroyableWallG2, destroyableWallG3; 
    public ScreenCoordinator screenCoordinator;

    public TestMap(ScreenCoordinator screenCoordinator, int currentMap) {
        super("test_map.txt", new CommonTileset(), screenCoordinator);
        this.playerStartPosition = getMapTile(25, 27).getLocation();
        this.screenCoordinator = screenCoordinator;
        this.currentMap = 0;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // Adding a coin to the map
        //Coin coin = new Coin(getMapTile(35, 20).getLocation());
        //enhancedMapTiles.add(coin);

        // not using this anymore
        // // Adding the farmer to the map
        // TestingFarmer farmer = new TestingFarmer(getMapTile(20, 5).getLocation()); // Set appropriate location for farmer
        // enhancedMapTiles.add(farmer);

        // Adding destroyable walls to the map
        destroyableWallV1 = new DestroyableWall(getMapTile(21, 25).getLocation(), "GateVertical.png");
        destroyableWallV2 = new DestroyableWall(getMapTile(21, 26).getLocation(), "GateVertical.png");
        destroyableWallV3 = new DestroyableWall(getMapTile(21, 27).getLocation(), "GateVertical.png");

        // destroyableWallV4 = new DestroyableWall(getMapTile(29, 25).getLocation(), "GateVertical.png");
        // destroyableWallV5 = new DestroyableWall(getMapTile(29, 26).getLocation(), "GateVertical.png");
        // destroyableWallV6 = new DestroyableWall(getMapTile(29, 27).getLocation(), "GateVertical.png");

        destroyableWallH1 = new DestroyableWall(getMapTile(4, 13).getLocation(), "GateHorizontal.png");
        destroyableWallH2 = new DestroyableWall(getMapTile(5, 13).getLocation(), "GateHorizontal.png");
        destroyableWallH3 = new DestroyableWall(getMapTile(6, 13).getLocation(), "GateHorizontal.png");
        destroyableWallH4 = new DestroyableWall(getMapTile(7, 13).getLocation(), "GateHorizontal.png");
        destroyableWallH5 = new DestroyableWall(getMapTile(8, 13).getLocation(), "GateHorizontal.png");

        destroyableWallG1 = new DestroyableWall(getMapTile(30,25).getLocation(), "GateVertical.png");
        destroyableWallG2 = new DestroyableWall(getMapTile(30,26).getLocation(), "GateVertical.png");
        destroyableWallG3 = new DestroyableWall(getMapTile(30,27).getLocation(), "GateVertical.png");

        enhancedMapTiles.add(destroyableWallV1);
        enhancedMapTiles.add(destroyableWallV2);
        enhancedMapTiles.add(destroyableWallV3);

        // enhancedMapTiles.add(destroyableWallV4);
        // enhancedMapTiles.add(destroyableWallV5);
        // enhancedMapTiles.add(destroyableWallV6);

        enhancedMapTiles.add(destroyableWallH1);
        enhancedMapTiles.add(destroyableWallH2);
        enhancedMapTiles.add(destroyableWallH3);
        enhancedMapTiles.add(destroyableWallH4);
        enhancedMapTiles.add(destroyableWallH5);

        enhancedMapTiles.add(destroyableWallG1);
        enhancedMapTiles.add(destroyableWallG2);
        enhancedMapTiles.add(destroyableWallG3);

        System.out.println("DEBUG: Successfully loaded all enhanced tiles to ArrayList");

        return enhancedMapTiles;
    }

    public void destroyWall1() {
        destroyableWallV1.destroyWall();
        destroyableWallV2.destroyWall();
        destroyableWallV3.destroyWall();
        
    }
    public void destroyWall2() {
        destroyableWallH1.destroyWall();
        destroyableWallH2.destroyWall();
        destroyableWallH3.destroyWall();
        destroyableWallH4.destroyWall();
        destroyableWallH5.destroyWall();
    }
    public void destroyWall3() {
        destroyableWallG1.destroyWall();
        destroyableWallG2.destroyWall();
        destroyableWallG3.destroyWall();
    }

    // public void destroyWall2() {
    //     destroyableWallV4.destroyWall();
    //     destroyableWallV5.destroyWall();
    //     destroyableWallV6.destroyWall();
        
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(27, 19).getLocation().subtractY(40));
        npcs.add(walrus);

        FishBunny fishBunny = new FishBunny(3, getMapTile(17, 37).getLocation().subtractY(40));
        fishBunny.setInteractScript(new FisherScript());
        npcs.add(fishBunny);

        PirateBunny pirateBunny = new PirateBunny(3, getMapTile(18, 35).getLocation().subtractY(40));
        pirateBunny.setInteractScript(new PirateBunnyScript());
        npcs.add(pirateBunny);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Add triggers
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));

        return triggers;
    }

    @Override
    public void loadScripts(ScreenCoordinator screenCoordinator) {
        getMapTile(2, 6).setInteractScript(new TreeScript());
        getMapTile(31, 17).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile(25, 18).setInteractScript(new ShopScript(screenCoordinator));
        getMapTile(30, 18).setInteractScript(new HiddenScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }
    public void loadBossScript1(ScreenCoordinator screenCoordinator, float x, float y) {
        getMapTile((int)x+1, (int)y).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x-1, (int)y).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x, (int)y+1).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x, (int)y-1).setInteractScript(new BossChallengeScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }
    public void loadBossScript2(ScreenCoordinator screenCoordinator, float x, float y) {
        getMapTile((int)x+1, (int)y).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x-1, (int)y).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x, (int)y+1).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x, (int)y-1).setInteractScript(new BossChallengeScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }
    public void loadBossScript3(ScreenCoordinator screenCoordinator, float x, float y) {
        getMapTile((int)x+1, (int)y).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x-1, (int)y).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x, (int)y+1).setInteractScript(new BossChallengeScript(screenCoordinator));
        getMapTile((int)x, (int)y-1).setInteractScript(new BossChallengeScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }
}
