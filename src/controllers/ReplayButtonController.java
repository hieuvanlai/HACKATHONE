package controllers;

import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

public class ReplayButtonController extends Controller{
    Animation animation;
    ArrayList<Image> images;
    private boolean selected;

    public ReplayButtonController(int x, int y, Image image) {
        super(new GameRect(x,y,140/4,65/4),new ImageRender(image));
        images = new ArrayList<>();
        images.add(Utils.loadImage("res/replay-button.png"));
        images.add(Utils.loadImage("res/blurred-replay-button.png"));
        animation = new Animation(images,20,false);
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void draw(Graphics graphics){
        if(selected) {
            animation.drawbutton(graphics, gameRect);
        }
        else{
            imageRender.render(graphics,gameRect);
        }
    }
}
