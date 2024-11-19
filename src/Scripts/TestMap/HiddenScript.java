package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import Level.ScriptState;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import ScriptActions.ScriptAction;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;

// Script for challenging the boss
public class HiddenScript extends Script {
     // Create an instance of ScreenCoordinator
    private ScreenCoordinator screenCoordinator;

    public HiddenScript(ScreenCoordinator screenCoordinator) {
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
                System.out.println("Transitioning to Hidden area");
                screenCoordinator.saveState();
                screenCoordinator.setGameState(GameState.HIDDEN);
                return ScriptState.COMPLETED;
            }
        });

        // Unlock player movement
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}