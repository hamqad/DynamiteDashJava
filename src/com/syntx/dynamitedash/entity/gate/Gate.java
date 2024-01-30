package com.syntx.dynamitedash.entity.gate;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;

public class Gate extends Entity {
	
	Sprite sprite = Sprite.gate; // The gates sprite
	int playerX, playerY; // The players x and y position
	boolean progressed; // Weather the player has progressed to the next level
	
	public Gate(int x, int y) { // Spawns a gate at the x and y positions
		this.x = x;
		this.y = y;
		
	}
	
	public void update() { // Updates the gate
		if(!progressed) { // If the player hasnt already progressed to the next level
			for(int i = 0; i < Level.players.size(); i++) { // loops throuugh each player on the map
				Player player = Level.players.get(i); // Current player
				playerX = (int)Math.ceil(player.getX() / 16); // X coordinate of the player in tile precision
				playerY = (int)Math.ceil(player.getY() / 16); // Y coordinate of the player in tile precision
				if(playerX == x && playerY == y) { // If the player is colliding with the gate
					progressed = true; 
					Level.reset(); // Resets the level
					if(Game.state == Game.STATE.GAME) Level.nextLevel(); // Progresses to the next level if its singleplayer
					else {  
						Game.winner = player; // Sets the player to winner if its multiplayer 
						Game.state = Game.STATE.WIN; 
					}
				}
			}
		}
	}
	
	public void render(Screen screen) { // Renders the gate
		screen.renderSprite(x << 4, y << 4, sprite, true);
	}
	
}
