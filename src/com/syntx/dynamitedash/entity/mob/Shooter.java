package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;

import com.syntx.dynamitedash.entity.mob.Mob.Direction;
import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.SpriteSheet;

public class Shooter extends Mob {
	// Animation of chasers each direction
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite hurt = new AnimatedSprite(SpriteSheet.dummy_dead, 32, 32, 2);
	
	private AnimatedSprite animSprite = down; // Current animation
	
	AnimatedSprite temp; // A temporary animation sprite
	
	private boolean reset = false; // Weather the sprite should be reset 
	
	public int health = 100; // The health of the mob
	
	double speed = 0.1;
	
	private int time = 0; // Time that the mob has been alive 
	// Displacement of the mobs x and y position:
	double xa = 0;  
	double ya = 0;
	
	private int fireRate = 100; // Fire rate of the shooter pro
	private int fireTracker = fireRate; // This tracks the current value of the fire delay

	public Shooter(int x, int y) {
		ya = 1;
		this.x = x << 4;
		this.y = y << 4;
		sprite = sprite.dummy;
	}
	
	public void update() {
		if (fireTracker > 0) fireTracker --; // Decreases the fire rate every update cycle
		time++;
		if(reset) { // If the sprite needs to be reset
			if(time % 30 == 0) { // Wait for 1/2 of a second
				animSprite = temp; // set the sprite the one previously stored
				reset = false; // Set reset to false
			}
		}
		if (health <= 0) removed = true; // If the health is below or equal to zero, remove the mob from the level
	
		// Logic to move the shooter:
		if (collision(xa,ya)) ya = -ya ;  // Changes mobs direction
		if (ya > 0) ya += speed;
		else if (ya < 0) ya -= speed;
		else y--;
		if(walking) animSprite.update();  // If the mob is walking the animation will be updated
		else animSprite.setFrame(0);  // If it is not, the  sprite will be set to that of the first frame, which is a stationary position
		// Updates dummies animation and direction depending on its displacement
				if(ya < 0) {
					animSprite = up;
					dir = Direction.UP;
					
				}else if(ya > 0) {
					animSprite = down;
					dir = Direction.DOWN;					
				} 
				if(xa < 0) {
					animSprite = left;
					dir = Direction.LEFT;
				}else if(xa > 0){	
					animSprite = right;
					dir = Direction.RIGHT;
				}
				if(xa != 0 || ya != 0) {   // If the shooter needs to be displaced, the dummy is moved according to the displacement and walking is set to true
					move(xa,ya);
					walking = true;
				}else {
					walking = false;
				}
				
		Player p = level.getClientPLayer(); // Sets player to the current player
		double dx = p.getX() - x; // difference between the shooters and players x position
		double dy = p.getY() - y; // difference between the shooters and players y position
		double dir = Math.atan2(dy, dx); // Resultant direction
		
		
	}
	
	public void damage(int i) {
		health -= i;
		temp = animSprite;
		animSprite = hurt;
		reset = true;
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
