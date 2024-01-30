package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;

//Adds 20 to the players health
public class HealthPotion extends Powerup {
	 static String path = "res/textures/sprites/potionicon.png";
	 static Sprite sprite = Sprite.health_potion;

	public HealthPotion(int x, int y){	 
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Adds 20 to the players health
		player.addHealth(20);
	}
	
	
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
