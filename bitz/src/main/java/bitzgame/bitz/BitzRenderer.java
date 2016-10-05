package bitzgame.bitz;


import bitzgame.bitz.GameObject;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Timo
 */
public class BitzRenderer {
    private ArrayList<GameObject> renderList;
    
    public BitzRenderer(ArrayList<GameObject> rList){
        renderList = rList;
    }
    
    public void renderTheList(Camera mainCamera){
        for (GameObject j : renderList) {      //draw objects on the list
            if (j.renderable(mainCamera)) {
                j.getSprite().draw((float) j.getX() - mainCamera.getX(), (float) j.getY() - mainCamera.getY());
            }
        }
    }
    
    public void renderPlayer(Player timo){        
        timo.getSprite().draw(200, 200);        
    }
    
    public void renderInventory(Player timo, Graphics g){
        int var = 1;        //draw inventory and the items in it
        for (Collectible j : timo.getInventory().getBag()) {
            g.drawString("|INVENTORY|", 20, 30);
            if (j != null) {
                g.drawString("- " + j.getName() + " -", 30, 20 + var * 20);
            }
            var++;
        }
    }
    
}
