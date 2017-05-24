package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import controllers.PlayerController;
import models.GameRect;
import utils.Utils;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/20/2017.
 */
public class Boss02BulletController extends Controller implements Collider {
    private int oX;      // Player(x, y) - Boss(x, y)
    private int oY;      // đích trừ điểm bắt đầu
    private double length;  // độ dài đường đi từ đầu đến đích
    private double step;    // số bước cần phải thực hiện để đi từ đầu đến đích
    private double speed = 7; // vận tốc mặc định

    public Boss02BulletController(int x, int y, Image image, EnemyBoss02Controller boss) {
        this.gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);
        this.oX = (PlayerController.x) - boss.getGameRect().getX();
        this.oY = (PlayerController.y) - boss.getGameRect().getY();
        this.length = Math.sqrt(oX * oX + oY * oY);
        this.step = this.length / this.speed;
        Utils.playSound("res/Hit_Hurt12.wav", false);
        CollisionManager.instance.add(this);

    }

    @Override
    public void update() {
        this.gameRect.move((int) (oX / step), (int) (oY / step));
    }

    @Override
    public void draw(Graphics g) {
        this.imageRender.render(g, this.gameRect);
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
}