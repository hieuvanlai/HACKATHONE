package enemys;

import models.GameRect;

import java.awt.*;

/**
 * Created by hieuv on 5/11/2017.
 */
public class MoveBeHavior {
    public void moveup(GameRect gameRect){
        gameRect.move(0,-1);

    }

    public void moveleft(GameRect gameRect){
        gameRect.move(-1,0);
    }
    public void movestop(GameRect gameRect){
        gameRect.move(0,0);
    }
    public void moveright(GameRect gameRect){
        gameRect.move(1,0);
    }
    public boolean moveZ(int timemove,GameRect gameRect,int xstart){
        if (xstart<480){
            if (timemove>0&&timemove<50){
                gameRect.move(1,+2);
            }if (timemove>50&&timemove<100){
                gameRect.move(1,0);
            }if (timemove>100&&timemove<150){
                gameRect.move(1,+2);
            }
            if (timemove>150&&timemove<200){
                gameRect.move(-1,-2);

            }
            if (timemove>200&&timemove<250){
                gameRect.move(-1,0);

            }if (timemove>250&&timemove<300){
                gameRect.move(-1,-2);

            }
            return true;
        }else{
            if (timemove>0&&timemove<50){
                gameRect.move(-1,+2);
            }if (timemove>50&&timemove<100){
                gameRect.move(-1,0);
            }if (timemove>100&&timemove<150){
                gameRect.move(-1,+2);
            }
            if (timemove>150&&timemove<200){
                gameRect.move(+1,-2);

            }
            if (timemove>200&&timemove<250){
                gameRect.move(1,0);

            }if (timemove>250&&timemove<300){
                gameRect.move(1,-2);

            }
            return false;
        }







    }

}
