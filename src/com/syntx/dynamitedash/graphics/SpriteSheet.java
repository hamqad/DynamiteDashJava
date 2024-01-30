package com.syntx.dynamitedash.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;  // The file path of the sprite sheet
	public final int SIZE; // Size of the sprites
	public final int SPRITE_WIDTH, SPRITE_HEIGHT; // The width and height of the spritesheet
	private int width, height; // The width and height of the spritesheet
	public int[] pixels;  //Array of each pixels in the sprite sheet
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheetnew.png", 256);
	public static SpriteSheet level1 = new SpriteSheet("/textures/sheets/level1.png", 48);
	public static SpriteSheet projectile_enemy1 = new SpriteSheet("/textures/sheets/projectiles/enemy1.png", 48);
	public static SpriteSheet powerups = new SpriteSheet("/textures/sheets/powerups/powerups.png", 160, 64 );
	public static SpriteSheet dynamite = new SpriteSheet("/textures/sheets/dynamite/dynamite.png", 16, 48);
	public static SpriteSheet dynamite_animation = new SpriteSheet(dynamite, 0, 0, 1, 5, 16);
	
	// Mobs:
	public static SpriteSheet player1 = new SpriteSheet("/textures/sheets/player1.png", 160, 128);  // Spritesheet of player1
	public static SpriteSheet player2 = new SpriteSheet("/textures/sheets/player2.png", 128, 96);  // Spritesheet of player2
	public static SpriteSheet player3 = new SpriteSheet("/textures/sheets/player3.png", 128, 96);  // Spritesheet of player3
	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/dummy.png", 128, 96); // Spritesheet for the first enemy mob, the dummy
	public static SpriteSheet dummyDeath = new SpriteSheet("/textures/sheets/dummy_death.png", 128, 96); // Spritesheet for the first enemy mob, the dummy
	public static SpriteSheet wizard = new SpriteSheet("/textures/sheets/wizard.png", 128, 96); // Spritesheet for the first enemy mob, the dummy
	public static SpriteSheet zombie = new SpriteSheet("/textures/sheets/zombie.png", 96, 96); // Spritesheet for the zombie
	public static SpriteSheet dragon = new SpriteSheet("/textures/sheets/dragon2.png", 256);
	public static SpriteSheet dragon3 = new SpriteSheet("/textures/sheets/dragon3.png", 300, 192);
	
	
	
	// Holds the sub-spritesheets of each players direction:
	
	// Player 1:
	public static SpriteSheet player_down = new SpriteSheet(player1, 0, 0, 1, 3, 32);
	public static SpriteSheet player_up = new SpriteSheet(player1, 1, 0, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(player1, 2, 0, 1, 3, 32);
	public static SpriteSheet player_right = new SpriteSheet(player1, 3, 0, 1, 3, 32);
	public static SpriteSheet player_hurt = new SpriteSheet(player1, 4, 0, 1, 3, 32);
	
	// Player 2:
	public static SpriteSheet player2_down = new SpriteSheet(player2, 0, 0, 1, 3, 32);
	public static SpriteSheet player2_up = new SpriteSheet(player2, 1, 0, 1, 3, 32);
	public static SpriteSheet player2_left = new SpriteSheet(player2, 2, 0, 1, 3, 32);
	public static SpriteSheet player2_right = new SpriteSheet(player2, 3, 0, 1, 3, 32);
	public static SpriteSheet player2_hurt = new SpriteSheet(player1, 4, 0, 1, 3, 32);
	
	// Player 3:
	public static SpriteSheet player3_down = new SpriteSheet(player3, 0, 0, 1, 3, 32);
	public static SpriteSheet player3_up = new SpriteSheet(player3, 1, 0, 1, 3, 32);
	public static SpriteSheet player3_left = new SpriteSheet(player3, 2, 0, 1, 3, 32);
	public static SpriteSheet player3_right = new SpriteSheet(player3, 3, 0, 1, 3, 32);
	public static SpriteSheet player3_hurt = new SpriteSheet(player1, 4, 0, 1, 3, 32);
		
		
	// Holds the sub-spritesheets of each direction of the dummy mob:
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);
	public static SpriteSheet dummy_dead = new SpriteSheet(dummyDeath, 0, 0, 1, 2, 32);
	
	// Holds the sub-spritesheets of each direction of the wizard mob:
	public static SpriteSheet wizard_right = new SpriteSheet(wizard, 0, 0, 1, 1, 32);
	public static SpriteSheet wizard_left = new SpriteSheet(wizard, 1, 0, 1, 1, 32);
	
	// Holds the sub-spritesheets of each direction of the zombie mob:
	public static SpriteSheet zombie_down = new SpriteSheet(zombie, 0, 0, 1, 3, 32);
	public static SpriteSheet zombie_up = new SpriteSheet(zombie, 1, 0, 1, 3, 32);
	public static SpriteSheet zombie_hurt = new SpriteSheet(zombie, 2, 0, 1, 1, 32);
	
		
	
	
	private Sprite[] sprites; // An array of sprites used for animations
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {  // Used for making sub sprite sheets from an existing spritesheet
		int xx = x * spriteSize;   
		int yy = y * spriteSize;
		int w = width * spriteSize;  // turns the width relative to sprite position into pixel precision
		int h = height * spriteSize; // turns the width relative to sprite position into pixel precision
		if(width == height) SIZE = width;
		else SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for(int y0 = 0; y0 < h; y0++ ){  // Extracts the pixels needed from the section of the spritesheet specified
			int yp = yy + y0; 
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		
		int frame = 0;  // Keeps track of the current frame
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {    // Loops through each individual sprite in the spritesheet (Sprite precision)  
			for(int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize]; // An array of pixels that holds each individual sprites pixels 
				for(int y0 = 0; y0 < spriteSize; y0++) {  // Loops through each of the sprites pixels (pixel precision)
					for(int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize)*SPRITE_WIDTH]; // populates the sprites pixel array with the pixels of the sprites 
						
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);  // Creates a sprite from the sprite pixel array dependent on the loops position inside the spritesheet  
				sprites[frame++] = sprite; // Adds the sprite that was just created into the sprites array at the current frames position and increments the frame each time 
			}
		}
		
	}
	
	public SpriteSheet(String path, int size) {  // Used for loading a square sized sprite-sheet
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		pixels = new int[SIZE * SIZE];  // sets the array size to the size of the sprite sheet
		load(); 
	}
	
	public SpriteSheet(String path, int width, int height) {  // Used for loading sprite-sheets of any shape       
		this.path = path;
		SIZE = -1; 	 
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];  // sets the array size to the size of the sprite sheet
		load(); 
	}
	
	public Sprite[] getSprites() {  // returns the current sprite array
		return sprites;
	}
	
	public int getWidth() { // Returns the width of the spritesheet
		return width;
	}
	
	public int getHeight() { // Returns the height of the spritesheet
		return height;
	}
	
	public int[] getPixels() { // Returns the pixels array 
		return pixels;
	}
	
	private void load() {   //loads sprite sheet
		try {
 			System.out.print("Trying to load:" + path + " ....");
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));  // reads image from the path
			System.out.println(" Success!");
			width = image.getWidth();   
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);  // sets each pixel of the loaded image into the pixels array
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(" Failed!");			
		}
		
	}
	
	
}
