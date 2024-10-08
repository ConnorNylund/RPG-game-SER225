package Scripts.TestMap;

import java.util.ArrayList;

import Game.GameState;
import Level.Script;
import Level.ScriptState;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import ScriptActions.ScriptAction;

// Script for interacting with an object to challenge the boss
public class BossChallengeScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        // Lock the player during interaction
        scriptActions.add(new LockPlayerScriptAction());

        // Directly switch to the boss choice screen
        scriptActions.add(new ScriptAction() {
            @Override
            public ScriptState execute() {
                // Assuming screenCoordinator is accessible
                screenCoordinator.setGameState(GameState.BOSS);
            }
        });

        // Unlock the player after the action
        scriptActions.add(new UnlockPlayerScriptAction());
        
        return scriptActions;
    }
}
