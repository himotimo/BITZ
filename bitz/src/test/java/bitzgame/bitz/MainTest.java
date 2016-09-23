/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Supplier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Image;

/**
 *
 * @author Timo
 */

public class MainTest {

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        GameTestCtx.setup();
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
    @Test
    public void imageTest() throws InterruptedException {
        // NOTE to Timo: Do NOT use assertions inside the () -> { test }! Only catch errors and return them.
        GameTestCtx.inputQueue.put(() -> {
            try {
                Image test = new Image("src/assets/spr_item_crest.png");
                if (test == null) {
                    throw new Exception();
                }
                return GameTestCtx.ok;
            } catch (Exception e) {
                return e;
            }
        });
        Throwable ex = GameTestCtx.returnQueue.take();
        assertEquals("Did it work?", ex, GameTestCtx.ok);
    }
    
    @Test
    public void test1() throws InterruptedException {
        // NOTE to Timo: Do NOT use assertions inside the () -> { test }! Only catch errors and return them.
        GameTestCtx.inputQueue.put(() -> {
            try {
                GameObject g = new GameObject(0,0);
                Camera c = new Camera(0,0);
                if(g.renderable(c)){
                    return GameTestCtx.ok;
                } else {
                    return new ErrMsg("ei kameran sisällä");
                }
            } catch (Exception e) {
                return e;
            }
        });
        Throwable ex = GameTestCtx.returnQueue.take();
        assertEquals("Did it work?", ex, GameTestCtx.ok);
    }

}
