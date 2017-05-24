package enemys;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import controllers.*;
import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/20/2017.
 */
public class EnemyPokemonController extends Controller implements Collider {
    private int xplanestart;
    private int xenlenystart;

    ArrayList<Image> imagesx = new ArrayList<>();
    ArrayList<Image> imageBullet = new ArrayList<>();
    public EnemyPokemonController(int x, ArrayList<Image> images,ArrayList<Image> imageBullet) {
        this.imageBullet = imageBullet;
        this.gameRect = new GameRect(x, 385,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.imageRender = new ImageRender(images.get(0));
        animation = new Animation(images,100);
        imagestart = images;
        for (Image image:images){
            imagesFlip.add(Utils.FlipImage(image));
        }
        CollisionManager.instance.add(this);

        imagesx.add(Utils.loadImage("res/lua-1.png"));
        imagesx.add(Utils.loadImage("res/lua-2.png"));
        imagesx.add(Utils.loadImage("res/lua-3.png"));
        imagesx.add(Utils.loadImage("res/lua-4.png"));
        AnimationWitchController animationWitchController = new AnimationWitchController(new GameRect(x,426-50,0,0),imagesx,40);
        ControllerManager.instance.add(animationWitchController);
    }

    @Override
    public void onCollider(Collider other) {

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

    private void shootBullet() {
        GameRect  rect = new GameRect(gameRect.getX()+gameRect.getWidth(),gameRect.getY()+10);
        if (xenlenystart<xplanestart){
            EnemyBullet enemyBullet  = new EnemyBullet(rect,imageBullet,xenlenystart>xplanestart);
            ControllerManager.instance.add(enemyBullet);
        }else {
            ArrayList<Image> images = new ArrayList<>();
            for (Image imBullet:imageBullet){
                images.add(Utils.FlipImage(imBullet));
            }
            EnemyBullet enemyBullet  = new EnemyBullet(rect,images,xenlenystart>xplanestart);
            ControllerManager.instance.add(enemyBullet);
        }


    }
    @Override
    public void draw(Graphics g) {
        if (timemove>=100){
            animation.draw(g,gameRect);
        }


    }
}
