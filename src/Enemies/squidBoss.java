package Enemies;


import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Player;
import Players.Bunny;
import Utils.Point;
import Weapons.Bullet;

public class squidBoss extends Enemy{
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Squidboss.png"), 32, 32);
     public squidBoss(int id, Point location, String startingAnimation, Player player) { //startingAnimation is still in here so I don't break other things, it does nothing
        super(id, location, spriteSheet, "DAMAGE4");
        moveSpeed = 1f; // Higher is faster
        attackRadius = 320f; // Higher is farther
        totalHealth = 4; // Bigger is more health
        attackSpeed = 0.5f; // Lower is faster

        this.curHealth = totalHealth; 
    }
}
