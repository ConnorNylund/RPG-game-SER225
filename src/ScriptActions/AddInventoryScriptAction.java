package ScriptActions;

import Inventory.Inventory;
import Level.ScriptState;

public class AddInventoryScriptAction extends ScriptAction {
    @Override
    public ScriptState execute() {
        Inventory.addItem(entity.getItemData());
        System.out.println("Added " + entity.getItemData().getName() + " to Inventory!");
        System.out.println(Inventory.printInventory());
        return ScriptState.COMPLETED;
    }
}
