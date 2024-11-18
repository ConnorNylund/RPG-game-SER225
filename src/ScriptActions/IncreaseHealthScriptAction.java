package ScriptActions;

import Level.ScriptState;
import Players.Bunny;

public class IncreaseHealthScriptAction extends ScriptAction {
    @Override
    public ScriptState execute() {
        if (Bunny.coinCount >= 5 && Bunny.health != 4) {
            Bunny.coinCount -= 5;
            Bunny.health++;
            Bunny.dmgState++;
            System.out.println(Bunny.health + " Player Health");
        }
        
        return ScriptState.COMPLETED;
    }
}
