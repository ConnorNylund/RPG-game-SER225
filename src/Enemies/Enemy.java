/*
 * THe base script for enemy objects, can be extended and modifed for specific enemies and enemy
 * types
 */
package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Players.Bunny;
import Utils.Point;
import EnhancedMapTiles.Coin;
import Level.MapTile;

import java.util.HashMap;

public class Enemy extends NPC {
    protected int id = 0;
    protected float curHealth;
    protected int totalHealth;
    protected double antiJankTimer;
    protected float moveSpeed;
    protected float lastAmountMovedY, lastAmountMovedX; 
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Fox.png"), 16, 16);
    protected double lastAttack; 
    protected float attackRadius; 
    protected float attackSpeed;

    public Enemy(int id, Point location, SpriteSheet spriteSheet, String startingAnimation) { //Constructor for subclasses
        super(id, location.x, location.y, spriteSheet, startingAnimation); 
        this.id = id; 
        antiJankTimer = System.nanoTime();
    }

    public Enemy(int id, Point location, String startingAnimation, Player player) { //sample call: Enemy(id = 0, xPos = 0, yPos = 0, spriteSheet = enemy.png, startingAnimation = ("DAMAGE" + totalHealth), totalHealth = 3)
        super(id, location.x, location.y, spriteSheet, startingAnimation);
        this.id = id;
        //Variables to change for stats
        moveSpeed = 1.7f; // Higher is faster
        attackRadius = 60; // Higher is farther
        totalHealth = 3; // Bigger is more health
        attackSpeed = 1; // Lower is faster

        //Don't mess with these ones tho
        lastAttack = 0; 
        curHealth = totalHealth;
        antiJankTimer = System.nanoTime(); 
    }

    public void takeDamage(float dmgAmt) {
        curHealth -= dmgAmt;
        if (curHealth <= 0) {
            isLocked = true;
            this.enemDeath();
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            
            System.out.println("Coin dropped at enemy location: (" + this.getX() + ", " + this.getY() + ")");
            
            // Trigger the onDeath method
        } else if (curHealth >= 1) {
            int tempHealth = (int)(curHealth + 0.5f); //Rounds up the dmg amt then casts to int for animations
            System.out.println("DEBUG: DAMAGE" + curHealth);
            setCurrentAnimationName("DAMAGE" + tempHealth);
        } else {
            setCurrentAnimationName("DAMAGE1"); 
        }
    }

    public void update(Player player) {
        super.update(player); 
        //System.out.println("DEBUG: RealEnemPos=" + this.getX() + "/" + this.getY());
        //System.out.println("DEBUG: Time difference: " + (System.nanoTime()-antiJankTimer)/1000000000.0); //Doing this so the mouse can't just be spammed, puts a 1 second cooldown on the players ability to attack, could maybe make this adjustable in the future if we rlly wanted
        //System.out.println("DEBUG: MousePos-" + MouseHandler.mousePos + " isClicked?-" + MouseHandler.leftMouseDown + " thisPos-x,y" + this.x + "," + this.y);

        //Checks if the mouse is on the enemy before taking damage, will need to eventually be updated for projectile detection
        // if ((System.nanoTime()-antiJankTimer)/1000000000.0 > .5 && this.contains2(MouseHandler.mousePos) && MouseHandler.leftMouseDown) {
        //     //System.out.println("DEBUG: clicked");
        //     //System.out.println("DEBUG: MousePos = " + MouseHandler.mousePos.x + "/" + MouseHandler.mousePos.y);
        //     antiJankTimer = System.nanoTime();
        //     this.takeDamage(); 
        // }

        //Player tracker
        /*
         * Need to get the position of the player
         * Get the position of the enemy
         * Calculate the angle between them
         * Take the enemies move speed, break that into X, Y components based on said angle
         * Move however much we can in a straight line towards the player using those components 
         */
        float px = player.getX(); 
        float py = player.getY();
        float ex = this.x;
        float ey = this.y;
        
        float dx = px - ex;
        float dy = py - ey;

        float dMag = (float) Math.sqrt((double)(dx * dx + dy * dy));
        float xRatio = dx / dMag; //What percentage of our movement should go in the X direction
        float yRatio = dy / dMag; //What percentage of our movement should go in the Y direction

        double angToPlay = Math.atan2(dx, dy);
        //System.out.println("DEBUG:\n Enemy Coords: " + ex + ", " + ey + "\n Player Coords: " + px + ", " + py + "\n Angle Between: " + angToPlay + "\n\n");
        if (!isLocked) {
            //System.out.println("DEBUG: Moving " + moveSpeed * (xRatio/2) + " in X      Moving " + moveSpeed * (yRatio/2) + " in Y");
            lastAmountMovedX = super.moveXHandleCollision(moveSpeed * (xRatio));
            lastAmountMovedY = super.moveYHandleCollision(moveSpeed * (yRatio));
        }

        float Pdx = (this.getX() + this.getWidth() / 2) - (player.getX() + player.getWidth() / 2);
        float Pdy = (this.getY() + this.getHeight() / 2) - (player.getY() + player.getHeight() / 2);
        if ((float) Math.sqrt(Pdx * Pdx + Pdy * Pdy) < attackRadius) {
            attackPlayer(player);
        }
        //System.out.println("DEBUG: xRatio/yRatio" + xRatio + "/" + yRatio); 
    }

    protected void attackPlayer(Player player) {
        if ((System.nanoTime() - lastAttack) / 1000000000.0 > attackSpeed) {
            ((Bunny) player).takeDamage(1);
            lastAttack = System.nanoTime(); 
        }
    }
    protected void enemDeath() { //Made into a seperate method so that individual instances can have custom death stuff
        
        // Spawn a coin at the enemy's current location upon death
        MapTile currentTile = map.getMapTile((int) (this.getX() / map.getTileset().getScaledSpriteWidth()), 
                                             (int) (this.getY() / map.getTileset().getScaledSpriteHeight()));
        Coin droppedCoin = new Coin(currentTile.getLocation());
        map.addEnhancedMapTile(droppedCoin);
    }

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) { //I hate this thing but u guys don't need to worry about it... Colors r definitely backwards rn tho I just need to remake the spritesheet
        return new HashMap<String, Frame[]>() {{
            put("DAMAGE1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2,1), 14)
                    .withScale(3)
                    .withBounds(0,0,16,16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(2,2),14)
                    .withScale(3)
                    .withBounds(0,0,16,16)
                    .build(),
            });
            put("DAMAGE2", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1,1),14)
                    .withScale(3)
                    .withBounds(0,0,16,16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1,2),14)
                    .withScale(3)
                    .withBounds(0,0,16,16)
                    .build(),
            });
            put("DAMAGE3", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,1),14)
                    .withScale(3)
                    .withBounds(0,0,16,16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0,2),14)
                    .withScale(3)
                    .withBounds(0,0,16,16)
                    .build(),
            });

        }};
    }
}
