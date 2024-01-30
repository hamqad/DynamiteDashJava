package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;
import java.util.List;

import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.SpriteSheet;
import com.syntx.dynamitedash.level.Node;
import com.syntx.dynamitedash.util.Vector2i;

public class ChaserPro extends Mob {
	
	public int health = 100; // The health of the mob
	int frame = 0; // The current frame of the mobs death
	
	// Animation of chasers each direction
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite hurt = new AnimatedSprite(SpriteSheet.dummy_dead, 32, 32, 2);
	
	private AnimatedSprite animSprite = down; // Current animation
	
	AnimatedSprite temp; // A temporary animation sprite
	
	private boolean reset = false; // Weather the sprite should be reset 
	
	// Displacement of chaser:
	private double xa = 0;
	private double ya = 0;
	
	private List<Node> path = null; // The path the chaser pro will take to chase the player
	private int time = 0; // The time the chaser pro has existed

	
	
	public ChaserPro(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = sprite.dummy;
	}
	
	private void move() { // Moves the chaser
		xa = 0;
		ya = 0;
		int px = level.getPlayerAt(0).getX(); // Current players x coordinate position in pixel precision
		int py = level.getPlayerAt(0).getY(); // Current players y coordinate position in pixel precision
		Vector2i start = new Vector2i(getX() >> 4, getY() >> 4); // The start position of the chaser pro in tile precision
		Vector2i destination = new Vector2i(px >> 4, py >> 4); // The destination of the chaser pro which is the players position in tile precision
		if(time % 3 == 0) path = level.findPath(start, destination); // The fastest path for the chaser pro to get from its start position to the players position which is updated every 20 seconds
		if (path != null) { // If there is a path available 
			if(path.size() > 0) { // If the destination has not been reached
				Vector2i vec = path.get(path.size() - 1).tile; // The position of the next tile the chaser pro needs to traverse
				if (x < vec.getX() << 4) xa++; // Move the chaser pro's x position to the right if the x coordinate of the next tile is larger in value 
				if (x > vec.getX() << 4) xa--; // Move the chaser pro's x position to the left if the x coordinate of the next tile is smaller in value
				if (y < vec.getY() << 4) ya++; // Move the chaser pro's y position downwards if the y coordinate of the next tile is larger in value
				if (y > vec.getY() << 4) ya--; // Move the chaser pro's y position upwards if the y coordinate of the next tile is smaller in value
			}
		}	
		if(xa != 0 || ya != 0) {   // if the chasers displacement is not 0  it will move in the specific direction and walking will be set to true
			move(xa,ya);
			walking = true;
		}else {
			walking = false;
		}
		
	}
	
	
	public void update() {
		Player p = level.getClientPLayer(); // Sets player to the current player
		p.deductPlayerHealth(x, y);
		time++;
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
		health -= i;
		temp = animSprite;
		animSprite = hurt;
		reset = true;
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int)(y - 16), this);
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
