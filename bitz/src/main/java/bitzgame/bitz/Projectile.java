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
public class Projectile extends GameObject {

    public int life;

    public Projectile(float nowx, float nowy, String sprpath, int dir) throws SlickException {
        super(nowx, nowy, sprpath, dir);
        life = 800;
    }

    public int getLife() {
        return this.life;
    }

    public void minusLife() {
        this.life -= 1;
    }

    public void move(float deltaspd) {
        if (direction == 0) {
            this.moveY(-2 * deltaspd);
        } else if (direction == 1) {
            this.moveX(2 * deltaspd);
        } else if (direction == 2) {
            this.moveY(2 * deltaspd);
        } else {
            this.moveX(-2 * deltaspd);
        }
    }

}
