package Weapons;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.Map;
import Level.MapEntity;
import Level.Player;

public class Bullet extends MapEntity{
    private float projSpeed; 
    private float Fx, Fy; //Final X and Y
    private static Frame bulletPic = new Frame(ImageLoader.load("Sword.png"));
    public Bullet(SpriteSheet spriteSheet, float Sx, float Sy, float Fx, float Fy, float projSpeed, Map map) { //Takes in a starting x and y, and a final x and y, then will travel between the two in a set bullet speed
        super(Sx, Sy, bulletPic);  
        // this.setMap(map);
        this.Fx = Fx;
        this.Fy = Fy; 
    }
    public void update(Player player) { 
        float curX = this.getX();
        float curY = this.getY();
        System.out.println("DEBUG: Current Bullet Position (x,y): " + this.getX() + "," + this.getY());

        float dx = Fx-curX;
        float dy = Fy-curY;

        float dMag = (float)Math.sqrt((double)(dx*dx+dy*dy));
        float xRatio = dx/dMag;
        float yRatio = dy/dMag;

        super.moveXHandleCollision(projSpeed*xRatio);
        super.moveYHandleCollision(projSpeed*yRatio); 
    }
}
