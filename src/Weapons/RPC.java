package Weapons;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Utils.Point;

public class RPC extends Weapon{
    private static SpriteSheet gunPic = new SpriteSheet(ImageLoader.load("SMG.png"), 16, 16);
    public RPC(Point playerLoc, Map map) {
        super(gunPic, playerLoc, map);
        this.fireRate = 3f;
        this.DPH = 3f; 
        this.bulletSpeed = 2;
        this.AOE = 40;
        this.bulletCt = 5; 
    }
}
