package enemys;

import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import controllers.PlayerController;
import models.GameRect;
import utils.Util;
import views.Animation;
import views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/10/2017.
 */
public class EnemyController extends Controller implements Collider {

    public boolean moveleftright;
    private int dame =1;
    private int dameLimit=0;
    private int dameLimitLater=0;
    private int damecount=0;



    MoveBeHavior moveBeHavior = new MoveBeHavior();
    public EnemyController (int x, ArrayList<Image> images){
        this.gameRect = new GameRect(x, 416+74+60,images.get(0).getWidth(null),images.get(0).getHeight(null));
        this.imageRender = new ImageRender(images.get(0));
        animation = new Animation(images,70);
        this.imagestart = images;
        for (Image image:images){
            imagesFlip.add(Util.FlipImage(image));
        }
        CollisionManager.instance.add(this);

    }
    public void moveleftringenemy(GameRect gameRect){
        if (timemove>=100&&timemove<=300){

            //enemy sang trái hoặc phải
            if (moveleftright){
                if (gameRect.getX()>=960-imageRender.getImage().getWidth(null)*4){


                    timemove=301;

                }else {
                    moveBeHavior.moveright(gameRect);
                    animation.setImages(imagestart);


                }

            }else {
                if (gameRect.getX()<=0){
                    moveBeHavior.movestop(gameRect);

                    timemove=301;

                }else {
                    moveBeHavior.moveleft(gameRect);
                    animation.setImages(imagesFlip);

                }


            }
        }
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

        if (timemove==1){
            moveleftright = Util.random.nextBoolean();
        }

            //enemy đi lên
        if (gameRect.getY()>390){
            if (timemove>60){
                moveBeHavior.moveup(gameRect);
            }
            if (timemove>100){
                timemove=0;
            }

        }

        moveleftringenemy(gameRect);
        //timemove từ 0-100 là thời gian ennemy không di chuyển


        //enemy chạy đc 1 khoảng thời gian thì về stop
        if (timemove>300) timemove=0;




    }

    @Override
    public void draw(Graphics g) {

        animation.draw(g,gameRect);
    }

    @Override
    public void onCollider(Collider other) {

        if(other instanceof PlayerController){
                dameLimit++;
                //cắn máu phát đầu tiên
                if (dameLimit==1){
                    other.getGameRect().getHit(dame);

                    dameLimitLater=dameLimit;
                    Util.playSound("res/enemycan.wav",false);
                }
                //nếu va chạm liên tục thì giới hạn lại
                if (dameLimit==dameLimitLater){
                    if (moveleftright){
                        gameRect.move(-1,0);
                    }else{
                        gameRect.move(1,0);
                    }
                    damecount++;
                    if (damecount==20){
                        Util.playSound("res/enemycan.wav",false);
                        other.getGameRect().getHit(dame);
                        damecount=0;
                    }

                }

        }

    }
}
