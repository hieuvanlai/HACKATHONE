package controllers;

import enemys.EnemyController;
import enemys.EnemyFlyController;
import enemys.EnemySnakeController;
import enemys.Gold;
import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/10/2017.
 */
public class PlayerWeaponController extends Controller implements Collider{
    public PlayerWeaponController(int x, int y, int width, int height, Image image){
        this.gameRect = new GameRect(x, y,image.getWidth(null),image.getHeight(null));
        this.imageRender = new ImageRender(image);
        CollisionManager.instance.add(this);
    }

    @Override
    public void draw(Graphics g) {
        this.imageRender.render(g, this.gameRect);
    }

    public void moveWeapon(int x, int y, boolean isLeft, boolean isRight) { //di chuyển weapon theo hướng của player

        if (isRight) {
            this.gameRect.setX(x + 68);
            this.gameRect.setY(y + 36);
            this.imageRender.setImage(this.imageRender.getImageStart());
        }
        if (isLeft){
            this.gameRect.setX(x - this.gameRect.getWidth());
            this.gameRect.setY(y + 36);
            this.imageRender.setImage(Util.FlipImage(imageRender.getImageStart()));
        }

    }
    public void addGold(Collider other){
        GameRect gameRectg = new GameRect(other.getGameRect().getX(),other.getGameRect().getY(),10,10);
        Gold gold = new Gold(gameRectg,new ImageRender(Util.loadImage("res/coin01.png")));
        ControllerManager.instance.add(gold);
    }

    @Override
    public void onCollider(Collider other) {
        if (other instanceof EnemyController) {
            addGold(other);

            ((EnemyController) other).gameRect.setDead(true);
            Util.playSound("res/gietenemy.wav",false);
            CollisionManager.instance.remove(other);

            }
        if(other instanceof EnemySnakeController)

        {
            addGold(other);
            ((EnemySnakeController) other).gameRect.setDead(true);
            Util.playSound("res/gietenemy.wav",false);
            CollisionManager.instance.remove(other);
        }
        if(other instanceof EnemyFlyController)

        {
            addGold(other);
            ((EnemyFlyController) other).gameRect.setDead(true);
            Util.playSound("res/gietenemy.wav",false);

            CollisionManager.instance.remove(other);
        }

        }

    }



