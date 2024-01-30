package com.syntx.dynamitedash.entity.particle;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;

public class Particle extends Entity {

	private Sprite sprite;

	private boolean collide = true;
	private int life; // Life of particle
	private int time = 0; // tracks the time the particle has existed

	protected double xx, yy, zz; // location of particles in each axis
	protected double xa, ya, za; // amount of pixels the particle moves in the x, y and z axis

	
	
	
	public Particle(int x, int y, int life, Sprite sprite, boolean collide) { // Creates one particle
		this.collide = collide;
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		this.sprite = sprite;
		this.xa = random.nextGaussian() + 1.8; // gives random number between 0.8 and 2.8 and sets it to the x displacement
		if (this.xa < 0) xa = 0.1;
		this.ya = random.nextGaussian(); // gives random number between -1 and 1 and sets it to the y displacement
		this.zz = random.nextFloat() + 2.0; // random decimal between 2 and 3 
	}

	public void update() { // Updates the position of the particles
		time++; // increments the time each update cycle
		if (time >= 7400 - 1) time = 0; // Sets time back to 0 if the particles life exceeds the duration 7400
		if (time > life) remove(); // If the time the particle has been alive exceeds the particles life, the particle is removed
		za -= 0.1; // exponentially decreases the displacement of the z axis to simulate gravity
		
		move(xx + xa, (yy + ya) + (zz + za));

		// Adds the displacement of the particles to the position:
	}

	private void move(double x, double y) {  // Displaces the particle in all three axis 
		
			if(collision(x,y)) { // If there is a collision change the direction of each of the displacement axis of the particle
				this.xa *= -0.5;
				this.ya *= -0.5;
			}
			
		// Updates positions by adding displacements:
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
		
	}
	
	public boolean collision(double x, double y) {  // handles the collisions of particles with objects by taking in the x and y positions of the particle
		boolean solid = false; // Default collision is set to false
		for (int c = 0; c < 4; c++) { // Loops through each corner of the tile for corner precision collision detection
			double xt = (x - c % 2 * 16) / 16; // Sets the area of the tile on the horizontal plane which is colliding with the particle to the exact size of the tile
			double yt = (y - c / 2 * 16) / 16; // Sets the area of the tile on the vertical plane which is colliding with the particle to the exact size of the tile
			int ix = (int) Math.ceil(xt);  // Changes the double nature of xt to an integer
			int iy = (int) Math.ceil(yt);  // Changes the double nature of yt to an integer	
			if (c % 2 == 0) ix = (int)Math.floor(xt); // If there is a collision on the left side of the tile, it will round down the double value
			if (c / 2 == 0) iy = (int)Math.floor(yt); // If there is a collision on the top side of the tile, it will round down the double value
			if (level.getTile(ix, iy).solid()) solid = true;  // Checks weather the tile ahead of the particle is solid or not, if it is the particle wont be able to move in that direction
		}
		return solid;
	}

	public void render(Screen screen) { // Renders the particles
		screen.renderSprite((int) xx - 6 , (int) yy - (int) zz, sprite, true);
	}
	
}
