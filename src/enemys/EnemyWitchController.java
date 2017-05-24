package enemys;

import controllers.*;
import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import javax.rmi.CORBA.Util;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/20/2017.
 */
public class EnemyWitchController extends Controller  implements Collider {
    private int xplanestart;
    private int xenlenystart;
    ArrayList<Image> images;
    ArrayList<Image> imagesx = new ArrayList<>();

    public EnemyWitchController(int x, ArrayList<Image> images) {
        this.gameRect = new GameRect(x, 385,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.imageRender = new ImageRender(images.get(0));
        animation = new Animation(images,70);
        imagestart = images;
        for (Image image:images){
            imagesFlip.add(Utils.FlipImage(image));
        }
        CollisionManager.instance.add(this);
        this.images = new ArrayList<>();
        this.images.add(Utils.loadImage("res/dan1.png"));
        this.images.add(Utils.loadImage("res/dan2.png"));
        this.images.add(Utils.loadImage("res/dan3.png"));
        this.images.add(Utils.loadImage("res/dan4.png"));
        imagesx.add(Utils.loadImage("res/khoi1.png"));
        imagesx.add(Utils.loadImage("res/khoi2.png"));
        imagesx.add(Utils.loadImage("res/khoi3.png"));

        AnimationWitchController animationWitchController = new AnimationWitchController(new GameRect(x,426-50-20,0,0),imagesx,80);
        ControllerManager.instance.add(animationWitchController);

    }

    @Override
    public void onCollider(Collider other) {

    }
    public void shootBullet(){
        GameRect  rect = new GameRect(gameRect.getX()+gameRect.getWidth(),gameRect.getY()+gameRect.getHeight()/2-images.get(1).getHeight(null)/2);
        EnemyBullet enemyBullet  = new EnemyBullet(rect,images,xenlenystart>xplanestart);
        ControllerManager.instance.add(enemyBullet);
    }
    @Override
    public void draw(Graphics g) {
        if (timemove>=100){
            animation.draw(g,gameRect);
        }


    }



    @Override
    public void update(){
        timemove++;

        if (timemove==102){
            xplanestart=PlayerController.x;
            xenlenystart=gameRect.getX();

        }

        if (timemove>100&&timemove<200){
            if (xenlenystart>xplanestart){
                if (gameRect.getX()<=0){

                    xenlenystart=481;
                    timemove=101;
                }else {
                    gameRect.move(-1,0);

                    animation.setImages(imagesFlip);
                }


            }else {
                if (gameRect.getX()>=960-40){

                    xenlenystart=479;
                    timemove=101;
                }
                else {
                    gameRect.move(1,0);
                    animation.setImages(imagestart);
                }

            }
        }
        if (timemove==202){
            shootBullet();

        }
        if (timemove>400){
            timemove=101;
        }



    }
}
