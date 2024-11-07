package Scripts;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.AddInventoryScriptAction;
import ScriptActions.ScriptAction;

public class ItemPickUpScript extends Script{

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new AddInventoryScriptAction());
        return scriptActions;
    }
    
}
