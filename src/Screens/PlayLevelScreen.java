package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.TrainingDummy;  // Import the TrainingDummy class
import Level.*;
import Maps.TestMap;
import Players.Bunny;
import Utils.Direction;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    
    // Declare the TrainingDummy variable
    protected TrainingDummy trainingDummy;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // Setup state flags
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        // Define/setup map
        map = new TestMap(screenCoordinator);
        map.setFlagManager(flagManager);

        // Setup player
        player = new Bunny(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        map.setPlayer(player);

        // Let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // Preload all scripts ahead of time
        map.preloadScripts(screenCoordinator);

        winScreen = new WinScreen(this);

        // Initialize the Training Dummy
        trainingDummy = new TrainingDummy(100, 200);  // Set x, y position for the dummy
    }

    public void update() {
        // Based on screen state, perform specific actions
        switch (playLevelScreenState) {
            case RUNNING:
                player.update();
                map.update(player);
                
                // Update the training dummy
                trainingDummy.update();  // Manually update the training dummy
                break;

            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // If flag is set at any point during gameplay, game is "won"
        if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // Based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                
                // Draw the training dummy
                trainingDummy.draw(graphicsHandler);  // Manually draw the training dummy
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

    // Enum representing the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
