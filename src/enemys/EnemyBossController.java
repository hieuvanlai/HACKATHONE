package enemys;

import controllers.Collider;

import controllers.CollisionManager;
import controllers.Controller;
import controllers.ControllerManager;
import levels.Level1;
import models.GameRect;
import utils.Util;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/12/2017.
 */
public class EnemyBossController extends Controller implements Collider {
    private int timeCounter = 20;
    private int timeTornado = 70;
    public static int hp=200;
    //kien tra thoi gian ra skill
    private int timeShot = 0;
    private int gravity = 1;
    private int dy;
    private ArrayList<Image> imageTornado = new ArrayList<>();
    private ArrayList<Image> imageShot= new ArrayList<>();
    //đầu rồng
    public EnemyHpBossController enemyHpBossControllerHit;
    //Bụng Rồng
    public EnemyHpBossController enemyHpBossControllerDame;




    public EnemyBossController(int x, int y, ArrayList<Image> images) {


        this.gameRect = new GameRect(x, y,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.gameRect.setHP(160);
        this.imageRender = new ImageRender(images.get(0));
        animation = new Animation(images,15);
        imageTornado.add(images.get(8));
        imageTornado.add(images.get(9));


        imageShot.add(images.get(5));
        imageShot.add(images.get(6));
        imageShot.add(images.get(7));



        for (Image image:images){
            imagesFlip.add(Util.FlipImage(image));
        }
        Util.playSound("res/rongxuathien.wav",false);
        CollisionManager.instance.add(this);

        enemyHpBossControllerHit = new EnemyHpBossController(new GameRect(190,275,10,10));

        enemyHpBossControllerDame = new EnemyHpBossController(new GameRect(190,275+30+60+60-20-10,10,10));
        enemyHpBossControllerDame.dame=true;



    }

    @Override
    public void update() {
        timeShot++;
        dy += gravity;
        this.gameRect.move(0, dy);
        if (this.gameRect.getY() >= Level1.GROUND + this.gameRect.getHeight()) {
            this.dy = gravity = 0;              // khi chạm đất thì dừng lại
            this.gameRect.setY(Level1.GROUND - this.gameRect.getHeight());
        }


        if (timeShot>0&&timeShot<300){
            animation.setImageIndex(0);
        }//
        if (timeShot==100){{
            Util.playSound("res/rongfulllua.wav",false);
        }}
        if (timeShot>301+200&&timeShot<700){
            animation.setImages(imageShot);
            doShoot();
        }
        if (timeShot>701+200&&timeShot<1000+200){
            animation.setImageIndex(0);
        }
        if (timeShot>1001+200&&timeShot<1300+200){

            animation.setImages(imageTornado);
                doShootTornado();
        }
        if (timeShot>1301+200){
            timeShot=0;
        }










    }

    private void doShoot(){
        timeCounter--;
        if (timeCounter <= 0){

            EnemyBulletController enemyBulletController = new EnemyBulletController(
                    this.gameRect.getX()+ this.gameRect.getWidth(),
                    this.gameRect.getY()+30,
                    Util.loadImage("res/enemyBoss01-Skill01_1.png"));

            ControllerManager.instance.add(enemyBulletController);
            enemyBulletController.setShoot(true);
            timeCounter = 40;
        }
    }
    private void doShootTornado(){

        timeTornado--;
        if (timeTornado <= 0){

            EnemyBulletTornadoController enemyBulletTornadoController = new EnemyBulletTornadoController(this.gameRect.getX()+ this.gameRect.getWidth(),this.gameRect.getY()+100, Util.loadImage("res/enemyBoss01-Skill02_1.png"));
            ControllerManager.instance.add(enemyBulletTornadoController);
            Util.playSound("res/wind1.wav",false);

            timeTornado = 100;
        }
    }

    @Override
    public void draw(Graphics g) {
        animation.draw(g,gameRect);

    }

    @Override
    public void onCollider(Collider other) {

    }
}
