package src;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Creature
{
    private Animation animDown;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 22;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 30;

        //Animations
        animDown = new Animation(500, Assets.player_down);
    }     

    public void tick()
    {
        //Animations
        animDown.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
        {
            yMove = -speed;
        }

        if(handler.getKeyManager().down)
        {
            yMove = speed;
        }

        if(handler.getKeyManager().left)
        {
            xMove = -speed;
        }

        if(handler.getKeyManager().right)
        {
            xMove = speed;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(animDown.getCurrentFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);

        g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffset()),
				(int) (y + bounds.y - handler.getGameCamera().getXOffset()),
				bounds.width, bounds.height);
    }
} 
