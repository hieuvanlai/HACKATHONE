package enemys;

import controllers.AnimationSnakeController;
import controllers.Controller;
import controllers.ControllerManager;
import controllers.PlayerController;
import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;

/**
 * Created by hieuv on 5/12/2017.
 */
public class EnemySnakeController extends Controller {
    public boolean moveleftright;
    private int xenlenystart;

    public   int timemove=0;
    MoveBeHavior moveBeHavior = new MoveBeHavior();
    public EnemySnakeController(int x, Image image){

        this.gameRect = new GameRect(x, 385,image.getWidth(null),image.getHeight(null));
        this.imageRender = new ImageRender(image,gameRect);
//        AnimationSnakeController  animationSnakeController = new AnimationSnakeController(gameRect);
//        ControllerManager.instance.add(animationSnakeController);

    }


    @Override
    public void update() {

        timemove++;
        if (timemove==102){
            xenlenystart=PlayerController.x;
        }



        if (timemove>100&&timemove<300){
            if (xenlenystart<480){
                if (gameRect.getX()<=0){

                    xenlenystart=481;
                    timemove=101;
                }else {
                    moveBeHavior.moveleft(gameRect);
                    imageRender.setImage(Util.FlipImage(imageRender.getImageStart()));
                }


            }else {
                if (gameRect.getX()>=960-40){

                    xenlenystart=479;
                    timemove=101;
                }
                else {
                    moveBeHavior.moveright(gameRect);
                    imageRender.setImage(imageRender.getImageStart());
                }

            }
            System.out.println(xenlenystart);
        }
        //enemy đi lên



        //timemove từ 0-100 là thời gian ennemy không di chuyển


        //enemy chạy đc 1 khoảng thời gian thì về stop
        if (timemove>300) timemove=101;




    }

    @Override
    public void draw(Graphics g) {
        if (timemove>=100){
            this.imageRender.render(g, gameRect);
        }


    }
}