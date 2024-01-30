package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
// Subtracts 1 from the players place rate
public class FastPlace extends Powerup {
	 static String path = "res/textures/sprites/placerateicon.png";
	 static Sprite sprite = Sprite.place_rate;

	public FastPlace(int x, int y){	 
		super(x, y, path, sprite);
		
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Subtracts one from the players place rate
		player.placeRate -= 1;
	}
	
	
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
