package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
// Provides the player with brief invisibility 
public class Ghost extends Powerup {
	 static String path = "res/textures/sprites/ghosticon.png";
	 static Sprite sprite = Sprite.ghost;

	public Ghost(int x, int y){	 
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Provides the player with brief invisibility 
		player.invisibility = true;
	}
	
	
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
