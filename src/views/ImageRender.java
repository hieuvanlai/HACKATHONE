package views;

import models.GameRect;

import java.awt.*;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class ImageRender {
    private Image image;
    private Image ImageStart;




    public Image getImageStart() {
        return ImageStart;
    }

    //Sửa Lại Ảnh
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public ImageRender(Image image){
        this.image = image;
        this.ImageStart=image;

    }

    public void render(Graphics g, GameRect gameRect){
        g.drawImage(this.image, gameRect.getX(), gameRect.getY(), gameRect.getWidth(), gameRect.getHeight(), null);
    }
}
