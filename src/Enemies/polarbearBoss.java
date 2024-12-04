package Enemies;


import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import EnhancedMapTiles.Coin;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.MapTile;
import Level.Player;
import Scripts.TestMap.ReturnScriptB2;
import Utils.Point;

public class polarbearBoss extends Enemy{
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Resources/polarbearBoss.png"), 32, 32);
     public polarbearBoss(int id, Point location, String startingAnimation, Player player) { //startingAnimation is still in here so I don't break other things, it does nothing
        super(id, location, spriteSheet, "DAMAGE3");
        moveSpeed = 1f; // Higher is faster
        attackRadius = 150f; // Higher is farther
        totalHealth = 4; // Bigger is more health
        attackSpeed = 0.5f; // Lower is faster

        this.curHealth = totalHealth; 
    }
    @Override
    public void enemDeath() {
        //Coin Stuff
        MapTile currentTile = map.getMapTile((int) (this.getX() / map.getTileset().getScaledSpriteWidth()), 
                                             (int) (this.getY() / map.getTileset().getScaledSpriteHeight()));
        Coin droppedCoin = new Coin(currentTile.getLocation());
        map.addEnhancedMapTile(droppedCoin);
        
        this.map.getMapTile(5, 14).setInteractScript(new ReturnScriptB2(this.map.screenCoordinator));
    }
    @Override //Put custom animations here
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) { //I hate this thing but u guys don't need to worry about it... Colors r definitely backwards rn tho I just need to remake the spritesheet
        return new HashMap<String, Frame[]>() {{
            put("DAMAGE1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 1), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 2), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
            });
            put("DAMAGE2", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
            });
            put("DAMAGE3", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
            });
            put("DAMAGE4", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                    .withScale(5)
                    .withBounds(0,0,32,32)
                    .build()
            });

        }};
    }
}
