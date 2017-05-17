package controllers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class ControllerManager {
    private ArrayList<Controller> controllers;
    public static final ControllerManager instance = new ControllerManager();

    private ControllerManager() {
        this.controllers = new ArrayList<>();
    }

    public void add(Controller controller) {
        controllers.add(controller);
    }

    public void update() {
        for (int i = 0; i < controllers.size(); i++) {
            if (controllers.get(i).gameRect.isDead()) {
                controllers.remove(i);
            }
        }
        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).update();
        }
    }

    public void draw(Graphics g) {

        boolean x=true;
        while (x){
            try {
                for (int i = 0; i < controllers.size(); i++){
                    controllers.get(i).draw(g);
                }
                x=false;
            }catch (Exception e){
                x=true;

            }

        }



    }
}
