package NPCs;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.NPC;

public class Boss1Teleporter extends NPC {
    static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Sword.png"), 16, 16); 
        public Boss1Teleporter(int id, float x, float y, String startingAnim) {
            super(id, x, y, spriteSheet, startingAnim); 
    }
}
