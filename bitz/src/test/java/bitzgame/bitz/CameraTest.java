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
public class CameraTest {

    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }

    @Test
    public void cameraTest() {
        ErrMsg err = new ErrMsg("Wrong type arg");
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                Camera c = new Camera(0,0);
                float f = c.getX();
                f = c.getY();
                
            } catch (Exception E) {
                throw err;
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }

}
