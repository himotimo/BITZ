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

public class Main extends BasicGame {

    Player timo;
    Input input;
    float gameslow;
    float deltaspd;
    Collectible crest;
    Collectible stick;
    ArrayList<GameObject> renderList;
    ArrayList<Collectible> collectList;
    Camera mainCamera;

    public Main(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        renderList = new ArrayList<GameObject>();
        collectList = new ArrayList<Collectible>();
        crest = new Collectible(200, 200, "src/assets/spr_item_crest.png", "crest");
        stick = new Collectible(300, 300, "src/assets/spr_item_stick.png", "stick");
        timo = new Player(0, 0, "src/assets/spr_char1.png");
        mainCamera = new Camera(-100,-100);
        renderList.add(crest);
        renderList.add(stick);
        collectList.add(crest);
        collectList.add(stick);
        input = new Input(480); //
        gameslow = 4; //the bigger value the slower game is. used to divide delta.
    }

    public float moveRight() {
        if (input.isKeyDown(Input.KEY_D)) {
            return 1;
        }
        return 0;
    }

    public float moveLeft() {
        if (input.isKeyDown(Input.KEY_A)) {
            return -1;
        }
        return 0;
    }

    public float moveUp() {
        if (input.isKeyDown(Input.KEY_W)) {
            return -1;
        }
        return 0;
    }

    public float moveDown() {
        if (input.isKeyDown(Input.KEY_S)) {
            return 1;
        }
        return 0;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        deltaspd = delta;
        deltaspd /= gameslow; //makes running slower
        timo.moveX(deltaspd * moveLeft() + deltaspd * moveRight());  //move timo
        timo.moveY(deltaspd * moveUp() + deltaspd * moveDown());
        mainCamera.setX(timo.getX()-100);
        mainCamera.setY(timo.getY()-100);

        Collectible j = (Collectible) timo.collidesAny(collectList);
        if (j != null) {
            if (timo.collect(j)) {
                System.out.println("inventoryssa " + j.getName());
                renderList.remove(j);
                collectList.remove(j);
                System.out.println(j.getName() + " poistettu listalta");
                System.out.println(j.getName() + " tuhotaan");
                //j = null;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        timo.getSprite().draw(100,100);
        for (GameObject j : renderList) {      //draw objects on the 'object' list
            j.getSprite().draw((float) j.getX()-mainCamera.getX(), (float) j.getY()-mainCamera.getY());
        }

        int var = 1;        //draw inventory and the items in it
        for (Collectible j : timo.getInventory().getBag()) {
            g.drawString("|INVENTORY|", 20, 30);
            if (j != null) {
                g.drawString("- " + j.getName() + " -", 30, 20 + var * 20);
            }
            var++;
        }
        //newimg.draw(x_pos, y_pos);
        //newimg.draw(gc.getInput().getMouseX(), gc.getInput().getMouseY());
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();

        } catch (SlickException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
