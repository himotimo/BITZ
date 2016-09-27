/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.Input;

/**
 *
 * @author Timo
 */

public class MainTest {

    public MainTest() {
    }
    
    // Unfortunately it seems that Slick2D supports only one game window per process.
    // This means that this field can be static as well.
    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void imageTest() {
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                Image test = new Image("src/assets/spr_item_crest.png");
                return new OkResult();
            } catch (Exception e) {
                return e;
            }
        });
        assertEquals("Did it work?", new OkResult(), result);
    }
}
