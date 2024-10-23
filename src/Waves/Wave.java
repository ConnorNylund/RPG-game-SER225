package Waves;

import java.util.ArrayList;

import Enemies.Enemy;

public class Wave {
    private int numEnemies;
    private ArrayList<Enemy> enemies;

    public Wave(int numEnemies, ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        this.numEnemies = enemies.size();
    }

    public int getNumEnemies() {
        return this.numEnemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
}
