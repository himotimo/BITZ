/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Timo
 */
public class CollectibleTest {

    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }

    @org.junit.Test
    public void collectibleTest() {
        ErrMsg err = new ErrMsg("Wrong type arg");
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                Collectible c = new Collectible((float)0.0,(float) 0.0, "src/assets/spr_enemy1.png", "ee");
                
            } catch (Exception E) {
                throw err;
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }

}
