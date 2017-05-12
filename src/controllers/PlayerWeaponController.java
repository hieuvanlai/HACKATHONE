package controllers;

import models.GameRect;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/10/2017.
 */
public class PlayerWeaponController extends Controller{
    public PlayerWeaponController(int x, int y, int width, int height, Image image){
        this.gameRect = new GameRect(x, y,image.getWidth(null),image.getHeight(null));
        this.imageRender = new ImageRender(image,gameRect);
    }

    @Override
    public void draw(Graphics g) {
        this.imageRender.render(g, this.gameRect);
    }

    public void moveWeapon(int x, int y){ //di chuyen weapon theo player
        this.gameRect.setX(x + 68);
        this.gameRect.setY(y + 34);


    }
}
