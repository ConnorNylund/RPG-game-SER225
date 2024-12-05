package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.TileType;
import Utils.Point;
import Engine.GraphicsHandler;

public class Coin extends EnhancedMapTile {
    private boolean isCollected;

    public void update() {
        // Check if the coin is collected each time the coins update method is called
        System.out.println("Coin update called. Collected status: " + isCollected);
    }
    

    public Coin(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("thecoin.png"), 16, 16), TileType.PASSABLE);
        this.isCollected = false;
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void collect() {
        this.isCollected = true;
        
    }

    

    // Override the draw method to make the coin disappear if collected
    @Override
public void draw(GraphicsHandler graphicsHandler) {
    if (!isCollected) {
        System.out.println("Drawing coin at: (" + this.getX() + ", " + this.getY() + ")");
        super.draw(graphicsHandler);
    } else {
        System.out.println("Coin not drawn because it is collected.");
    }
}

}
