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
public class InventoryTest {
    
    GameTestCtx gameLoop;

    @Before
    public void setUp() throws SlickException {
        gameLoop = GameTestCtx.setup();
    }
    
    
    
    @Test
    public void enemyTest() {
        Throwable result = gameLoop.runInLoop(() -> {
            try {
               Inventory i = new Inventory();
               Collectible[] col = i.getBag();
               int in = i.getSize();
               i.put(new Collectible(0,0,"src/assets/spr_enemy1.png","item"));
               Collectible c = i.getItem("item");
               boolean bool = i.isFull();               
            } catch (Exception E){
                throw new ErrMsg("");
            }
        });
        assertEquals("Did it work?", gameLoop.ok, result);
    }
    
}
