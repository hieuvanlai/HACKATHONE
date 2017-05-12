package controllers;

import models.GameRect;
import utils.Util;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class PlayerController extends Controller {
    private int timeCount = 0;
    private int speed = 2;
    private int dx;
    private int dy;
    private boolean isJumped;
    private int gravity = 0;

    private PlayerWeaponController playerWeaponController;

    public PlayerController(int x, int y, int width, int height, Image image) {
        this.gameRect = new GameRect(x, y);
        this.imageRender = new ImageRender(image);
        playerWeaponController = new PlayerWeaponController(x + 68, y +30, 45, 25, Util.loadImage("res/weapon01_1.png"));
        ControllerManager.instance.add(playerWeaponController);
    }

    public void processInput(boolean isUpPressed, boolean isDownPressed, boolean isLeftPressed, boolean isRightPressed) {
        if (isUpPressed) {
            if (!isJumped) {
                this.dy = -3;
                this.isJumped = true;
            }
        }
        if (isDownPressed) {
            if (isJumped) {
                this.dy = 6;
            }
        }
        if (isLeftPressed) {
            if (this.gameRect.getX()+speed >= 0) {
                this.dx = -this.speed;
            } else this.dx = 0;
        }
        if (isRightPressed) {
            if (this.gameRect.getX()+speed < 890) {
                this.dx = this.speed;
            } else this.dx = 0;
        }
    }

    public void update() {
        timeCount++;
        this.gameRect.move(dx, dy);
        this.check();
        playerWeaponController.moveWeapon(this.gameRect.getX(), this.gameRect.getY()); //di chuyen weapon theo player
    }

    private void check() {
        if (this.getGameRect().getY() >= 382) {//khi player chạm đất
            this.getGameRect().setY(382
            );
            this.isJumped = false;
        }

        if (this.isJumped && this.getGameRect().getY() <= 128) {
            this.dy = 3;                      // nhảy xong thì rơi xuống
        }

        if (this.gameRect.getX()+speed <= 0 || this.gameRect.getX()+speed >= 890) {
            this.dx = 0;                       // nếu va vào w h của màn hình thì dừng lại
        }
    }

    public void draw(Graphics g) {
        imageRender.render(g, this.gameRect);
    }
}
