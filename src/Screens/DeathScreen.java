package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;
import Engine.Key;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class DeathScreen extends Screen {
    private ScreenCoordinator screenCoordinator;
    private SpriteFont deathMessage;
    private SpriteFont instructionMessage;
    private BufferedImage deathImage; 

    public DeathScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        deathMessage = new SpriteFont("YOU DIED", 275, 100, "Arial", 50, Color.RED);
        deathMessage.setOutlineColor(Color.BLACK);
        deathMessage.setOutlineThickness(3);

        instructionMessage = new SpriteFont("Press ENTER to return to the Main Menu", 110, 500, "Arial", 30, Color.YELLOW);
        instructionMessage.setOutlineColor(Color.BLACK);
        instructionMessage.setOutlineThickness(2);

        
        deathImage = ImageLoader.load("diebunny.png");

    }

    @Override
    public void update() {
        // returns the player back to main menu
        if (Keyboard.isKeyDown(Key.ENTER)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        if (deathImage != null) {
            graphicsHandler.drawImage(deathImage, 200, 140, 400, 300); 
        }

        // Draw the messages
        deathMessage.draw(graphicsHandler);
        instructionMessage.draw(graphicsHandler);
    }
}
