package com.syntx.dynamitedash.entity.mob;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.entity.dynamite.Dynamite;
import com.syntx.dynamitedash.entity.spawner.ParticleSpawner;
import com.syntx.dynamitedash.graphics.AnimatedSprite;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.graphics.SpriteSheet;
import com.syntx.dynamitedash.graphics.UI.UIPanel;
import com.syntx.dynamitedash.input.Keyboard;
import com.syntx.dynamitedash.util.Vector2i;

public class PlayerTwo extends Player {
	
	Keyboard input;

	// Holds the animated sprites for each direction of the player:
	AnimatedSprite down = new AnimatedSprite(SpriteSheet.player2_down, 32, 32, 3);
	AnimatedSprite up = new AnimatedSprite(SpriteSheet.player2_up, 32, 32, 3);
	AnimatedSprite left = new AnimatedSprite(SpriteSheet.player2_left, 32, 32, 3);
	AnimatedSprite right = new AnimatedSprite(SpriteSheet.player2_right, 32, 32, 3);
	AnimatedSprite hurt = new AnimatedSprite(SpriteSheet.player2_hurt, 32, 32, 1);
	
	AnimatedSprite animSprite = down; // Holds the current animation of the player, set to down as default
	AnimatedSprite temp;
	
	static UIPanel panel = (UIPanel) new UIPanel(new Vector2i(450 * 3, 330 * 3), new Vector2i(180 * 3, 20 * 3), true).setColour(0x7f95a5a6); // Creates a panel on the UI at the bottom of the screen
	
	public PlayerTwo(String id, int x, int y, Keyboard input) { // Spawns the player two at the given coordinates
		super(id, x, y, panel);
		sprite = Sprite.player2_down;
		this.input = input;
	}
	
	public void update() {
		super.update();
		if (health <= 0) Game.twoAlive = false;
		if(walking) animSprite.update();  // Updates the animation of the player if it is walking
		else animSprite.setFrame(0); // Sets the frame back to 0 if the player has stopped moving
		
		if(reset) {
			if(time % 30 == 0) {
				animSprite = temp;
				reset = false;
			}
		}
		
		// Handles the players movement:

		double xa = 0, ya = 0;
		if(input.twoUp) {
			animSprite = up;
			ya -= speed;
		} 
		if(input.twoDown) {
			animSprite = down;
			ya += speed;
		}
		if(input.twoLeft) {
			animSprite = left;
			xa -= speed;
		}
		if(input.twoRight) {
			animSprite = right;
			xa += speed;
		}
		
		if(xa != 0 || ya != 0) {   // if a key is pressed the player will move in the specific direction and walking will be set to true
			move(xa,ya);
			walking = true;
		}else {
			walking = false;
		}
		// Updates the players dynamite and powerup:
		updateDynamite();
		updatePowerup();
	}
		
	public void damage(int d) { // Damages the player by a given amount
		if(health - d < 0) health = 0;
		else health -= d;
		
		temp = animSprite;
		animSprite = hurt;
		reset = true;
	}
	
	public void deductPlayerHealth(int x , int y) {
		int playerX = (int)Math.ceil(this.x / 16); // The players current x tile coordinate
		int playerY = (int)Math.ceil(this.y / 16); // The players current y tile coordinate
		int enemyX = (int)Math.ceil(x / 16); // The enemies current x tile position
		int enemyY = (int)Math.ceil(y / 16); // The enemies current y tile position
		if (playerX == enemyX && playerY == enemyY) {	
			damage(10);	
		}
	}
	
	public void updateDynamite() { // Checks for dynamite placement
		if(dynamiteNumber != 0) { // If the player has dynamite available to place
			if (input.twoPlace && currentPlace <= 0) { // And the player has pressed the place button and the place rate allows it
				dynamiteNumber --; // Reduce the amount of dynamite the player has available
				Dynamite dynamite = new Dynamite(x, y, detonateTime, range, this); // Adds a dynamite at the players position
				level.add(dynamite); // adds the dynamite to the level
				// Creates a fire like particle effect:
				new ParticleSpawner(dynamite.getX(), dynamite.getY(), 10, 10, level, Sprite.particle_fire2, false);
				new ParticleSpawner(dynamite.getX(), dynamite.getY(), 10, 10, level, Sprite.particle_fire1, false);
				currentPlace = placeRate; // resets the place rate 
			}
		}
	}
	
	public void updatePowerup() {	// Checks if the powerup should be activated
		if (input.twoActivate && !used && time %  10 == 0) {
			used =  true;
			onMenu = false;
		}
	}
	
	
	public void render(Screen screen) {  // Renders the player 
		int flip = 0; // Default flip is 0
		sprite = animSprite.getSprite();
		if(!invisibility) screen.renderMob((int) (x - 16), (int) (y - 16), sprite, flip);	
	}
	
	
}
