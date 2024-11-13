package Weapons;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Utils.Point;

public class SMG extends Weapon {
    private static SpriteSheet gunPic = new SpriteSheet(ImageLoader.load("SMG.png"), 16, 16);
    public SMG(Point playerLoc, Map map) {
        super(gunPic, playerLoc, map); 
        this.fireRate = .25f; 
        this.DPH = .25f;
        this.bulletSpeed = 4;
        this.AOE = 20; 
    }
}
