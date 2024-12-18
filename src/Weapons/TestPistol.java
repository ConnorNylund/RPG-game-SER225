package Weapons;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Utils.Point;

public class TestPistol extends Weapon{
    private static SpriteSheet gunPic = new SpriteSheet(ImageLoader.load("Resources/Pistol1WBackground.png"), 16, 16);
        public TestPistol(Point playerLoc, Map map) { //In theory, this is all that's needed for a custom weapon...
            super(gunPic, playerLoc, map); 
            this.fireRate = 0.5f; //Lower is faster
            this.DPH = 1.0f; //Higher is more damage
            this.bulletSpeed = 6; //Higher is faster
            this.AOE = 20; //Larger is bigger
            System.out.println("DEBUG: Weapon Successfuly created");
    }
}
