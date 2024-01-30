package com.syntx.dynamitedash.entity.powerup;

import com.syntx.dynamitedash.entity.mob.Mob.Direction;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.entity.mob.PlayerClone;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
// Adds a clone of the player to the level for a brief amount of time
public class Clone extends Powerup {
	static String path = "res/textures/sprites/cloneicon.png";
	static Sprite sprite = Sprite.clone;
	int playerX, playerY;
	Direction dir; 
	 
	public Clone(int x, int y){	 
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Adds a clone of the player to the level for a brief amount of time
		playerX = (player.getX()) >> 4;
		playerY = (player.getY()) >> 4;
		dir = player.dir;
		level.add(new PlayerClone(playerX, playerY, dir, player));
	}
	
	
	
	public void render(Screen screen) {
		super.render(screen);
	}
}
