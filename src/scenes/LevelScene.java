package scenes;

import console.GameWindow;
import controllers.*;
import enemys.*;
import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class LevelScene implements GameScene {
    private Image background;
    private Image backgroundControll;
    private int time=0;
    int timeSeconds=0;
    private int quantityEnemy=0;
    private int quantityEnemyFly=0;
    private int quantityEnemySnake=0;
    private int quantityEnemyWich=0;

    public static  EnemyBossController enemyBossController;


    private IconController  iconBossController;
    private IconController  iconBossScopeController;
    private IconController  iconLeverController;
    private IconController  iconPlayerController;
    private IconController  iconHpBossController;
    private IconController  iconHpPlayerController;
    private IconController  iconEnemyController;
    private IconController  iconEnemyFlyController;
    private IconController  iconEnemySnakeController;
    private IconController  iconEnemyWicthController;


    private Image bgCloud1;
    private Image bgCloud2;
    private Image bgCloud3;
    private Image bgStatus;

    private Image goldCount= Utils.loadImage("res/Untitled-129.png");
    private Image gold0= Utils.loadImage("res/Untitled-130.png");
    private Image gold1= Utils.loadImage("res/Untitled-131.png");
    private Image gold2= Utils.loadImage("res/Untitled-132.png");
    private Image gold3= Utils.loadImage("res/Untitled-133.png");
    private Image gold4= Utils.loadImage("res/Untitled-134.png");
    private Image gold5= Utils.loadImage("res/Untitled-135.png");
    private Image gold6= Utils.loadImage("res/Untitled-136.png");
    private Image gold7= Utils.loadImage("res/Untitled-137.png");
    private Image gold8= Utils.loadImage("res/Untitled-138.png");
    private Image gold9= Utils.loadImage("res/Untitled-139.png");



    private Background bg1;  // scroll cloud
    private Background bg2;  // scroll cloud
    private Background bg3;  // scroll cloud

    private PlayerController playerController;
    private Animation animation;

    ArrayList<Image> imagesEnemy = new ArrayList<>();
    ArrayList<Image> imagesEnemyFly = new ArrayList<>();
    ArrayList<Image> imagesEnemySnake = new ArrayList<>();
    ArrayList<Image> imagesEnemyWictch = new ArrayList<>();
    ArrayList<Image> imagesEnemyPokemon = new ArrayList<>();
    ArrayList<Image> imageseign = new ArrayList<>();
    ArrayList<Image> imageboss = new ArrayList<>();
    public void addEnemy(){
//        quantityEnemy++;
        for (int i = 0; i < 1+quantityEnemy ; i++) {
            imagesEnemy.add(Utils.loadImage("res/enemy01_1.png"));
            imagesEnemy.add(Utils.loadImage("res/enemy01_2.png"));
            imagesEnemy.add(Utils.loadImage("res/enemy01_3.png"));
            EnemyController enemyController = new EnemyController(Utils.random.nextInt(600),imagesEnemy);
            ControllerManager.instance.add(enemyController);
        }


    }
    private Image imagegold(int i){
        switch (i){
            case 0:
                return gold0;

            case 1:
                return gold1;

            case 2:
                return gold2;

            case 3:
                return gold3;

            case 4:
                return gold4;

            case 5:
                return gold5;

            case 6:
                return gold6;

            case 7:
                return gold7;
            case 8:
                return gold8;
            case 9:
                return gold9;

        }
        return null;
    }

    public void dawGold(Graphics g){
        int gold = playerController.gold;
        for (int i = 0; i < 10; i++) {
            if (gold%10==i){
                g.drawImage(imagegold(i),50+200+20+20+20+30-10,50,imagegold(i).getWidth(null)*4,imagegold(i).getHeight(null)*4,null);
            }
            if (gold/10%10==i){
                g.drawImage(imagegold(i),50+200+20+25,50,imagegold(i).getWidth(null)*4,imagegold(i).getHeight(null)*4,null);
            }
            if (gold/100%10==i){
                g.drawImage(imagegold(i),50+200+20-5-5,50,imagegold(i).getWidth(null)*4,imagegold(i).getHeight(null)*4,null);
            }
            if (gold/1000%10==i){
                g.drawImage(imagegold(i),50+170+5,50,imagegold(i).getWidth(null)*4,imagegold(i).getHeight(null)*4,null);
            }
        }
        g.drawImage(goldCount,50,50,goldCount.getWidth(null)*4,goldCount.getHeight(null)*4,null);
    }

    public void addEnemyFly(){
        quantityEnemyFly++;
        if (quantityEnemyFly==8){
            for (int i = 0; i < 1 ; i++) {
                imagesEnemyFly.add(Utils.loadImage("res/enemy-fly04_1.png"));
                imagesEnemyFly.add(Utils.loadImage("res/enemy-fly04_2.png"));
                EnemyFlyController enemyController1 = new EnemyFlyController(Utils.random.nextInt(600),imagesEnemyFly);
                ControllerManager.instance.add(enemyController1);


            }
            quantityEnemyFly=0;
        }

    }
    public void addEnemySnake(){
        quantityEnemySnake++;
        if (quantityEnemySnake==4){
            for (int i = 0; i < 1; i++) {
                imagesEnemySnake.add(Utils.loadImage("res/snake1.2.png"));
                imagesEnemySnake.add(Utils.loadImage("res/snake1.1.png"));
                EnemySnakeController enemySnakeController = new EnemySnakeController(Utils.random.nextInt(600),imagesEnemySnake);
                ControllerManager.instance.add(enemySnakeController);
            }
            quantityEnemySnake=0;
        }


    }
    public void addEnemyWitch(){
        quantityEnemyWich++;
        if (quantityEnemyWich==4){
            for (int i = 0; i < 1; i++) {
                imagesEnemyWictch.add(Utils.loadImage("res/witch1.png"));
                imagesEnemyWictch.add(Utils.loadImage("res/witch2.png"));
                imagesEnemyWictch.add(Utils.loadImage("res/witch3.png"));

                EnemyWitchController enemyWICHController = new EnemyWitchController(Utils.random.nextInt(600),imagesEnemyWictch);
                ControllerManager.instance.add(enemyWICHController);
            }
            quantityEnemyWich=0;
        }


    }


    public void addEnemyBoss(){
        playerController.getGameRect().setX(900);
        imageboss.add(Utils.loadImage("res/enemyBoss01_1.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_2.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_3.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_4.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_5.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_6.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_7.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_8.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_9.png"));
        imageboss.add(Utils.loadImage("res/enemyBoss01_10.png"));
         enemyBossController = new EnemyBossController(20, 0, imageboss);
        ControllerManager.instance.add(enemyBossController);
    }




    public LevelScene() {



        iconBossController = new IconController(new GameRect(28,578),new ImageRender(Utils.loadImage("res/iconboss.png")));
        iconBossScopeController = new IconController(new GameRect(190-20-10,275-20-10),new ImageRender(Utils.loadImage("res/enemyBoss01-Scope.png")));
        iconEnemyController = new IconController(new GameRect(250,578),new ImageRender(Utils.loadImage("res/enemy01_2.png")));
        iconEnemyFlyController = new IconController(new GameRect(380,578),new ImageRender(Utils.loadImage("res/enemy-fly04_1.png")));
        iconEnemySnakeController = new IconController(new GameRect(510,578),new ImageRender(Utils.loadImage("res/snake1.2.png")));
        iconEnemyWicthController = new IconController(new GameRect(610+30,578),new ImageRender(Utils.loadImage("res/witch1.png")));
        iconHpBossController = new IconController(new GameRect(32,714),new ImageRender(Utils.loadImage("res/iconhp.png")));
        iconHpPlayerController = new IconController(new GameRect(768,714,0,0),new ImageRender(Utils.loadImage("res/iconhp.png")));
        iconLeverController = new IconController(new GameRect(472,678),new ImageRender(Utils.loadImage("res/iconlever.png")));
        iconPlayerController = new IconController(new GameRect(765,578),new ImageRender(Utils.loadImage("res/iconplay.png")));

        //vừa vào game thì ẩn nó đi
        iconEnemyFlyController.getGameRect().setWidth(0);
        iconEnemySnakeController.getGameRect().setWidth(0);
        iconEnemyWicthController.getGameRect().setWidth(0);
        iconBossController.getGameRect().setWidth(0);
        iconHpBossController.getGameRect().setWidth(0);
        iconBossScopeController.getGameRect().setWidth(0);

        imageseign.add(Utils.loadImage("res/map01_ensign1.png"));
        imageseign.add(Utils.loadImage("res/map01_ensign2.png"));
        imageseign.add(Utils.loadImage("res/map01_ensign3.png"));

        background = Utils.loadImage("res/map01_2.png");

        bgCloud1 = Utils.loadImage("res/map01_cloud.png");
        bgCloud2 = Utils.loadImage("res/map01_cloud.png");
        bgCloud3 = Utils.loadImage("res/map01_cloud.png");
        bgStatus = Utils.loadImage("res/status1.png");

        bg1 = new Background(960, 50);
        bg2 = new Background(1360, 50);
        bg3 = new Background(1760, 50);

        animation = new Animation(imageseign,20);
        playerController = new PlayerController(840, 382, Utils.loadImage("res/player01_1.png"));
        ControllerManager.instance.add(playerController);
    }

    @Override
    public void update() {
        if (playerController.getGameRect().isDead()){
            GameWindow.instance.setCurrentScene(new GameOverScene());
        }


        iconHpPlayerController.getGameRect().setWidth(playerController.getGameRect().getHP());

        iconLeverController.getGameRect().setWidth(timeSeconds);
        if (timeSeconds>208){
            iconLeverController.getGameRect().setWidth(208);
        }else {
            //đổi thanh timelever theo theo giời gian chơi
//            float x = (float) timeSeconds/150;
            iconLeverController.getGameRect().setWidth(timeSeconds);

        }

        time++;


        bg1.update();
        bg2.update();
        bg3.update();

            playerController.processInput(InputManager.istance.isUpPressed(), InputManager.istance.isDownPressed(), InputManager.istance.isLeftPressed(), InputManager.istance.isRightPressed());
            ControllerManager.instance.update();



        CollisionManager.instance.update();



        if (time>60){
            timeSeconds++;
            time=0;
        }

        if (timeSeconds>0&&timeSeconds<206){

            if (time==1){
                addEnemy();



//
            }
        }

        if (timeSeconds>50&&timeSeconds<206){
            if (time==30){
                iconEnemyFlyController.getGameRect().setWidth(64);
                addEnemyFly();

            }

        }
        if (timeSeconds>100&&timeSeconds<206){
            if (time==59){
                iconEnemySnakeController.getGameRect().setWidth(64);
                addEnemySnake();

            }
        }
        if (timeSeconds>150&&timeSeconds<206){

            if (time==2){
//
                addEnemyWitch();
                iconEnemyWicthController.getGameRect().setWidth(64);

            }
        }
        if (timeSeconds==208){

            if (time==2){
                addEnemyBoss();
            }

        }
        if (timeSeconds>=208&&timeSeconds<212){
            iconBossScopeController.getGameRect().setWidth(63*4);
        }else {
            iconBossScopeController.getGameRect().setWidth(0);
        }
        if (enemyBossController!=null){
            iconHpBossController.getGameRect().setWidth(enemyBossController.getGameRect().getHP());
            iconBossController.getGameRect().setWidth(155);
            if (enemyBossController.getGameRect().isDead()){
                ControllerManager.instance.Clean();
                GameWindow.instance.setCurrentScene( new LevelScene2());

            }
        }


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
        g.drawImage(bgStatus, 0, 550, 960, 192, null);
        iconPlayerController.draw(g);
        iconHpPlayerController.draw(g);
        iconHpBossController.draw(g);
        iconLeverController.draw(g);
        iconEnemySnakeController.draw(g);
        iconEnemyFlyController.draw(g);
        iconEnemyController.draw(g);
        iconBossController.draw(g);
        iconEnemyWicthController.draw(g);
        iconBossScopeController.draw(g);
        dawGold(g);
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
