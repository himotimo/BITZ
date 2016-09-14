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
public class Player extends GameObject {

    Inventory inventory;

    public Player(int nowx, int nowy, String sprpath) throws SlickException {
        super(nowx, nowy, sprpath);
        System.out.println("Player luotu!!!!!!!!!!");
        inventory = new Inventory();
    }

    public boolean collect(Collectible item) {
        //check surroundings for collectibles XYs and if close,
        // put them into inventory and destroy them from the world
        return this.inventory.put(item);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

}
