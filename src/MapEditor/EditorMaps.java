package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.Bossmap;

import java.util.ArrayList;

import Game.ScreenCoordinator;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Bossmap");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap(null);
            case "TitleScreen":
                return new TitleScreenMap(null);
            case "Bossmap":
                return new Bossmap(null);
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
