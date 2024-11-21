package Screens;

import Engine.GraphicsHandler;
import Engine.Keyboard;
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
import SpriteFont.SpriteFont; 
import Utils.Direction;
import Utils.Point;
import Waves.WaveManager;
import Engine.Key;
import java.awt.Color;  

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
    protected InventoryScreen invScreen;
    private SpriteFont coinCountText; 

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasTalkedToPirate", false);
        flagManager.addFlag("hasTalkedToFisher", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("hasSeenScript", false);

        // define/setup map
        map = new TestMap(screenCoordinator, 0);
        map.setFlagManager(flagManager);

        waveManager = new WaveManager(15, map);
        

        // setup player
        player = new Bunny(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        player = new Bunny(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        ((Bunny) player).setScreenCoordinator(screenCoordinator); // Set the ScreenCoordinator
        player.setMap(map);

        inventory = new Inventory(player);
        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts(screenCoordinator);

        // winScreen = new WinScreen(this);
        invScreen = new InventoryScreen();

        // COIN COUNT
        coinCountText = new SpriteFont("Coins: 0", 700, 20, "Georgia", 20, Color.YELLOW);
        coinCountText.setOutlineColor(Color.BLACK);
        coinCountText.setOutlineThickness(2);
    }

    public void update() {
        // check if the key "T" is pressed to show damage taken
        if (Keyboard.isKeyDown(Key.T)) {
            System.out.println("Training dummy has taken damage!");
        }

        waveManager.update();

        // this updates coin count text based on the player's collected coins
        int coinCount = ((Bunny) player).getCoinCount();
        coinCountText.setText("Coins: " + coinCount);

        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            case RUNNING:
                player.update();
                map.update(player);
                invScreen.update();
                break;

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
                invScreen.draw(graphicsHandler);
                coinCountText.draw(graphicsHandler); // this draws the coin count text on screen
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
