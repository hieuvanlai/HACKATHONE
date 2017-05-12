package views;

import models.GameRect;

import java.awt.*;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class ImageRender {
    private Image image;
    public Image ImageStart;
    private GameRect gameRect;
    //Sửa Lại Ảnh
    public void setImage(Image image) {
        this.image = image;



    }

    public ImageRender(Image image, GameRect gameRect) {
        this.image = image;
        this.gameRect = gameRect;
    }

    public Image getImage() {
        return image;
    }

    public ImageRender(Image image){
        this.image = image;
        gameRect.setWidth(image.getWidth(null)*4);
        gameRect.setHeight(image.getHeight(null)*4);
    }

    public void render(Graphics g, GameRect gameRect){
        g.drawImage(this.image, gameRect.getX(), gameRect.getY(), gameRect.getWidth(), gameRect.getHeight(), null);
    }
}
