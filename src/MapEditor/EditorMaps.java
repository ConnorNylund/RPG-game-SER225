package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.Bossmap1;
import Maps.Bossmap2;
import Maps.Bossmap3;
import Maps.Shopmap;
import Maps.Hiddenmap;

import java.util.ArrayList;

import Game.ScreenCoordinator;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Bossmap1");
            add("Bossmap2");
            add("Bossmap3");
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
            case "Bossmap1":
                return new Bossmap1(null, 1);
            case "Bossmap2":
                return new Bossmap2(null, 1);
            case "Bossmap3":
                return new Bossmap3(null, 1);
            case "Shopmap":
                return new Shopmap(null);
            case "Hiddenmap":
                return new Hiddenmap(null);
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
