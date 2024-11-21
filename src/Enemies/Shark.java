package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Player;
import Utils.Point;

public class Shark extends Enemy{
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Sharksprite.png"), 16, 16);
    public Shark(int id, Point location, String startingAnimation, Player player) {
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