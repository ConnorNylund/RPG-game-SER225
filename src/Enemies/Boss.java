package Enemies;


import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import EnhancedMapTiles.Coin;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.MapTile;
import Level.Player;
import Players.Bunny;
import Scripts.TestMap.ReturnScriptB1;
import Utils.Point;
import Weapons.Bullet;

public class Boss extends Enemy{
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("THEfarmerssheet.png"), 32, 32);
     public Boss(int id, Point location, String startingAnimation, Player player) { //startingAnimation is still in here so I don't break other things, it does nothing
        super(id, location, spriteSheet, "DAMAGE4");
        moveSpeed = 0.9f; // Higher is faster
        attackRadius = 320; // Higher is farther
        totalHealth = 4; // Bigger is more health
        attackSpeed = 1; // Lower is faster

        this.curHealth = totalHealth; 
    }
    @Override
    protected void attackPlayer(Player player) { 
        if((System.nanoTime()-lastAttack)/1000000000.0 > attackSpeed) {
            if(player.getX() > this.getX()) {
                new Bullet(this.x+15+this.getWidth(), this.y, player.getX(), player.getY(), 5, 20, super.map, 1, false);
            } else {
                new Bullet(this.x-15, this.y, player.getX(), player.getY(), 5, 20, super.map, 1, false);
            }
            lastAttack = System.nanoTime(); 
        }
    }
    @Override
    protected void enemDeath() {
        //Coin Stuff
        MapTile currentTile = map.getMapTile((int) (this.getX() / map.getTileset().getScaledSpriteWidth()), 
                                             (int) (this.getY() / map.getTileset().getScaledSpriteHeight()));
        Coin droppedCoin = new Coin(currentTile.getLocation());
        map.addEnhancedMapTile(droppedCoin);

        //Map swap & Boss death event
        this.map.getMapTile(5, 14).setInteractScript(new ReturnScriptB1(this.map.screenCoordinator));
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
