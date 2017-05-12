package enemys;

import controllers.Controller;
import controllers.PlayerController;
import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;

/**
 * Created by hieuv on 5/12/2017.
 */
public class EnemyFlyController extends Controller {
    public boolean moveleftright;
    private int xenlenystart;



    public   int timemove=0;
    MoveBeHavior moveBeHavior = new MoveBeHavior();
    public EnemyFlyController(int x, Image image){
        this.gameRect = new GameRect(x, 150,image.getWidth(null),image.getHeight(null));
        xenlenystart = x;


        this.imageRender = new ImageRender(image,gameRect);

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
                    imageRender.setImage(imageRender.getImageStart());






                }

            }else {
                if (gameRect.getX()<=0){
                    moveBeHavior.movestop(gameRect);

                    timemove=501;

                }else {
                    moveBeHavior.moveleft(gameRect);

                    imageRender.setImage(Util.FlipImage(imageRender.getImageStart()));

                }


            }
            if (timemove == 500){
                xenlenystart = gameRect.getX();
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

        if (moveBeHavior.moveZ(timemove,gameRect,xenlenystart)){
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

        this.imageRender.render(g, gameRect);
    }
}
