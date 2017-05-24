package controllers;

import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by valky on 5/13/2017.
 */
public class QuitButtonController extends Controller {
    Animation animation;
    ArrayList<Image> images;
    private boolean selected;

    public QuitButtonController(int x, int y, Image image) {
        super(new GameRect(x,y,image.getWidth(null)/4+1,image.getHeight(null)/4),new ImageRender(image));
        images = new ArrayList<>();
        images.add(Utils.loadImage("res/quit-button.png"));
        images.add(Utils.loadImage("res/blurred-quit-button.png"));
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
