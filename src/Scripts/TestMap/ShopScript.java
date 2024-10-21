package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import Level.ScriptState;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import ScriptActions.ScriptAction;
import Game.GameState;
import Game.ScreenCoordinator;

// Script for challenging the boss
public class ShopScript extends Script {
     // Create an instance of ScreenCoordinator
    private ScreenCoordinator screenCoordinator;

    public ShopScript(ScreenCoordinator screenCoordinator) {
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
                System.out.println("Transitioning to ShopChoiceScreen");
                screenCoordinator.setGameState(GameState.SHOP);
                return ScriptState.COMPLETED;
            }
        });

        // Unlock player movement
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}