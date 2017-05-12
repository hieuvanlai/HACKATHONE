package controllers;

import models.GameRect;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class Controller {
    public  ArrayList<Image> imagestart = new ArrayList<>();
    public  ArrayList<Image> imagesFlip = new ArrayList<>();
    protected GameRect gameRect;
    protected ImageRender imageRender;
    protected Animation animation ;
    public   int timemove=0;
    ArrayList<Image> images;

    public Controller(){



    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public Controller(GameRect gameRect, ImageRender imageRender){
        this.gameRect = gameRect;
        this.imageRender = imageRender;
    }

    public void update(){

    }

    public void draw(Graphics g){
        imageRender.render(g, this.gameRect);
    }
}
