package console;

import models.GameRect;

/**
 * Created by hieuv on 5/2/2017.
 */
public interface Collider {
    GameRect getGameRect();
    void  onCollider(Collider other);
}
