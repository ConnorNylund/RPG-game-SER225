/*
 * THe base script for enemy objects, can be extended and modifed for specific enemies and enemy
 * types
 */
package Enemies;

import Builders.FrameBuilder;
import Engine.MouseHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Point;

import java.util.HashMap;

public class Enemy extends NPC {
    protected int id = 0;
    protected boolean isLocked = false;
    protected int curHealth;
    protected int totalHealth;
    protected double antiJankTimer;
    protected float moveSpeed;

    public Enemy(int id, Point location, SpriteSheet spriteSheet, String startingAnimation, int totalHealth) { //sample call: Enemy(id = 0, xPos = 0, yPos = 0, spriteSheet = enemy.png, startingAnimation = ("DAMAGE" + totalHealth), totalHealth = 3)
        super(id, location.x, location.y, spriteSheet, startingAnimation);
        this.id = id;
        moveSpeed = 2;

        this.totalHealth = totalHealth;
        curHealth = totalHealth;
        antiJankTimer = System.nanoTime(); 
    }
    public void takeDamage() { //Call this when the enemy takes damage, done as it's own method in case something else needs to call it other than clicking
        curHealth -= 1;
        if(curHealth == 0) {
            
            //Dead stuff (Doing later cuz I've got a couple solutions and am not sure which is best yet)
            //Lazy plan for this is to setLocation off the map, then changed moveSpeed to 0
        } else {
            int tempHealth = curHealth-1; 
            setCurrentAnimationName("DAMAGE" + tempHealth);
        }

    }
    public void update(Player player) {
        //System.out.println("DEBUG: Time difference: " + (System.nanoTime()-antiJankTimer)/1000000000.0); //Doing this so the mouse can't just be spammed, puts a 1 second cooldown on the players ability to attack, could maybe make this adjustable in the future if we rlly wanted
        System.out.println("DEBUG: MousePos-" + MouseHandler.mousePos + " isClicked?-" + MouseHandler.leftMouseDown + " thisPos-x,y" + this.x + "," + this.y);
        if ((System.nanoTime()-antiJankTimer)/1000000000.0 > 1 && this.contains2(MouseHandler.mousePos) && MouseHandler.leftMouseDown) {
            System.out.println("DEBUG: clicked");
            antiJankTimer = System.nanoTime();
            this.takeDamage(); 
        }


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
        float xRatio = (px-ex)/(py-ey);
        float yRatio = (py-ey)/(px-ex);

        double angToPlay = Math.atan2(px-ex, py-ey); //Converts the coordinate to being relative to the enemy (makes the angle more applicable)
        //System.out.println("DEBUG:\n Enemy Coords: " + ex + ", " + ey + "\n Player Coords: " + px + ", " + py + "\n Angle Between: " + angToPlay + "\n\n");
        if(!isLocked) {
            moveXHandleCollision(moveSpeed*xRatio);
            moveYHandleCollision(moveSpeed*yRatio);
        }

    }
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) { //I hate this thing but u guys don't need to worry about it... Colors r definitely backwards rn tho I just need to remake the spritesheet
        return new HashMap<String, Frame[]>() {{
            put("DAMAGE1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0))
                    .withScale(3)
                    .withBounds(6,12,12,7)
                    .build(),
            });
            put("DAMAGE2", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,1))
                    .withScale(3)
                    .withBounds(6,12,12,7)
                    .build(),
            });
            put("DAMAGE3", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,2))
                    .withScale(3)
                    .withBounds(6,12,12,7)
                    .build(),
            });

        }};
    }
}
