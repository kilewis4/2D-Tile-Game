package src;

import java.awt.image.BufferedImage;

public class Assets
{
    private static final int width = 32, height = 32;
    public static BufferedImage dirt, grass, stone, tree, rock, wood;
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] btn_start;

    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("../res/texture/sheet_ep32.png"));

        wood = sheet.crop(width, height, width, height);

        btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);

        player_down = new BufferedImage[2];

        player_down[0] = sheet.crop(width * 4, 0, width, height);
        player_down[1] = sheet.crop(width * 5, 0, width, height);

        player_up = new BufferedImage[2];

        player_up[0] = sheet.crop(width * 6, 0, width, height);
        player_up[1] = sheet.crop(width * 7, 0, width, height);

        player_left = new BufferedImage[2];

        player_left[0] = sheet.crop(width * 6, height, width, height);
        player_left[1] = sheet.crop(width * 7, height, width, height);

        player_right = new BufferedImage[2];

        player_right[0] = sheet.crop(width * 4, height, width, height);
        player_right[1] = sheet.crop(width * 5, height, width, height);

        //player = sheet.crop(0, 0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, 0, width, height * 2);
        rock = sheet.crop(0, height * 2, width, height);
    }    
}
