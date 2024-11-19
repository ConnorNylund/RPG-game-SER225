package Scripts.TestMap;

import java.util.ArrayList;

import Game.GameState;
import Game.ScreenCoordinator;
import Level.Script;
import Level.ScriptState;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.UnlockPlayerScriptAction;

public class Boss1TeleporterScript extends Script {
    // Create an instance of ScreenCoordinator
    private ScreenCoordinator screenCoordinator;

    public Boss1TeleporterScript(ScreenCoordinator screenCoordinator) {
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
                System.out.println("Transitioning to BossChoiceScreen");
                screenCoordinator.saveState();
                screenCoordinator.setGameState(GameState.BOSS);
                return ScriptState.COMPLETED;
            }
        });

        // Unlock player movement
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
    
}