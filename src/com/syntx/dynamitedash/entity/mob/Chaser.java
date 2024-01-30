package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;
import java.util.List;

import com.syntx.dynamitedash.entity.mob.Mob.Direction;
import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.SpriteSheet;
import com.syntx.dynamitedash.level.Level;

public class Chaser extends Mob {
	
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
	
	private int time = 0; // The duration of the chasers existence
	
	// Displacement of chaser:
	private double xa = 0;
	private double ya = 0;
	private double speed = 1; // Speed of movement 
	
	public Chaser (int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = sprite.dummy;		
	}
	
	private void move() { // Moves the chaser
		xa = 0;
		ya = 0;
		
		List<Player> players = level.getPlayers(this, 70);  // List of players that are close to the chaser
		// Logic of chasers movement: (If the players position is not equal to the chasers distance, move towards the player)
		if (players.size() > 0) {
		Player player = players.get(0); // The closest player in the list
		if(x < player.getX()) xa += speed;
		if(x > player.getX()) xa -= speed;
		if(y < player.getY()) ya += speed;
		if(y > player.getY()) ya -= speed;
		
		}
		if(xa != 0 || ya != 0) {   // if the chasers displacement is not 0  it will move in the specific direction and walking will be set to true
			move(xa,ya);
			walking = true;
		}else {
			walking = false;
		}
		
	}
	
	public void update() {
		time++;
		Player p = Level.getClientPLayer(); // Sets player to the current player
		p.deductPlayerHealth(x, y); // Deducts the players health if it collides with it
		if(reset) { // If the sprite needs to be reset
			if(time % 30 == 0) { // Wait for 1/2 of a second
				animSprite = temp; // set the sprite the one previously stored
				reset = false; // Set reset to false
			}
		}
		if (health <= 0) removed = true; // If the health is below or equal to zero, remove the mob from the level
	
		move(); // moves the chaser each update cycle
		if(walking) animSprite.update();  // If the chaser is walking the animation will be updated
		else animSprite.setFrame(0);  // If it is not, the  sprite will be set to that of the first frame, which is a stationary position
		// Updates the chasers animation and direction depending on its displacement
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

	}
	
	public void damage(int i) { 
		health -= i; // Deducts from the chasers health
		temp = animSprite;
		animSprite = hurt;
		reset = true;
	}
	
	public void render(Screen screen) { // Renders the chaser
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int)(y - 16), this);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
