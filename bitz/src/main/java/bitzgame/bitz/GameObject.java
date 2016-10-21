/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Timo
 */
/**
 * Luokka antaa perusominaisuudet kaikille pelin objekteille
 */
public class GameObject {

    protected float x;
    protected float y;
    protected int value;
    private Image sprite;
    private String name;
    protected int direction;
    protected int cantmove = 4;
    private float xspd;
    private float yspd;
    private int width;
    private int height;

    public GameObject(float nowx, float nowy) throws SlickException {
        x = nowx;
        y = nowy;
        xspd = 0;
        yspd = 0;
    }

    public GameObject(float nowx, float nowy, String sprpath) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
        xspd = 0;
        yspd = 0;
    }

    public GameObject(float nowx, float nowy, String sprpath, int dir) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
        direction = dir;
        xspd = 0;
        yspd = 0;
    }

    public GameObject(float nowx, float nowy, String sprpath, String itemname) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
        name = itemname;
        xspd = 0;
        yspd = 0;
    }

    /**
     * Metodi kertoo kuuluuko objektia renderöidä. jos se on "kuvaruudussa",
     * niin sitä tulee renderöidä
     *
     * @param other pelin kamera
     *
     * @return true tai false tulisiko objektia renderöidä
     */
    public boolean renderable(Camera other) {
        if (other == null) {    //checking if this is near the game screen
            return false;
        }
        float leftEdge = other.getX() - 50;
        float rightEdge = leftEdge + 740;
        float upEdge = other.getY() - 50;
        float downEdge = upEdge + 580;
        if (this.getX() > leftEdge && this.getX() < rightEdge) {
            if (this.getY() > upEdge && this.getY() < downEdge) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi kertoo törmääkö pelaajaobjekti seinään jos törmätään, muutetaan
     * xspeed tai yspeed nollaksi riippuen siitä, miltä puolelta seinään
     * törmätään
     *
     * @param walls on Walls-luokan objekti
     *
     * @return true tai false törmätäänkö tällä hetkellä johonkin seinään
     */
    public boolean collidingWallX(Walls walls) {
        boolean collided = false;
        GameObject[][] w = walls.getWallArray();
        float thisx = this.getX();
        float thisy = this.getY();
        float otherx = 0;
        float othery = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (w[i][j] != null) {
                    otherx = w[i][j].getX();
                    int otherHeight = w[i][j].getHeight();
                    othery = w[i][j].getY();
                    int otherWidth = w[i][j].getWidth();
                    if ((thisx + this.xspd * 2 + this.getWidth()) > otherx && (thisx + this.xspd * 2) < otherx + otherWidth && (thisy + this.getHeight()) > othery && (thisy) < othery + otherHeight) {
                        collided = true;
                        if (collided) {
                            break;
                        }
                    }
                }
            }
            if (collided) {
                return true;
            }
        }
        return false;
    }

    public boolean collidingWallY(Walls walls) {
        boolean collided = false;
        GameObject[][] w = walls.getWallArray();
        float thisx = this.getX();
        float thisy = this.getY();
        float otherx = 0;
        float othery = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (w[i][j] != null) {
                    otherx = w[i][j].getX();
                    int otherWidth = w[i][j].getWidth();
                    othery = w[i][j].getY();
                    int otherHeight = w[i][j].getHeight();
                    if ((thisx + this.getWidth()) > otherx && thisx < otherx + otherWidth && (thisy + this.yspd * 2 + this.getHeight()) > othery && (thisy + this.yspd * 2) < othery + otherHeight) {
                        collided = true;
                        if (collided) {
                            break;
                        }
                    }
                }
            }
            if (collided) {
                return true;
            }
        }
        return false;
    }

    public int getCantMove() {
        return this.cantmove;
    }

    /**
     * Metodi kertoo törmääkö se other objektiin.
     *
     *
     * @param other toinen objekti
     *
     * @return true tai false törmätäänkö tällä hetkellä otheriin
     */
    public boolean collidesWith(GameObject other) {
        if (this != other) {
            if (this.getX() > other.getX() - 20 && this.getX() < other.getX() + 20) {
                if (this.getY() > other.getY() - 20 && this.getY() < other.getY() + 20) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi kertoo törmääkö se mihinkään listan objektiin.
     *
     * @param other lista objekteja
     *
     * @return GameObject johon törmätään, muussa tapauksessa null
     */
    public GameObject collidesAny(ArrayList<? extends GameObject> other) {
        for (GameObject j : other) {
            if (this.collidesWith(j)) {
                return j;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int a) {
        this.value = a;
    }

    public void setSprite(String sprpath) throws SlickException {
        this.sprite = new Image(sprpath);
    }

    public Image getSprite() {
        return this.sprite;
    }

    public void moveX(float addX) {
        this.x += addX;
    }

    public void moveY(float addY) {
        this.y += addY;
    }

    public float getY() {
        return this.y;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float nowx) {
        this.x = nowx;
    }

    public void setY(float nowy) {
        this.y = nowy;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    public void setXspd(float f) {
        this.xspd = f;
    }

    public float getXspd() {
        return this.xspd;
    }

    public void setYspd(float f) {
        this.yspd = f;
    }

    public float getYspd() {
        return this.yspd;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int h) {
        this.height = h;
    }

}
