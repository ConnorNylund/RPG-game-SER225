package Waves;

import Enemies.Enemy;
import Enemies.FarmerBoss;  // Import FarmerBoss
import Level.Map;
import Maps.TestMap;

import java.util.ArrayList;

public class WaveManager {
    private ArrayList<Wave> waves;
    public static int numEnemies;       // Track number of fox enemies in the current wave
    public static int numFarmerBosses;  // Track number of FarmerBosses in the current wave
    public static int currentWaveIndex;
    private Wave currentWave;
    private int numWaves;
    private Map testMap;

    public WaveManager(int numWaves, Map map) {
        this.testMap = map;
        this.numWaves = numWaves;
        currentWaveIndex = 0;
        waves = new ArrayList<>(numWaves);

        // Create waves with a specific number of regular enemies and FarmerBosses
        for (int i = 0; i < numWaves; i++) {
            int numFarmerBosses = (i == numWaves - 1) ? 1 : 0; // Add a FarmerBoss only in the last wave
            Wave wave = new Wave((i + 1) * 2, numFarmerBosses, map); // Increment number of fox enemies with each wave
            waves.add(wave);
        }

        newWave();
    }

    // Starts a new wave if there are any remaining
    public void newWave() {
        if (currentWaveIndex < numWaves) {
            currentWave = waves.get(currentWaveIndex);
            currentWave.Spawn(); // Spawn enemies and FarmerBosses in the current wave
            currentWaveIndex++;
        }
    }

    // Updates the current wave and triggers the next wave if all enemies and FarmerBosses are defeated
    public void update() {
        if (currentWave != null) {
            currentWave.update();
            numEnemies = currentWave.getEnemiesAlive();
            
            numFarmerBosses = currentWave.getFarmerBossesAlive();

            // If no enemies or FarmerBosses are left, start the next wave
            if (numEnemies <= 0 && numFarmerBosses <= 0) {
                newWave();
            }
        }

        // Example of additional logic that depends on wave count
        if (currentWaveIndex >= 2) {
            ((TestMap) testMap).destroyWall1(); // Custom map logic
        }
    }

    // Method for handling only the FarmerBoss wave updates (if needed)
    public void bossupdate() {
        if (currentWave != null) {
            currentWave.update();
            
            numFarmerBosses = currentWave.getFarmerBossesAlive();

            if (numFarmerBosses <= 0) {
                newWave();
            }
        }
    }

    // Optional method to add additional waves, if needed
    public void addWave(Wave wave) {
        waves.add(wave);
    }

    // Getter for the current wave
    public Wave getCurrentWave() {
        return this.currentWave;
    }
}
