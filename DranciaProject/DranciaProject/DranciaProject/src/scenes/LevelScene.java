package scenes;

import controllers.*;
import enemys.EnemyController;
import utils.Util;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class LevelScene implements GameScene {
    private Image background;
    private Image backgroundControll;

    private Image bgCloud1;
    private Image bgCloud2;
    private Image bgCloud3;

    private Background bg1;  // scroll cloud
    private Background bg2;  // scroll cloud
    private Background bg3;  // scroll cloud

    private PlayerController playerController;


    public LevelScene() {

        EnemyController enemyController = new EnemyController(Util.random.nextInt(600),Util.loadImage("res/enemy01_1.png"));
        ControllerManager.instance.add(enemyController);
        background = Util.loadImage("res/map01_2.png");

        bgCloud1 = Util.loadImage("res/map01_cloud.png");
        bgCloud2 = Util.loadImage("res/map01_cloud.png");
        bgCloud3 = Util.loadImage("res/map01_cloud.png");

        bg1 = new Background(960, 50);
        bg2 = new Background(1360, 50);
        bg3 = new Background(1760, 50);

        playerController = new PlayerController(840, 382, 68, 64, Util.loadImage("res/player01_1.png"));
        ControllerManager.instance.add(playerController);
    }

    @Override
    public void update() {
        bg1.update();
        bg2.update();
        bg3.update();

        playerController.processInput(InputManager.istance.isUpPressed(), InputManager.istance.isDownPressed(), InputManager.istance.isLeftPressed(), InputManager.istance.isRightPressed());

        ControllerManager.instance.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, 960, 550, null);

        g.drawImage(bgCloud1, bg1.getBgX(), bg1.getBgY(), 252, 39, null);
        g.drawImage(bgCloud2, bg2.getBgX(), bg2.getBgY(), 252, 39, null);
        g.drawImage(bgCloud3, bg3.getBgX(), bg3.getBgY(), 252, 39, null);

        ControllerManager.instance.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        InputManager.istance.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       InputManager.istance.keyReleased(e);
    }
}