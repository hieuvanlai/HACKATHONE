package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import models.GameRect;
import utils.Util;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/12/2017.
 */
public class EnemyFlyController extends Controller implements Collider {
    public boolean moveleftright;
    //toa do enemy ban dau
    private int xEnemystart;



    public   int timemove=0;
    MoveBeHavior moveBeHavior = new MoveBeHavior();
    public EnemyFlyController(int x,  ArrayList<Image> images){
        this.gameRect = new GameRect(x, 150,images.get(0).getWidth(null),images.get(0).getHeight(null));
        xEnemystart = x;

        this.imageRender = new ImageRender(images.get(0));
        this.imagestart = images;
        animation = new Animation(images,70);
        for (Image image:images){
            imagesFlip.add(Util.FlipImage(image));
        }
        CollisionManager.instance.add(this);


    }
    public void moveleftringenemy(GameRect gameRect){
        if (timemove>=300&&timemove<=500){

            //enemy sang trái hoặc phải
            if (moveleftright){
                if (gameRect.getX()>=960-imageRender.getImage().getWidth(null)*4){
                    moveBeHavior.movestop(gameRect);

                    timemove=501;

                }else {
                    moveBeHavior.moveright(gameRect);
                    animation.setImages(imagestart);






                }

            }else {
                if (gameRect.getX()<=0){
                    moveBeHavior.movestop(gameRect);

                    timemove=501;

                }else {
                    moveBeHavior.moveleft(gameRect);

                    animation.setImages(imagesFlip);

                }


            }
            if (timemove == 500){
                xEnemystart = gameRect.getX();
            }
        }
    }

    @Override
    public void update() {
        timemove++;
        if (timemove==1){
            moveleftright = Util.random.nextBoolean();
        }




        moveleftringenemy(gameRect);

        if (moveBeHavior.moveZ(timemove,gameRect, xEnemystart)){
            imageRender.setImage(imageRender.getImageStart());
        }else {
            imageRender.setImage(Util.FlipImage(imageRender.getImageStart()));
        }

//




        //timemove từ 0-100 là thời gian ennemy không di chuyển


        //enemy chạy đc 1 khoảng thời gian thì về stop
        if (timemove>500) timemove=0;




    }

    @Override
    public void draw(Graphics g) {

        animation.draw(g,gameRect);
    }

    @Override
    public void onCollider(Collider other) {

    }
}
