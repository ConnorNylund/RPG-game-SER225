package Players;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Player;

public class Bunny extends Player{

    public Bunny(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("bloody bunny.png"), 24, 24), x, y, startingAnimationName);
        
    }

    public void update() {
        super.update();
    }
}
