package Waves;

import java.util.ArrayList;
public class WaveManager {
    public static ArrayList<Enemy> currentEnemies;
    public static int numEnemies;
    public static int currentWave;
    public static final int TOTAL_WAVES = 5;

    public WaveManager() {
        currentWave = 1;

        for (int i = 0; i < 5; i++) {
            // logic to add enemies to the initial wave should go here
            // the condition in this loop should be i < amount of enemies you want
            // increment numEnemies as you go
        }
    }

    public void newWave(int numEnemies) {
        currentWave++;
    }

    public void killEnemy(Enemy e) {
        currentEnemies.remove(e);
    }
} 
