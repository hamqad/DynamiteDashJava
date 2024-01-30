package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
// Adds 1 to the maximum dynamite amount
public class ExtraBomb extends Powerup {
	 static String path = "res/textures/sprites/extraicon.png";
	 static Sprite sprite = Sprite.extra_bomb;

	public ExtraBomb(int x, int y){	 
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Adds 1 dynamite to the players current dynamite number
		player.dynamiteNumber += 1;
	}
	
	
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
