/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Timo
 */
public class PlayerTest {

    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }

    @Test
    public void argTest() {
        ErrMsg err = new ErrMsg("Wrong type arg");
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                ArrayList<GameObject> list = new ArrayList<GameObject>();
                Input in = new Input(480);
                Player p = new Player(0, 0, "src/assets/spr_char1.png", 0, in);
                p.setTryShoot(true);
                p.tryShoot((float) 0.5);
                Projectile pr = p.shoot((float) 0.5);
                p.move((float) 0.5);

            } catch (Exception E) {
                throw err;
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
}
