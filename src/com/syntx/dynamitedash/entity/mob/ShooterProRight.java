package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.graphics.SpriteSheet;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.util.Debug;
// Enemy that shoots towards the player
public class ShooterProRight extends Mob {
	
	// Animation of chasers each direction
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.wizard_right, 32, 32, 1);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.wizard_right, 32, 32, 1);
	private AnimatedSprite hurt = new AnimatedSprite(SpriteSheet.wizard_right, 32, 32, 1);
		
	private AnimatedSprite animSprite = down; // Current animation
		
	AnimatedSprite temp; // A temporary animation sprite
		
	public int health = 100; // The health of the mob
	
	private int time = 0; // Time that the mob has been alive 
	// Displacement of the mobs x and y position:
	double xa = 0;  
	double ya = 0;
	
	private int fireRate = 100; // Fire rate of the shooter pro
	private int fireTracker = fireRate; // This tracks the current value of the fire delay

	public ShooterProRight(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = sprite.dummy;
	}
	
	public void update() {
		Player p = Level.getClientPLayer(); // Sets player to the current player
		// Handles the reduction of the players health:
		p.deductPlayerHealth(x, y);
		if(Game.playerCount == 2) {
			p = Level.players.get(1);
			p.deductPlayerHealth(x, y);
		}
		if(Game.playerCount == 3) {
			p = Level.players.get(2);
			p.deductPlayerHealth(x, y);
		}
		time++;
		if (health <= 0) removed = true; // If the health is below or equal to zero, remove the mob from the level
		if (fireTracker > 0) fireTracker --; // Decreases the fire rate every update cycle
		time ++; // Increments the time of mobs existence
		p = level.getClosestPlayer(this);					
		double dx = p.getX() - x; // difference between the shooters and players x position
		double dy = p.getY() - y; // difference between the shooters and players y position
		double dir = Math.atan2(dy, dx); // Resultant direction
		if (fireTracker <= 0) { // Shoots a fireball from the shooter to the player if the delay has reached 0
		shoot(x + 13, y + 16, dir, this);  
		fireTracker = fireRate; // Resets the delay to the fire rate value 
		}
		
	}
	
	public void damage(int i) {
		health -= i;
		temp = animSprite;
		animSprite = hurt;
	}

	public void render(Screen screen) {
		screen.renderMob(x, y, this);
		sprite = animSprite.getSprite();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	
}
