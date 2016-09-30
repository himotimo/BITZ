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
public class GameObject {

    protected float x;
    protected float y;
    protected int value;
    private Image sprite;
    private String name;
    protected int direction;

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
