package EnhancedMapTiles;

import Level.EnhancedMapTile;
import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Direction;
import Utils.Point;

public class DestroyableWall extends EnhancedMapTile{
    private boolean isDestroyed;
    public DestroyableWall(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("WallTileset.png"), 16, 16), TileType.NOT_PASSABLE);
        isDestroyed = false;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if(isDestroyed) {
            this.setLocation(100, 100);
        }
    }
    public void destroyWall() {
        isDestroyed = true;
    }
}
