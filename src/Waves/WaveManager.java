package Waves;

import Enemies.Enemy;
import Enemies.FarmerBoss;  // Import FarmerBoss
import Level.Map;
import Maps.TestMap;
import NPCs.Boss1Teleporter;
import Waves.Wave;

import java.util.ArrayList;

public class WaveManager {
    private ArrayList<Wave> waves;
    public static int numEnemies;       // Track number of fox enemies in the current wave
    public static int numFarmerBosses;  // Track number of FarmerBosses in the current wave
    public static int currentWaveIndex;
    private Wave currentWave;
    private int numWaves;
    private Map testMap;

    private boolean penguinsSpawned = false;
    private boolean SharksSpawned = false;

    private boolean wave5Done, wave10Done;


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
        wave5Done = false;
        wave10Done = false; 
    }

    // Starts a new wave if there are any remaining
    public void newWave() {
        if (currentWaveIndex < numWaves) {
            currentWave = waves.get(currentWaveIndex);
            currentWave.SpawnFox(); // Spawn enemies and FarmerBosses in the current wave
            penguinsSpawned = false;
            SharksSpawned = false; // Reset penguin spawn flag when a new wave starts
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
            if (numEnemies <= 0) {
                newWave();
            }
        }

        // Example of additional logic that depends on wave count
        if(currentWaveIndex == 3) { //Lava area final wave
            currentWave.SpawnBossTP(3,3, 1, testMap);
        }
        else if (currentWaveIndex >= 2 && !SharksSpawned) { //Snow area final wave
            currentWave.SpawnBossTP(6,15, 1, testMap);
            // int penguinsToSpawn = Math.max(1, currentWaveIndex / 1); 
            // currentWave.SpawnPenguin(penguinsToSpawn); 
            // penguinsSpawned = true; 
            int sharksToSpawn = Math.max(1, currentWaveIndex / 1); 
            currentWave.SpawnShark(sharksToSpawn); 
            SharksSpawned = true; 

        } else if (currentWaveIndex >= 1 && !penguinsSpawned) { //Field area final wave
            currentWave.SpawnBossTP(31,26, 1, testMap);
            //((TestMap) testMap).destroyWall1(); // Custom map logic
            // Adjusts the number of penguins based on the wave index, not the total enemies
            int penguinsToSpawn = Math.max(1, currentWaveIndex / 1); 
            currentWave.SpawnPenguin(penguinsToSpawn); 
            penguinsSpawned = true; 

            //Logic for boss zone opening

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
