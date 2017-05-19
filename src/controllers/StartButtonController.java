package controllers;
import models.GameRect;
import utils.Util;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

public class StartButtonController extends Controller {
    Animation animation;
    ArrayList<Image> images;
    private boolean selected;

    public StartButtonController(int x, int y, Image image) {
        super(new GameRect(x,y,120/4,50/4),new ImageRender(image));
        images = new ArrayList<>();
        images.add(Util.loadImage("res/start-button.png"));
        images.add(Util.loadImage("res/blurred-start-button.png"));
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
