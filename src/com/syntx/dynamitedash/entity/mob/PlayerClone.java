package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;

import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.graphics.SpriteSheet;

public class PlayerClone extends Mob {
	
	// Animation of chasers each direction
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	private AnimatedSprite hurt = new AnimatedSprite(SpriteSheet.player_hurt, 32, 32, 2);
	
	private AnimatedSprite animSprite = down; // Current animation
	
	AnimatedSprite temp; // A temporary animation sprite
	
	public int health = 100; // The health of the mob
	
	private boolean reset = false; // Weather the sprite should be reset 
	
	private int time = 0; // Time that the mob has been alive 
	// Displacement of the mobs x and y position:
	double xa = 0;  
	double ya = 0;
	
	public PlayerClone(int x, int y, Direction dir, Player player) {
		if(dir == Direction.UP) {
			this.x = x << 4;
			this.y = y - 1 << 4;
			sprite = Sprite.player_up;
		} else if(dir == Direction.DOWN) {
			this.x = x << 4;
			this.y = y + 1 << 4;
			sprite = Sprite.player_down;
		} else if(dir == Direction.LEFT) {
			this.x = x - 1 << 4;
			this.y = y << 4;
			sprite = Sprite.player_left;	
		} else if(dir == Direction.RIGHT) {
			this.x = x + 1 << 4;
			this.y = y << 4;
			sprite = Sprite.player_right;
		} else {
			this.x = x << 4;
			this.y = y << 4;
			sprite = Sprite.player_down;  // Default sprite to avoid errors
		}
		
		
	}
	
	public void update() {
		time++;
		if(reset) { // If the sprite needs to be reset
			if(time % 30 == 0) { // Wait for 1/2 of a second
				animSprite = temp; // set the sprite the one previously stored
				reset = false; // Set reset to false
			}
		}
		if (health <= 0) removed = true; // If the health is below or equal to zero, remove the clone from the level
		if (time % 600 == 0) removed = true; // If the clone has been alive for a certain amount of time, remove it from the level
		// Logic to move the clone:
		if (time % (random.nextInt(50) + 30) == 0) {
			// xa = -xa ;  // Changes mobs direction
			xa = (double)random.nextInt(3) - 1; // Gives a value between - 1 and 1 which changes its direction randomly
			ya = (double)random.nextInt(3) - 1; // Gives a value between - 1 and 1 which changes its direction randomly
			if (random.nextInt(4) == 3) {  // Randomly stops the clone
				xa = 0;
				ya = 0;
			}
			
		}
		if(walking) animSprite.update();  // If the clone is walking the animation will be updated
		else animSprite.setFrame(0);  // If it is not, the  sprite will be set to that of the first frame, which is a stationary position
		// Updates clones animation and direction depending on its displacement
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
				if(xa != 0 || ya != 0) {   // If the clone needs to be displaced, the clone is moved according to the displacement and walking is set to true
					move(xa, ya);
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
		screen.renderMob((int)x - 16, (int)y - 16, sprite, 0);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
