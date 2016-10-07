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
public class BitzRendererTest {
    
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
                BitzRenderer b = new BitzRenderer(new ArrayList<GameObject>());
                ArrayList<GameObject> a = b.getRenderList();
            } catch (Exception E){
                throw err;
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
    @Test
    public void renderTest() {
        ErrMsg err = new ErrMsg("couldnt render");
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                BitzRenderer b = new BitzRenderer(new ArrayList<GameObject>());
                GameObject g = new GameObject(0,0, "src/assets/spr_enemy1.png");
                GameObject gg = new GameObject(0,0, "src/assets/spr_enemy1.png");
                GameObject ggg = new GameObject(0,0, "src/assets/spr_enemy1.png");
                b.getRenderList().add(g);
                b.getRenderList().add(gg);
                b.getRenderList().add(ggg);
                Camera mainCamera = new Camera(0,0);
                b.renderTheList(mainCamera);
            } catch (Exception E){
                throw err;
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
    
    
    
    
}
