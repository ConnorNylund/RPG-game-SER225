// File: GameBlurbScript.java (in Scripts.TestMap)
package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

public class GameBlurbScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Welcome to Bunny’s RPG Adventure!");
            addText("Embark on a journey with your brave bunny avatar, as you explore a vast world, collect coins, and face off against enemies.");
            addText("Master your movements, gather treasures, and save the day in this exciting RPG quest!");
        }});

        scriptActions.add(new ChangeFlagScriptAction("hasSeenBlurb", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
