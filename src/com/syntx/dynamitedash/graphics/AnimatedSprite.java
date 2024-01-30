package com.syntx.dynamitedash.graphics;

public class AnimatedSprite extends Sprite {
	
	private int frame = 0; // The current frame of the animation
	private Sprite sprite; // The current sprite of the animation
	private int rate = 5; // How often the sprite will change
	private int time = 0; // How much time has elapsed since the start of the animation
	private int length = -1; // The amount of frames in the animation defaulted to -1
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {  // Creates an animation 
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0]; // Default sprite is at the first position in the array of sprites
		if(length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long!");  // Handles the case where the length of the animation is longer than the sprites in the spritesheet 
	}	
	
	public void update() {  // Updates the animation cycle 
		time++; // Increments the time value each time the cycle updates
		if(time % rate == 0) { // Loops through the animation at the specified rate
			if(frame >= length - 1) frame = 0; // Resets the frames when it has exceeded the length of the animation
			else frame++; // Goes to the next frame
			sprite = sheet.getSprites()[frame]; // Outputs the sprite that is at the position of the current frame
		}
	}
	
	public Sprite getSprite() {  // returns the current sprite
		return sprite;
	}
	
	public void setFrameRate(int frames) { // Sets the frame rate to the one specified
		rate = frames;
	}
 
	public void setFrame(int index) {  // Allows the current frame to be set
		if(index > sheet.getSprites().length - 1) {   // Handles the case where the frame that is being set is larger in value than the size of the sprites array
			System.err.println("Index out of bounds in" + this);
			return;
		}
		sprite = sheet.getSprites()[index];
		
	}
	
	public int getFrame() {
		return frame;
	}
	
}
