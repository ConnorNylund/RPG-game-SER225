package Weapons;

import java.util.ArrayList;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
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
    protected float fireRate;
    protected float DPH; //Damage per hit
    protected float AOE; 
    protected float antiJankTimer, bulletSpeed;
    protected Map map;
    protected ArrayList<Bullet> bullets; 
    protected int bulletCt = 1;
    
    public Weapon(SpriteSheet spriteSheet, Point playerLoc, Map map) { //Copy paste this constructor into your subclass
        super(playerLoc.x, playerLoc.y, spriteSheet, "Anim1");
        this.map = map; 
        this.setMap(map); //This should be making it so that the item actually exists (it's how Map.java instantiates everything) but it just... isn't
        this.mapEntityStatus = mapEntityStatus.ACTIVE; 
        //Input value for subclass weapon here
        // this.fireRate = 0; 
        // this.DPH = 0;
        // this.bulletSpeed = 0;
        antiJankTimer = 0;
        bullets = new ArrayList(0); 
    }
    public void shoot(float Sx, float Sy, float Fx, float Fy) {
        if ((System.nanoTime()-antiJankTimer)/1000000000.0 > fireRate) {
            System.out.println("DEBUG: shot");
            antiJankTimer = System.nanoTime();
            for(int i = 0; i<bulletCt; i++) {
                bullets.add(new Bullet(Sx, Sy, Fx, Fy, bulletSpeed, AOE, map, DPH, true));
            }
            System.out.println("DEBUG: Bullet Added to array list, Active bullets: " + bullets.size()); 
        }
    }
    public void update(Player player) {
        this.setLocation(player.getX(), player.getY()+16); 
        for(int i = 0; i<bullets.size(); i++) {
            //System.out.println("Looping, on " + i + "th iteration");
            if(!bullets.get(i).reachedTarget()) {
                bullets.get(i).update(player); 
                //System.out.println("DEBUG: Bullet Succesfully updated"); 
            }
        }
    }
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("Anim1", new Frame[]{
                new FrameBuilder(spriteSheet.getSprite(0,0))
                    .withScale(2)
                    .withBounds(6,12,12,7)
                    .build()
            });
        }};
    }
}
