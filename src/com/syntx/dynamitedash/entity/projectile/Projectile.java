package com.syntx.dynamitedash.entity.projectile;
import java.util.Random;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.graphics.Sprite;

public class Projectile extends Entity {
	
	final protected double xOrigin, yOrigin;  // Holds the coordinates of where the projectile is spawned
	protected double angle;  // Holds the angle of the direction of projectile
	protected Sprite sprite; // Sprite of projectile
	protected double x, y;  // Holds the current position of the projectile
	protected double nx, ny; // Holds the x and y axis location of the angle the projectile needs to be moved
	protected double distance; // Distance from the origin of projectile
	protected double speed, range, damage; // Basic attributes of the projectile
	
	protected final Random random = new Random();
	
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;	
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	protected void move() {  // moves the projectile using the x and y axis of the angle 
		x += nx;
		y += ny;
		
	}

	

}
