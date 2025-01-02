package src;

import java.awt.Graphics;

public class World
{
    private Game game;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    
    public World(Game game, String path)
    {
        this.game = game;
        loadworld(path);
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        int xStart = (int) Math.max(0, game.getGameCamera().getXOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (game.getGameCamera().getXOffset() + game.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, game.getGameCamera().getYOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (game.getGameCamera().getYOffset() + game.getHeight()) / Tile.TILEHEIGHT + 1);;

        for(int y = yStart; y < yEnd; y++)
        {
            for(int x = xStart; x < xEnd; x++)
            {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - game.getGameCamera().getXOffset()), 
                                    (int) (y * Tile.TILEHEIGHT - game.getGameCamera().getYOffset()));
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
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
}
