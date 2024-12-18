package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.AboutScreen;
import Screens.CreditsScreen;
import Screens.DeathScreen;
import Screens.HiddenMapScreen;
import Screens.MenuScreen;
import Screens.PlayBossScreen1;
import Screens.PlayBossScreen2;
import Screens.PlayBossScreen3;
import Screens.PlayLevelScreen;
import Screens.PlayShopScreen; // Import AboutScreen
import Screens.WinScreen; // Import DeathScreen

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
    // currently shown Screen
    public Screen currentScreen = new DefaultScreen();

    // keep track of gameState so ScreenCoordinator knows which Screen to show
    protected GameState gameState;
    protected GameState previousGameState;

    public GameState getGameState() {
        return gameState;
    }

    // Other Screens can set the gameState of this class to force it to change the currentScreen
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    protected Screen savedScreen = null;
    protected GameState savedState = null;

    public void saveState() {
        savedScreen = this.currentScreen;
        savedState = this.gameState;
    }

    @Override
    public void initialize() {
        // start game off with Menu Screen
        gameState = GameState.MENU;
    }

    @Override
    public void update() {
        do {
            // if previousGameState does not equal gameState, it means there was a change in gameState
            // this triggers ScreenCoordinator to bring up a new Screen based on what the gameState is
            if (previousGameState != gameState) {
                if (savedScreen != null && savedState != null && savedState == gameState) {
                    currentScreen = savedScreen;
                    gameState = savedState;
                    savedScreen = null;
                    savedState = null;
                } else {
                    switch (gameState) {
                        case MENU:
                            currentScreen = new MenuScreen(this);
                            break;
                        case LEVEL:
                            currentScreen = new PlayLevelScreen(this);
                            break;
                        case ABOUT:
                            currentScreen = new AboutScreen(this);
                            break;
                        case BOSS1:
                            currentScreen = new PlayBossScreen1(this);
                            break;
                        case BOSS2:
                            currentScreen = new PlayBossScreen2(this);
                            break;
                        case BOSS3:
                            currentScreen = new PlayBossScreen3(this);
                            break;
                        case SHOP:
                            currentScreen = new PlayShopScreen(this);
                            break;
                        case CREDITS:
                            currentScreen = new CreditsScreen(this);
                            break;
                        case HIDDEN:
                            currentScreen = new HiddenMapScreen(this);
                            break;
                        case DEATH: // New case for DEATH screen
                            currentScreen = new DeathScreen(this);
                            break;
                        case WIN:
                            currentScreen = new WinScreen(this);
                            break;


                    }
                    currentScreen.initialize();
                }
            }
            previousGameState = gameState;

            // call the update method for the currentScreen
            currentScreen.update();
        } while (previousGameState != gameState);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        // call the draw method for the currentScreen
        currentScreen.draw(graphicsHandler);
    }
}
