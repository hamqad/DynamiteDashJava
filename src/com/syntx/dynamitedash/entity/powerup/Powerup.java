package com.syntx.dynamitedash.entity.powerup;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.level.tile.Tile;

public abstract class Powerup extends Entity{
	protected Sprite sprite; // The sprite of the powerup
	protected String path; // The path of the powerup icon
	public BufferedImage image;
	public int x, y, powerupX, powerupY, playerX, playerY; // The pixel and tile positions of the powerup and player
	public boolean collected = false; // Weather the powerup has been collected or not
	
	public Powerup(int x, int y, String path, Sprite sprite) {  
		this.sprite = sprite;	
		this.x = x;
		this.y = y;
		this.powerupX = (int)Math.ceil(x / 16); // the x coordinate in tile precision of the powerup
		this.powerupY = (int)Math.ceil(y / 16); // the y coordinate tile precision of the powerup
		this.path = path;
		setIcon();
	}
	
	public void update() { 
		for(int p = 0; p < Level.getPlayers().size(); p++) {
			Player player = level.getPlayerAt(p);
			if(!player.onMenu) { // Checks for player collision if the player doesn't already have a powerup
				if(!collected) { // Checks for player collision if the powerup hasn't already been collected
					for(int i = 0; i < Level.getPlayers().size(); i++) {  // Loops through each player on the level and checks for collision with the powerup
						playerX = (int)Math.ceil(Level.players.get(i).getX() / 16); // X coordinate of the player in tile precision
						playerY = (int)Math.ceil(Level.players.get(i).getY() / 16); // Y coordinate of the player in tile precision
						if(playerX == powerupX && playerY == powerupY) {  
							Level.players.get(i).setPowerup(this); // Sets the players powerup to the current one
							player = Level.players.get(i); // Sets the player to the one that collected the powerup
							collected = true; // Sets collected to true
							player.onMenu = true; // Puts the powerup in the tray
						}
					}
				}
			}
			if (collected && player.used) { // If the powerup has been collected and the player activated it, carry out its effect
				player.used = false;
				effect(player); // Carries out the effect on the player
				removed = true; // Removes the powerup after its use has been carried out
			}
		}	
	}
	
	abstract public void effect(Player player); // What the powerup will achieve after activation
	
	public void setIcon() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPath(){ // Returns the path of the powerup's icon
		return path; 
		
	}
	
	public void render (Screen screen) { // Renders the powerup on the level if it hasn't already been collected
		if(!collected && (level.getTile(powerupX, powerupY) != Tile.level1_box)) screen.renderSprite(x, y, sprite, true);
	}
	

}
