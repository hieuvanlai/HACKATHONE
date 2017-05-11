package scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ADMIN on 5/8/2017.
 */
public interface GameScene {
    void update();
    void draw(Graphics g);
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
}
