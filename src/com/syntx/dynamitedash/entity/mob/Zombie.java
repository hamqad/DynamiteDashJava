package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.graphics.SpriteSheet;
import com.syntx.dynamitedash.level.Level;

public class Zombie extends Mob {
	
	// Animation of chasers each direction
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.zombie_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.zombie_up, 32, 32, 3);
	private AnimatedSprite hurt = new AnimatedSprite(SpriteSheet.zombie_hurt, 32, 32, 1);
	
	private AnimatedSprite animSprite = down; // Current animation
	
	AnimatedSprite temp; // A temporary animation sprite
	
	public int health = 100; // The health of the zombie
	
	private boolean reset = false; // Weather the sprite should be reset 
	
	private int time = 0; // Time that the zombie has been alive 
	// Displacement of the mobs x and y position:  
	boolean toggle = false;
	double ya = 0;
	double xa = 0;

	public Zombie(int x, int y) {
		this.x = x << 4; // The x coordinate of where the zombie will spawn / 16 to get it into tile precision
		this.y = y << 4;  // The y coordinate of where the zombie will spawn / 16 to get it into tile precision
		sprite = Sprite.dummy;  // Default sprite to avoid errors
	}
	
	public void update() {
		// Handles the reduction of the players health:
		Player p = Level.getClientPLayer(); // Sets player to the current player
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
		if(reset) { // If the sprite needs to be reset
			if(time % 30 == 0) { // Wait for 1/2 of a second
				animSprite = temp; // set the sprite the one previously stored
				reset = false; // Set reset to false
			}
		}
		if (health <= 0) removed = true; // If the health is below or equal to zero, remove the mob from the level
		// Logic to move the zombie:
		ya = 0;
		if(toggle) ya = -2;
		if(!toggle) ya = 2;
		if(collision(xa, ya)) toggle = !toggle;
		if(walking) animSprite.update();  // If the mob is walking the animation will be updated
		else animSprite.setFrame(0);  // If it is not, the  sprite will be set to that of the first frame, which is a stationary position
		// Updates zombies animation and direction depending on its displacement
				if(ya < 0) {
					animSprite = up;
					dir = Direction.UP;
				}else if(ya > 0) {
					animSprite = down;
					dir = Direction.DOWN;					
				} 
				if(ya != 0) {   // If the zombie needs to be displaced, the zombie is moved according to the displacement and walking is set to true
					move(0, ya);
					walking = true;
				}else {
					walking = false;
				}
	}
	
	public void damage(int i) {
		health -= i;
		temp = animSprite;
		animSprite = hurt;
		reset = true;
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int)x - 8, (int)y - 16, sprite, 0);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
