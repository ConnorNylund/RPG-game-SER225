package Waves;
import Enemies.Enemy;

import java.util.ArrayList;
public class WaveManager {
    private ArrayList<Wave> waves;
    public static int numEnemies;
    public static int currentWaveIndex;
    private Wave currentWave;
    private int numWaves;

    public WaveManager(int numWaves) {
        this.numWaves = numWaves;
        currentWaveIndex = 0;
        waves = new ArrayList<Wave>(5);
        
        newWave();
    }

    public void newWave() {
        if (currentWaveIndex != numWaves) {
            currentWaveIndex++;
            if (!waves.isEmpty()) {
                currentWave = waves.get(currentWaveIndex-1);
            }
        }
    }

    public void addWave(Wave w) {
        waves.add(w);
    }

    public Wave getCurrentWave() {
        return this.currentWave;
    }
} 
