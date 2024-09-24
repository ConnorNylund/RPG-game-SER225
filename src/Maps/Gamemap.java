package Maps;

import Level.*;
import Tilesets.WallTileset;
import Tilesets.CommonTileset;

import java.util.ArrayList;

public class Gamemap extends Map {

    public Gamemap() {
        super("Gamemap.txt", new WallTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

}