/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import org.newdawn.slick.SlickException;

/**
 *
 * @author Timo
 */
/**
     * Luokka hallinnoi vihollisobjekteja
     *
     * 
     */

public class Enemy extends GameObject {

    private int health;
    private float spd;

    public Enemy(float nowx, float nowy, String sprpath) throws SlickException {
        super(nowx, nowy, sprpath);
        spd = (float) (Math.random() * 0.7);
        if (Math.random() < 0.5) {
            this.setX(nowx - (float) (Math.random() * 200) - 100);
        } else {
            this.setX(nowx + (float) (Math.random() * 200) + 100);
        }
        if (Math.random() < 0.5) {
            this.setY(nowy - (float) (Math.random() * 200) - 100);
        } else {
            this.setY(nowy + (float) (Math.random() * 200) + 100);
        }

        health = 2;
    }
    
    /**
     * Metodi liikuttaa vihollista pelaajaa kohti.
     *
     * @param other pelaajaobjekti
     * @param deltaspd pelin deltaspeed, jolla tasoitetaan objektin liikett
     * 
     * 
     *
     */
    

    public void moveToPlayer(Player other, float deltaspd) {
        if (this.getX() < other.getX()) {
            this.moveX((float) spd * deltaspd);
        } else if (this.getX() > other.getX()) {
            this.moveX((float) -spd * deltaspd);
        }
        if (this.getY() < other.getY()) {
            this.moveY((float) spd * deltaspd);
        } else if (this.getY() > other.getY()) {
            this.moveY((float) -spd * deltaspd);
        }
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int i) {
        this.health = i;
    }

    public void addToHealth(int i) {
        this.health += i;
    }

}
