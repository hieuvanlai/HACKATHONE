package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import levels.Level1;
import models.GameRect;
import views.ImageRender;

/**
 * Created by hieuv on 5/17/2017.
 */
public class Gold extends Controller implements Collider {
    public Gold(GameRect gameRect, ImageRender imageRender) {
        super(gameRect, imageRender);
        CollisionManager.instance.add(this);
    }
    @Override
    public void update(){
        timemove++;
        System.out.println("ádddddddddddđ");
        if (timemove>0&&timemove<30){
            gameRect.move(-1,-4);
        }
        if (timemove>30){
            if (gameRect.getY()< 390+10){
                gameRect.move(-1,+6);
            }
        }

    }

    @Override
    public void onCollider(Collider other) {

    }
}
