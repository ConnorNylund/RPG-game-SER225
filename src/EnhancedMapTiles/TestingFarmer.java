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

public class TestingFarmer extends EnhancedMapTile {

    public TestingFarmer(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("thefarmer.png"), 32, 32), TileType.NOT_PASSABLE);
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        // Load the first frame of the farmer image from the sprite sheet
        Frame frame = new FrameBuilder(spriteSheet.getSprite(0, 0))
                .withScale(3) // Scale up the image if needed
                .build();
        return new GameObject(x, y, frame);
    }

    // Override draw method to render the farmer sprite
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        System.out.println("Drawing farmer at: (" + this.getX() + ", " + this.getY() + ")");
        super.draw(graphicsHandler);
    }
}
