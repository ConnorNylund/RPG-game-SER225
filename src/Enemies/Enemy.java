package Enemies;

import Builders.FrameBuilder;
import Engine.MouseHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Point;
import Waves.WaveManager;

import java.util.HashMap;

public class Enemy extends NPC {
    protected int id = 0;
    protected boolean isLocked = false;
    protected int curHealth;
    protected int totalHealth;
    protected double antiJankTimer;

    public Enemy(int id, Point location, SpriteSheet spriteSheet, String startingAnimation, int totalHealth) { //sample call: Enemy(id = 0, xPos = 0, yPos = 0, spriteSheet = enemy.png, startingAnimation = ("DAMAGE" + totalHealth), totalHealth = 3)
        super(id, location.x, location.y, spriteSheet, startingAnimation);
        this.id = id;

        this.totalHealth = totalHealth;
        curHealth = totalHealth;
        antiJankTimer = System.nanoTime(); 

        WaveManager.currentEnemies.add(this);
    }
    public void takeDamage() { //Call this when the enemy takes damage, done as it's own method in case something else needs to call it other than clicking
        curHealth -= 1;
        if(curHealth == 0) {
            //Dead stuff (Doing later cuz I've got a couple solutions and am not sure which is best yet)
        } else {
            int tempHealth = curHealth-1; 
            setCurrentAnimationName("DAMAGE" + tempHealth);
        }

    }
    public void update(Player player) {
        System.out.println("DEBUG: Time difference: " + (System.nanoTime()-antiJankTimer)/1000000000.0); //Doing this so the mouse can't just be spammed, puts a 1 second cooldown on the players ability to attack
        if ((System.nanoTime()-antiJankTimer)/1000000000.0 > 1 && this.contains2(MouseHandler.mousePos) && MouseHandler.leftMouseDown) {
            System.out.println("clicked");
            antiJankTimer = System.nanoTime();
            this.takeDamage(); 
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
