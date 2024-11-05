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
    private float prevAngle, curAngle; //Angle shouldn't change, so if it does, we've passed the target
    private static Frame bulletPic = new Frame(ImageLoader.load("Sword.png")); //Temporary
    public Bullet(SpriteSheet spriteSheet, float Sx, float Sy, float Fx, float Fy, float projSpeed, Map map) { //Takes in a starting x and y, and a final x and y, then will travel between the two in a set bullet speed
        super(Sx, Sy, bulletPic);  
        // this.setMap(map);
        this.Fx = Fx;
        this.Fy = Fy; 
        curAngle = (float)Math.atan2(Fy-Sy, Fx-Sx);
        prevAngle = (float)Math.atan2(Fy-Sy, Fx-Sx); 
    }
    public void update(Player player) { 
        
        float curX = this.getX();
        float curY = this.getY();
        System.out.println("DEBUG: Current Bullet Position (x,y): " + curX + "," + curY);

        float dx = Fx-curX;
        float dy = Fy-curY;

        prevAngle = curAngle; 
        curAngle = (float)Math.atan2(dy, dx); //Once the bullet passes it's target, these angles should be different

        float dMag = (float)Math.sqrt((double)(dx*dx+dy*dy));
        float xRatio = dx/dMag;
        float yRatio = dy/dMag;

        super.moveXHandleCollision(projSpeed*xRatio);
        super.moveYHandleCollision(projSpeed*yRatio); 
        
        float tempTest = prevAngle-curAngle; 
        System.out.println("DEBUG: Angle to Bullet Target Prev-Cur = " + tempTest); 

        if(this.reachedTarget()) {
            //Destroy bullet, damage whats been hit
            this.projSpeed = 0; //Temporary so bullets will stop when they reach their targets 
        }
    }
    public boolean reachedTarget() {
        return curAngle!=prevAngle; 
    }
}
