/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Supplier;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Image;
//import org.newdawn.slick.Input;

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
    /*
    @Test
    public void gameObjectRenderableTest1() throws InterruptedException {
        // NOTE to Timo: Do NOT use assertions inside the () -> { test }! Only catch errors and return them.
        GameTestCtx.inputQueue.put(() -> {
            try {
                GameObject g = new GameObject(0,0);
                Camera c = new Camera(0,0);
                if(!g.renderable(c)){
                    throw new Exception();
                } 
                return GameTestCtx.ok;
            } catch (Exception e) {
                return e;
            }
        });
        Throwable ex = GameTestCtx.returnQueue.take();
        assertEquals("Did it work?", ex, GameTestCtx.ok);
    }*/
    
    /*@Test
    public void gameObjectCollidesWithTest() throws InterruptedException {
        // NOTE to Timo: Do NOT use assertions inside the () -> { test }! Only catch errors and return them.
        GameTestCtx.inputQueue.put(() -> {
            try {
                GameObject g = new GameObject(0,0);
                GameObject gg = new GameObject(1,1);
                if(g.collidesWith(gg)){
                    return GameTestCtx.ok;
                } else {
                    return new ErrMsg("Doesnt collide");
                }
            } catch (Exception e) {
                return e;
            }
        });
        Throwable ex = GameTestCtx.returnQueue.take();
        assertEquals("Did it work?", ex, GameTestCtx.ok);
    }*/
    
    /*@Test
    public void gameObjectCollidesAnyTest() throws InterruptedException {
        // NOTE to Timo: Do NOT use assertions inside the () -> { test }! Only catch errors and return them.
        GameTestCtx.inputQueue.put(() -> {
            try {
                GameObject g = new GameObject(0,0);
                GameObject gg = new GameObject(1,1);
                Projectile p = new Projectile(15,15,"src/assets/spr_item_crest.png",3);
                ArrayList<GameObject> list = new ArrayList<GameObject>();
                list.add(gg);
                list.add(p);
                if(g.collidesAny(list)!=null){
                    return GameTestCtx.ok;
                } else {
                    return new ErrMsg("Doesn't collide with anything");
                }
            } catch (Exception e) {
                return e;
            }
        });
        Throwable ex = GameTestCtx.returnQueue.take();
        assertEquals("Did it work?", ex, GameTestCtx.ok);
    }*/
    
    /*@Test
    public void playerShootTest() throws InterruptedException {
        // NOTE to Timo: Do NOT use assertions inside the () -> { test }! Only catch errors and return them.
        GameTestCtx.inputQueue.put(() -> {
            try {
                Input i = new Input(480);
                Player p = new Player(0,0,"src/assets/spr_item_crest.png",5,i);
                p.setTryShoot(true);
                if (p.shoot(2) != null) {
                    return GameTestCtx.ok;
                } else {
                    return new ErrMsg("Nothing shot");
                }
                
            } catch (Exception e) {
                return e;
            }
        });
        Throwable ex = GameTestCtx.returnQueue.take();
        assertEquals("Did it work?", ex, GameTestCtx.ok);
    }*/
    
    
    

}
