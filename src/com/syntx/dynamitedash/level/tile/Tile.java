package com.syntx.dynamitedash.level.tile;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.level.tile.level1.Level1BoxTile;
import com.syntx.dynamitedash.level.tile.level1.Level1DoorTile;
import com.syntx.dynamitedash.level.tile.level1.Level1GrassTile;
import com.syntx.dynamitedash.level.tile.level1.Level1StoneFloorTile;
import com.syntx.dynamitedash.level.tile.level1.Level1StoneWallTile;

public class Tile {

	public int x, y;    // Position of tile
	public Sprite sprite;  
	
	public static Tile grass = new GrassTile(Sprite.grass);  
	public static Tile box = new BoxTile(Sprite.box);
	public static Tile stone_wall = new StoneWallTile(Sprite.stone_wall);
	public static Tile stone_floor = new StoneFloorTile(Sprite.stone_floor);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	// Level one sprites initialised:
	public static Tile level1_grass = new Level1GrassTile(Sprite.one_grass);
	public static Tile level1_box = new Level1BoxTile(Sprite.one_box);
	public static Tile level1_stone_wall = new Level1StoneWallTile(Sprite.one_stone_wall);
	public static Tile level1_stone_floor = new Level1StoneFloorTile(Sprite.one_stone_floor);
	public static Tile level1_door = new Level1DoorTile(Sprite.one_door);
	
	// Colours for the level 1 sprites, used in the level maker:
	
	public static final int col_level1_box = 0xffAF7B36;
	public static final int col_level1_door = 0xff4F3F27;
	public static final int col_level1_grass = 0xff4B8B2A;
	public static final int col_level1_stone_floor = 0xffA09E98;
	public static final int col_level1_stone_wall = 0xff898782;
	public static final int col_level1_spawn = 0xffC12025;
	
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	
	public void render(int x, int y, Screen screen) { 
		
	}
	
	public boolean solid() {  // Specifies weather mobs can traverse through the tile
		return false;  // Default is false
		
	}
	
	public boolean breakable() { // Specifies weather the tile can be broken or not
		return false; // Default is false
	}
	
	public int getX() {
		return x * 16; 
	}
	
	public int getY() {
		return y * 16;
	}
	
	public int getCol(Tile tile){
		if (tile instanceof Level1BoxTile || tile instanceof BoxTile) return col_level1_box;
		if (tile instanceof Level1DoorTile ) return col_level1_door;
		if (tile instanceof Level1GrassTile || tile instanceof GrassTile) return col_level1_grass;
		if (tile instanceof Level1StoneFloorTile || tile instanceof StoneFloorTile) return col_level1_stone_floor;
		if (tile instanceof Level1StoneWallTile || tile instanceof StoneWallTile) return col_level1_stone_wall;
		return 0;
		
	}
	
	
}
