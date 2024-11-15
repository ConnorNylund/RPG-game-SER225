package Inventory;

import java.awt.image.BufferedImage;

import Engine.ImageLoader;

public class InventoryItem {
    private String name;
    private BufferedImage image;
    private String fileName;
    private int spriteWidth;
    private int spriteHeight;
    private int coinReq;

    public InventoryItem(String name, String fileName, int coinReq) {
        this.name = name;
        this.fileName = fileName;
        this.coinReq = coinReq;
        image = ImageLoader.load(fileName);
    }

    public String getName() {return this.name;}

    public BufferedImage getImage() {return this.image;}

    public int getCoinReq() {return this.coinReq;}
}
