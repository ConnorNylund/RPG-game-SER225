package Screens;

import Engine.GraphicsHandler;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Inventory.Inventory;
import Level.*;
import Maps.Bossmap3;
import Players.Bunny;
import Enemies.squidBoss;
import Utils.Direction;

// This class is for when the RPG game is actually being played
public class PlayBossScreen3 extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;

    public PlayBossScreen3(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup state
        flagManager = new FlagManager();

        // define/setup map
        map = new Bossmap3(screenCoordinator, 1);
        map.setFlagManager(flagManager);

        // Add boss to the map
        squidBoss testboss = new squidBoss(1, map.getMapTile((int) 5, (int) 5).getLocation(), "DAMAGE3", map.getPlayer());
        map.addNPC(testboss);
        testboss.setMap(map);

        // setup player
        player = new Bunny(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        ((Bunny) player).setScreenCoordinator(screenCoordinator); // Ensure ScreenCoordinator is set for Bunny

        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        map.preloadScripts(screenCoordinator);
    }

    @Override
    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            case RUNNING:
                player.update();
                map.update(player);
                break;

            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // Check if the player is dead and trigger the death screen
        if (Bunny.health <= 0) {
            screenCoordinator.setGameState(GameState.DEATH);
        }
    }

    @Override
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
