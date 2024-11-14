package Maps;

import EnhancedMapTiles.PushableRock;
import EnhancedMapTiles.Coin;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.*;
// import NPCs.Bug;
 import NPCs.Dinosaur;
// import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;

import java.util.ArrayList;

import Enemies.Enemy;
import Enemies.Boss;
import Engine.ImageLoader;

// Represents a test map to be used in a level
public class Hiddenmap extends Map {

    public Hiddenmap(ScreenCoordinator screenCoordinator) {
        super("Hiddenmap.txt", new CommonTileset(), screenCoordinator);
        this.playerStartPosition = getMapTile(5, 5).getLocation();
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void loadScripts(ScreenCoordinator screenCoordinator) {
        //getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        //getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        //getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        //getMapTile(3, 3).setInteractScript(new SimpleTextScript("Eventually will be Shop"));

        //getMapTile(22, 34).setInteractScript(new SimpleTextScript("Eventually will be NPC's area"));

        getMapTile(0, 0).setInteractScript(new ReturnScript(screenCoordinator));

        super.loadScripts(screenCoordinator);
    }    
}

