package com.syntx.dynamitedash.entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;

public class Entity {
	 
	protected int x, y;  // location of entity
	protected Sprite sprite;  // Sprite of entity
	protected BufferedImage image; // Image of entity
	public boolean removed = false; // Specifies weather the entity has been removed from the level
	protected Level level; // The level the entity is located in
	protected final Random random = new Random();
	
	public Entity() {
		
	}
	
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void update() { // Updates the entity
		
	}
	
	public void render(Screen screen) {  // Renders the entity onto the screen
		if(sprite != null) screen.renderSprite((int)x, (int)y, sprite, true);
	}
	
	public void remove() {  // Removes entity from level
		removed = true;
	}
	
	public int getX() {  // Return the x coordinate of the entity
		return x;
	}
	
	public int getY() {  // Return the y coordinate of the entity
		return y;
	}
	
	public Sprite getSprite() { // Returns the entities sprite
		return sprite;
	}
	
	public boolean isRemoved() { // Returns weather the entity has been removed from the level or not
		return removed;
	}
	
	public void init(Level level) {  // Assigns the current level the entity is in to level
		this.level = level;
	}
	
}
