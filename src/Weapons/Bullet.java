package Weapons;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Enemies.Enemy;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Level.Map;
import Level.MapEntity;
import Level.MapTile;
import Level.NPC;
import Level.Player;
import Players.Bunny;
import Utils.Point;

public class Bullet extends NPC{
    private float projSpeed; 
    private float Fx, Fy; //Final X and Y
    private float prevAngle, curAngle; //Angle shouldn't change, so if it does, we've passed the target
    private static SpriteSheet bulletPic = new SpriteSheet(ImageLoader.load("Sword.png"), 16, 16); //Temporary
    private static int bulletCt = 60;
    private boolean hasDamaged; 
    private float AOE; 
    private float bulletLife, creationTime;
    public Bullet(SpriteSheet spriteSheet, float Sx, float Sy, float Fx, float Fy, float projSpeed, float AOE, Map map) { //Takes in a starting x and y, and a final x and y, then will travel between the two in a set bullet speed
        super(bulletCt, Sx, Sy, bulletPic, "Anim1");  
        bulletCt++; 
        this.setMap(map);
        map.addNPC(this); 
        this.Fx = Fx;
        this.Fy = Fy; 
        this.projSpeed = projSpeed; 
        this.AOE = AOE;
        curAngle = (float)Math.atan2(Fy-Sy, Fx-Sx);
        prevAngle = curAngle; 
        hasDamaged = false; 
        creationTime = System.nanoTime(); 
        bulletLife = 5; 
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
        ArrayList<Enemy> activeEnemies = new ArrayList(0);
            for(int i = 0; i<map.getActiveNPCs().size(); i++) { //In theory this clusterfuck filters out only enemies in potentially the jankiest way I could
                try {
                    activeEnemies.add((Enemy)map.getActiveNPCs().get(i)); //Grabs all active NPCs by attempting to cast NPCs to an Enemy and catching the error if it's not an enemy
                } catch(ClassCastException e) {
                    System.out.println("Java getting mad about jank casts");
                }
                //System.out.println("DEBUG: Active Enemies = " + activeEnemies.size());
            }
            for(int i = 0; i<activeEnemies.size(); i++) {
                Enemy temp = activeEnemies.get(i);
                // Rectangle enemHitBox = temp.getCalibratedBounds();
                
                // boolean inX1 = this.x >= enemHitBox.getX1()-attackRange;
                // boolean inX2 = this.x <= enemHitBox.getX2()+attackRange;
                // boolean inY1 = this.y >= enemHitBox.getY1()-attackRange;
                // boolean inY2 = this.y <= enemHitBox.getY2()+attackRange;
                //System.out.println("DEBUG: x1="+enemHitBox.getX1() + " x2=" + enemHitBox.getX2() + " y1=" + enemHitBox.getY1() + " y2=" + enemHitBox.getY2());
                if( this.x >= temp.getX()-AOE && 
                    this.x <= temp.getX()+temp.getWidth()+AOE &&
                    this.y >= temp.getY()-AOE &&
                    this.y <= temp.getY()+temp.getHeight()+AOE &&
                    !hasDamaged) {
                        temp.takeDamage(); 
                        hasDamaged = true; 
                        this.mapEntityStatus = mapEntityStatus.REMOVED; 
                }

                //System.out.println("DEBUG: >x1" + inX1 + " <x2" + inX2 + " >y1" + inY1 + " <y2" + inY2);
            }

        if(this.reachedTarget()) {
            System.out.println("DEBUG: Is reaching target"); 
            //Destroy bullet, damage whats been hit
            this.projSpeed = 0; //Temporary so bullets will stop when they reach their targets 

            this.mapEntityStatus = mapEntityStatus.REMOVED; 
        }
        if((System.nanoTime()-creationTime)/1000000000.0 > bulletLife) {
            this.mapEntityStatus = mapEntityStatus.REMOVED;
        }
    }
    public boolean reachedTarget() {
        // System.out.println("DEBUG: Fx = " + Fx + " CurX = " + this.x);
        return (int)Fx == (int)this.x || (int)Fy == (int)this.y; 
        
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
