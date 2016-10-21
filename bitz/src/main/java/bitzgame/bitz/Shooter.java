/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Timo
 */
public class Shooter {

    public ArrayList<Projectile> projectiles;

    public Shooter() {
        this.projectiles = new ArrayList<Projectile>();
    }

    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public Projectile[] moveAllProjectiles(float deltaspd) {
        Projectile[] toBeDestroyed = new Projectile[20];
        int k = 0;
        for (Projectile p : projectiles) {
            p.move(deltaspd);
            p.minusLife();
            if (p.getLife() <= 0) {
                toBeDestroyed[k] = p;
            }
            k++;
        }
        for (int i = 0; i < 20; i++) {
            if (toBeDestroyed[i] != null) {
                this.projectiles.remove(toBeDestroyed[i]);
            }
        }
        return toBeDestroyed;
    }

    public Projectile shoot(float startX, float startY, int dir) throws SlickException {
        Projectile projectile = new Projectile(startX, startY, "src/assets/Bullet.png", dir);
        this.projectiles.add(projectile);
        return projectile;
    }

}
