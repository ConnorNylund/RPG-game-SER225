package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*; 

// This class is for the about screen
public class AboutScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont aboutLabel;
    protected SpriteFont infoLabel;
    protected SpriteFont infoLabel2;
    protected SpriteFont infoLabel3;
    protected SpriteFont infoLabel4;
    protected SpriteFont infoLabel5;
    protected SpriteFont infoLabel6;
    protected SpriteFont infoLabel7;
    protected SpriteFont infoLabel8;
    protected SpriteFont instructionsLabel;
    protected SpriteFont returnInstructionsLabel;

    public AboutScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // Setup background and text for "About" screen
        background = new TitleScreenMap(screenCoordinator);
       // background.setBackgroundImage("newBackgroundImage.png");
        background.setAdjustCamera(false);
        aboutLabel = new SpriteFont("About", 360, 7, "Times New Roman", 30, Color.white);
        infoLabel = new SpriteFont("Welcome to Sector Survival, created by Team Byte Me! Dive into a world where cute meets chaotic!", 20, 100, "Serif", 18, Color.yellow);
        infoLabel.setFontStyle(Font.BOLD);
        infoLabel3 = new SpriteFont("Navigating the Menu: Use the Up and Down arrow keys to scroll through options,", 20, 150, "Serif", 18, Color.black);
        infoLabel3.setFontStyle(Font.BOLD);
        infoLabel4 = new SpriteFont("then press Space to select.", 20, 175, "Serif", 18, Color.black);
        infoLabel4.setFontStyle(Font.BOLD);
        infoLabel2 = new SpriteFont("Movement: Use WASD keys (W: up, A: left, S: down, D: right) to move around.", 20, 250, "Serif", 18, Color.black);
        infoLabel2.setFontStyle(Font.BOLD);
        infoLabel5 = new SpriteFont("Interacting: Press Space to talk to NPCs, enter shops, and collect items.", 20, 275, "Serif", 18, Color.black);
        infoLabel5.setFontStyle(Font.BOLD);
        infoLabel6 = new SpriteFont("Combat: Click on enemies to attack them and gain an advantage in battles.", 20, 300, "Serif", 18, Color.black);
        infoLabel6.setFontStyle(Font.BOLD);
        infoLabel7 = new SpriteFont("Pause/Resume: Press P to start or pause the game anytime.", 20, 325, "Serif", 18, Color.black);
        infoLabel7.setFontStyle(Font.BOLD);
        infoLabel8 = new SpriteFont("HAVE FUN !", 20, 400, "Papyrus", 40, Color.black);
        infoLabel8.setFontStyle(Font.BOLD);


       // instructionsLabel = new SpriteFont("Use arrow keys to move, space to attack. Collect coins to buy weapons!", 20, 200, "Arial", 18, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 510, "Times New Roman", 30, Color.black);
        returnInstructionsLabel.setFontStyle(Font.BOLD);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // If space is pressed, return to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        aboutLabel.draw(graphicsHandler);
        infoLabel.draw(graphicsHandler);
        infoLabel2.draw(graphicsHandler);
        infoLabel3.draw(graphicsHandler);
        infoLabel4.draw(graphicsHandler);
        infoLabel5.draw(graphicsHandler);
        infoLabel6.draw(graphicsHandler);
        infoLabel7.draw(graphicsHandler);
        infoLabel8.draw(graphicsHandler);
       // instructionsLabel.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
