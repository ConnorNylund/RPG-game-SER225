package Scripts;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.IncreaseHealthScriptAction;
import ScriptActions.ScriptAction;
public class HealthPickUpScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new IncreaseHealthScriptAction());
        return scriptActions;
    }
}
