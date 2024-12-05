package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Utils.Point;

public class Boss1Teleporter extends NPC {
    static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("MagicHole.png"), 16, 16);
    private int waveNum;
    public Boss1Teleporter(int id, Point pos, String startingAnim, int waveNum, Map map) {
        super(id, pos.x, pos.y, spriteSheet, startingAnim); 
        this.waveNum = waveNum;
    }
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) { //I hate this thing but u guys don't need to worry about it... Colors r definitely backwards rn tho I just need to remake the spritesheet
        return new HashMap<String, Frame[]>() {{
            put("ANIM1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0), 14)
                    .withScale(4)
                    .withBounds(0,0,16,16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0,1),14)
                    .withScale(4)
                    .withBounds(0,0,16,16)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0,2),14)
                    .withScale(4)
                    .withBounds(0,0,16,16)
                    .build(),
                });
                
            }};
    }
}
