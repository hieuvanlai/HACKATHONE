package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import controllers.PlayerController;
import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/20/2017.
 */
public class EnemyBullet extends Controller implements Collider {
    private boolean leftright;

    public EnemyBullet(GameRect gameRect, ArrayList<Image> images, boolean leftright) {
        this.gameRect = gameRect;
        this.gameRect.setHeight(images.get(0).getHeight(null));     //set w and set h for bullet
        this.gameRect.setWidth(images.get(0).getWidth(null));

        this.imageRender = new ImageRender(images.get(0));
        this.leftright = leftright;
        this.imagestart = images;
        animation = new Animation(images, 50);

        CollisionManager.instance.add(this);
    }

    @Override
    public void update() {
        timemove++;
        if (leftright == false) {
            gameRect.move(2, 0);
        } else {
            gameRect.move(-2, 0);
        }

        if (timemove > 100) {
            gameRect.setDead(true);
            CollisionManager.instance.remove(this);
        }

    }

    @Override
    public void onCollider(Collider other) {
        if (other instanceof PlayerController) {
            Utils.playSound("res/enemycanPlay.wav", false);
            other.getGameRect().getHit(1);
            gameRect.setDead(true);
            CollisionManager.instance.remove(this);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        animation.draw(graphics, gameRect);
    }
}
