package models;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class GameRect {
    private int x;
    private int y;
    private int Width;
    private int Height;

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public GameRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        Width = width;
        Height = height;
    }

    private boolean isDead;
    private int HP;

    public GameRect(int x, int y) {
        this.x = x;
        this.y = y;


    }

    public void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public boolean isDead() {
        return isDead;
    }

    public int getHP() {
        return HP;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
