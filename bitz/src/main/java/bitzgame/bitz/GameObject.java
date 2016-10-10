/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
    protected int cantmove;

    public GameObject(float nowx, float nowy) throws SlickException {
        x = nowx;
        y = nowy;
    }

    public GameObject(float nowx, float nowy, String sprpath) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
    }

    public GameObject(float nowx, float nowy, String sprpath, int dir) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
        direction = dir;
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

    public void collidingWallDirection(Walls walls) {
        boolean moved = false;
        GameObject[][] w = walls.getWallArray();
        float thisx = this.getX();
        float thisy = this.getY();
        float otherx = 0;
        float othery = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (w[i][j] != null) {
                    otherx = w[i][j].getX();
                    othery = w[i][j].getY();
                    
                    if (thisx > otherx && thisx <= otherx + 50 && thisy > othery && thisy <= othery + 5) {
                        this.cantmove = 2; //cant move down
                        moved = true;
                        break;
                    } else if (thisx >= otherx && thisx < otherx +5 && thisy > othery && thisy <= othery+50){
                        this.cantmove =1; //cant move right
                        moved = true;
                        break;
                    } else if(thisx > otherx && thisx <= otherx + 50 && thisy > othery+45 && thisy <= othery+50){
                        this.cantmove = 0;
                        moved = true;
                        break;
                    } else if (thisx >= otherx+45 && thisx < otherx +50 && thisy > othery && thisy <= othery+50){
                        this.cantmove = 3;
                        moved = true;
                        break;
                    }else{
                        this.cantmove = 4;
                    }
                }
            }
            if (moved) {
                break;
            }
        }
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

    public GameObject(float nowx, float nowy, String sprpath, String itemname) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
        name = itemname;
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

}
