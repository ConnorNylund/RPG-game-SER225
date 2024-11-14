package ScriptActions;

import Level.ScriptState;
import Maps.TestMap;

public class DestroyGrassWallsScriptAction extends ScriptAction {
    public DestroyGrassWallsScriptAction() {
        
    }

    @Override
    public ScriptState execute() {
        ((TestMap)(this.map)).destroyWall3();
        return ScriptState.COMPLETED; 
    }
}
