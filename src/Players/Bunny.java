package Players;

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
import Game.GameState;
import Game.ScreenCoordinator;
import Weapons.*;
import Inventory.*;

public class Bunny extends Player {
    public static float health;
    public static int dmgState;
    private Weapon currentWeapon;
    private boolean test;
    public static int coinCount = 0; 
    private static SpriteSheet sptSht = new SpriteSheet(ImageLoader.load("Resources/bunnyWalkv2.png"), 16, 16);
    private ScreenCoordinator screenCoordinator; 

    private String prevWep; 

    public Bunny(float x, float y) {
        super(sptSht, x, y, "STAND_RIGHT");
        health = 4;
        dmgState = 0;
        test = true;
        prevWep = " ";
    }

    public void setScreenCoordinator(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public Map getMap() {
        return this.map;  // Accessing the protected map field inherited from the Player class
    }

    public void setWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    @Override
public void update() {
    // Adjust walk speed based on inventory selection BEFORE calling super.update()
    if (Inventory.items[Inventory.currentSelection] != null &&
        Inventory.items[Inventory.currentSelection].getName().equals("Speed Potion")) {
        this.walkSpeed = 5.0f; // Increased speed
        System.out.println("Speed Potion activated! walkSpeed: " + walkSpeed);
    }

    super.update(); // Call the parent update AFTER walkSpeed has been updated

    if (test) {
        // Initialize the default weapon
        currentWeapon = new TestPistol(this.getLocation(), this.getMap());
        test = false;
    }

    // Weapon swapping
    try {
        switch (Inventory.items[Inventory.currentSelection].getName()) {
            case ("Pistol With Bayonet"):
                if (!prevWep.equals(Inventory.items[Inventory.currentSelection].getName())) {
                    currentWeapon = new GoodPistol(this.getLocation(), this.getMap());
                    System.out.println("DEBUG: GoodPistol");
                }
                break;
            case ("Carrot Rocket Launcher"):
                if (!prevWep.equals(Inventory.items[Inventory.currentSelection].getName())) {
                    currentWeapon = new RPC(this.getLocation(), this.getMap());
                    System.out.println("DEBUG: RPC");
                }
                break;
            case ("Small Machine Gun"):
                if (!prevWep.equals(Inventory.items[Inventory.currentSelection].getName())) {
                    currentWeapon = new SMG(this.getLocation(), this.getMap());
                    System.out.println("DEBUG: SMG");
                }
                break;
            case ("Bloody Cleaver"):
                if (!prevWep.equals(Inventory.items[Inventory.currentSelection].getName())) {
                    currentWeapon = new Bow(this.getLocation(), this.getMap());
                    System.out.println("DEBUG: BCleav");
                }
                break;
            default:
                System.out.println("DEBUG: NullDumDum");
                break;
        }
        prevWep = Inventory.items[Inventory.currentSelection].getName();
    } catch (NullPointerException e) {
        // Handle potential null errors
    }

    if (Inventory.items[Inventory.currentSelection] != null) {
        System.out.println("Selected Item: " + Inventory.items[Inventory.currentSelection].getName());
    }

    // Handle coin pickup
    checkCoinPickup();

    // Update weapon actions
    if (MouseHandler.leftMouseDown) {
        float calibratedX = MouseHandler.mousePos.x + map.getCamera().getX();
        float calibratedY = MouseHandler.mousePos.y + map.getCamera().getY();

        if (calibratedX > this.x + this.getWidth()) {
            currentWeapon.shoot(this.x + 15 + this.getWidth(), this.y, calibratedX, calibratedY);
        } else {
            currentWeapon.shoot(this.x - 15, this.y, calibratedX, calibratedY);
        }
    }

    currentWeapon.update(this);

    // Check if player is dead to trigger DeathScreen
    if (health <= 0) {
        triggerDeathScreen();
    }
}
    

    public void takeDamage(float dmgAmt) {
        if (health > 0) {
            health -= dmgAmt;
            if (dmgState < 3) {
                dmgState++;
                this.animations = loadAnimations(sptSht);
            }
        } else {
            // Ensure health doesn't go negative and trigger the death sequence
            health = 0;
        }
        System.out.println("DEBUG: Health = " + health + " Dmg State = " + dmgState);
    }

    private void triggerDeathScreen() {
        if (screenCoordinator != null) {
            screenCoordinator.setGameState(GameState.DEATH); // Switch to DeathScreen
        } else {
            System.out.println("ERROR: ScreenCoordinator not set!");
        }
    }

    // new code for win screen
    public void triggerWinScreen() {
    if (screenCoordinator != null) {
        screenCoordinator.setGameState(GameState.WIN); // Switch to WinScreen
    } else {
        System.out.println("ERROR: ScreenCoordinator not set!");
    }
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

