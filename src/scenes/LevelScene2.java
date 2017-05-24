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
public class LevelScene2 implements GameScene {
    private Image background;
    private Image backgroundControll;
    private int time=0;
    int timeSeconds=0;
    private int quantityEnemy=0;
    private int quantityEnemyFly=0;
    private int quantityEnemyPokeMon=0;
    private int quantityEnemyWich=0;


    private EnemyBoss02Controller enemyBoss02Controller;
    private IconController  iconBossController;
    private IconController  iconBossScopeController;
    private IconController  iconLeverController;
    private IconController  iconPlayerController;
    private IconController  iconHpBossController;
    private IconController  iconHpPlayerController;
    private IconController  iconEnemyController;
    private IconController  iconEnemyFlyController;
    private IconController  iconEnemyPokemonController;
    private IconController  iconEnemyWicthController;


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

    public  static PlayerController playerController;
    private Animation animation;

    ArrayList<Image> imagesEnemy = new ArrayList<>();
    ArrayList<Image> imagesEnemyFly = new ArrayList<>();
    ArrayList<Image> imagesEnemySnake = new ArrayList<>();
    ArrayList<Image> imagesEnemyWictch = new ArrayList<>();
    ArrayList<Image> imagesEnemyPokemon = new ArrayList<>();
    ArrayList<Image> imageboss = new ArrayList<>();
    int ramdom;
    public  void  addEnemyRoot(){


            EnemyRootsControler enemyRootsControler = new EnemyRootsControler(ramdom,Utils.loadImage("res/boss02_skill2.png"));
            ramdom= ramdom+192;
            ControllerManager.instance.add(enemyRootsControler);



    }
    public void addEnemy(){
        for (int i = 0; i < 1+quantityEnemy ; i++) {
            imagesEnemy.add(Utils.loadImage("res/enemy01_1.png"));
            imagesEnemy.add(Utils.loadImage("res/enemy01_2.png"));
            imagesEnemy.add(Utils.loadImage("res/enemy01_3.png"));
            EnemyController enemyController = new EnemyController(Utils.random.nextInt(600),imagesEnemy);
            ControllerManager.instance.add(enemyController);
        }
    }
    private void addEnemyBoss2(){
        this.enemyBoss02Controller = new EnemyBoss02Controller(Utils.loadImage("res/boss02_1.png"));
        ControllerManager.instance.add(enemyBoss02Controller);
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

    public void addEnemyPokemon(){
        quantityEnemyPokeMon++;
        if (quantityEnemyPokeMon==4){
            for (int i = 0; i < 1; i++) {
                imagesEnemyPokemon.add(Utils.loadImage("res/quai-tim-1.png"));
                imagesEnemyPokemon.add(Utils.loadImage("res/quai-tim-2.png"));
                imagesEnemyPokemon.add(Utils.loadImage("res/quai-tim-3.png"));
                ArrayList<Image> imagesbullet = new ArrayList<>();
                imagesbullet.add(Utils.loadImage("res/quai-tim-skill.png"));
                EnemyPokemonController enemyPokemonController = new EnemyPokemonController(Utils.random.nextInt(600),imagesEnemyPokemon,imagesbullet);
                ControllerManager.instance.add(enemyPokemonController);
            }
            quantityEnemyPokeMon=0;
        }


    }





    public LevelScene2() {



        iconBossController = new IconController(new GameRect(28,578),new ImageRender(Utils.loadImage("res/iconboss02.png")));
        iconBossScopeController = new IconController(new GameRect(190-20-10,275-20-10),new ImageRender(Utils.loadImage("res/enemyBoss01-Scope.png")));
        iconEnemyController = new IconController(new GameRect(250,578),new ImageRender(Utils.loadImage("res/enemy01_2.png")));
        iconEnemyFlyController = new IconController(new GameRect(380,578),new ImageRender(Utils.loadImage("res/enemy-fly04_1.png")));
        iconEnemyPokemonController = new IconController(new GameRect(510,578),new ImageRender(Utils.loadImage("res/enemy04_1.png")));
        iconEnemyWicthController = new IconController(new GameRect(610+30,578),new ImageRender(Utils.loadImage("res/witch1.png")));
        iconHpBossController = new IconController(new GameRect(32,714),new ImageRender(Utils.loadImage("res/iconhp.png")));
        iconHpPlayerController = new IconController(new GameRect(768,714,0,0),new ImageRender(Utils.loadImage("res/iconhp.png")));
        iconLeverController = new IconController(new GameRect(472,678),new ImageRender(Utils.loadImage("res/iconlever.png")));
        iconPlayerController = new IconController(new GameRect(765,578),new ImageRender(Utils.loadImage("res/iconplay.png")));

        //vừa vào game thì ẩn nó đi
        iconEnemyFlyController.getGameRect().setWidth(0);
        iconEnemyPokemonController.getGameRect().setWidth(0);
        iconEnemyWicthController.getGameRect().setWidth(0);
        iconBossController.getGameRect().setWidth(0);
        iconHpBossController.getGameRect().setWidth(0);
        iconBossScopeController.getGameRect().setWidth(0);



        background = Utils.loadImage("res/level2-background.png");

        bgStatus = Utils.loadImage("res/status1.png");

        bg1 = new Background(960, 50);
        bg2 = new Background(1360, 50);
        bg3 = new Background(1760, 50);


        playerController = new PlayerController(840, 382, Utils.loadImage("res/player01_1.png"));
        ControllerManager.instance.add(playerController);
    }

    @Override
    public void update() {

        iconHpPlayerController.getGameRect().setWidth(playerController.getGameRect().getHP());

        iconLeverController.getGameRect().setWidth(timeSeconds);

        if (timeSeconds>208){
            iconLeverController.getGameRect().setWidth(208);
        }else {

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
                iconEnemyPokemonController.getGameRect().setWidth(64);
                addEnemyPokemon();

            }
        }
        if (timeSeconds>150&&timeSeconds<206){

            if (time==2){

                addEnemyWitch();
                iconEnemyWicthController.getGameRect().setWidth(64);

            }
        }
        if (timeSeconds==210){

            if (time==2){
                ramdom=Utils.random.nextInt(100);

                addEnemyBoss2();

            }

        }
        if (timeSeconds==210+3){
            if (time==1){
                addEnemyRoot();
            }
        }
        if (timeSeconds==210+4){
            if (time==1){
                addEnemyRoot();
            }
        }
        if (timeSeconds==210+5){
            if (time==1){
                addEnemyRoot();
            }
        }
        if (timeSeconds==210+6){
            if (time==1){
                addEnemyRoot();
            }
        }
        if (timeSeconds==210+7){
            if (time==1){
                addEnemyRoot();
            }
        }

        if (timeSeconds>=208&&timeSeconds<212){
            iconBossScopeController.getGameRect().setWidth(63*4);
        }else {
            iconBossScopeController.getGameRect().setWidth(0);
        }
        if (enemyBoss02Controller!=null){

            iconHpBossController.getGameRect().setWidth(enemyBoss02Controller.getGameRect().getHP()/3);
            iconBossController.getGameRect().setWidth(155);
        }
        if (playerController.getGameRect().isDead()){

            GameWindow.instance.setCurrentScene(new GameOverScene());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, 960, 550, null);


        ControllerManager.instance.draw(g);
        g.drawImage(bgStatus, 0, 550, 960, 192, null);
        iconPlayerController.draw(g);
        iconHpPlayerController.draw(g);
        iconHpBossController.draw(g);
        iconLeverController.draw(g);
        iconEnemyPokemonController.draw(g);
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
