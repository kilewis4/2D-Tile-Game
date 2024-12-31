package src;

import java.awt.Graphics;

public class World
{
    private int width, height;
    private int[][] tiles;
    
    public World(String path)
    {
        loadworld(path);
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
    }

    public Tile getTile(int x, int y)
    {
        Tile t = Tile.tiles[tiles[x][y]];

        if(t == null)
        {
            return Tile.dirtTile;
        }

        return t;
    }

    private void loadworld(String path)
    {
        
    }
}
