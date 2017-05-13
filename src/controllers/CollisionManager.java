package controllers;

import models.GameRect;

import java.util.ArrayList;

/**
 * Created by hieuv on 5/2/2017.
 */
public class CollisionManager {
    public static final CollisionManager instance = new CollisionManager();
    private ArrayList<Collider> colliders;

    private CollisionManager() {
        colliders = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = 0; j < colliders.size(); j++) {
                Collider ci = colliders.get(i);
                Collider cj = colliders.get(j);

                GameRect recti = ci.getGameRect();
                GameRect rectj = cj.getGameRect();

                if (recti.intersects(rectj)) {
                    ci.onCollider(cj);
                    cj.onCollider(ci);
                }

            }
        }


    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    public void remove(Collider collider) {
        colliders.remove(collider);

    }
}
