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

    private float x;
    private float y;
    private int value;
    private Image sprite;
    private String name;

    public GameObject(float nowx, float nowy, String sprpath) throws SlickException {
        x = nowx;
        y = nowy;
        sprite = new Image(sprpath);
    }

    public boolean collidesWith(GameObject other) {
        if (this != other) {
            if (this.getX() > other.getX() - 20 && this.getX() < other.getY() + 20) {
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

}
