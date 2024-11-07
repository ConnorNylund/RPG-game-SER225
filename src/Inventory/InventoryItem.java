package Inventory;

import java.awt.image.BufferedImage;

import Engine.ImageLoader;

public class InventoryItem {
    private String name;
    private BufferedImage image;
    private String fileName;
    private int spriteWidth;
    private int spriteHeight;

    public InventoryItem(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
        image = ImageLoader.load(fileName);
    }

    public String getName() {return this.name;}

    public BufferedImage getImage() {return this.image;}
}
