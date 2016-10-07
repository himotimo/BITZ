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
public class EnemyTest {
    
    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }
    
    
    
    @Test
    public void enemyTest() {
        Throwable result = gameLoop.runInLoop(() -> {
            try {
               Enemy enemy = new Enemy((float)0,(float)0,"src/assets/spr_enemy1.png");
               Input i = new Input(480);
               Player p = new Player(50,50,"src/assets/spr_char1.png",0,i);
               enemy.moveToPlayer(p, (float)3);
               int x = enemy.getDirection();
               enemy.setHealth((int)9);
               x = enemy.getHealth();
               enemy.addToHealth((int)9);
               
            } catch (Exception E){
                throw new ErrMsg("");
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
}
