package scenes;

import controllers.*;
import enemys.EnemyBossController;
import enemys.EnemyController;
import enemys.EnemyFlyController;
import enemys.EnemySnakeController;
import models.GameRect;
import utils.Util;
import views.Animation;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class LevelScene implements GameScene {
    private Image background;
    private Image backgroundControll;

    private Image bgCloud1;
    private Image bgCloud2;
    private Image bgCloud3;
    private Image bgStatus;

    private Background bg1;  // scroll cloud
    private Background bg2;  // scroll cloud
    private Background bg3;  // scroll cloud

    private PlayerController playerController;
    private Animation animation;

    ArrayList<Image> imagesEnemy = new ArrayList<>();
    ArrayList<Image> imagesEnemyFly = new ArrayList<>();
    ArrayList<Image> imagesEnemySnake = new ArrayList<>();
    ArrayList<Image> imageseign = new ArrayList<>();
    ArrayList<Image> imageboss = new ArrayList<>();




    public LevelScene() {
        imageboss.add(Util.loadImage("res/enemyBoss01_1.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_2.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_3.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_4.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_5.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_6.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_7.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_8.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_9.png"));
        imageboss.add(Util.loadImage("res/enemyBoss01_10.png"));


        imagesEnemy.add(Util.loadImage("res/enemy01_1.png"));
        imagesEnemy.add(Util.loadImage("res/enemy01_2.png"));
        imagesEnemy.add(Util.loadImage("res/enemy01_3.png"));



        imagesEnemyFly.add(Util.loadImage("res/enemy-fly04_1.png"));
        imagesEnemyFly.add(Util.loadImage("res/enemy-fly04_2.png"));

        imageseign.add(Util.loadImage("res/map01_ensign1.png"));
        imageseign.add(Util.loadImage("res/map01_ensign2.png"));
        imageseign.add(Util.loadImage("res/map01_ensign3.png"));

        imagesEnemySnake.add(Util.loadImage("res/snake1.2.png"));
        imagesEnemySnake.add(Util.loadImage("res/snake1.1.png"));
        animation = new Animation(imageseign,20);



        EnemyController enemyController = new EnemyController(Util.random.nextInt(600),imagesEnemy);
        EnemyFlyController enemyController1 = new EnemyFlyController(Util.random.nextInt(600),imagesEnemyFly);
        EnemySnakeController enemySnakeController = new EnemySnakeController(Util.random.nextInt(600),imagesEnemySnake);


        EnemyBossController enemyBossController = new EnemyBossController(20, 0, imageboss);


        ControllerManager.instance.add(enemyController);
        ControllerManager.instance.add(enemyController1);
        ControllerManager.instance.add(enemySnakeController);
        ControllerManager.instance.add(enemyBossController);
        background = Util.loadImage("res/map01_2.png");

        bgCloud1 = Util.loadImage("res/map01_cloud.png");
        bgCloud2 = Util.loadImage("res/map01_cloud.png");
        bgCloud3 = Util.loadImage("res/map01_cloud.png");
        bgStatus = Util.loadImage("res/status1.png");

        bg1 = new Background(960, 50);
        bg2 = new Background(1360, 50);
        bg3 = new Background(1760, 50);


        playerController = new PlayerController(840, 382, Util.loadImage("res/player01_1.png"));
        ControllerManager.instance.add(playerController);
    }

    @Override
    public void update() {

        bg1.update();
        bg2.update();
        bg3.update();
        ControllerManager.instance.update();
        playerController.processInput(InputManager.istance.isUpPressed(), InputManager.istance.isDownPressed(), InputManager.istance.isLeftPressed(), InputManager.istance.isRightPressed());

        CollisionManager.instance.update();


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, 960, 550, null);


        g.drawImage(bgCloud1, bg1.getBgX(), bg1.getBgY(), 252, 39+20, null);
        g.drawImage(bgCloud2, bg2.getBgX(), bg2.getBgY(), 252, 39+20, null);
        g.drawImage(bgCloud3, bg3.getBgX(), bg3.getBgY(), 252, 39+20, null);
        animation.draw(g, new GameRect(210,156,imageseign.get(0).getWidth(null),imageseign.get(0).getWidth(null)));
        animation.draw(g, new GameRect(60,156,imageseign.get(0).getWidth(null),imageseign.get(0).getWidth(null)));
        ControllerManager.instance.draw(g);
        g.drawImage(bgStatus, 0, 550, 960, 140, null);


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
