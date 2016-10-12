/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Timo
 */
/**
 * Luokka hallinnoi pelin logiikkaa
 */
public class Logic {

    private float deltaspd;
    private ArrayList<GameObject> renderList;
    private Enemy[] enemies;
    private Player timo;
    private ArrayList<Collectible> collectList;
    private Walls walls;

    public Logic(ArrayList<GameObject> rlist, Player player) {
        walls = new Walls();
        renderList = rlist;
        collectList = new ArrayList<Collectible>();
        enemies = new Enemy[3];
        timo = player;
    }

    /**
     * aseta delta
     *
     * @param delta float pelin delta
     */
    public void setDelta(float delta) {
        this.deltaspd = delta;
    }

    /**
     * päivitä pelaajaobjektin logiikka
     */
    public void playerLogicUpdate(GameContainer gc) throws SlickException {
        timo.move(deltaspd);
        for (int i = 0; i < 3; i++) {
            if (enemies[i] != null) {
                if (enemies[i].getX() < timo.getX() + 10 && enemies[i].getX() > timo.getX()) {
                    if (enemies[i].getY() < timo.getY() + 10 && enemies[i].getY() > timo.getY()) {
                        timo.addToHealth(-1);
                    }
                }
            }
        }
        if(timo.getHealth()<0){
            timo.setDead(true);
        }
        if(timo.getDead()){
            gc.reinit();
        }

    }

    /**
     * päivittää vihollisobjektien logiikan eli liikuttaa niitä lähemmäs
     * pelaajaa
     */
    public void enemyLogicUpdate() throws SlickException {
        for (int i = 0; i < 3; i++) {   //enemy generation
            if (enemies[i] == null) {
                enemies[i] = new Enemy(timo.getX(), timo.getY(), "src/assets/spr_enemy1.png");
                renderList.add(enemies[i]);
            }
            if (enemies[i] != null) {   //enemy movement and destruction
                enemies[i].moveToPlayer(timo, deltaspd);
                GameObject s = enemies[i].collidesAny(timo.getShooter().getProjectiles());
                if (s != null) {
                    renderList.remove(enemies[i]);
                    enemies[i] = null;
                    renderList.remove(s);
                    timo.getShooter().getProjectiles().remove((Projectile) s);
                    s = null;
                }
            }
        }

    }

    /**
     * päivittää projectilejen logiikan eli liikuttaa niitä kohti niiden suuntaa
     * ja tarkistaa onko niiden elinikä lopussa
     */
    public void projectileLogicUpdate() throws SlickException {
        Projectile p = timo.shoot(deltaspd);    //shooting
        if (p != null) {
            renderList.add(p);
        }
        //destroy projectile
        Projectile[] toBeDestroyed = timo.getShooter().moveAllProjectiles(deltaspd);
        for (int i = 0; i < 20; i++) {
            if (toBeDestroyed[i] != null) {
                renderList.remove(toBeDestroyed[i]);
            }
        }
        toBeDestroyed = null;
    }

    /**
     * siirtää kameraa suhteessa pelaajan sijaintiin
     */
    public void cameraLogicUpdate(Camera mainCamera) {
        mainCamera.setX(timo.getX() - 200); //camera follow
        mainCamera.setY(timo.getY() - 200);
    }

    /**
     * tarkistaa onko pelaajaobjekti lähellä kerättäviä tavaroita ja kerää ne
     * pelaajalle mikäli ne ovat lähellä
     */
    public void collectibleLogicUpdate() {
        Collectible j = (Collectible) timo.collidesAny(collectList);    //collect item
        if (j != null) {
            if (timo.collect(j)) {
                renderList.remove(j);
                collectList.remove(j);
                //j = null;
            }
        }

    }

    /**
     * alustaa kerättävät tavarat
     */
    public void collectibleSetup() throws SlickException {
        Collectible crest = new Collectible(200, 200, "src/assets/spr_item_crest.png", "crest");
        Collectible stick = new Collectible(300, 300, "src/assets/spr_item_stick.png", "stick");
        renderList.add(crest);
        renderList.add(stick);
        collectList.add(crest);
        collectList.add(stick);

    }

    /**
     * alustaa taustakentän
     */
    public void wallsSetup() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameObject wall = walls.getWallArray()[i][j];
                if (wall != null) {
                    renderList.add(wall);
                }
            }
        }
        timo.setWalls(walls);
    }

}
