package bitzgame.bitz;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

public class Main extends BasicGame
{

    private Player timo;
    private Input input;
    private double gameslow;
    private double deltaspd;
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
            timo = new Player(0,0, "src/assets/spr_char1.png");
            input = new Input(480); //
            gameslow = 4; //the bigger value the slower game is. used to divide delta.
        }
        
        public int moveRight(){
            if(input.isKeyDown(Input.KEY_D)){
                return 1;
            }
            return 0;
        }
        
        public int moveLeft(){
            if(input.isKeyDown(Input.KEY_A)){
                return -1;
            }
            return 0;
        }
        
        public int moveUp(){
            if(input.isKeyDown(Input.KEY_W)){
                return -1;
            }
            return 0;
        }
        
        public int moveDown(){
            if(input.isKeyDown(Input.KEY_S)){
                return 1;
            }
            return 0;
        }

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
            deltaspd = delta;
            deltaspd/=gameslow; //makes running slower
            timo.moveX(deltaspd*moveLeft()+deltaspd*moveRight());  //move timo
            timo.moveY(deltaspd*moveUp()+deltaspd*moveDown());
            
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
            timo.getSprite().draw((float)timo.getX(),(float)timo.getY());
            //newimg.draw(x_pos, y_pos);
		//newimg.draw(gc.getInput().getMouseX(), gc.getInput().getMouseY());
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Simple Slick Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
