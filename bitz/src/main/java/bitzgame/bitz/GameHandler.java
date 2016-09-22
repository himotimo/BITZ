/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 *
 * @author Timo
 */
public class GameHandler {

    private Player timo;
    private Input input;
    private float gameslow;
    private float deltaspd;
    private Collectible crest;
    private Collectible stick;
    private ArrayList<GameObject> renderList;
    private ArrayList<Collectible> collectList;
    private Camera mainCamera;
    private Walls jere;


    public void init(GameContainer gc) throws SlickException {
        renderList = new ArrayList<GameObject>();
        collectList = new ArrayList<Collectible>();
        input = new Input(480);
        crest = new Collectible(200, 200, "src/assets/spr_item_crest.png", "crest");
        stick = new Collectible(300, 300, "src/assets/spr_item_stick.png", "stick");
        timo = new Player(0, 0, "src/assets/spr_char1.png", 0, input);
        mainCamera = new Camera(-200, -200);
        jere = new Walls();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                GameObject wall = jere.getWallArray()[i][j];
                if (wall != null) {
                    renderList.add(wall);
                }
            }
        }
        renderList.add(crest);
        renderList.add(stick);
        collectList.add(crest);
        collectList.add(stick);
        gameslow = 4; //the bigger value the slower game is. used to divide delta.

    }

    public void update(GameContainer gc, int delta) throws SlickException {
        deltaspd = delta;
        deltaspd /= gameslow; //makes running slower
        timo.move(deltaspd);
        Projectile p = timo.shoot(deltaspd);
        if(p != null){
            renderList.add(p);
        }
        Projectile[] toBeDestroyed = timo.getShooter().moveAllProjectiles(deltaspd);
        for(int i = 0; i<20;i++){
            if(toBeDestroyed[i]!=null){
                renderList.remove(toBeDestroyed[i]);
            }
        }
        toBeDestroyed = null;
        //timo.moveX(deltaspd * moveLeft() + deltaspd * moveRight());  //move timo
        //timo.moveY(deltaspd * moveUp() + deltaspd * moveDown());
        mainCamera.setX(timo.getX() - 200);
        mainCamera.setY(timo.getY() - 200);

        Collectible j = (Collectible) timo.collidesAny(collectList);
        if (j != null) {
            if (timo.collect(j)) {
                renderList.remove(j);
                collectList.remove(j);
                //j = null;
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (GameObject j : renderList) {      //draw objects on the 'object' list
            if (j.renderable(mainCamera)) {
                j.getSprite().draw((float) j.getX() - mainCamera.getX(), (float) j.getY() - mainCamera.getY());
            }
        }

        int var = 1;        //draw inventory and the items in it
        for (Collectible j : timo.getInventory().getBag()) {
            g.drawString("|INVENTORY|", 20, 30);
            if (j != null) {
                g.drawString("- " + j.getName() + " -", 30, 20 + var * 20);
            }
            var++;
        }
        timo.getSprite().draw(200, 200);
    }

}
