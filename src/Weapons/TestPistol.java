package Weapons;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Map;
import Utils.Point;

public class TestPistol extends Weapon{
    private static Frame gunPic = new Frame(ImageLoader.load("Pistol1.png"));
            public TestPistol(Point playerLoc, Map map) { //In theory, this is all that's needed for a custom weapon... Eventually should stop using the Spritesheet in constructor tho
                super(gunPic, playerLoc, map); 
            this.fireRate = 1; 
            this.DPH = .5f;
            this.bulletSpeed = 3;
            System.out.println("DEBUG: Weapon Successfuly created");
    }
}
