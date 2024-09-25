package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite bunny;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        Point bunnyLocation = getMapTile(8, 5).getLocation().subtractX(6).subtractY(7);
        bunny = new Sprite(ImageLoader.loadSubImage("bloody bunny.png", Colors.MAGENTA, 0, 0, 16, 16));
        bunny.setScale(3);
        bunny.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        bunny.setLocation(bunnyLocation.x, bunnyLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        bunny.draw(graphicsHandler);
    }
}
