package controllers;

import models.GameRect;
import views.ImageRender;

/**
 * Created by hieuv on 5/15/2017.
 */
public class IconController extends Controller {
    public IconController(GameRect gameRect, ImageRender imageRender) {
        super(gameRect, imageRender);
        this.getGameRect().setWidth(imageRender.getImage().getWidth(null)*4);
        this.getGameRect().setHeight(imageRender.getImage().getHeight(null)*4);
    }
}
