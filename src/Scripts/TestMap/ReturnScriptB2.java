package Scripts.TestMap;

import Game.GameState;
import Game.ScreenCoordinator;
import Level.Script;
import Level.ScriptState;
import Maps.TestMap;
import Screens.PlayLevelScreen;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import java.util.ArrayList;

// Script for challenging the boss
public class ReturnScriptB2 extends Script {
     // Create an instance of ScreenCoordinator
    private ScreenCoordinator screenCoordinator;
    

    public ReturnScriptB2(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator; // Initialize the screen coordinator
    }

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        // Lock player movement
        scriptActions.add(new LockPlayerScriptAction());

        // Change to the boss map when the player interacts
        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                System.out.println("Transitioning to Map");
                screenCoordinator.setGameState(GameState.LEVEL);
                screenCoordinator.update(); 
                ((TestMap)(((PlayLevelScreen)(screenCoordinator.currentScreen)).map)).destroyWall2();
                return ScriptState.COMPLETED;
            }
        });

        // Unlock player movement
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}