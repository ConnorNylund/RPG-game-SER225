package Weapons;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.Map;
import Level.MapEntity;
import Level.NPC;
import Level.Player;

public class Bullet extends NPC{
    private float projSpeed; 
    private float Fx, Fy; //Final X and Y
    private float prevAngle, curAngle; //Angle shouldn't change, so if it does, we've passed the target
    private static SpriteSheet bulletPic = new SpriteSheet(ImageLoader.load("Sword.png"), 16, 16); //Temporary
    private static int bulletCt = 60;
    public Bullet(SpriteSheet spriteSheet, float Sx, float Sy, float Fx, float Fy, float projSpeed, Map map) { //Takes in a starting x and y, and a final x and y, then will travel between the two in a set bullet speed
        super(bulletCt, Sx+50, Sy, bulletPic, "Anim1");  
        bulletCt++; 
        this.setMap(map);
        map.addNPC(this); 
        this.Fx = Fx;
        this.Fy = Fy; 
        this.projSpeed = projSpeed; 
        curAngle = (float)Math.atan2(Fy-Sy, Fx-Sx);
        prevAngle = curAngle; 
    }
    public void update(Player player) { 
        //float Fx = Fx;
        //float Fy = Fy;
        float curX = this.x;
        float curY = this.y;
        //System.out.println("DEBUG: Current Bullet Position (x,y): " + curX + "," + curY);

        float dx = Fx-curX;
        float dy = Fy-curY;

        prevAngle = curAngle; 
        curAngle = (float)Math.atan2(dy, dx); //Once the bullet passes it's target, these angles should be different

        float dMag = (float)Math.sqrt((double)(dx*dx+dy*dy));
        float xRatio = dx/dMag;
        float yRatio = dy/dMag;

        //System.out.println("DEBUG: curAngle = " + curAngle + " prevAngle" + prevAngle + " projSpeed = " + projSpeed); 

        super.moveXHandleCollision(projSpeed*xRatio);
        super.moveYHandleCollision(projSpeed*yRatio); 
        
        float tempTest = prevAngle-curAngle; 
        //System.out.println("DEBUG: Angle to Bullet Target Prev-Cur = " + tempTest); 

        if(this.reachedTarget()) {
            //Destroy bullet, damage whats been hit
            this.projSpeed = 0; //Temporary so bullets will stop when they reach their targets 
        }
    }
    public boolean reachedTarget() {
        return false; 
        //return curAngle!=prevAngle; 
    }
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("Anim1", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0,0))
                    .withScale(1)
                    .withBounds(0,0,16,16)
                    .build()
            });
        }};
    }
}
