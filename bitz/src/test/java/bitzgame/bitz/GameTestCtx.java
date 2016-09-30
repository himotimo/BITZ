/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitzgame.bitz;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Pyry Kontio
*/

@FunctionalInterface
interface Test {
   void runTest() throws Exception;
}

// A stateless class for signaling the "no errors or exceptions" case.
// Meant to be used as a singleton. 
class OkResult extends Throwable {
    @Override
    public boolean equals(Object o) {
        return o instanceof OkResult;
    }

    @Override
    public int hashCode() { return 0; }
}

// A class for returning an error message to signal error cases in the tests.
class ErrMsg extends Exception {
    String errmsg;
    ErrMsg(String e){
        errmsg = e;
    }
}

class GameTestCtx extends BasicGame implements Runnable {
    
    public static OkResult ok;
    public static GameTestCtx theOneContext; // Unfortunately there can be only
                                             // one per process, due to the limitations of Slick2D
    private final AppGameContainer container;
    private final BlockingQueue<Test> inputQueue;
    private final BlockingQueue<Throwable> returnQueue;

    public GameTestCtx(String title) throws SlickException {
        super(title);
            ok = new OkResult();
            container = new AppGameContainer(this);
            inputQueue = new ArrayBlockingQueue<>(1);
            returnQueue = new ArrayBlockingQueue<>(1);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        System.out.println("GameTestCtx.update: Start a new frame.");
        try {
            Test test = inputQueue.take();
            try {
                test.runTest();
                returnQueue.put(ok);
            } catch (Exception res) {
                returnQueue.put(res);
            }
            System.out.println("GameTestCtx.update: One test successfully run.");
        } catch (InterruptedException ex) {
            System.out.println("Update method: Exception: " + ex);
        } 
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
    }

    @Override
    public void run() {
        try {
            container.setDisplayMode(640, 480, false);
            container.start();

        } catch (SlickException ex) {
            System.out.println("Error: Can't initialize the Game Test Context.");
        }
    }

    public static GameTestCtx setup() throws SlickException {
        if (theOneContext == null) {
            theOneContext = new GameTestCtx("TestCtx");
            Thread t = new Thread(theOneContext);
            t.start();
            System.out.println("Initialized the Game Test Context.");
        }
        return theOneContext;
    }
    
    public Throwable runInLoop(Test test) {
        try {
            inputQueue.put(test);
            return returnQueue.take();
        } catch (InterruptedException e) {
            System.out.println("runInLoop method: Error while sending the test to another thread, trying again: " + e);
            return this.runInLoop(test);
        }
    }
}
