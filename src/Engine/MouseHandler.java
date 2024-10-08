package Engine;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Utils.Point;

public class MouseHandler implements MouseListener, MouseMotionListener{
    public static Point mousePos;
    public static boolean leftMouseDown;
    public static boolean rightMouseDown;

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouseDown = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftMouseDown = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = new Point(e.getX(), e.getY());
    }
}
