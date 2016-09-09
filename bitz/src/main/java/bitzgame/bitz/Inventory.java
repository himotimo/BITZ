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
public class Inventory {
    private Collectible[] inventory;
    private int size = 3;
    
    public void Inventory(){
        inventory = new Collectible[size];
    }
    
    public int GetSize(){
        return this.size;
    }
    
    public Collectible Get(String x){
        for(int i =0; i<this.size; i++){
            if(this.inventory[i].getName()==x){
                return this.inventory[i];
            }
        }
        return null;
    }
    
    public boolean put(Collectible item){
        if(!this.isFull()){
            int k = 0;
            while(k<this.size){
                if(this.inventory[k]!=null){
                    this.inventory[k]=item;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isFull(){
        boolean isfull = false;
        int k = 0;
        while(k<this.size){
            if(this.inventory[k] != null && isfull ==false){
                isfull=true;
            }
        }
        return isfull;
    }
    
}
