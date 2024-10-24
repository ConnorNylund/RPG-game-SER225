package Waves;
import Enemies.Enemy;
import Level.Map;
import Maps.TestMap;

import java.util.ArrayList;
public class WaveManager {
    private ArrayList<Wave> waves;
    public static int numEnemies;
    public static int currentWaveIndex;
    private Wave currentWave;
    private int numWaves;
    private Map testMap;

    public WaveManager(int numWaves, Map map) {
        this.testMap = map;
        this.numWaves = numWaves;
        currentWaveIndex = 0;
        waves = new ArrayList<Wave>(5);

        for (int i = 0; i < numWaves; i++) {
            Wave wave = new Wave((i + 1) * 2, map);
            waves.add(wave);
        }
        
        newWave();
    }

    public void newWave() {
        if (currentWaveIndex != numWaves) {
            currentWaveIndex++;
            if (!waves.isEmpty()) {
                currentWave = waves.get(currentWaveIndex-1);
                currentWave.Spawn();
            }
        }
    }

    public void update() {
        if (currentWave != null) {
            currentWave.update();
            numEnemies = currentWave.getEnemiesAlive();

            if (currentWave.getEnemiesAlive() <= 0) {
                newWave();
            }
        }
        if(currentWaveIndex >= 4) {
            ((TestMap)testMap).destroyWall1(); 
        }
    }

    public void addWave(Wave w) {
        waves.add(w);
    }

    public Wave getCurrentWave() {
        return this.currentWave;
    }
} 
