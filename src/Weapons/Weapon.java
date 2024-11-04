package Weapons;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Engine.MouseHandler;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.Map;
import Level.MapEntity;
import Level.Player;
import Utils.Point;

public class Weapon extends MapEntity{
    protected int fireRate;
    protected float DPH; //Damage per hit
    protected float antiJankTimer, bulletSpeed;
    protected Map map;
    
    public Weapon(Frame img, Point playerLoc, Map map) { //Copy paste this constructor into your subclass
        super(playerLoc.x, playerLoc.y, img);
        this.map = map; 
        this.setMap(map);
        this.mapEntityStatus = mapEntityStatus.ACTIVE; 
        //Input value for subclass weapon here
        this.fireRate = 0; 
        this.DPH = 0;
        this.bulletSpeed = 0;
        antiJankTimer = 0;
    }
    public void shoot(float Sx, float Sy, float Fx, float Fy) {
        if ((System.nanoTime()-antiJankTimer)/1000000000.0 > fireRate) {
            System.out.println("DEBUG: shot");
            antiJankTimer = System.nanoTime();
            new Bullet(new SpriteSheet(ImageLoader.load("tempEnemy.png"), 16, 16), Sx, Sy, Fx, Fy, bulletSpeed, map);
        }
    }
    public void update(Player player) {
        this.setLocation(player.getX(), player.getY()); 
        System.out.println("DEBUG: Current Gun Position (x,y): " + this.getX() + "," + this.getY());
    }
}
