package enemys;

import controllers.Controller;
import controllers.ControllerManager;
import controllers.AnimationSnakeControler;
import controllers.PlayerController;
import models.GameRect;
import utils.Util;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/12/2017.
 */
public class EnemySnakeController extends Controller {

    private int xplanestart;
    private int xenlenystart;


    public   int timemove=0;
    MoveBeHavior moveBeHavior = new MoveBeHavior();
    public EnemySnakeController(int x,ArrayList<Image> images){

        AnimationSnakeControler explosionController = new AnimationSnakeControler(new GameRect(x,426,0,0));
        ControllerManager.instance.add(explosionController);


        this.gameRect = new GameRect(x, 385,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.imageRender = new ImageRender(images.get(0),gameRect);
        animation = new Animation(images,70);
        imagestart = images;
        for (Image image:images){
            imagesFlip.add(Util.FlipImage(image));
        }



    }


    @Override
    public void update() {
        System.out.println(gameRect.getX());

        timemove++;
        if (timemove==102){
            xplanestart=PlayerController.x;
            xenlenystart=gameRect.getX();

        }



        if (timemove>100&&timemove<300){
            if (xenlenystart>xplanestart){
                if (gameRect.getX()<=0){

                    xenlenystart=481;
                    timemove=101;
                }else {
                    moveBeHavior.moveleft(gameRect);

                    animation.setImages(imagesFlip);
                }


            }else {
                if (gameRect.getX()>=960-40){

                    xenlenystart=479;
                    timemove=101;
                }
                else {
                    moveBeHavior.moveright(gameRect);
                    animation.setImages(imagestart);
                }

            }

        }
        //enemy đi lên



        //timemove từ 0-100 là thời gian ennemy không di chuyển


        //enemy chạy đc 1 khoảng thời gian thì về stop
        if (timemove>300) timemove=101;




    }

    @Override
    public void draw(Graphics g) {
        if (timemove>=100){
            animation.draw(g,gameRect);
        }


    }
}