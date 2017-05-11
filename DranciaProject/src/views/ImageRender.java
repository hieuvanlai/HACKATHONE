package views;

import models.GameRect;

import java.awt.*;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class ImageRender {
    private Image image;
    //Sửa Lại Ảnh
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public ImageRender(Image image){
        this.image = image;
    }

    public void render(Graphics g, GameRect gameRect){
        g.drawImage(this.image, gameRect.getX(), gameRect.getY(), image.getWidth(null)*4, image.getHeight(null)*4, null);
    }
}
