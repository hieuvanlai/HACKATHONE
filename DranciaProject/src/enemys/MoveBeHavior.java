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
}
