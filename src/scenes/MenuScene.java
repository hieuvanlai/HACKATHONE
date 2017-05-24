package scenes;

import console.GameWindow;
import controllers.QuitButtonController;
import controllers.StartButtonController;
import utils.Utils;
import views.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class MenuScene implements GameScene {
    Animation animation;
    ArrayList<Image> images;
    StartButtonController startButton = new StartButtonController(GameWindow.SCREEN_WIDTH/2-60,
            GameWindow.SCREEN_HEIGHT/3+60,
            Utils.loadImage("res/start-button.png"));
    QuitButtonController quitButton = new QuitButtonController(GameWindow.SCREEN_WIDTH/2-65,
            GameWindow.SCREEN_HEIGHT/2+30,
            Utils.loadImage("res/quit-button.png"));

    int count = 0;

    public MenuScene() {
        startButton.setSelected(true);
        quitButton.setSelected(false);
        Utils.playSound("res/menu-theme.wav",true);
    }

    Image menuBackGround = Utils.loadImage("res/menu-background.png");
    Image warrior = Utils.loadImage("res/warrior.png");
    Image dragon = Utils.loadImage("res/dragon.png");
    Image credits = Utils.loadImage("res/credits.png");


    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackGround,0,0,GameWindow.SCREEN_WIDTH,GameWindow.SCREEN_HEIGHT,null);
        g.drawImage(dragon,GameWindow.SCREEN_WIDTH/13+70,140,200,350,null);
        g.drawImage(warrior,GameWindow.SCREEN_WIDTH/2+130,140,190,360,null);
        g.drawImage(credits,GameWindow.SCREEN_WIDTH/2-200,GameWindow.SCREEN_HEIGHT-40,400,25,null);
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
