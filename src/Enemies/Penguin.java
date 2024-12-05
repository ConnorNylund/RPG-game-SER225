package Enemies;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Player;
import Utils.Point;

public class Penguin extends Enemy{
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("penguinsprites.png"), 16, 16);
    public Penguin(int id, Point location, String startingAnimation, Player player) {
        super(id, location, spriteSheet, startingAnimation);
        this.id = id; 
        moveSpeed = 2.0f;
        attackRadius = 60; 
        totalHealth = 3;
        attackSpeed = 3; 

        lastAttack = 0; 
        curHealth = totalHealth;
        antiJankTimer = System.nanoTime();
    }
}
