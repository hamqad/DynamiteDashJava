package com.syntx.dynamitedash.graphics;

import java.util.Random;

import com.syntx.dynamitedash.entity.mob.Chaser;
import com.syntx.dynamitedash.entity.mob.ChaserPro;
import com.syntx.dynamitedash.entity.mob.Mob;
import com.syntx.dynamitedash.entity.projectile.Projectile;
import com.syntx.dynamitedash.level.tile.Tile;

public class Screen {

	public int width, height; // The dimensions of the Screen
	public int[] pixels; // holds the data of each pixel of the screen
	public final int MAP_SIZE = 64; // Size of the map
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	private final int ALPHA_COL = 0xffff00d8;

	public int xOffset, yOffset; // The dimensions of the displacement of the screen

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // The array that holds each tile

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff); // sets each individual tile to a random colour
		}
	}

	public void clear() { // makes the pixels array equal to 0 to clear the screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) { // Renders a sprite-sheet, for testing purposes
		if (fixed) { // if the position of the screen is fixed, sets the offset of the screen
			xp -= xOffset; // The position of the tile relative to the maps position
			yp -= yOffset;
		}

		for (int y = 0; y < sheet.SPRITE_WIDTH; y++) { // Loops through each pixel of the sprite-sheet and outputs them to the screen
			int ya = y + yp;
			for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue ; // Checks if the screens pixel is out of the bounds of the screen
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
			}
		}
		
	}

	public void renderTextCharacter(int xp, int yp, Sprite sprite, int colour, boolean fixed) { // esponsible for rendering text
		if (fixed) { // if the position of the screen is fixed, sets the offset of the screen
			xp -= xOffset; // The position of the tile relative to the maps position
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) { // Loops through each pixel of the sprite and outputs them to the screen
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue ; // Checks if the screens pixel is out of the bounds of the screen
				int col = sprite.pixels[x + y * sprite.getWidth()]; // Colour of current pixel of sprite 
				if(col != 0xFFFF00FF && col != 0xFF7F007F) pixels[xa + ya * width] = colour; // Sets the pixels to the sprites pixels if the colour is not equal to the alpha 
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) { // responsible for rendering sprite
		if (fixed) { // if the position of the screen is fixed, sets the offset of the screen
			xp -= xOffset; // The position of the tile relative to the maps position
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) { // Loops through each pixel of the sprite and outputs them to the screen
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue ; // Checks if the screens pixel is out of the bounds of the screen
				int col = sprite.pixels[x + y * sprite.getWidth()]; // Colour of current pixel of sprite 
				if(col != 0xFFFF00FF && col != 0xFF7F007F && col != ALPHA_COL) pixels[xa + ya * width] = col; // Sets the pixels to the sprites pixels if the colour is not equal to the alpha 
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile) { // Renders tile
		xp -= xOffset; // The position of the tile relative to the maps position
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) { // loops through each pixel and sets them to the tiles pixels
			int ya = y + yp; // sets the absolute y position equal to y + the offset of y
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp; // same as above but for x
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break; // Checks weather the pixels are out of the bounds of the screen
				if (xa < 0)
					xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) { // Renders projectile
		xp -= xOffset; // The position of the sprites relative to the maps position
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) { // loops through each pixel and sets them to the sprite's pixels
			int ya = y + yp; // sets the absolute y position equal to y + the offset of y
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp; // same as above but for x
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height)
					break; // Checks weather the pixels are out of the bounds of the screen
				if (xa < 0)
					xa = 0;
				int col = p.getSprite().pixels[x + y * 16];
				if (col != ALPHA_COL) {
					pixels[xa + ya * width] = p.getSprite().pixels[x + y * p.getSpriteSize()];
				}
			}
		}
	}

	public void renderMob(int xp, int yp, Sprite sprite, int flip) {  // Renders a mob
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) { // loops through each pixel and sets them to the sprite's pixels
			int ya = y + yp; // sets the absolute y position equal to y + the offset of y
			int ys = y;
			if (flip == 2 || flip == 3) ys = 31 - y;   // Flips the sprite horizontally
			for (int x = 0; x < 32; x++) {
				int xa = x + xp; // same as above but for x
				int xs = x;
				if (flip == 1 || flip == 3) xs = 31 - x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break; // Checks weather the pixels are out of the bounds of the screen
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * 32];
				if (col != ALPHA_COL) pixels[xa + ya * width] = col; // Sets the pixels of the player sprite to the pxiels of the screen if the colour is not equal to a specific colour.
			}
		}
	}
	
	
	public void renderMob(int xp, int yp, Mob mob) { // Renders a mob
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) { // loops through each pixel and sets them to the sprite's pixels
			int ya = y + yp; // sets the absolute y position equal to y + the offset of y
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp; // same as above but for x
				int xs = x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break; // Checks weather the pixels are out of the bounds of the screen
				if (xa < 0) xa = 0;
				int col = mob.getSprite().pixels[xs + ys * 32];
				if ((mob instanceof Chaser) && col == 0xFFFF0800) col = 0xFF070FFF; // Sets the colour of the mob to a blue colour if its a chaser
				//if ((mob instanceof ChaserPro) && col == 0xFFFF0800) col = 0xFFFFFF00; // Sets the colour of the mob to a blue colour if its a chaser
				if (col != ALPHA_COL) pixels[xa + ya * width] = col; // Sets the pixels of the player sprite to the pxiels of the screen if the colour is not equal to a specific colour.
			}
		}
	}

	public void drawRect(int xp, int yp, int width, int height,int colour, boolean fixed) { // Draws a rectangle to the screen
		for(int x = xp; x < xp + width; x++) { // Draws two line the size of width, one starting from xp and yp and one in the same position but offset downwards by height
			if (x < 0 || x >= this.width || yp >=  this.height) continue; // Skips the iteration of the for loop if top lines position variables are out of bounds
			if (yp > 0) pixels[x + yp * this.width] = colour; // The top horizontal line
			if(yp + height >= this.height) continue; // Skips the iteration of the for loop if bottom lines position variables are out of bounds
			if (yp + height > 0) pixels[x + (yp + height) * this.width] = colour; // The bottom horizontal line
		}
		for (int y = yp; y <= yp + height; y++) { // Draws two lines the size of height, one starting from xp and yp and one in the same position but offset to the right by width
			if (xp >= this.width || y < 0 || y >= this.height) continue; // Skips the iteration of the for loop if the left lines position variables are out of bounds
			if (xp > 0) pixels[yp + y * this.width] = colour; // The left vertical line
			if(xp + width >= this.width) continue; // Skips the iteration of the for loop if the left lines position variables are out of bounds
			if(xp + width > 0) pixels[(yp + width) + y * this.width] = colour;	// The right vertical line
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}


}

/*
 * counter++; if(counter % 100 == 0) xtime--; if(counter % 100 == 0) ytime--;
 * 
 * for(int y = 0; y > height; y++) { if(ytime < 0 || ytime > height) break; for (int x = 0; x < width; x ++) { if(xtime < 0 || xtime >= width) break; pixels[xtime + ytime * width] = 0xff00ff; } }
 */
