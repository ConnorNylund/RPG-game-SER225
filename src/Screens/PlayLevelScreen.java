// PLAYLEVELSCREEN CODE

package Screens;

import Engine.GraphicsHandler;
import Engine.Keyboard;  // Import this to use Keyboard handling
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Inventory.Inventory;
import Level.*;
import Maps.Shopmap;
import Maps.Bossmap;
import Maps.TestMap;
import Players.Bunny;
import ScriptActions.TextboxScriptAction;
import Utils.Direction;
import Utils.Point;
import Waves.WaveManager;
import Engine.Key;  // Import Key class for using specific keys
import java.util.ArrayList;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    protected int currentMap;  
    protected int slowTileIndex;
    protected WaveManager waveManager;
    protected Inventory inventory;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("hasSeenBlurb", false);
    
        // define/setup map
        map = new TestMap(screenCoordinator, 0);
        map.setFlagManager(flagManager);

        waveManager = new WaveManager(5, map);

        inventory = new Inventory();
    
        // setup player
        player = new Bunny(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING; 
        player.setFacingDirection(Direction.LEFT);
    
        map.setPlayer(player);
    
        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());
    
        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts(screenCoordinator);
    
        winScreen = new WinScreen(this);
    }
    

    public void update() {
        // check is the key "T" is pressed to show damage taken
        if (Keyboard.isKeyDown(Key.T)) {
            System.out.println("Training dummy has taken damage!");
        }

        waveManager.update();


        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);

                // if (currentMap != map.getCurrentMap()) {
                //     currentMap = map.getCurrentMap();
                //     this.map = new Bossmap(screenCoordinator, 1);
                // }
                break;

            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }
    }


    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}




