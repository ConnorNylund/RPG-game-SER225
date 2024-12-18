package Enemies;


import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Player;
import Utils.Point;

public class Boss2 extends Enemy{
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Resources/farmersprites.png"), 32, 32);
     public Boss2(int id, Point location, String startingAnimation, Player player) { //startingAnimation is still in here so I don't break other things, it does nothing
        super(id, location, spriteSheet, "DAMAGE4");
        moveSpeed = 0.9f; // Higher is faster
        attackRadius = 120; // Higher is farther
        totalHealth = 4; // Bigger is more health
        attackSpeed = 1; // Lower is faster

        this.curHealth = totalHealth; 
    }
    @Override //Put custom animations here
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) { //I hate this thing but u guys don't need to worry about it... Colors r definitely backwards rn tho I just need to remake the spritesheet
        return new HashMap<String, Frame[]>() {{
            put("DAMAGE1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,3))
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
            });
            put("DAMAGE2", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,2))
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
            });
            put("DAMAGE3", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,1))
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
            });
            put("DAMAGE4", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build()
            });

        }};
    }
}
