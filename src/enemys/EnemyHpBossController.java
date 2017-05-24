package enemys;

import controllers.*;
import models.GameRect;
import scenes.LevelScene;
import utils.Utils;

/**
 * Created by hieuv on 5/16/2017.
 */
public class EnemyHpBossController extends Controller implements Collider {
    public  boolean dame;
    private int dameLimit=0;
    private int dameLimitLater=0;
    private int damecount=0;
    private boolean isboss;

    public EnemyHpBossController(GameRect gameRect,boolean isboss) {
        this.gameRect=gameRect;
        this.isboss = isboss;
        CollisionManager.instance.add(this);


    }
    @Override
    public void update(){


        if (dameLimitLater>dameLimit){
            dameLimitLater=0;
            dameLimit=0;
            damecount=0;
        }
        //tăng  damlimitlate để kiểm tra xem có phải va chạm liên tục hay không
        if (dameLimitLater>0){
            dameLimitLater++;

        }

    }

    @Override
    public void onCollider(Collider other) {
        if (isboss){
            if(other instanceof PlayerController){
                other.getGameRect().move(50,0);
                if (dame){
                    other.getGameRect().getHit(2);
                    Utils.playSound("res/enemycanPlay.wav",false);
                }else {
                    LevelScene.enemyBossController.getGameRect().getHit(5);
                    Utils.playSound("res/dieenemy.wav",false);
                    if (LevelScene.enemyBossController.getGameRect().isDead()){
                        CollisionManager.instance.remove(LevelScene.enemyBossController.enemyHpBossControllerDame);
                        CollisionManager.instance.remove(LevelScene.enemyBossController.enemyHpBossControllerHit);
                        CollisionManager.instance.remove(LevelScene.enemyBossController);
                        CollisionManager.instance.remove(this);

                    }


                }


            }
        }else {

            if(other instanceof PlayerController){
                dameLimit++;
                //cắn máu phát đầu tiên
                if (dameLimit==1){
                    other.getGameRect().getHit(1);

                    dameLimitLater=dameLimit;
                    Utils.playSound("res/enemycanPlay.wav",false);
                }
                //nếu va chạm liên tục thì giới hạn lại

                if (dameLimit==dameLimitLater){
                    damecount++;
                    if (damecount==5){
                        Utils.playSound("res/enemycanPlay.wav",false);
                        other.getGameRect().getHit(1);
                        damecount=0;
                    }

                }

            }

        }



    }

}
