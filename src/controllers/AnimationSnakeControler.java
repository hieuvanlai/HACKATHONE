package controllers;

import views.Animation;
import models.GameRect;
import utils.Util;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/7/2017.
 */
public class AnimationSnakeControler extends Controller implements Collider {

    private Animation animation ;
    public AnimationSnakeControler(GameRect gameRect){
        super(gameRect,null);
        ArrayList<Image> images = new ArrayList<>();
        images.add(Util.loadImage("res/animation-snake3.png"));
        images.add(Util.loadImage("res/animation-snake1.png"));
        images.add(Util.loadImage("res/animation-snake2.png"));


        this.animation = new Animation(images,80,true);
        CollisionManager.instance.add(this);
    }
    @Override
    public  void draw(Graphics graphics){
        animation.draw(graphics,gameRect);

        if (animation.isHasEnded()){
            gameRect.setDead(true);
        }

    }

    @Override
    public void onCollider(Collider other) {

    }
}
