package Engine;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Level.Camera;
import Level.Map;

public class MouseHandler implements MouseListener{
    private Camera cam;

    public MouseHandler(Camera cam) {
        this.cam = cam;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("button pressed");
        if (e.getButton() == 1) {
            System.out.println("left mouse pressed");
            for (int i = 0; i < cam.getActiveEnhancedMapTiles().size(); i++) {
                if (e.getX() >= cam.getActiveEnhancedMapTiles().get(i).getX1() && 
                e.getX() <= cam.getActiveEnhancedMapTiles().get(i).getX2() && 
                e.getY() >= cam.getActiveEnhancedMapTiles().get(i).getY1() &&
                e.getY() <= cam.getActiveEnhancedMapTiles().get(i).getY2()) {
                    System.out.println("enhanced tile clicked");
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
