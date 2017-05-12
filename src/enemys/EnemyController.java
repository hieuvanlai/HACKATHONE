package enemys;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import controllers.Controller;
import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/10/2017.
 */
public class EnemyController extends Controller {
    public boolean moveleftright;

    public   int timemove=0;
    MoveBeHavior moveBeHavior = new MoveBeHavior();
    public EnemyController (int x, Image image){
        this.gameRect = new GameRect(x, 416+74+60,image.getWidth(null),image.getHeight(null));
        this.imageRender = new ImageRender(image,gameRect);

    }
    public void moveleftringenemy(GameRect gameRect){
        if (timemove>=100&&timemove<=300){

            //enemy sang trái hoặc phải
            if (moveleftright){
                if (gameRect.getX()>=960-imageRender.getImage().getWidth(null)*4){
                    moveBeHavior.movestop(gameRect);

                    timemove=301;

                }else {
                    moveBeHavior.moveright(gameRect);
                    imageRender.setImage(imageRender.getImageStart());






                }

            }else {
                if (gameRect.getX()<=0){
                    moveBeHavior.movestop(gameRect);

                    timemove=301;

                }else {
                    moveBeHavior.moveleft(gameRect);

                    imageRender.setImage(Util.FlipImage(imageRender.getImageStart()));

                }


            }
        }
    }

    @Override
    public void update() {
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

        this.imageRender.render(g, gameRect);
    }
}