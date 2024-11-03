package Weapons;

import GameObject.GameObject;
import Utils.Point;

public class Weapon extends GameObject{
    private int fireRate;
    private int DPH;
    public Weapon(int fireRate, int DPH, Point playerLoc) {
        super(playerLoc.x, playerLoc.y); 
        this.fireRate = fireRate;
        this.DPH = DPH;
    }
    public void shoot() {

    }
}
