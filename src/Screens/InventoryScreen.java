package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Inventory.Inventory;
import java.awt.*;

public class InventoryScreen extends Screen{
    protected KeyLocker keyLocker = new KeyLocker();
    
    public InventoryScreen() {
        initialize();
    }

    @Override
    public void initialize() {
        keyLocker.lockKey(Key.ONE);
        keyLocker.lockKey(Key.TWO);
        keyLocker.lockKey(Key.THREE);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.ONE)) {
            keyLocker.unlockKey(Key.ONE);
        }
        if (Keyboard.isKeyUp(Key.TWO)) {
            keyLocker.unlockKey(Key.TWO);
        }
        if (Keyboard.isKeyUp(Key.THREE)) {
            keyLocker.unlockKey(Key.THREE);
        }

        if (Keyboard.isKeyDown(Key.ONE) && !keyLocker.isKeyLocked(Key.ONE)) {
            Inventory.currentSelection = 0;
        }
        else if (Keyboard.isKeyDown(Key.TWO) && !keyLocker.isKeyLocked(Key.TWO)) {
            Inventory.currentSelection = 1;
        }
        else if (Keyboard.isKeyDown(Key.THREE) && !keyLocker.isKeyLocked(Key.THREE)) {
            Inventory.currentSelection = 2;
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        switch (Inventory.currentSelection) {
            case 0:
                graphicsHandler.drawFilledRectangleWithBorder(ScreenManager.getScreenWidth()/2-52, ScreenManager.getScreenHeight()-52, 50, 50, Color.black, Color.yellow, 2);
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2, ScreenManager.getScreenHeight()-52,50, 50, Color.black);
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2+52, ScreenManager.getScreenHeight()-52,50, 50, Color.black);
                break;
            case 1:
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2-52, ScreenManager.getScreenHeight()-52,50, 50, Color.black);
                graphicsHandler.drawFilledRectangleWithBorder(ScreenManager.getScreenWidth()/2, ScreenManager.getScreenHeight()-52, 50, 50, Color.black, Color.yellow, 2);
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2+52, ScreenManager.getScreenHeight()-52,50, 50, Color.black);
                break;
            case 2:
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2-52, ScreenManager.getScreenHeight()-52,50, 50, Color.black);
                graphicsHandler.drawFilledRectangle(ScreenManager.getScreenWidth()/2, ScreenManager.getScreenHeight()-52,50, 50, Color.black);
                graphicsHandler.drawFilledRectangleWithBorder(ScreenManager.getScreenWidth()/2+52, ScreenManager.getScreenHeight()-52, 50, 50, Color.black, Color.yellow, 2);
                break;
        }
        for (int i = 0; i < Inventory.items.length; i++) {
            int x = ScreenManager.getScreenWidth()/2;
            int y = ScreenManager.getScreenHeight()-52;
            if (Inventory.items[i] != null) {
                switch (i) {
                    case 0:
                        x -= 52;
                        graphicsHandler.drawImage(Inventory.items[i].getImage(), x, y, 50, 50);
                        break;
                    case 1:
                        graphicsHandler.drawImage(Inventory.items[i].getImage(), x, y, 50, 50);
                        break;
                    case 2: 
                        x += 52;
                        graphicsHandler.drawImage(Inventory.items[i].getImage(), x, y, 50, 50);
                        break;
                }
            }
        }
    }
    
}
