package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature
{
    private Animation animDown;
    private Animation animUp;
    private Animation animLeft;
    private Animation animRight;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 22;
        bounds.y = 30;
        bounds.width = 20;
        bounds.height = 30;

        //Animations
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);

        inventory = new Inventory(handler);
    }     

    public void tick()
    {
        //Animations
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();
    }

    private void checkAttacks()
    {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
        {
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp)
        {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }
        else if(handler.getKeyManager().aDown)
        {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }
        else if(handler.getKeyManager().aLeft)
        {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }
        else if(handler.getKeyManager().aRight)
        {
            ar.x = cb.x + arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }
        else
        {
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this))
            {
                continue;
            }
            if(e.getCollisionBounds(0, 0).intersects(ar))
            {
                e.hurt(1);
                return;
            }
        }
    }

    public void die()
    {
        System.out.println("You lose");   
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
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getXOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
        inventory.render(g);

        //g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getXOffset()),
		//		(int) (y + bounds.y - handler.getGameCamera().getXOffset()),
		//		bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0)
        {
            return animLeft.getCurrentFrame();
        }
        else if(xMove > 0)
        {
            return animRight.getCurrentFrame();
        }
        else if(yMove < 0)
        {
            return animUp.getCurrentFrame();
        }
        else
        {
            return animDown.getCurrentFrame();
        }
    }

    public Inventory getInventory()
    {
        return inventory;
    }
} 
