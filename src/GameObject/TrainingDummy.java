package GameObject;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import Engine.Key;


import java.awt.Graphics;

public class TrainingDummy extends GameObject {
    private int healthState;  // 0 = normal, 1 = damaged, 2 = heavily damaged
    private Sprite normalSprite;
    private Sprite damagedSprite;
    private Sprite heavilyDamagedSprite;
    private Sprite sprite;  // This will hold the current sprite for the TrainingDummy


    public TrainingDummy(float x, float y) {
        // Call the GameObject constructor with initial x, y position and sprite frame
        super(x, y, new Frame(ImageLoader.load("Resources/normal.png"), ImageEffect.NONE, 1, null));

        // Initialize health state and load sprites
        healthState = 0;
        loadSprites();
    }

    private void loadSprites() {
        normalSprite = new Sprite(ImageLoader.load("Resources/normal.png"));
        damagedSprite = new Sprite(ImageLoader.load("Resources/damaged.png"));
        heavilyDamagedSprite = new Sprite(ImageLoader.load("Resources/heavily_damaged.png"));

    }

    public void setSprite(Sprite sprite) {
    this.sprite = sprite;  // Assuming 'sprite' is a member variable in the parent class or TrainingDummy
}


    // Method to handle damage and switch between sprites
    public void takeDamage() {
        if (healthState < 2) {
            healthState++;  // Move to the next state
        } else {
            healthState = 0;  // Reset to normal after heavily damaged
        }
        updateSprite();  // Update the sprite based on the new health state
    }

    private void updateSprite() {
        switch (healthState) {
            case 0:
                setSprite(normalSprite);
                break;
            case 1:
                setSprite(damagedSprite);
                break;
            case 2:
                setSprite(heavilyDamagedSprite);
                break;
        }
    }

    @Override
    public void update() {
        // Detect key press for taking damage (assuming your input system has Key.T)
        if (Keyboard.isKeyDown(Key.T)) {
            takeDamage();
        }
        super.update();  // Call GameObject's update method
    }

    @Override
public void draw(GraphicsHandler graphicsHandler) {  // Use GraphicsHandler instead of Graphics
    if (sprite != null) {
        sprite.draw(graphicsHandler);  // Assuming the Sprite class expects GraphicsHandler
    }
}


    
}