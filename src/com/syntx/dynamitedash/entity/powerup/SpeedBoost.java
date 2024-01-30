package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;

//Increments the players speed by 1
public class SpeedBoost extends Powerup {
	 static String path = "res/textures/sprites/speedicon.png";
	 static Sprite sprite = Sprite.speed_boost;

	public SpeedBoost(int x, int y){
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Increments the players speed by 1
		player.speed += 1;
	}
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
