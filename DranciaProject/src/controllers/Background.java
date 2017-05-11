package controllers;

import java.awt.*;

/**
 * Created by ADMIN on 5/9/2017.
 */
public class Background {
    private int bgX;
    private int bgY;
    private int speed;

    public Background(int bgX, int bgY) {
        this.bgX = bgX;
        this.bgY = bgY;
        this.speed = 1;
    }

    public void update(){
        this.bgX -= speed;
        if (this.bgX <= -252){
            this.bgX = 960;
        }
    }

    public int getBgX() {
        return bgX;
    }

    public int getBgY() {
        return bgY;
    }
}
