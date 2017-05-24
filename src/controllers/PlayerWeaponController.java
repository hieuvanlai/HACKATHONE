package controllers;

import enemys.*;
import models.GameRect;
import scenes.LevelScene;
import scenes.LevelScene2;
import utils.Utils;
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
            this.imageRender.setImage(Utils.FlipImage(imageRender.getImageStart()));
        }

    }
    public void addGold(Collider other){
        GameRect gameRectg = new GameRect(other.getGameRect().getX(),other.getGameRect().getY(),10,10);
        Gold gold = new Gold(gameRectg);
        ControllerManager.instance.add(gold);
    }

    @Override
    public void onCollider(Collider other) {
        if (other instanceof EnemyController) {
            addGold(other);

            ((EnemyController) other).gameRect.setDead(true);
            Utils.playSound("res/dieenemy.wav",false);
            CollisionManager.instance.remove(other);

        }
        if (other instanceof EnemyBoss02Controller) {

            ((EnemyBoss02Controller) other).gameRect.getHit(1);
            if (((EnemyBoss02Controller) other).gameRect.getX()+((EnemyBoss02Controller) other).gameRect.getWidth()<this.getGameRect().getX()+10){
                LevelScene2.playerController.getGameRect().move(20,0);
            }else {
                LevelScene2.playerController.getGameRect().move(-20,0);
            }

            Utils.playSound("res/dieenemy.wav",false);


        }
        if(other instanceof EnemySnakeController)

        {
            addGold(other);
            ((EnemySnakeController) other).gameRect.setDead(true);
            Utils.playSound("res/dieenemy.wav",false);
            CollisionManager.instance.remove(other);
        }
        if(other instanceof EnemyPokemonController)

        {
            addGold(other);
            ((EnemyPokemonController) other).gameRect.setDead(true);
            Utils.playSound("res/dieenemy.wav",false);
            CollisionManager.instance.remove(other);
        }
        if(other instanceof EnemyWitchController)

        {
            addGold(other);
            ((EnemyWitchController) other).gameRect.setDead(true);
            Utils.playSound("res/dieenemy.wav",false);
            CollisionManager.instance.remove(other);
        }
        if(other instanceof EnemyFlyController)

        {
            addGold(other);
            ((EnemyFlyController) other).gameRect.setDead(true);
            Utils.playSound("res/dieenemy.wav",false);
            CollisionManager.instance.remove(other);
        }

        }


    }



