package NPCs;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;

public class Boss1Teleporter extends NPC {
    static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("Sword.png"), 16, 16); 
    private int waveNum;
    public Boss1Teleporter(int id, float x, float y, String startingAnim, int waveNum) {
        super(id, x, y, spriteSheet, startingAnim); 
        this.waveNum = waveNum;
    }
    public void update(Player player) {
        if(waveNum > 5) {
            //Handle which boss is meant to be TPed too (I think this is unneeded and I'm pretty sure the script needs to handle this but whatever)
        }
    }
}
