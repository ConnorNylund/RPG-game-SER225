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
    private float x;
    private float y;
    public DestroyableWall(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("WallTileset.png"), 16, 16), TileType.NOT_PASSABLE);
        this.x = x;
        this.y = y;
        isDestroyed = false;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        if(isDestroyed) {
            this.setLocation(x+100, y);
        }
    }
    public void destroyWall() { //In theory, calling this method destroys an instance of these walls. In order to destroy an entire section, we could group them all in an array, then call this method on each member of the array horribly inefficient so O'neil would be crying but in theory it would work
        isDestroyed = true;
    }
}
