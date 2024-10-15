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
    private boolean beenDestroyed;
    private DestroyableWall neighbor;//Doesn't do anything yet, but just an idea to make handling mass amount of walls easier.


    public DestroyableWall(Point location, String gateTitle) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load(gateTitle), 16, 16), TileType.NOT_PASSABLE);

        isDestroyed = true;
        beenDestroyed = false; 
    }

    @Override
    public void update(Player player) {
        super.update(player);
        //System.out.println("DEBUG: I am a wall located at x" + x + " y" + y + "\nisDestroyed: " + isDestroyed + " beenDestroyed: " + beenDestroyed);
        if(isDestroyed && !beenDestroyed) { //At some point, this should be moved to the destroy method, so this stupid constant comparison doesn't need to be done
            System.out.println("I am a wall that sohuld've moved at x" + x + " y" + y);
            this.setLocation(x+4800, y);
            beenDestroyed = true; 
        }
    }
    public void destroyWall() { //In theory, calling this method destroys an instance of these walls. In order to destroy an entire section, we could group them all in an array, then call this method on each member of the array horribly inefficient so O'neil would be crying but in theory it would work
        isDestroyed = true;
        // this.setLocation(x+4800, y);
        // beenDestroyed = true; 
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(3)
                .build();
        return new GameObject(x, y, frame);
    }
}
