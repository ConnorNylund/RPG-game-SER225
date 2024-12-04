package Weapons;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Utils.Point;

public class GoodPistol extends Weapon {
    private static SpriteSheet gunPic = new SpriteSheet(ImageLoader.load("Resources/UpgradedPistol.png"), 16, 16);
    public GoodPistol(Point playerLoc, Map map) {
        super(gunPic, playerLoc, map); 
        this.fireRate = .8f;
        this.DPH = 1.25f;
        this.bulletSpeed = 6; 
        this.AOE = 20; 
    }
}
