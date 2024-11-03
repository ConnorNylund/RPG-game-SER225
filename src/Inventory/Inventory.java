package Inventory;

public class Inventory {
    public static InventoryItem[] items;

    public Inventory() {
        items = new InventoryItem[3];
    }

    // tries to add an item to the inventory, returns true if it works, false if inventory is full
    public static boolean addItem(InventoryItem newItem) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = newItem;
                return true;
            }
        }

        return false;
    }

    public void removeItem(int itemSlot) {
        items[itemSlot] = null;
    }

    public boolean isFull() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                return false;
            }
        }

        return true;
    }

    public static String printInventory() {
        String str = "";
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                str += items[i].getName() + " ";
            }
            else {
                str += "null ";
            }
        }
        return str;
    }
}
