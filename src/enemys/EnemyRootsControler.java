package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import controllers.ControllerManager;
import models.GameRect;
import views.ImageRender;

import java.awt.*;

/**
 * Created by hieuv on 5/24/2017.
 */
public class EnemyRootsControler extends Controller implements Collider {
    public boolean move;
    private EnemyHpBossController enemyHpBossController;
    private boolean moveLeft;
    public EnemyRootsControler(int x, Image image) {
        this.gameRect = new GameRect(x,550,image.getWidth(null),image.getHeight(null));
        enemyHpBossController = new EnemyHpBossController(new GameRect(x,this.gameRect.getY(),10,10),false);
        this.enemyHpBossController.dame=true;
        this.imageRender = new ImageRender(image);
        CollisionManager.instance.add(this);

    }

    public void update(){
        enemyHpBossController.update();
            timemove++;

            if (timemove>0&&timemove<160){
                enemyHpBossController.getGameRect().move(0,-1);
                this.gameRect.move(0,-1);
            }else {
                this.gameRect.move(0,1);
                enemyHpBossController.getGameRect().move(0,1);
            }
            if (timemove>160*2){
                if (moveLeft){
                    gameRect.move(192/2,0);
                    moveLeft=false;
                }else {
                    gameRect.move(-192/2,0);
                    moveLeft=true;
                }
                timemove=0;
            }

    }

    @Override
    public void onCollider(Collider other) {

    }
}
