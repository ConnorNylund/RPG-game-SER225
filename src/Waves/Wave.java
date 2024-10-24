package Waves;

import java.util.ArrayList;

import Enemies.Enemy;
import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Level.MapEntityStatus;
import Level.MapTile;
import Utils.Point;

public class Wave {
    private ArrayList<Enemy> enemies;
    private int numEnemies;
    private Map map;

    public Wave(int numEnemies, Map map) {
        this.enemies = new ArrayList<>();
        this.numEnemies = numEnemies;
        this.map = map;
    }

    public void Spawn() {
        for (int i = 0; i < this.numEnemies; i++) {
            MapTile grassTile = map.getRandomGrassTile();
            Point position = grassTile.getLocation();
            Point scaledPos = new Point(position.x / map.getTileset().getScaledSpriteWidth(), position.y / map.getTileset().getScaledSpriteHeight());

            Enemy testEnem = new Enemy(1, map.getMapTile((int) scaledPos.x, (int) scaledPos.y).getLocation(), new SpriteSheet(ImageLoader.load("tempEnemy.png"), 16, 16), "DAMAGE3", 3); 
            this.enemies.add(testEnem);
            map.addNPC(testEnem);
        }
    }

    public void update() {
        ArrayList<Enemy> enemiesToDie = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.getMapEntityStatus() == MapEntityStatus.REMOVED) {
                enemiesToDie.add(enemy);
            }
        }

        for (Enemy enemy : enemiesToDie) {
            this.enemies.remove(enemy);
        }
    }

    public int getEnemiesAlive() {
        return this.enemies.size();
    }

    public int getNumEnemies() {
        return this.numEnemies;
    }
}
