package Waves;

import java.util.ArrayList;

import Enemies.Enemy;
import Enemies.Boss;
import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Level.MapEntityStatus;
import Level.MapTile;
import Utils.Point;

public class Wave {
    private ArrayList<Enemy> enemies;
    private ArrayList<Boss> bosses;
    private int numEnemies;
    private int numBosses;
    private Map map;

    public Wave(int numEnemies, Map map) {
        this.enemies = new ArrayList<>();
        this.numEnemies = numEnemies;
        this.map = map;
        this.bosses = new ArrayList<>();
        this.numBosses = numBosses;
        this.map = map;
    }

    public void Spawn() {
        // for (int i = 0; i < this.numBosses; i++) {
        //     MapTile bossTile = map.getRandomBossTile();
        //     Point position = bossTile.getLocation();
        //     Point scaledPos = new Point(position.x / map.getTileset().getScaledSpriteWidth(), position.y / map.getTileset().getScaledSpriteHeight());

        //     Boss testBoss = new Boss(0, map.getMapTile((int) scaledPos.x, (int) scaledPos.y).getLocation(), "DAMAGE3", map.getPlayer()); 
        //     this.enemies.add(testBoss);
        //     map.addNPC(testBoss);
        // }

        for (int i = 0; i < this.numEnemies; i++) {
            MapTile grassTile = map.getRandomGrassTile();
            Point position = grassTile.getLocation();
            Point scaledPos = new Point(position.x / map.getTileset().getScaledSpriteWidth(), position.y / map.getTileset().getScaledSpriteHeight());

            Enemy testEnem = new Enemy(1, map.getMapTile((int) scaledPos.x, (int) scaledPos.y).getLocation(), "DAMAGE3", map.getPlayer()); 
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

        ArrayList<Boss> bossesToDie = new ArrayList<>();
        for (Boss boss : this.bosses) {
            if (boss.getMapEntityStatus() == MapEntityStatus.REMOVED) {
                bossesToDie.add(boss);
            }
        }

        for (Boss boss : bossesToDie) {
            this.enemies.remove(boss);
        }
    }

    public int getEnemiesAlive() {
        return this.enemies.size();
    }

    public int getNumEnemies() {
        return this.numEnemies;
    }

    public int getBossesAlive() {
        return this.bosses.size();
    }

    public int getNumBosses() {
        return this.numBosses;
    }
}
