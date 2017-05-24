package enemys;

import console.GameWindow;
import controllers.Collider;
import controllers.CollisionManager;
import controllers.Controller;
import controllers.ControllerManager;
import levels.Level1;
import models.GameRect;
import utils.Utils;
import views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/20/2017.
 */
public class EnemyBoss02Controller extends Controller implements Collider {
    private boolean isShot = true;
    private boolean moveLeft = false;
    private boolean moveRight = true;
    private boolean moveDown = false;
    private boolean moveUp = false;

    private boolean left = false;           // hướng của boss
    private boolean right = true;          // lúc bay lên thì hướng sẽ quyết định boss bay bên trái hay bên phải

    private int timeCount = 0;
    private int timeMoveDown = 0;
    private int timeMoveUp = 0;
    private int boss02MaxY = 100;
    private Image imageBoss02_05;           // rễ cây trên người boss
    private Boss02BulletController boss02BulletController;

    public EnemyBoss02Controller(Image image) {
        this.gameRect = new GameRect(480 - image.getWidth(null) * 4 / 2, 100, image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);
        imageBoss02_05 = Utils.loadImage("res/boss02_5.png");
        CollisionManager.instance.add(this);
        gameRect.setHP(160*3);
    }

    @Override
    public void update() {
        timeCount++;
        if (timeCount % 50 == 0) {
            this.shotOnTarget(isShot);
        }
        if (this.gameRect.getX() <= 100) {       //move right
            this.left = false;
            this.right = true;
            this.moveRight = true;
            this.moveLeft = false;

            this.timeMoveDown = 0;
        }
        if (this.gameRect.getX() == GameWindow.SCREEN_WIDTH / 2 - this.gameRect.getWidth() / 2) {
            this.isShot = false;                           //khi xuống thì không được bắn
            this.moveRight = false;
            this.moveLeft = false;                       //down 1
            this.timeMoveDown++;
            if (timeMoveDown == 300) {
                this.moveDown = true;
                //      timeMoveDown = 0;
            }
        }
        if (this.gameRect.getX() >= GameWindow.SCREEN_WIDTH - this.gameRect.getWidth() / 2 - 100) {
            this.left = true;
            this.right = false;
            this.moveLeft = true;                        //move left
            this.moveRight = false;

            this.timeMoveDown = 0;
        }
        if (moveDown) {
            if (this.gameRect.getY() + this.gameRect.getHeight() >= Level1.GROUND) {
                this.gameRect.setY(Level1.GROUND - this.gameRect.getHeight());
                timeMoveUp++;                               //up
                if (timeMoveUp >= 500) {
                    moveDown = false;
                    moveUp = true;
                    timeMoveUp = 0;
                }
            }
        }
        if (moveUp) {
            if (this.gameRect.getY() <= this.boss02MaxY) {
                this.moveUp = false;
                this.gameRect.setY(this.boss02MaxY);
                if (right) {
                    this.moveRight = true;
                    this.isShot = true;
                    System.out.println("a");
                }
                if (left) {
                    this.moveLeft = true;
                    this.isShot = true;             //bật bắn thi di chuyển trái phải
                    System.out.println("B");
                }
                System.out.println("c");
            }
        }

        this.move();
    }

    private void move() {            //thang if else dung de move Boss
        if (moveRight) {
            this.gameRect.move(1, 0);
        } else if (moveLeft) {
            this.gameRect.move(-1, 0);
        } else if (moveUp) {
            this.gameRect.move(0, -1);
        } else if (moveDown) {
            this.gameRect.move(0, 4);
        }
    }

    private void shotOnTarget(boolean isShot) {
        if (isShot) {

            this.boss02BulletController = new Boss02BulletController(
                    this.gameRect.getX() + this.gameRect.getWidth() / 2 - 12,
                    this.gameRect.getY() + this.gameRect.getHeight() / 2,
                    Utils.loadImage("res/boss02_skill1.png"),
                    this);
            CollisionManager.instance.add(boss02BulletController);
            ControllerManager.instance.add(boss02BulletController);
        }
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(imageBoss02_05,         //draw rễ cây trên người boss
                this.gameRect.getX() + this.gameRect.getWidth() / 2 - imageBoss02_05.getWidth(null) * 4 / 2,
                this.gameRect.getY() - imageBoss02_05.getHeight(null) * 4,
                imageBoss02_05.getWidth(null) * 4,
                imageBoss02_05.getHeight(null) * 4,
                null);
    }

    @Override
    public void onCollider(Collider other) {

    }

    private void skill_02(){                // skill 2 khi boss lao xuống thì đc kích hoạt

    }
}
