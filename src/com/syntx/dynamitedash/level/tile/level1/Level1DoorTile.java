package com.syntx.dynamitedash.level.tile.level1;

import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.tile.Tile;

public class Level1DoorTile extends Tile {

	public Level1DoorTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 4,y << 4, this);
	}

}
