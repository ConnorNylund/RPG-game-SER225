package Players;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import Engine.Key;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;
import Level.Map;
import EnhancedMapTiles.Coin; 
import Level.EnhancedMapTile; 
import java.util.Iterator;


public class Bunny extends Player {

    public Bunny(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("bunnyWalkFixed.png"), 16, 16), x, y, "STAND_RIGHT");
    }

    public Map getMap() {
        return this.map;  // Accessing the protected map field inherited from the Player class
    }

    @Override
    public void update() {
        super.update();
        checkCoinPickup();
    }


    private boolean isNearCoin(Coin coin) {
        
        float pickupRadius = 30.0f; 
    
        
        float dx = (this.getX() + this.getWidth() / 2) - (coin.getX() + coin.getWidth() / 2);
        float dy = (this.getY() + this.getHeight() / 2) - (coin.getY() + coin.getHeight() / 2);
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
    
        // Print the distance for debugging 
       // System.out.println("Distance to coin: " + distance);
    
        // return true if the bunny is within the pickup radius
        return distance <= pickupRadius;
    }

    private void checkCoinPickup() {
    
    Iterator<EnhancedMapTile> iterator = getMap().getEnhancedMapTiles().iterator();

    while (iterator.hasNext()) {
        EnhancedMapTile tile = iterator.next();
        if (tile instanceof Coin) {
            Coin coin = (Coin) tile;

            // checks if the coin is near the bunny and not collected yet, and if the 'E' key is being pressed
            if (!coin.isCollected() && isNearCoin(coin)) {
                if (Keyboard.isKeyDown(Key.SPACE)) {
                    coin.collect(); // marks the coin as collected
                    System.out.println("Coin picked up! Coin collected status: " + coin.isCollected());
                    iterator.remove(); // removes the coin from the map
                    System.out.println("Coin removed from the map.");
                }
            }
        }
    }
}

    
    
    
    

    
    
    

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

   @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(6, 12, 12, 7)
                    .build()
            });

            put("STAND_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(6, 12, 12, 7)
                    .build()
            });

            put("WALK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withBounds(6, 12, 12, 7)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withBounds(6, 12, 12, 7)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withBounds(6, 12, 12, 7)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withBounds(6, 12, 12, 7)
                    .build()
            });

            put("WALK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(6, 12, 12, 7)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(6, 12, 12, 7)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(6, 12, 12, 7)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(6, 12, 12, 7)
                    .build()
            });
        }};
    }
}
