package views;

import models.GameRect;

import java.awt.*;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class ImageRender {
    private Image image;
    private Image ImageStart;
    private GameRect gameRect;

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

    public ImageRender(Image image,GameRect gameRect){
        this.image = image;
        this.ImageStart=image;
        this.gameRect = gameRect;
    }

    public void render(Graphics g, GameRect gameRect){
        g.drawImage(this.image, gameRect.getX(), gameRect.getY(), gameRect.getWidth(), gameRect.getHeight(), null);
    }
}
