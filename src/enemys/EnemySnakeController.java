package enemys;

import controllers.*;
import models.GameRect;
import utils.Utils;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hieuv on 5/12/2017.
 */
public class EnemySnakeController extends Controller implements Collider {

    private int xplanestart;
    private int xenlenystart;
    private int dame =1;
    private int dameLimit=0;
    private int dameLimitLater=0;
    private int damecount=0;
    AnimationWitchController explosionController;
    ArrayList<Image> imageAppeat= new ArrayList<>();
//


    public   int timemove=0;

    public EnemySnakeController(int x,ArrayList<Image> images){

        imageAppeat.add(Utils.loadImage("res/animation-snake3.png"));
        imageAppeat.add(Utils.loadImage("res/animation-snake1.png"));
        imageAppeat.add(Utils.loadImage("res/animation-snake2.png"));

        explosionController = new AnimationWitchController(new GameRect(x,426,0,0),imageAppeat,80);
        ControllerManager.instance.add(explosionController);


        this.gameRect = new GameRect(x, 385,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.imageRender = new ImageRender(images.get(0));
        animation = new Animation(images,70);
        imagestart = images;
        for (Image image:images){
            imagesFlip.add(Utils.FlipImage(image));
        }
        CollisionManager.instance.add(this);


    }


    @Override
    public void update() {
        //nếu không va chạm nữa thì về 0
        if (dameLimitLater>dameLimit){
            dameLimitLater=0;
            dameLimit=0;
            damecount=0;
        }
        //tăng  damlimitlate để kiểm tra xem có phải va chạm liên tục hay không
        if (dameLimitLater>0){
            dameLimitLater++;
        }



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
                    gameRect.move(-3,0);

                    animation.setImages(imagesFlip);
                }


            }else {
                if (gameRect.getX()>=960-40){

                    xenlenystart=479;
                    timemove=101;
                }
                else {
                    gameRect.move(3,0);
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

    @Override
    public void onCollider(Collider other) {
        //sau khi hết hiệu ứng thì mới cho xét va chạm
        if (timemove>200){
            if(other instanceof PlayerController){
                dameLimit++;
                //cắn máu phát đầu tiên
                if (dameLimit==1){
                    other.getGameRect().getHit(dame);

                    dameLimitLater=dameLimit;
                    Utils.playSound("res/enemycanPlay.wav",false);
                }
                //nếu va chạm liên tục thì giới hạn lại
                if (dameLimit==dameLimitLater){

                    if (gameRect.getX()>=960-40){
                        gameRect.move(-3,0);
                    }
                    if (xenlenystart>xplanestart){
                        gameRect.move(3,0);
                    }
                    damecount++;
                    if (damecount==20){
                        Utils.playSound("res/enemycanPlay.wav",false);
                        other.getGameRect().getHit(dame);
                        damecount=0;
                    }

                }

            }
        }


    }
}