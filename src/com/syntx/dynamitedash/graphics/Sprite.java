package com.syntx.dynamitedash.graphics;

public class Sprite {

	public final int SIZE; // Size of the specific sprite 
	private int x, y; // The position of the sprite in the sprite sheet
	private int width, height; // Width and height of sprite
	public int[] pixels;  
	protected SpriteSheet sheet; // Which sprite sheet the sprite is located in
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);  // The grass tile
	public static Sprite box = new Sprite(16, 1, 0, SpriteSheet.tiles);  // The box tile
	public static Sprite stone_wall = new Sprite(16, 2, 0, SpriteSheet.tiles);  // The stone wall tile
	public static Sprite stone_floor = new Sprite(16, 3, 0, SpriteSheet.tiles);  // The stone floor tile
	public static Sprite gate = new Sprite(16, 4, 0, SpriteSheet.tiles);  // The gate tile
	public static Sprite voidSprite = new Sprite(16, 0x1266ed);
		
	//Level One sprites here:
	
	public static Sprite one_grass = new Sprite(16, 0, 0, SpriteSheet.level1);
	public static Sprite one_box = new Sprite(16, 1, 0, SpriteSheet.level1);
	public static Sprite one_stone_wall = new Sprite(16, 2, 0, SpriteSheet.level1);
	public static Sprite one_stone_floor = new Sprite(16, 0, 1, SpriteSheet.level1);
	public static Sprite one_door = new Sprite(16, 1, 1, SpriteSheet.level1);
	
	
	// Each of the players animation sprites
	public static Sprite player_down = new Sprite(32, 0, 6, SpriteSheet.tiles);  // The main player 1 sprite facing down
	public static Sprite player_up = new Sprite(32, 3, 6, SpriteSheet.tiles);    // The main player 1 sprite facing up
	public static Sprite player_left = new Sprite(32, 2, 6, SpriteSheet.tiles);  // The main player 1 sprite facing left
	public static Sprite player_right = new Sprite(32, 1, 6, SpriteSheet.tiles); // The main player 1 sprite facing right
	
	public static Sprite player2_down = new Sprite(32, 4, 6, SpriteSheet.tiles);  // The main player 1 sprite facing down
	public static Sprite player2_up = new Sprite(32, 7, 6, SpriteSheet.tiles);    // The main player 1 sprite facing up
	public static Sprite player2_left = new Sprite(32, 6, 6, SpriteSheet.tiles);  // The main player 1 sprite facing left
	public static Sprite player2_right = new Sprite(32, 5, 6, SpriteSheet.tiles); // The main player 1 sprite facing right
	
	public static Sprite player3_down = new Sprite(32, 4, 7, SpriteSheet.tiles);  // The main player 1 sprite facing down
	public static Sprite player3_up = new Sprite(32, 7, 7, SpriteSheet.tiles);  // The main player 1 sprite facing down
	public static Sprite player3_left = new Sprite(32, 6, 7, SpriteSheet.tiles);  // The main player 1 sprite facing down
	public static Sprite player3_right = new Sprite(32, 5, 7, SpriteSheet.tiles);  // The main player 1 sprite facing down
	
	// Projectile sprites here:
	public static Sprite fireball_enemy1 = new Sprite(16, 0, 0, SpriteSheet.projectile_enemy1);
	
	// Particle sprites here:
	public static Sprite particle_grey = new Sprite(3, 0x898782);
	public static Sprite particle_fire1 = new Sprite(3, 0xe83e26);
	public static Sprite particle_fire2 = new Sprite(3, 0xeb7737);
	public static Sprite blood = new Sprite(3, 0xff0000);
	
	// Powerups sprites here:
	public static Sprite speed_boost = new Sprite(16, 0, 0, SpriteSheet.powerups);
	public static Sprite health_potion = new Sprite(16, 1, 0, SpriteSheet.powerups);
	public static Sprite mega_bomb = new Sprite(16, 2, 0, SpriteSheet.powerups);
	public static Sprite extra_bomb = new Sprite(16, 3, 0, SpriteSheet.powerups);
	public static Sprite place_rate = new Sprite(16, 4, 0, SpriteSheet.powerups);
	public static Sprite ghost = new Sprite(16, 5, 0, SpriteSheet.powerups);
	public static Sprite clone = new Sprite(16, 6, 0, SpriteSheet.powerups);
	public static Sprite boxPowerup = new Sprite(16, 0, 1, SpriteSheet.powerups);
	
	// Dragon sprites here:
//	public static Sprite dragonWingUp = new Sprite(32, 0, 0, SpriteSheet.dragon);
//	public static Sprite dragonWingDown = new Sprite(32, 0, 1, SpriteSheet.dragon);
//	public static Sprite dragonBreathe = new Sprite(32, 0, 2, SpriteSheet.dragon);
	public static Sprite dragonWingUp = new Sprite(64, 0, 0, SpriteSheet.dragon3);
	public static Sprite dragonWingDown = new Sprite(64, 0, 1, SpriteSheet.dragon3);
	public static Sprite dragonBreathe = new Sprite(64, 0, 2, SpriteSheet.dragon3);

	// Dummy sprites here:
	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);
	public static Sprite dummydeath = new Sprite(32, 0, 0, SpriteSheet.dummy_down);
		
	
	// Dynamite sprites here:
	public static Sprite dynamite = new Sprite(16, 0, 0, SpriteSheet.dynamite);
	public static Sprite explosion = new Sprite(16, 0, 4, SpriteSheet.dynamite);
	
	
	protected Sprite(SpriteSheet sheet, int width, int height) {  // Used for animated sprites
		SIZE = (width == height) ? width : -1; // Sets the size to width if the sprite-sheet is a square or sets size to -1 to avoid errors
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {   // Sprite of a specific size, loaded from a spritesheet
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE*SIZE]; // Array of pixels that is the size of the sprite
		this.x = x * SIZE; 
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	} 
	
	public Sprite(int width, int height, int colour) {   // Sprite of specific shape and colour
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		setColour(colour);
		
	}
	
	public Sprite(int size, int colour) {   // Sprite of specific size and colour
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	public Sprite(int[] pixels, int width, int height) {  // Creates a sprite from an array of pixels
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for(int i = 0; i < pixels.length; i++) { // Manually adds the values inside the provided array to the current array
			this.pixels[i] = pixels[i];
		}
	}
	
	public static Sprite[] split(SpriteSheet sheet) { // Splits a sprite-sheet into an array of sprites
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT); // Amount of sprites in the sprite sheet
		Sprite[] sprites = new Sprite[amount]; // The array of sprites
		int current = 0; // Keeps track of which sprite is being created
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT]; // An array of the pixels of one sprite
		for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++){ // Loops through each individual sprite 
			for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) { 
				
				for(int y = 0; y < sheet.SPRITE_HEIGHT; y++) { // Loops through the pixels of the current sprite
					for(int x = 0; x < sheet.SPRITE_WIDTH; x++) {
						int xo = x + xp * sheet.SPRITE_WIDTH; // How much the pixel needs to be offset by in the x axis to reach the current sprite
						int yo = y + yp * sheet.SPRITE_HEIGHT; // How much the pixel needs to be offset by in the y axis to reach the current sprite
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()]; // Sets the pixels array to the current sprites pixels in the sprite-sheet 
							
					}
				}
				
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT); // Creates a new sprite with the newly populated pixels array and adds it to the sprites array   
			}
		}
		return sprites; // Returns the array of sprites

	}

	private void setColour(int colour){  // Sets each pixel of a sprite to a specific colour
		for(int i = 0; i < width*height; i++) {
			pixels[i] = colour;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void load() { // loops through the individual pixels of the sprite  and sets them  to the pixels that are in the sprite sheet  
		for(int y = 0; y < SIZE; y++) {   
			for(int x = 0; x < SIZE; x++) {
				pixels[x+y*width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];		
			}
		}
	}
	
}
