package controllers;

import com.sun.org.apache.xpath.internal.SourceTree;
import enemys.*;
import levels.Level1;
import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;
import  controllers.CollisionManager;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class PlayerController extends Controller implements Collider {
    private int timeCount = 0;
    private int speed = 2;
    private int maxHeightJump = 194;
    private int dx;
    private int dy;
    private boolean isJumped;
    private boolean isMoveLeft = false; // weapon di chuyển theo hướng player
    private boolean isMoveRight = true;
    private int gravity = 3; //  trọng lượng
    public static int  x;
    public int gold=0;



    private PlayerWeaponController playerWeaponController;

    public PlayerController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);
        this.gameRect.setHP(160);
        playerWeaponController = new PlayerWeaponController(x + 68, y + 36, 45, 25, Util.loadImage("res/weapon01_1.png"));

        // ControllerManager.instance.add(playerWeaponController);
        CollisionManager.instance.add(this);
    }


    public void processInput(boolean isUpPressed, boolean isDownPressed, boolean isLeftPressed, boolean isRightPressed) {

        if (isUpPressed) {
            if (!isJumped) {
                this.dy = -5;
                this.isJumped = true;

            }
        }
        if (isDownPressed) {
            if (isJumped) {
                this.dy = 10;

            }
        }
        if (isLeftPressed) {
            if (this.gameRect.getX() + speed >= 0) {
                this.dx = -this.speed;
                isMoveLeft = true;
                isMoveRight = false;
            } else this.dx = 0;
            this.imageRender.setImage(Util.FlipImage(this.imageRender.getImageStart()));
        }
        if (isRightPressed) {
            if (this.gameRect.getX() + speed < 890) {
                this.dx = this.speed;
                isMoveLeft = false;
                isMoveRight = true;
            } else this.dx = 0;
            this.imageRender.setImage(this.imageRender.getImageStart());
        }
    }

    public void update() {
        if (getGameRect().getHP()<2){
            System.out.println(gameRect.getHP());
            this.getGameRect().setDead(true);
            playerWeaponController.getGameRect().setDead(true);
            CollisionManager.instance.remove(this);

        }



        this.x =  gameRect.getX();

        timeCount++;
        this.gameRect.move(dx, dy);
        this.check();
        playerWeaponController.moveWeapon(this.gameRect.getX(), this.gameRect.getY(), isMoveLeft, isMoveRight); //di chuyen weapon theo player
    }

    private void check() {
        if (this.getGameRect().getY() >= Level1.GROUND - this.gameRect.getHeight()) {//khi player chạm đất

            this.getGameRect().setY(Level1.GROUND - this.gameRect.getHeight());
            this.isJumped = false;
        }

        if (this.isJumped && this.getGameRect().getY() <= this.maxHeightJump) {
            this.dy = (dy + gravity);
            gameRect.move(0, dy);                      // nhảy xong thì rơi xuống
        }

        if (this.gameRect.getX() + speed <= 0 || this.gameRect.getX() + speed >= 890) {
            this.dx = 0;                       // nếu va vào w h của màn hình thì dừng lại
        }
    }

    public void draw(Graphics g) {
        imageRender.render(g, this.gameRect);
        playerWeaponController.imageRender.render(g, playerWeaponController.gameRect);
    }

    @Override
    public void onCollider(Collider other) {
        if (other instanceof Gold){
            Util.playSound("res/gietenemy.wav",false);
            gold++;
            ((Gold) other).gameRect.setDead(true);
            CollisionManager.instance.remove(other);
        }
    }
}
