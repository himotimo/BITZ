/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Timo
 */
class GameTestCtx extends BasicGame implements Runnable {

    public GameTestCtx(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
    }
    
    Function<Integer, Integer> nextTest;

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (MainTest.doIt) {
            Integer result = GameTestCtx.nextTest(new Integer(0));
            MainTest.doIt = false;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
    }

    @Override
    public void run() {
        try {
            GameTestCtx game = new GameTestCtx("Simple Slick Game");
            MainTest.container = new AppGameContainer(game);
            MainTest.container.setDisplayMode(640, 480, false);
            MainTest.container.start();

        } catch (SlickException ex) {
        }
    }

}

public class MainTest {

    public MainTest() {
    }

    static AppGameContainer container;

    @BeforeClass
    public static void setUpClass() {
        doIt = false;
        (new Thread(new GameTestCtx("TestCtx"))).start();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Main.
     */
    static boolean doIt;
    public static Function<Integer, Integer> nextTest;

    @Test
    public void imageTest() {
        nextTest = (input) -> {
            try {
                Image test = new Image("src/assets/spr_item_crest.png");
                assertNotEquals("joo", test, null);
            } catch (Exception e) {
                System.out.println("caught");
            }
            return 1;
        };
        doIt = true;
    }


}
