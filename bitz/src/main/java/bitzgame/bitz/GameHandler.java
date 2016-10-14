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
/**
 * Luokka hallinnoi pelin init- ja main looppeja, sekä renderöintiä vielä tässä
 * vaiheessa
 *
 *
 *
 *
 */
public class GameHandler {

    private Player timo;
    private Input input;
    private float gameslow;
    private float deltaspd;
    private ArrayList<GameObject> renderList;
    private Camera mainCamera;
    private Logic logiikka;
    private BitzRenderer renderer;

    /**
     * Metodi alustaa pelille tärkeät oliot ja muuttujat
     *
     * @param gc on GameContainer, jota tarvitaan tietyissä slick2dn metodeissa
     *
     *
     */
    public void init(GameContainer gc) throws SlickException {
        mainCamera = new Camera(-200, -200);
        renderList = new ArrayList<GameObject>();
        input = new Input(480);
        gameslow = 4; //the bigger value the slower game is. used to divide delta.
        timo = new Player(0, 0, "src/assets/spr_char1.png", 0, input);
        renderer = new BitzRenderer(renderList);

        logiikka = new Logic(renderList, timo);
        logiikka.wallsSetup();
        logiikka.collectibleSetup();
    }

    /**
     * Pelilooppi, jossa päivitetään pelilogiikan eri osia
     *
     * @param gc on GameContainer, jota tarvitaan tietyissä slick2dn metodeissa
     * @param delta on pelin deltaspeed, jota käytetään normalisoimaan
     * esimerkiksi peliobjektien nopeuksia ruudulla
     *
     */
    public void update(GameContainer gc, int delta) throws SlickException {
        deltaspd = delta;   //used for the actual deltaspeed in the game
        deltaspd /= gameslow; //makes the game run slower
        logiikka.setDelta(deltaspd);
        logiikka.enemyLogicUpdate();
        logiikka.playerLogicUpdate(gc);
        logiikka.projectileLogicUpdate();
        logiikka.cameraLogicUpdate(mainCamera);
        logiikka.collectibleLogicUpdate();
    }

    /**
     * Metodi on renderöintilooppi, jossa renderöidään eri asioita ruudulle
     *
     * @param gc on GameContainer, jota tarvitaan tietyissä slick2dn metodeissa
     * @param g on slick2dn Graphics-objekti, jota tarvitaan renderöinnissä
     *
     *
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        renderer.renderTheList(mainCamera);
        renderer.renderInventory(timo, g);
        renderer.renderPlayer(timo);
    }

    public Player getPlayer() {
        return this.timo;
    }

    public Input getInput() {
        return this.input;
    }

    public float getGameslow() {
        return this.gameslow;
    }

    public float getDeltaspd() {
        return this.deltaspd;
    }

    public ArrayList<GameObject> getRenderList() {
        return this.renderList;
    }

    public Camera getCamera() {
        return this.mainCamera;
    }

    public Logic getLogic() {
        return this.logiikka;
    }

    public BitzRenderer getRenderer() {
        return this.renderer;
    }

}
