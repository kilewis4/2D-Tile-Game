package src;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Creature
{
    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 62;
        bounds.height = 62;
    }     

    public void tick()
    {
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
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);

        g.setColor(Color.black);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffset()),
                    (int) (y + bounds.y - handler.getGameCamera().getYOffset()),
                    bounds.width, bounds.height);
    }
} 
