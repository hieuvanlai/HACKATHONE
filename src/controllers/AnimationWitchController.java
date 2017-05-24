package controllers;

import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/20/2017.
 */
public class AnimationWitchController extends Controller implements Collider {

    public AnimationWitchController(GameRect gameRect,ArrayList<Image> imagesx,int interval) {
        super(gameRect, null);



        this.animation = new Animation(imagesx,interval,true);
        CollisionManager.instance.add(this);

    }



    @Override
    public void onCollider(Collider other) {

    }
    @Override
    public  void draw(Graphics graphics){
        animation.draw(graphics,gameRect);

        if (animation.isHasEnded()){
            gameRect.setDead(true);
        }

    }
}
