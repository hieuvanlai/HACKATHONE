package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import levels.Level1;
import models.GameRect;
import utils.Util;
import views.ImageRender;

/**
 * Created by hieuv on 5/17/2017.
 */
public class Gold extends Controller implements Collider {
    private boolean leftrRight;
    public Gold(GameRect gameRect, ImageRender imageRender) {

        super(gameRect, imageRender);
        CollisionManager.instance.add(this);
        leftrRight = Util.random.nextBoolean();
    }
    @Override
    public void update(){
        timemove++;
        if (leftrRight){
            if (timemove>0&&timemove<40){
                gameRect.move(-1,-4);
            }
            if (timemove>40){
                if (gameRect.getY()< 390+10){
                    gameRect.move(-1,+6);
                }
            }
        }else {
            if (timemove>0&&timemove<40){
                gameRect.move(1,-4);
            }
            if (timemove>40){
                if (gameRect.getY()< 390+10){
                    gameRect.move(1,6);
                }
            }
        }



    }

    @Override
    public void onCollider(Collider other) {

    }
}
