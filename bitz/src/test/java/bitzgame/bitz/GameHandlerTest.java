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
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Timo
 */
public class GameHandlerTest {
    
    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }
    
    
    
    @Test
    public void initTest() {
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                GameHandler gh = new GameHandler();
                AppGameContainer appgc = new AppGameContainer(new Main("Simple Slick Game"));
                gh.init(appgc);
            } catch (Exception E){
                throw new ErrMsg("");
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
    @Test
    public void initTest2() {
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                GameHandler gh = new GameHandler();
                AppGameContainer appgc = new AppGameContainer(new Main("Simple Slick Game"));
                gh.init(appgc);
                Player timo = gh.getPlayer();
                Input i = gh.getInput();
                float f = gh.getGameslow();
                float ff = gh.getDeltaspd();
                ArrayList<GameObject> a = gh.getRenderList();
                Camera c = gh.getCamera();
                Logic l = gh.getLogic();
                BitzRenderer r = gh.getRenderer();
            } catch (Exception E){
                throw new ErrMsg("");
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
    
    
    
    @Test
    public void updateWithoutInitTest() {
        ErrMsg err = new ErrMsg("Couldnt update without init");
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                GameHandler gh = new GameHandler();
                AppGameContainer appgc = new AppGameContainer(new Main("Simple Slick Game"));
                gh.update(appgc, 4);
            } catch (Exception E){
                throw err;
            }
        });
        assertEquals("Did it work?", err, result);
    }
    
    @Test
    public void updateWithBadDeltaTest() {
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                GameHandler gh = new GameHandler();
                AppGameContainer appgc = new AppGameContainer(new Main("Simple Slick Game"));
                gh.init(appgc);
                gh.update(appgc, -5);
            } catch (Exception E){
                throw new ErrMsg("");
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
    @Test
    public void renderWithoutInitTest() {
        ErrMsg err = new ErrMsg("Couldnt render without init");
        Throwable result = gameLoop.runInLoop(() -> {
            try {
                GameHandler gh = new GameHandler();
                AppGameContainer appgc = new AppGameContainer(new Main("Simple Slick Game"));
                gh.render(null, null);
            } catch (Exception E){
                throw err;
            }
        });
        assertEquals("Did it work?", err, result);
    }
    
}
