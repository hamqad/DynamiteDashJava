package com.syntx.dynamitedash.entity.explosion;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.spawner.ParticleSpawner;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;

public class Explosion extends Entity {
	
	private int playerX, playerY, enemyX, enemyY, explosionX, explosionY; // Holds the x and y coordinates of the required entities
	
	private boolean exploded = false; // Weather the explosion has occurred or note
	private boolean deductedPlayerHealth = false; // Weather or not the explosion has caused a decrease in the players health 
	private boolean deductedEnemyHealth = false; // Weather or not the explosion has caused a decrease in the enemies health 
	
	private int time = 0; // The time since the explosion was called for
	private int x, y; // The coordinates of the explosion
	
	public Explosion(int x, int y) { 
		this.x = x;
		this.y = y;
		sprite = Sprite.explosion; // The explosions sprite
	}
	
	public void update() { 
		time++; // Keeps track of the time
		if(!exploded) expload(); // Explodes if the explosion hasn't occurred already
			
			if(!deductedPlayerHealth) { // If the players health has not already been deducted
				for (int i = 0; i < Level.getPlayers().size(); i++) { // Loops through each player on the level
					playerX = (int)Math.ceil(Level.getPlayerAt(i).getX() / 16); // The players current tiles x coordinate
					playerY = (int)Math.ceil(Level.getPlayerAt(i).getY() / 16); // The players current tiles Y coordinate
					explosionX =(int)Math.ceil(x / 16); // The explosions current tiles x coordinate
					explosionY = (int)Math.ceil(y / 16); // The explosions current tiles y coordinate
					// Checks if the tile coordinates of the explosion and the player match
					if (playerX == explosionX && playerY == explosionY) {
						Level.getPlayerAt(i).damage(50); // Reduces the players health    
						deductedPlayerHealth = true;
					}
				}
			}
			if(!deductedEnemyHealth) {
				for(int i = 0; i < Level.enemies.size(); i++) {
					enemyX = (int)Math.ceil(Level.enemies.get(i).getX() / 16); // The enemies current tiles x coordinate
					enemyY = (int)Math.ceil(Level.enemies.get(i).getY() / 16); // The enemies current tiles y coordinate
					explosionX =(int)Math.ceil(x / 16); // The explosions current tiles x coordinate
					explosionY = (int)Math.ceil(y / 16); // The explosions current tiles y coordinate
					if(enemyX + 1 == explosionX && enemyY + 1 == explosionY) {
						Level.enemies.get(i).damage(100);
						deductedEnemyHealth = true;
					}
				}
				

			}
			if (time % 10 == 0) { // Checks for damage reduction in any players and removes the sprite after 1/10 of a second
			removed = true; // Removes the explosion from the screen
			}

	}
	
	public void expload() { // Causes an exploding effect
		new ParticleSpawner(x + 8, y + 8, 10, 10, level, Sprite.particle_fire2, false); // Spawns particles for the explosion
		new ParticleSpawner(x + 8, y + 8, 10, 10, level, Sprite.particle_fire1, false); // Spawns particles for the explosion
		exploded = true; // Sets exploded to true	
	}
	
	public void render(Screen screen) { // Renders the explosion
		screen.renderSprite(x, y, sprite, false);
	}
}
