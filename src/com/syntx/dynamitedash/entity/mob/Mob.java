package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.projectile.FireBall;
import com.syntx.dynamitedash.entity.projectile.Projectile;
import com.syntx.dynamitedash.entity.spawner.ParticleSpawner;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;

public abstract class Mob extends Entity{
	
	protected boolean moving = false; // holds value of weather a mob is moving
	protected boolean walking = false; // // holds value of weather a mob is walking
	
	public enum Direction {   // Holds the values of the directions the mob is facing
		UP, DOWN, LEFT, RIGHT;
	}
	
	public Direction dir;
	public int health;
	public int chaserProHealth;
	
	
	public void move(double xa, double ya) { // This method controls the mobs movement by taking the movement of the x and y axis as parameters
		if(xa !=0 && ya != 0) {  // If player is moving horizontally, the function is called again twice with each of the directions individually 
			move(xa, 0);
			move(0, ya);
			return;
		}
		// Sets the direction value of the mob correlated by the mobs direction 
		if (xa > 0) dir = Direction.RIGHT;
 		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP; 
		
		// This assures that the collision is always tested on the x axis each time the mob moves by 1 despite the overall displacement value:
		while(xa != 0) {   // While the mob still needs to be displaced 
			if (Math.abs(xa) > 1) { // If the displacement is still larger than one
				if(!collision(abs(xa), ya)) { // Displace the mob by 1 if there is no collision
					this.x += abs(xa);	
				}
				xa -= abs(xa);
			} else {
				if(!collision(abs(xa), ya)) { // Displace the mob by the decimal required if there is no collision
					this.x += xa;
				}
				xa = 0;
			}
		}
		// This assures that the collision is always tested on the y axis each time the mob moves by 1 despite the overall displacement value:
		while(ya != 0) {   // While the mob still needs to be displaced 
			if (Math.abs(ya) > 1) { // If the displacement is still larger than one
				if(!collision(xa, abs(ya))) { // Displace the mob by 1 if there is no collision
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if(!collision(xa, abs(ya))) { // Displace the mob by the decimal required if there is no collision
					this.y += ya;
				}
				ya = 0;
			}				
		}	
	}
	
	
	private int abs(double value) { // Return -1 or 1 depending on weather the value is a positive or a negative number
		if (value < 0) return -1;
		return 1;
	}
	
	public abstract void update();
	

	public abstract void render(Screen screen);
	
	public abstract void render(Graphics g);
	
	
	protected void shoot(double x, double y, double dir, Mob mob) {
		Projectile p = new FireBall (x, y, dir, mob); // Creates new fire ball 
		level.add(p); // Adds the fireball to the projectile list 

	}	
	
	public void damage(int i) { // Handles what happens when the mob is damaged
		level.add(new ParticleSpawner(x, y, 10, 20, level, Sprite.blood, false ));
	} 
	
	protected boolean collision(double xa, double ya) {
		boolean solid = false;   // Default collision is set to false
		for (int c = 0; c < 4; c++) {  // Loops through each corner of the tile for corner precision collision detection
			double xt = ((x + xa) - c % 2 * 15) / 16;  // Sets the area of the tile on the horizontal plane which is colliding with the mob to the exact size of the tile
			double yt = ((y + ya) - c / 2 * 15 ) / 16;	// Sets the area of the tile on the vertical plane which is colliding with the mob to the exact size of the tile
			int ix = (int) Math.ceil(xt);  // Changes the double nature of xt to an integer
			int iy = (int) Math.ceil(yt);  // Changes the double nature of yt to an integer	
			if (c % 2 == 0) ix = (int)Math.floor(xt); // If there is a collision on the left side of the tile, it will round down the double value
			if (c / 2 == 0) iy = (int)Math.floor(yt); // If there is a collision on the top side of the tile, it will round down the double value
			if (level.getTile(ix, iy).solid()) solid = true;   // Checks weather the tile ahead of the mob is solid or not, if it is the mob wont be able to move in that direction 
			}
		return solid;
	}

	
}
