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

public class WinScreen extends Screen {
    private ScreenCoordinator screenCoordinator;
    private SpriteFont winMessage;
    private SpriteFont instructionMessage;
    private BufferedImage winImage; 

    public WinScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("YOU WIN!", 275, 100, "Arial", 50, Color.GREEN);
        winMessage.setOutlineColor(Color.BLACK);
        winMessage.setOutlineThickness(3);

        instructionMessage = new SpriteFont("Press ENTER to return to the Main Menu", 110, 500, "Arial", 30, Color.YELLOW);
        instructionMessage.setOutlineColor(Color.BLACK);
        instructionMessage.setOutlineThickness(2);

        
        winImage = ImageLoader.load("Resources/bloodybunny.png");
    }

    @Override
    public void update() {
        if (Keyboard.isKeyDown(Key.ENTER)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        // Draw the win image if it exists
        if (winImage != null) {
            graphicsHandler.drawImage(winImage, 175, 170, 400, 300); 
        }

        // Draw the messages
        winMessage.draw(graphicsHandler);
        instructionMessage.draw(graphicsHandler);
    }
}
