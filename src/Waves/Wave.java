package Waves;

import java.util.ArrayList;

import Enemies.Enemy;
import Enemies.Penguin;
import Enemies.Shark;

import Level.Map;
import Level.MapEntityStatus;
import Level.MapTile;
import Maps.TestMap;
import NPCs.Boss1Teleporter;
import Scripts.TestMap.BossChallengeScript1;
import Utils.Point;

public class Wave {
    private ArrayList<Enemy> enemies;
    private int numEnemies;
    private int numFarmerBosses; // Number of FarmerBosses
    private Map map;

    // Constructor now takes numFarmerBosses as a parameter
    public Wave(int numEnemies, int numFarmerBosses, Map map) {
        this.enemies = new ArrayList<>();
        this.numEnemies = numEnemies;
        this.numFarmerBosses = numFarmerBosses; // Set the number of FarmerBosses
        this.map = map;
    }

    // Spawn method that adds both fox enemies and FarmerBosses to the map
    public void SpawnFox() {
        // Add regular enemies (foxes) to the wave
        for (int i = 0; i < this.numEnemies; i++) {
            MapTile grassTile = map.getRandomGrassTile();
            Point position = grassTile.getLocation();
            Point scaledPos = new Point(position.x / map.getTileset().getScaledSpriteWidth(), position.y / map.getTileset().getScaledSpriteHeight());

            Enemy testEnem = new Enemy(1, map.getMapTile((int) scaledPos.x, (int) scaledPos.y).getLocation(), "DAMAGE3", map.getPlayer()); 
            this.enemies.add(testEnem);
            map.addNPC(testEnem);
        }
    }

    public void SpawnPenguin(int numEnemiesToSpawn) {
        for (int i = 0; i < numEnemiesToSpawn; i++) {
            MapTile snowTile = map.getRandomSnowTile();
            Point position = snowTile.getLocation();
            Point scaledPos = new Point(position.x / map.getTileset().getScaledSpriteWidth(), position.y / map.getTileset().getScaledSpriteHeight());

            Penguin testEnem = new Penguin(1, map.getMapTile((int) scaledPos.x, (int) scaledPos.y).getLocation(), "DAMAGE3", map.getPlayer());
            this.enemies.add(testEnem);
            map.addNPC(testEnem);
        }
    }
    public void SpawnBossTP(float x, float y, int bossNum, Map map2) {
        Point point = new Point(map2.getMapTile((int)x, (int)y).getX(), map2.getMapTile((int)x, (int)y).getY()); 
        Boss1Teleporter BossTP = new Boss1Teleporter(9, point, "ANIM1", bossNum, map2);
        System.out.println("DEBUG: Passing X/Y = " +  x + "/" + y); 
        switch(bossNum) { //I should put the hole spawner in here, and hardcode the coords rather than doing it in WaveManager every time... But that makes too much sense and I'm lazy
            case(1):
            ((TestMap)map2).loadBossScript1(map2.screenCoordinator, x, y);
            break;
            case(2):
            ((TestMap)map2).loadBossScript2(map2.screenCoordinator, x, y);
            break;
            case(3):
            ((TestMap)map2).loadBossScript3(map2.screenCoordinator, x, y);
            break;
        }
         
        map2.addNPC(BossTP);
    }
    public void SpawnShark(int numEnemiesToSpawn) {
        for (int i = 0; i < numEnemiesToSpawn; i++) {
            MapTile fireTile = map.getRandomFireTile();
            Point position = fireTile.getLocation();
            Point scaledPos = new Point(position.x / map.getTileset().getScaledSpriteWidth(), position.y / map.getTileset().getScaledSpriteHeight());

            Shark testEnem = new Shark(1, map.getMapTile((int) scaledPos.x, (int) scaledPos.y).getLocation(), "DAMAGE3", map.getPlayer());
            this.enemies.add(testEnem);
            map.addNPC(testEnem);
        }
    }
    

    // Update method for managing active and removed enemies and FarmerBosses
    public void update() {
        // Remove enemies marked for removal
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

    // Returns the number of regular enemies currently alive
    public int getEnemiesAlive() {
        return this.enemies.size();
    }
    // Getter for the total number of regular enemies in this wave
    public int getNumEnemies() {
        return this.numEnemies;
    }

    // Getter for the total number of FarmerBosses in this wave
    public int getNumFarmerBosses() {
        return this.numFarmerBosses;
    }
}
