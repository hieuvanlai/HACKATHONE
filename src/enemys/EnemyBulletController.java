package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import controllers.PlayerController;
import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/13/2017.
 */
public class EnemyBulletController extends Controller implements Collider {
    private int timeCounter = 0;
    private int w;
    private int h;
    private boolean isShoot;

    public EnemyBulletController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);
        w = image.getWidth(null);
        h = image.getHeight(null);

        CollisionManager.instance.add(this);
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    @Override
    public void update() {
        if (isShoot) {
            timeCounter++;
            if (timeCounter <= 10) {
                gameRect.move(0, -1);
            } else if (timeCounter <= 50) {
                gameRect.move(1, 1);
            } else if (timeCounter <= 100) {
                gameRect.move(2, 1);
            } else if (timeCounter <= 150) {
                gameRect.move(3, 0);
            } else if (timeCounter > 150) {
                this.gameRect.setDead(true);
                CollisionManager.instance.remove(this);
            }
            if (timeCounter % 20 == 0){
                w += 5;  // thay đổi width height khi draw
                h += 5;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imageRender.getImage(), this.gameRect.getX(), this.gameRect.getY(),w, h,null );
        // override lại hàm draw, không dùng imageRender.render nữa
    }

    @Override
    public void onCollider(Collider other) {
        if (other instanceof PlayerController) {
            Util.playSound("res/enemycanPlay.wav",false);
            other.getGameRect().getHit(2);
            this.gameRect.setDead(true);
            CollisionManager.instance.remove(this);


        }
    }
}
