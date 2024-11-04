package Inventory;

import Engine.ImageLoader;
import GameObject.SpriteSheet;

public class InventoryItem {
    private String name;
    private SpriteSheet sprite;
    private String fileName;
    private int spriteWidth;
    private int spriteHeight;

    public InventoryItem(String name, String fileName, int spriteWidth, int spriteHeight) {
        this.name = name;
        this.fileName = fileName;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        //sprite = new SpriteSheet(ImageLoader.load(fileName), this.spriteWidth, this.spriteHeight);
    }

    public String getName() {return this.name;}
}
