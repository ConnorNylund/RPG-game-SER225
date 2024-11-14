package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.Bossmap;
import Maps.Shopmap;
import Maps.Hiddenmap;

import java.util.ArrayList;

import Game.ScreenCoordinator;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Bossmap");
            add("Shopmap");
            add("Hiddenmap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap(null, 0);
            case "TitleScreen":
                return new TitleScreenMap(null);
            case "Bossmap":
                return new Bossmap(null, 1);
            case "Shopmap":
                return new Shopmap(null);
            case "Hiddenmap":
                return new Hiddenmap(null);
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
