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
 * Created by hieuv on 5/13/2017.
 */
public class EnemyBulletTornadoController extends Controller implements Collider {
    private int imagechange =0;

    public EnemyBulletTornadoController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);

        CollisionManager.instance.add(this);



    }
    @Override
    public void update(){
        timemove++;
        imagechange++;
        if (timemove==10){
            gameRect.move(30,0);
            //đảo ảnh lóc xoáy
            if (imagechange==4){
                imageRender.setImage(Util.FlipImage(imageRender.getImage()));

            }else {
                imageRender.setImage(imageRender.getImageStart());
                
                imagechange=0;
            }

            timemove=0;
        }
        if (gameRect.getX()>960){
            this.gameRect.setDead(true);
            CollisionManager.instance.remove(this);
        }



    }

//    @Override
//    public void draw(Graphics g) {
//        g.drawImage(imageRender.getImage(), this.gameRect.getX(), this.gameRect.getY(),null );
//        // override lại hàm draw, không dùng imageRender.render nữa
//    }

    @Override
    public void onCollider(Collider other) {
        if (other instanceof PlayerController) {
            other.getGameRect().getHit(10);
            Util.playSound("res/enemycanPlay.wav",false);
            this.gameRect.setDead(true);
            CollisionManager.instance.remove(this);


        }

    }
}
