package scenes;

import console.GameWindow;
import controllers.ControllerManager;
import controllers.QuitButtonController;
import controllers.ReplayButtonController;
import utils.Utils;
import views.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScene implements GameScene{
    Animation animation;

    Image background = Utils.loadImage("res/gameover-background.png");
    Image logo = Utils.loadImage("res/gameover.png");
    ReplayButtonController replayButton = new ReplayButtonController(GameWindow.SCREEN_WIDTH/2-60,
            GameWindow.SCREEN_HEIGHT/3+160,
            Utils.loadImage("res/replay-button.png"));
    QuitButtonController quitButton = new QuitButtonController(GameWindow.SCREEN_WIDTH/2-60,
            GameWindow.SCREEN_HEIGHT/2+130,
            Utils.loadImage("res/quit-button.png"));

    int count = 0;
    public GameOverScene() {
        replayButton.setSelected(true);
        quitButton.setSelected(false);
        Utils.playSound("res/gameover-theme.wav",false);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0,0,GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT,null);
        g.drawImage(logo,0,GameWindow.SCREEN_HEIGHT/4,GameWindow.SCREEN_WIDTH,200,null);
        replayButton.draw(g);
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
                replayButton.setSelected(false);
                quitButton.setSelected(true);
            }

            if(count == 2){
                replayButton.setSelected(true);
                quitButton.setSelected(false);
                count = 0;
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(count == 1){
                System.exit(0);

            }
            if(count == 2) {
                ControllerManager.instance.Clean();
                GameWindow.instance.setCurrentScene(new LevelScene());
            }
            else{
                ControllerManager.instance.Clean();
                GameWindow.instance.setCurrentScene(new LevelScene());
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }
}
