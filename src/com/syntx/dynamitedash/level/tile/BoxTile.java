package com.syntx.dynamitedash.level.tile;

import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;

public class BoxTile extends Tile {

	public BoxTile(Sprite sprite) {
		super(sprite);
	}

	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 4,y << 4, this);
	}
	
	
}
