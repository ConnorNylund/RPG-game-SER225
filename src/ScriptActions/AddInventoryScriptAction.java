package ScriptActions;

import Inventory.Inventory;
import Level.ScriptState;
import Players.Bunny;

public class AddInventoryScriptAction extends ScriptAction {
    @Override
    public ScriptState execute() {
        if (Bunny.coinCount >= entity.getItemData().getCoinReq()) {
            Inventory.addItem(entity.getItemData());
            Bunny.coinCount -= entity.getItemData().getCoinReq();
            System.out.println("Added " + entity.getItemData().getName() + " to Inventory!");
            System.out.println(Inventory.printInventory());
        }
        return ScriptState.COMPLETED;
    }
}
