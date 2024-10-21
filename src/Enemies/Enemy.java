package Enemies;

import Builders.FrameBuilder;
import Engine.MouseHandler;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import java.util.HashMap;

public class Enemy extends NPC {
    protected int id = 0;
    protected boolean isLocked = false;
    protected int curHealth;
    protected int totalHealth;
    protected double antiJankTimer;

    public Enemy(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, int totalHealth) { //sample call: Enemy(id = 0, xPos = 0, yPos = 0, spriteSheet = enemy.png, startingAnimation = ("DAMAGE" + totalHealth), totalHealth = 3)
        super(id, x, y, spriteSheet, startingAnimation);
        this.id = id;

        this.totalHealth = totalHealth;
        curHealth = totalHealth;
    }
    public void takeDamage() {
        curHealth -= 1;
        if(curHealth == 0) {
            
        } else {
            setCurrentAnimationName("DAMAGE" + curHealth);
        }

    }
    public void update(Player player) {
        if ((System.nanoTime()-antiJankTimer)/1000000000.0 > 1 && this.contains2(MouseHandler.mousePos) && MouseHandler.leftMouseDown) {
            System.out.println("clicked");
            antiJankTimer = System.nanoTime();
        }
    }
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DAMAGE1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,1))
                    .withScale(3)
                    .withBounds(6,12,12,7)
                    .build(),
            });
            put("DAMAGE2", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,2))
                    .withScale(3)
                    .withBounds(6,12,12,7)
                    .build(),
            });
            put("DAMAGE3", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,3))
                    .withScale(3)
                    .withBounds(6,12,12,7)
                    .build(),
            });

        }};
    }
}
