package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Players.Bunny;
import Utils.Point;

import java.util.HashMap;

public class FarmerBoss extends NPC {
    protected int id = 0;
    protected int curHealth;
    protected int totalHealth;
    protected float moveSpeed;
    private static SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.load("farmersheet.png"), 32, 32); 
    private double lastAttack;
    private float attackRadius;
    private float attackSpeed;

    public FarmerBoss(int id, Point location, Player player) {
        super(id, location.x, location.y, spriteSheet, "DAMAGE4");
        
        // FarmerBoss attributes
        this.id = id;
        this.totalHealth = 4; // Set up to 4 damage states
        this.curHealth = totalHealth;
        this.moveSpeed = 1.2f; 
        this.attackRadius = 80; 
        this.attackSpeed = 1.5f; 
        this.lastAttack = 0;
    }

    // Method to handle taking damage
    public void takeDamage() {
        curHealth -= 1;
        if (curHealth <= 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED; // Remove FarmerBoss if defeated
        } else {
            setCurrentAnimationName("DAMAGE" + curHealth); // Change animation based on health level
        }
    }

    // Update movement and player tracking logic
    public void update(Player player) {
        super.update(player);

        // Calculate distance and movement toward player
        float dx = player.getX() - this.x;
        float dy = player.getY() - this.y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance > 0 && distance <= attackRadius) {
            this.x += (moveSpeed * dx) / distance;
            this.y += (moveSpeed * dy) / distance;
        }

        // Attack player if in range
        if (distance < attackRadius) {
            attackPlayer(player);
        }
    }

    // Attack player if within attack range
    private void attackPlayer(Player player) {
        if ((System.nanoTime() - lastAttack) / 1_000_000_000.0 > attackSpeed) {
            ((Bunny)player).takeDamage(); // Assuming player has a takeDamage() method
            lastAttack = System.nanoTime();
        }
    }

    // Load animations for each health state
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DAMAGE1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
            });
            put("DAMAGE2", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
            });
            put("DAMAGE3", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
            });
            put("DAMAGE4", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 0), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
                new FrameBuilder(spriteSheet.getSprite(3, 1), 500).withScale(3).withBounds(0, 0, 32, 32).build(),
            });
        }};
    }
}
