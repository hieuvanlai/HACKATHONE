package controllers;

import models.GameRect;
import utils.Util;
import views.Animation;


import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/7/2017.
 */
 public  class AnimationSnakeController extends Controller {
    private Animation animation;

    public AnimationSnakeController(GameRect gameRect) {
        super(gameRect, null);
        ArrayList<Image> images = new ArrayList<>();
        images.add(Util.loadImage("res/animation-snake1.png"));
        images.add(Util.loadImage("res/animation-snake2.png"));
        images.add(Util.loadImage("res/animation-snake3.png"));

        this.animation = new Animation(images, 5, true);

    }

    @Override
    public void draw(Graphics graphics) {
        animation.draw(graphics, gameRect);
        if (animation.isHasEnded()) {

        }

    }
}


