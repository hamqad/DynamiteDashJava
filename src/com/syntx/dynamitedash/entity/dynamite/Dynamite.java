package com.syntx.dynamitedash.entity.dynamite;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.explosion.Explosion;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.entity.spawner.ParticleSpawner;
import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.graphics.SpriteSheet;
import com.syntx.dynamitedash.level.tile.Tile;

public class Dynamite extends Entity {

	private AnimatedSprite dynamite_1 = new AnimatedSprite(SpriteSheet.dynamite_animation, 16, 16, 5);
	
	private Player player; // The player that placed the dynamite
	private int detonateTime; // The time in which the dynamite should detonate
	private int time = 0; // How long it has been since the dynamite's placement
	private int realTime; // The time the dynamite has been placed in seconds
	private boolean frameTracker = true; // toggles the first two sprite frames 
	private boolean detonateCheck = false; // Is true when the dynamite should detonate 
	private int frame = 0; // Holds the frame of the animation
	private int range; // How far the reach of the dynamite is
	private boolean collideX0, collideX1, collideY0, collideY1; // Collisions for each arm of the explosion
	private boolean exploded; // Weather or not _the dynamite has exploded
	
	
	public Dynamite(int x, int y, int detonateTime, int range, Player player) { // Places the dynamite in the specified position which detonates at a specific time
		sprite = Sprite.dynamite; // The default sprite of the dynamite
		this.player = player;
		this.detonateTime = detonateTime; 
		this.range = range;
		this.x = (int)(Math.ceil((x / 16) + 1) * 16) - 8; // Makes it so that the dynamites position will always be at the center of the tile
		this.y = (int)(Math.ceil((y / 16) + 1) * 16) - 8; // Makes it so that the dynamites position will always be at the center of the tile
	}
		
	public void update() { // Updates the dynamite
		time++; // Increments the time
		if (time % 10 == 0) { // Toggles between the first two frames of the animation 3 times a second
			if(detonateCheck == false) {
				if(frameTracker) {
					dynamite_1.setFrame(0);
					frameTracker = false;
				} else {
					dynamite_1.setFrame(1);
					frameTracker = true;
				}
			}
			finishAnimation(); // Finishes the animation of the dynamite if its time to detonate
		}	
		
		if (time % 60 == 0) {  // Updates the real time in seconds based on the time variable and finishes the dynamite animation if its time to detonate
			realTime ++;
		}
		if (realTime >= detonateTime) { // If its time to detonate, set the detonate checker to true
			detonateCheck = true;
		} 
	}
	
	public void finishAnimation() { // Finishes the animation if the dynamite needs to detonate
		if(detonateCheck) { // If its time to detonate
			if (frame != 4) frame ++; // Go to the next frame if the last frame hasnt been reached
			dynamite_1.setFrame(frame); // Sets the animation to the frame
			if (frame == 4) { // If the last frame has been reached, wait a second and remove the dynamite
				expload(); // Cause an explosion effect
				if(time % 60 == 0) removed = true;  
			}
		}
	}
	
	public void expload() { // Creates an explosion effect depending on the range of the dynamite
		// Spawns particles on the dyanmite's explosion location:
		if(!exploded) level.add(new Explosion(x - 8,y - 8));
		if(range == 2) { // If the players dynamite range is 2 
			
			// One tile to the left of the dynamite:
			
			// If the tile at the required explosions location is breakable or not solid, create an explosion at that position
			if(!exploded) {
				if(level.getTile((int)Math.ceil((x / 16)) - 1, (int)Math.ceil((y / 16))).solid() != true) { 
					level.add(new Explosion(x - 24, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) - 1, (int)Math.ceil((y / 16))).breakable()) {
					level.add(new Explosion(x - 24, y - 8));
					level.setTile(x - 16, y, Tile.level1_grass); // Set the tile of the breakable tile to grass when exploded
				}
	
			// One tile to the right of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)) + 1, (int)Math.ceil((y / 16))).solid() != true) {
					level.add(new Explosion(x + 8, y - 8));
				} else if( level.getTile((int)Math.ceil((x / 16)) + 1, (int)Math.ceil((y / 16))).breakable()) {
					level.add(new Explosion(x + 8, y - 8));
					level.setTile(x + 16, y, Tile.level1_grass);
				}
			
			
			// One tile under the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 1)).solid() != true) {
					level.add(new Explosion(x - 8, y + 8));
				} else if (level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 1)).breakable()) {
					level.add(new Explosion(x - 8, y + 8));
					level.setTile(x, y + 16, Tile.level1_grass);
				}
			
			
			// One tile on top of the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 1)).solid() != true) {
					level.add(new Explosion(x - 8, y - 24));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) -1)).breakable()) {
					level.add(new Explosion(x - 8, y - 24));
					level.setTile(x, y - 16, Tile.level1_grass);
				}
				exploded = true;
				player.dynamiteNumber++;
			}
				
		}
		
		
		if(range == 3) { // If the players dynamite range is 3
			
			if(!exploded) {
				
				// One tile to the left of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)) - 1, (int)Math.ceil((y / 16))).solid() != true) {
					level.add(new Explosion(x - 24, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) - 1, (int)Math.ceil((y / 16))).breakable()) {
					level.add(new Explosion(x - 24, y - 8));
					collideX0 = true;
					level.setTile(x - 16, y, Tile.level1_grass);
				} else collideX0 = true;
			
				// One tile to the right of the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)) + 1, (int)Math.ceil((y / 16))).solid() != true) {
					level.add(new Explosion(x + 8, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) + 1, (int)Math.ceil((y / 16))).breakable()) {
					level.add(new Explosion(x + 8, y - 8));
					collideX1 = true;
					level.setTile(x + 16, y , Tile.level1_grass);
				} else collideX1 = true;
			
				// One tile under the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 1)).solid() != true) {
					level.add(new Explosion(x - 8, y + 8));
				} else if (level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 1)).breakable()) {
					level.add(new Explosion(x - 8, y + 8));
					collideY1 = true;
					level.setTile(x, y + 16, Tile.level1_grass);
				} else collideY1 = true;
			
				// One tile on top of the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 1)).solid() != true) {
					level.add(new Explosion(x - 8, y - 24));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) - 1)).breakable()) {
					level.add(new Explosion(x - 8, y - 24));
					collideY0 = true;
					level.setTile(x, y - 16, Tile.level1_grass);	
				} else collideY0 = true;

			
			
			
				// Two tiles to the left of the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)) - 2, (int)Math.ceil((y / 16))).solid() != true && !collideX0) {
					level.add(new Explosion(x - 40, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) - 2, (int)Math.ceil((y / 16))).breakable() && ! collideX0) {
					level.add(new Explosion(x - 40, y - 8));
					level.setTile(x - 32, y , Tile.level1_grass);
				}
			
				// Two tiles to the right of the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)) + 2, (int)Math.ceil((y / 16))).solid() != true && !collideX1) {
					level.add(new Explosion(x + 24, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) + 2, (int)Math.ceil((y / 16))).breakable() && !collideX1) {
					level.add(new Explosion(x + 24, y - 8));
					level.setTile(x + 32, y , Tile.level1_grass);
				
				}
				
				// Two tiles under the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 2)).solid() != true && !collideY1) {
					level.add(new Explosion(x - 8, y + 24));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 2)).breakable() && !collideY1) {
					level.add(new Explosion(x - 8, y + 24));
					level.setTile(x, y + 32 , Tile.level1_grass);
					
				}
			
			// Two tiles on top of the dynamite:
			
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 2)).solid() != true && !collideY0) {
					level.add(new Explosion(x - 8, y - 40));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 2)).breakable() && !collideY0) {
					level.add(new Explosion(x - 8, y - 40));
					level.setTile(x, y - 32, Tile.level1_grass);
					
				}
				exploded = true;
				player.dynamiteNumber++;
			}
		}
		
		
		if(range == 4) { // If the players dynamite range is 4
			if(!exploded) {
				// One tile to the left of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)) - 1, (int)Math.ceil((y / 16))).solid() != true) {
					level.add(new Explosion(x - 24, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) - 1, (int)Math.ceil((y / 16))).breakable()) {
					level.add(new Explosion(x - 24, y - 8));
					collideX0 = true;
					level.setTile(x - 16, y , Tile.level1_grass);
				} else collideX0 = true;
	
				// One tile to the right of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)) + 1, (int)Math.ceil((y / 16))).solid() != true) {
					level.add(new Explosion(x + 8, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) + 1, (int)Math.ceil((y / 16))).breakable()) {
					level.add(new Explosion(x + 8, y - 8));
					collideX1 = true;
					level.setTile(x + 16, y , Tile.level1_grass);
				} else collideX1 = true;
				
				// One tile under the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 1)).solid() != true) {
					level.add(new Explosion(x - 8, y + 8));
				} else if (level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 1)).breakable()) {
					level.add(new Explosion(x - 8, y + 8));
					collideY1 = true;
					level.setTile(x, y + 16 , Tile.level1_grass);
				} else collideY1 = true;
				
				// One tile on top of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 1)).solid() != true) {
					level.add(new Explosion(x - 8, y - 24));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) - 1)).breakable()) {
					level.add(new Explosion(x - 8, y - 24));
					level.setTile(x, y - 16 , Tile.level1_grass);
					collideY0 = true;
				} else collideY0 = true;
	
				
				// Two tiles to the left of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)) - 2, (int)Math.ceil((y / 16))).solid() != true && !collideX0) {
					level.add(new Explosion(x - 40, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) - 2, (int)Math.ceil((y / 16))).breakable() && ! collideX0) {
					level.add(new Explosion(x - 40, y - 8));
					collideX0 = true;
					level.setTile(x - 32, y , Tile.level1_grass);
				} else collideX0 = true;
				
				// Two tiles to the right of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)) + 2, (int)Math.ceil((y / 16))).solid() != true && !collideX1) {
					level.add(new Explosion(x + 24, y - 8));
				} else if (level.getTile((int)Math.ceil((x / 16)) + 2, (int)Math.ceil((y / 16))).breakable() && !collideX1) {
					level.add(new Explosion(x + 24, y - 8));
					collideX1 = true;
					level.setTile(x + 32, y , Tile.level1_grass);
				} else collideX1 = true;
				
				// Two tiles under the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 2)).solid() != true && !collideY1) {
					level.add(new Explosion(x - 8, y + 24));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 2)).breakable() && !collideY1) {
					level.add(new Explosion(x - 8, y + 24));
					collideY1 = true;
					level.setTile(x, y + 32 , Tile.level1_grass);
				} else collideY1 = true;	
				
				// Two tiles on top of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 2)).solid() != true && !collideY0) {
					level.add(new Explosion(x - 8, y - 40));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 2)).breakable() && !collideY0) {
					level.add(new Explosion(x - 8, y - 40));
					collideY0 = true;
					level.setTile(x, y - 32, Tile.level1_grass);
				} else collideY0 = true;
				
				
				
				// Three tiles to the left of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16) - 3), (int)Math.ceil((y / 16))).solid() != true && !collideX0) {
					level.add(new Explosion(x - 56, y - 8));
				} else if(level.getTile((int)Math.ceil((x / 16) - 3), (int)Math.ceil((y / 16))).breakable() && !collideX0) {
					level.add(new Explosion(x - 56, y - 8));
					level.setTile(x - 48, y , Tile.level1_grass);
				}
				
				// Three tiles to the right of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16) + 3), (int)Math.ceil((y / 16))).solid() != true && !collideX1) {
					level.add(new Explosion(x + 40, y - 8));
				} else if(level.getTile((int)Math.ceil((x / 16) + 3), (int)Math.ceil((y / 16))).breakable() && !collideX1) {
					level.add(new Explosion(x + 40, y - 8));
					level.setTile(x + 48, y , Tile.level1_grass);
				}
				
				// Three tiles under the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 3)).solid() != true && !collideY1) {
					level.add(new Explosion(x - 8, y + 40));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) + 3)).breakable() && !collideY1) {
					level.add(new Explosion(x - 8, y + 40));
					level.setTile(x, y + 48 , Tile.level1_grass);
				}
				
				// Three tiles on top of the dynamite:
				
				if(level.getTile((int)Math.ceil((x / 16)), (int)Math.floor((y / 16) - 3)).solid() != true && !collideY0) {
					level.add(new Explosion(x - 8, y - 56));
				} else if(level.getTile((int)Math.ceil((x / 16)), (int)Math.ceil((y / 16) - 3)).breakable() && !collideY0) {
					level.add(new Explosion(x - 8, y - 56));
					level.setTile(x, y - 48 , Tile.level1_grass);
				}
			}
			exploded = true;
			player.dynamiteNumber++;
		}
		
	}
	
	public void render(Screen screen) { // Renders the dynamite
		screen.renderSprite(x - 8, y - 8, dynamite_1.getSprite(), false);
	}
	
	
}
