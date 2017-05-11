package controllers;

import models.GameRect;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRender imageRender;

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
