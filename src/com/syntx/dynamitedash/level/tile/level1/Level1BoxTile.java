package com.syntx.dynamitedash.level.tile.level1;

import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.level.tile.Tile;

public class Level1BoxTile extends Tile {

	public Level1BoxTile(Sprite sprite) {
		super(sprite);
		
	}
	
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 4,y << 4, this);
	}
	
	public boolean solid() {  
		return true;
		
	}
	
	public boolean breakable() {
		return true;
	}
	
	
	
}
