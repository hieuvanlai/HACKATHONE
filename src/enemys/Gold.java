package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import models.GameRect;
import utils.Utils;
import views.Animation;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/17/2017.
 */
public class Gold extends Controller implements Collider {
    private boolean leftrRight;
    Animation animation;
    ArrayList<Image> images = new ArrayList<>();


    public Gold(GameRect gameRect) {

        super(gameRect,null);
        CollisionManager.instance.add(this);
        leftrRight = Utils.random.nextBoolean();
        images.add(Utils.loadImage("res/coin01.png"));
        images.add(Utils.loadImage("res/coin02.png"));
        images.add(Utils.loadImage("res/coin03.png"));
        images.add(Utils.loadImage("res/coin04.png"));
        images.add(Utils.loadImage("res/coin05.png"));
        animation = new Animation(images,10);
    }
    @Override
    public void update(){

        timemove++;
        if (leftrRight){
            if (timemove>0&&timemove<40){
                if (gameRect.getX()==0){
                    leftrRight=false;
                    return;
                }
                gameRect.move(-1,-4);
            }
            if (timemove>40){
                if (gameRect.getY()< 390+10){
                    gameRect.move(-1,+6);
                }else {
                    animation.setImageIndex(0);
                }
            }
        }else {
            if (gameRect.getX()==960){
                leftrRight=true;
                return;
            }
            if (timemove>0&&timemove<40){
                gameRect.move(1,-4);
            }
            if (timemove>40){
                if (gameRect.getY()< 390+10+10){
                    gameRect.move(1,6);
                }else {
                    animation.setImageIndex(0);
                }
            }
        }
        if (timemove>300){
            gameRect.setDead(true);
            CollisionManager.instance.remove(this);
        }




    }

    @Override
    public void onCollider(Collider other) {

    }
    @Override
    public void draw(Graphics g){
                animation.draw(g,gameRect);

    }
}
