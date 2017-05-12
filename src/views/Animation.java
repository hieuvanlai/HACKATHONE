package views;

import models.GameRect;

import java.awt.*;
import java.util.List;

/**
 * Created by hieuv on 5/7/2017.
 */
public class Animation {
    private List<Image> images;
    private  int imageIndex= 0;
    private int interval=900;
    private int time;
    private boolean isOneTime;
    private  boolean hasEnded;

    public boolean isHasEnded() {
        return hasEnded;
    }

    public Animation(List<Image> images, int interval) {
        this.images = images;
        this.interval = interval;
    }

    public Animation(List<Image> images, int interval, boolean isOneTime) {
        this.images = images;
        this.interval = interval;
        this.isOneTime = isOneTime;
    }

    public Animation(List<Image> images, boolean isOneTime) {
        this.images = images;
        this.isOneTime = isOneTime;
    }

    public Animation(List<Image> images) {
        this.images = images;
        this.isOneTime= false;
    }
    public void draw(Graphics graphics, GameRect gameRect){
        time++;
        if (time>=interval){
            time=0;
            imageIndex++;
            if (imageIndex>=images.size()){
                if (isOneTime){
                    hasEnded=true;
                }
                imageIndex=0;
            }

        }

        graphics.drawImage(images.get(imageIndex),gameRect.getX(),gameRect.getY(),null);


    }
}
