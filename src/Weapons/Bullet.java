package Weapons;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Enemies.Enemy;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Level.Player;
import Players.Bunny;

public class Bullet extends NPC{
    private float projSpeed; 
    private float Fx, Fy; //Final X and Y
    private float prevAngle, curAngle; //Angle shouldn't change, so if it does, we've passed the target
    private static SpriteSheet bulletPic = new SpriteSheet(ImageLoader.load("bullet.png"), 16, 16); //Temporary
    private static int bulletCt = 60;
    private boolean hasDamaged; 
    private float AOE; 
    private float bulletLife, creationTime;
    private float DPH; 
    private Map map;
    private boolean enemBullet;
    public Bullet(float Sx, float Sy, float Fx, float Fy, float projSpeed, float AOE, Map map, float DPH, boolean enemBullet) { //Takes in a starting x and y, and a final x and y, then will travel between the two in a set bullet speed
        super(bulletCt, Sx, Sy, bulletPic, "Anim1");  
        bulletCt++; 
        this.setMap(map);
        map.addNPC(this); 
        this.map = map; 
        this.Fx = Fx;
        this.Fy = Fy; 
        this.projSpeed = projSpeed; 
        this.AOE = AOE;
        this.DPH = DPH;
        this.enemBullet = enemBullet; 

        curAngle = (float)Math.atan2(Fy-Sy, Fx-Sx);
        prevAngle = curAngle; 
        hasDamaged = false; 
        creationTime = System.nanoTime(); 
        bulletLife = 5; 
    }
    public void update(Player player) { 
        float curX = this.x;
        float curY = this.y;

        float dx = Fx-curX;
        float dy = Fy-curY;

        prevAngle = curAngle; 
        curAngle = (float)Math.atan2(dy, dx); //Once the bullet passes it's target, these angles should be different

        float dMag = (float)Math.sqrt((double)(dx*dx+dy*dy));
        float xRatio = dx/dMag;
        float yRatio = dy/dMag;

        super.moveXHandleCollision(projSpeed*xRatio);
        super.moveYHandleCollision(projSpeed*yRatio); 
        
        if(enemBullet) { //For player attacks
            float tempTest = prevAngle-curAngle; 
            ArrayList<Enemy> activeEnemies = new ArrayList(0);
            for(int i = 0; i<map.getActiveNPCs().size(); i++) { //In theory this clusterfuck filters out only enemies in potentially the jankiest way I could
                try {
                    activeEnemies.add((Enemy)map.getActiveNPCs().get(i)); //Grabs all active NPCs by attempting to cast NPCs to an Enemy and catching the error if it's not an enemy
                } catch(ClassCastException e) {
                    //System.out.println("Java getting mad about jank casts");
                }
            }
            for(int i = 0; i<activeEnemies.size(); i++) {
                Enemy temp = activeEnemies.get(i);
                if( this.x >= temp.getX()-AOE && 
                    this.x <= temp.getX()+temp.getWidth()+AOE &&
                    this.y >= temp.getY()-AOE &&
                    this.y <= temp.getY()+temp.getHeight()+AOE &&
                    !hasDamaged) { 
                        temp.takeDamage(DPH); 
                        hasDamaged = true; 
                        this.mapEntityStatus = mapEntityStatus.REMOVED; 
                }
            }
        } else { //For projectile damage from bosses
            if( this.x >= player.getX()-AOE && 
                this.x <= player.getX()+player.getWidth()+AOE &&
                this.y >= player.getY()-AOE &&
                this.y <= player.getY()+player.getHeight()+AOE &&
                !hasDamaged) { 
                    ((Bunny)player).takeDamage(DPH); 
                    hasDamaged = true; 
                    this.mapEntityStatus = mapEntityStatus.REMOVED; 
            }
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
