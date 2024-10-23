package Waves;
import Enemies.Enemy;

import java.util.ArrayList;
public class WaveManager {
    private ArrayList<Wave> waves;
    private int numEnemies;
    private int currentWaveIndex;
    private Wave currentWave;
    private int numWaves;

    public WaveManager(int numWaves) {
        this.numWaves = numWaves;
        currentWaveIndex = 0;
        waves = 
        //numEnemies = 5; // replace this with random enemy spawner number of enemies
        newWave();
    }

    public void newWave() {
        if (currentWaveIndex != 5) {
            currentWaveIndex++;
        }
    }

    public void addWave(Wave w) {
        waves.add(w);
    }
} 
