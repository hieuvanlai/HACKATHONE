package controllers;

import java.awt.event.KeyEvent;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class InputManager {
    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;

    public static final InputManager istance = new InputManager();

    private InputManager() {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.isUpPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.isDownPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.isLeftPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.isRightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.isUpPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.isDownPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.isLeftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.isRightPressed = false;
        }
    }

    public boolean isUpPressed() {
        return isUpPressed;
    }

    public boolean isDownPressed() {
        return isDownPressed;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }
}