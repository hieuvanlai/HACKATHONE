package enemys;

import controllers.*;
import models.GameRect;
import scenes.LevelScene;
import utils.Util;

/**
 * Created by hieuv on 5/16/2017.
 */
public class EnemyHpBossController extends Controller implements Collider {
    public  boolean dame;
    private int dameLimit=0;
    private int dameLimitLater=0;
    public EnemyHpBossController(GameRect gameRect) {
        this.gameRect=gameRect;
        CollisionManager.instance.add(this);

    }
    @Override
    public void update(){

    }

    @Override
    public void onCollider(Collider other) {
        if(other instanceof PlayerController){
            other.getGameRect().move(50,0);
            if (dame){
                other.getGameRect().getHit(2);
            }else {
                LevelScene.enemyBossController.getGameRect().getHit(5);
                if (LevelScene.enemyBossController.getGameRect().isDead()){
                    CollisionManager.instance.remove(LevelScene.enemyBossController);
                    CollisionManager.instance.remove(this);

                }


            }


        }

    }

}
