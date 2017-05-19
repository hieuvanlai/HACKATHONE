package scenes;

import console.GameWindow;
import controllers.QuitButtonController;
import controllers.StartButtonController;
import utils.Util;
import views.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class MenuScene implements GameScene {
    Animation animation;
    ArrayList<Image> images;
    StartButtonController startButton = new StartButtonController(200+200+20,300,
            Util.loadImage("res/start-button.png"));
    QuitButtonController quitButton = new QuitButtonController(200+200+20,400,
            Util.loadImage("res/quit-button.png"));

    int count = 0;

    public MenuScene() {
        startButton.setSelected(true);
        quitButton.setSelected(false);
    }

    Image menuBackGround = Util.loadImage("res/menu-background.png");
    Image warrior = Util.loadImage("res/warrior.png");
    Image dragon = Util.loadImage("res/dragon.png");
    Image credits = Util.loadImage("res/credits.png");
    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackGround,0,0,960,700+20+20,null);
        g.drawImage(dragon,960/13+70,140,200,350,null);
        g.drawImage(warrior,960/2+130,140,190,360,null);
        g.drawImage(credits,960/2-210,700+20+20-40,400,25,null);
        startButton.draw(g);
        quitButton.draw(g);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            count++;
            if(count == 1){
                startButton.setSelected(false);
                quitButton.setSelected(true);
            }

            if(count == 2){
                startButton.setSelected(true);
                quitButton.setSelected(false);
                count = 0;
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(count == 1){
                System.exit(0);

            }
            if(count == 2) {
                GameWindow.instance.setCurrentScene(new LevelScene());
            }
            else{
                GameWindow.instance.setCurrentScene(new LevelScene());

            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
}
