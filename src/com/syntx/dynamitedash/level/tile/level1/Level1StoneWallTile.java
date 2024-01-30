package com.syntx.dynamitedash.level.tile.level1;

import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.tile.Tile;

public class Level1StoneWallTile extends Tile {

	public Level1StoneWallTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 4,y << 4, this);
	}
	
	public boolean solid() {  
		return true;  
	}
}
