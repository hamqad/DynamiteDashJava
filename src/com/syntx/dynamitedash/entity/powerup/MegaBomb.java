package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
// Adds 1 to the dynamites range if its not already at its maximum
public class MegaBomb extends Powerup {
	 static String path = "res/textures/sprites/megaicon.png";
	 static Sprite sprite = Sprite.mega_bomb;

	public MegaBomb(int x, int y){	 
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Adds 1 to the dynamites range if its not already at its maximum
		if (player.range != 4) player.range++;
	}
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
