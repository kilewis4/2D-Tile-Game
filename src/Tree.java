package src;

import java.awt.Graphics;

public class Tree extends StaticEntity
{
    public Tree(Handler handler, float x, float y)
    {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getYOffset()), (int) (y - handler.getGameCamera().getYOffset()), width, height, null);
    }
}
