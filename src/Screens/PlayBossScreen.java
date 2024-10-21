package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.Bossmap;
import Maps.TestMap;
import Players.Cat;
import Players.Bunny;
import Utils.Direction;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class PlayBossScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    protected int currentMap;  

    public PlayBossScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        //flagManager.addFlag("hasLostBall", false);
        //flagManager.addFlag("hasTalkedToWalrus", false);
        //flagManager.addFlag("hasTalkedToDinosaur", false);
        //flagManager.addFlag("hasFoundBall", false);

        // define/setup map
        map = new Bossmap(screenCoordinator, 1);
        map.setFlagManager(flagManager);

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

    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);

                // if (currentMap != map.getCurrentMap()) {
                //     currentMap = map.getCurrentMap();
                //     this.map = new TestMap(screenCoordinator, 0);
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
