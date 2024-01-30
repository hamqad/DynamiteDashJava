package com.syntx.dynamitedash.entity.projectile;

import com.syntx.dynamitedash.entity.mob.Mob;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.entity.spawner.ParticleSpawner;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;

public class FireBall extends Projectile {
	
	private Mob mob; // The mob that fired the fireball
	
	private int playerX, playerY, enemyX, enemyY, fireballX, fireballY; //Holds the x and y coordinates of the required entites
	
	private boolean deductedPlayerHealth = false; // Weather or not the fire ball has caused a decrease in the players health 
	private boolean deductedEnemyHealth = false; // Weather or not the fire ball has caused a decrease in the enemies health 
	
	public static final int FIRE_RATE = 10;  // The time interval between each fire ball shot 

	public FireBall(double x, double y, double dir, Mob mob) {
		super(x, y, dir);
		this.mob = mob;
		// Basic attributes of the projectile:
		range = 1000;//random.nextInt(100) + 200;  // Range of fireball will be between 150 to 250
		speed = 3;      //random.nextInt(3) + 1;
		sprite = Sprite.fireball_enemy1;
		
		nx = speed *   Math.cos(angle); //  x axis of projectile  
		ny = speed *   Math.sin(angle); //  y axis of projectile
	}
	
	public void update() { // updates the fire ball
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 11, 3, 3)) {  // moves the fireball if there are no collisions in front and if there is, it removes that entity.
			level.add(new ParticleSpawner((int)x, (int)y, 22, 10, level, Sprite.particle_grey, true)); // Spawns particles when the projectile collides 
			remove(); 
		}   
		move(); // Moves the fireball
		deductHealth(); // Deducts the health from any mob that collides with the fire ball
	}
	
	public void deductHealth() { // Deducts the health from any mob that collides with the fire ball
		if(!(mob instanceof Player)) { // This ensures that the mob that fires the projectile can't damage itself
			if(!deductedPlayerHealth) { // If the players health has not already been reduced
				for (int i = 0; i < Level.getPlayers().size(); i++) { // Loops through each player on the level
					playerX = (int)Math.ceil(Level.getPlayerAt(i).getX() / 16) + 1; // The players current x tile coordinate
					playerY = (int)Math.ceil(Level.getPlayerAt(i).getY() / 16) + 1; // The players current y tile coordinate
					fireballX = (int)Math.ceil(x / 16); // The fire ball's current x tile position
					fireballY = (int)Math.ceil(y / 16); // The fire ball's current y tile position
					// Checks if the coordinates of the fire ball and the player match
					if (playerX == fireballX && playerY == fireballY) {
						Level.getPlayerAt(i).damage(25); // Reduces the players health    
						deductedPlayerHealth = true;
					}
				}
			}
		} else {	
			if(!deductedEnemyHealth) { // If the enemies health has not already been reduced
			//	System.out.println("Enemy X: " + ((int)Math.ceil(Level.enemies.get(0).getX() / 16) + 1) + " Fireball X: " + (int)Math.ceil(x / 16));
			//	System.out.println("Enemy Y: " + ((int)Math.ceil(Level.enemies.get(0).getY() / 16) + 2) + " Fireball Y: " + (int)Math.ceil(y / 16));
				for(int i = 0; i < Level.enemies.size(); i++) { // Loops through each enemy on the level
					enemyX = (int)Math.ceil(Level.enemies.get(i).getX() / 16) + 1; // The enemies current x coordinate
					enemyY = (int)Math.ceil(Level.enemies.get(i).getY() / 16) + 2; // The enemies current y coordinate
					fireballX = (int)Math.ceil(x / 16); // The fire ball's current x tile position
					fireballY = (int)Math.ceil(y / 16); // The fire ball's current y tile position
					if(enemyX == fireballX && enemyY == fireballY) { // If the enemy has collided with the fire ball
						Level.enemies.get(i).damage(20); // reduce the enemies health
						deductedEnemyHealth = true;  
					}
				}
			}
		}
	}
	
	protected void move () { // Moves the projectile  by updating its x and y positions from the spawn positions to the angle in which the projectile needs to move 
		x += nx;
		y += ny;
		if(distance() > range) remove();  // removes the projectile if the distance traveled has surpassed the speified range of the projectile
		}
	
	
	private double distance() { // Calculates the distance that the projectile has travelled using pythagoras' theorem
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin-y))); 
		return dist;
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int) x - 5, (int) y - 8, this); // renders the projectile starting at center of player
	}

}
