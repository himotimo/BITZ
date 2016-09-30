/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

/**
 *
 * @author Timo
 */
/**
 * Luokka hallinnoi inventaariota
 *
 */
public class Inventory {

    private Collectible[] bag;
    private int size = 3;

    public Inventory() {
        bag = new Collectible[size];
        System.out.println("inventory luotu!!!!! ");
    }

    public Collectible[] getBag() {
        return this.bag;
    }

    public int getSize() {
        return this.size;
    }

    public Collectible getItem(String x) {
        for (int i = 0; i < this.size; i++) {
            if (this.bag[i].getName() == x) {
                return this.bag[i];
            }
        }
        return null;
    }
    
    /**
     * Metodi laittaa inventoryyn Collectible-tavaran
     *
     * @param item inventoryyn laitettava objekti
     * @return boolean pystyttiinkö laittamaan tavara inventoryyn
     * 
     * 
     *
     */

    public boolean put(Collectible item) {
        if (!this.isFull()) {
            System.out.println("koitetaan laittaa");
            for (int k = 0; k < this.size; k++) {
                if (this.bag[k] == null) {
                    this.bag[k] = item;
                    System.out.println("inventoryyn laitettu: " + item.getName());
                    return true;
                }
            }
        }
        System.out.println("inventory on täynnä");
        return false;
    }

    /**
     * Metodi tarkistaa, onko inventory täynnä
     *
     * @return boolean, onko inventory täynnä
     * 
     * 
     *
     */
    
    public boolean isFull() {
        System.out.println("onkotäynnä");
        boolean isfull = true;
        for (int k = 0; k < this.size; k++) {
            if (this.bag[k] == null && isfull == true) {
                System.out.println("inventoryssa on tilaa");
                isfull = false;
            }
        }
        return isfull;
    }

}
