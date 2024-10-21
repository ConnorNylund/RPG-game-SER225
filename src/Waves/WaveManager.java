package Waves;
import Enemies.Enemy;

import java.util.ArrayList;
public class WaveManager {
    public static ArrayList<Enemy> currentEnemies;
    public static int numEnemies;
    public static int currentWave;
    public static final int TOTAL_WAVES = 5;

    public WaveManager() {
        currentWave = 1;
    }

    public void newWave(int numEnemies) {
        currentWave++;
    }

    public void killEnemy(Enemy e) {
        currentEnemies.remove(e);
        numEnemies--;
    }

    public static void addEnemy(Enemy e) {
        currentEnemies.add(e);
        numEnemies++;
    }
} 
