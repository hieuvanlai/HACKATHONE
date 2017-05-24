package console;

import controllers.ControllerManager;
import scenes.GameScene;
import scenes.LevelScene;
import scenes.MenuScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class GameWindow extends Frame{
    private BufferedImage bufferedImage;
    private Graphics backBufferGraphic;

    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 740;

    private GameScene currentScene;
    public static GameWindow instance;

    public GameWindow(){
        instance = this;
        currentScene = new MenuScene();
        setTitle("Drancia Project by CamBuoiTaoTeam");

        setVisible(true);
        setSize(960,640+52+10+10+10-2);

        bufferedImage = new BufferedImage(960, 700+20+20, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic = bufferedImage.getGraphics();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window opened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("window closing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("window closed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("window iconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("window deiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("window activated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("window deactivated");
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                currentScene.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentScene.keyReleased(e);
            }
        });

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        repaint();
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentScene.update();

                        ControllerManager.instance.update();




                }
            }
        });
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        currentScene.draw(backBufferGraphic);
        ControllerManager.instance.draw(backBufferGraphic);
        currentScene.draw(backBufferGraphic);

        g.drawImage(bufferedImage, 0,0,960, 640+52+10+10,null);
    }

    public void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }
}
