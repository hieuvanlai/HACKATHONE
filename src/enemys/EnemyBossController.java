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
    private int gravity = 1;
    private int dy;

    public EnemyBossController(int x, int y, ArrayList<Image> images) {



        this.gameRect = new GameRect(x, y,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.imageRender = new ImageRender(images.get(0));
        animation = new Animation(images,50);
        this.imagestart = images;
        for (Image image:images){
            imagesFlip.add(Util.FlipImage(image));
        }

        CollisionManager.instance.add(this);
    }

    @Override
    public void update() {
        dy += gravity;
        this.gameRect.move(0, dy);
        if (this.gameRect.getY() >= Level1.GROUND + this.gameRect.getHeight()) {
            this.dy = gravity = 0;              // khi chạm đất thì dừng lại
            this.gameRect.setY(Level1.GROUND - this.gameRect.getHeight());
        }
        if (animation.getImageIndex()>6){
            this.doShoot();        }


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
            timeCounter = 20;
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
