package Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Keyboard;
import Engine.Key;
import Engine.MouseHandler;
import EnhancedMapTiles.Coin;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Map;
import Level.Player;
import Utils.Point;
import Weapons.Weapon;
import Weapons.TestPistol;
import Weapons.*;

public class Bunny extends Player {
    private float health;
    private int dmgState;
    private Weapon currentWeapon;
    private boolean test;
    public static int coinCount = 0; // this is to track coins
    private static SpriteSheet sptSht = new SpriteSheet(ImageLoader.load("bunnyWalkv2.png"), 16, 16);

    public Bunny(float x, float y) {
        super(sptSht, x, y, "STAND_RIGHT");
        health = 4;
        dmgState = 0;
        test = true;
    }

    public Map getMap() {
        return this.map;  // Accessing the protected map field inherited from the Player class
    }
    public void setWeapon(Weapon weapon) {
        currentWeapon = weapon; 
    }

    @Override
    public void update() {
        super.update();
        if (test) {
            //currentWeapon = new TestPistol(this.getLocation(), this.getMap());
            currentWeapon = new TestPistol(this.getLocation(), this.getMap());
            test = false;
        }
        checkCoinPickup(); // Check for coin pickup each update
        getCalibratedXLocation();

        if (MouseHandler.leftMouseDown) {
            float calibratedX = MouseHandler.mousePos.x + map.getCamera().getX();
            float calibratedY = MouseHandler.mousePos.y + map.getCamera().getY();

            if (calibratedX > this.x + this.getWidth()) {
                currentWeapon.shoot(this.x + 15 + this.getWidth(), this.y, calibratedX, calibratedY);
            } else {
                currentWeapon.shoot(this.x - 15, this.y, calibratedX, calibratedY);
            }
            System.out.println("DEBUG: MouseX/MouseY" + calibratedX + "/" + calibratedY);
        }
        currentWeapon.update(this);
      // System.out.println("DEBUG: Player Pos = " + this.getX() + ", " + this.getY()); 
    }

    public void takeDamage(float dmgAmt) {
        if (health != 0) {
            health -= dmgAmt;
            if (dmgState < 3) {
                dmgState++;
                this.animations = loadAnimations(sptSht);
            }
        } else {
            this.lock();
            this.setLocation(860, 1200);
            System.out.println("DEBUG: Player has died");
        }
        System.out.println("DEBUG: Health = " + health + " Dmg State = " + dmgState);
    }

    private boolean isNearCoin(Coin coin) {
        float pickupRadius = 30.0f;
        float dx = (this.getX() + this.getWidth() / 2) - (coin.getX() + coin.getWidth() / 2);
        float dy = (this.getY() + this.getHeight() / 2) - (coin.getY() + coin.getHeight() / 2);
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance <= pickupRadius;
    }

    private void checkCoinPickup() {
        Iterator<EnhancedMapTile> iterator = getMap().getEnhancedMapTiles().iterator();

        while (iterator.hasNext()) {
            EnhancedMapTile tile = iterator.next();
            if (tile instanceof Coin) {
                Coin coin = (Coin) tile;

                if (!coin.isCollected() && isNearCoin(coin)) {
                    if (Keyboard.isKeyDown(Key.SPACE)) {
                        coin.collect(); // Mark the coin as collected
                        iterator.remove(); // Remove the coin from the map
                        coinCount++; // Increment the coin count
                        System.out.println("Coin picked up! Total coins: " + coinCount);
                    }
                }
            }
        }
    }

    // Getter for coin count to access in other classes
    public int getCoinCount() {
        return coinCount;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        currentWeapon.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        System.out.println("DEBUG: Anim Dmgstate = " + dmgState); 
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(dmgState, 0), 14)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 1), 14)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build()
            });

            put("STAND_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(dmgState, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 1), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 16, 16)
                    .build()
            });

            put("WALK_RIGHT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(dmgState, 0), 14)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 1), 14)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 2), 14)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 3), 14)
                    .withScale(3)
                    .withBounds(0, 0, 16, 16)
                    .build()
            });

            put("WALK_LEFT", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(dmgState, 0), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 1), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 2), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 16, 16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(dmgState, 3), 14)
                    .withScale(3)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .withBounds(0, 0, 16, 16)
                    .build()
            });
        }};
    }
}
