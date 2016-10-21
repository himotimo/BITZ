/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 *
 * @author Timo
 */
/**
 * Luokka hallinnoi pelaajaobjektia
 *
 */
public class Player extends GameObject {

    private Inventory inventory;
    private int health;
    private Input input;
    private Shooter shooter;
    private boolean tryShoot;
    private float bangCoolDown;
    private boolean dead;
    private Walls walls;
    private float playerspd = (float) 1;
    private int collected;

    public Player(float nowx, float nowy, String sprpath, int dir, Input in) throws SlickException {
        super(nowx, nowy, sprpath, dir);
        System.out.println("Player luotu!!!");
        inventory = new Inventory();
        health = 5;
        input = in;
        shooter = new Shooter(); // this object can shoot projectiles
        tryShoot = false;          //
        bangCoolDown = 0;               //cooldown timer of shooting
        dead = false;
        collected = 0;
    }

    public void setWalls(Walls w) {
        this.walls = w;
    }

    public void setTryShoot(boolean b) {
        this.tryShoot = b;
    }

    /**
     * Metodi koettaa, onko ampuminen mahdollista tässä framessa
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     */
    public void tryShoot(float deltaspd) throws SlickException {
        if (input.isKeyDown(Input.KEY_T) && this.bangCoolDown <= 0) {
            this.tryShoot = true;
        }
        if (this.bangCoolDown > 0) {
            this.bangCoolDown -= 1 * deltaspd;
        }
    }

    /**
     * Metodi koettaa ampua projectilen pelaajan katsomaan suuntaan.
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     *
     */
    public Projectile shoot(float deltaspd) throws SlickException {
        this.tryShoot(deltaspd);
        if (this.bangCoolDown <= 0 && tryShoot) {
            this.bangCoolDown = 100;
            this.tryShoot = false;
            return this.shooter.shoot(x, y, direction);
        } else {
            return null;
        }
    }

    public Shooter getShooter() {
        return this.shooter;
    }

    /**
     * Metodi liikuttaa pelaajaa erillisten liikutusfunktioiden avulla.
     * Tarkistetaan, onko suuntanäppäimet painettuna ja liikutaan niiden
     * suuntaan
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     *
     */
    public void move(float deltaspd) {
        this.moveX(deltaspd * this.moveLeft() + deltaspd * this.moveRight());  //move timo
        this.moveY(deltaspd * this.moveUp() + deltaspd * this.moveDown());
    }

    /**
     * Metodi tarkistaa, törmääkö pelaaja seinään ja jos ei, niin se nostaa
     * x-tai y-speediä, jos liikkumisnäppäintä on painettu
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     * @return float x- tai yspeed riippuen siitä, mihin suuntaan painetaan
     *
     */
    private float moveRight() {
        if (input.isKeyDown(Input.KEY_D)) {
            this.setDirection(1);
            this.setXspd(this.playerspd);
            if (!this.collidingWallX(walls)) {
                return this.getXspd();
            }
        }
        this.setXspd(0);
        return this.getXspd();
    }

    /**
     * Metodi tarkistaa, törmääkö pelaaja seinään ja jos ei, niin se nostaa
     * x-tai y-speediä, jos liikkumisnäppäintä on painettu
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     * @return float x- tai yspeed riippuen siitä, mihin suuntaan painetaan
     *
     */
    private float moveLeft() {
        if (input.isKeyDown(Input.KEY_A)) {
            this.setDirection(3);
            this.setXspd(-this.playerspd);
            if (!this.collidingWallX(walls)) {
                return this.getXspd();
            }
        }
        this.setXspd(0);
        return this.getXspd();
    }

    /**
     * Metodi tarkistaa, törmääkö pelaaja seinään ja jos ei, niin se nostaa
     * x-tai y-speediä, jos liikkumisnäppäintä on painettu
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     * @return float x- tai yspeed riippuen siitä, mihin suuntaan painetaan
     *
     */
    private float moveUp() {
        if (input.isKeyDown(Input.KEY_W)) {
            this.setDirection(0);
            this.setYspd(-this.playerspd);
            if (!this.collidingWallY(walls)) {

                return this.getYspd();
            }
        }
        this.setYspd(0);
        return this.getYspd();
    }

    /**
     * Metodi tarkistaa, törmääkö pelaaja seinään ja jos ei, niin se nostaa
     * x-tai y-speediä, jos liikkumisnäppäintä on painettu
     *
     * @param deltaspd pelin deltaspeed, jolla asetetaan ampumisen cooldownin
     * nopeus
     *
     * @return float x- tai yspeed riippuen siitä, mihin suuntaan painetaan
     *
     */
    private float moveDown() {
        if (input.isKeyDown(Input.KEY_S)) {
            this.setDirection(2);
            this.setYspd(this.playerspd);
            if (!this.collidingWallY(walls)) {
                return this.getYspd();
            }
        }
        this.setYspd(0);
        return this.getYspd();
    }

    /**
     * Metodi tarkistaa, törmääkö pelaaja seinään
     *
     * @param item on Collectible objekti, joka koitetaan laittaa inventoryyn
     * nopeus
     *
     * @return boolean saatiinko item laitettua inventoryyn
     *
     */
    public boolean collect(Collectible item) {
        //check surroundings for collectibles XYs and if close,
        // put them into inventory and destroy them from the world
        boolean b = this.inventory.put(item);
        if (b) {
            this.collected += 1;
        }
        return b;
    }

    public int getCollected() {
        return this.collected;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addToHealth(int i) {
        this.health += i;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int i) {
        this.health = i;
    }

    public void setDead(boolean b) {
        this.dead = b;
    }

    public boolean getDead() {
        return this.dead;
    }

}
