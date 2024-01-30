package com.syntx.dynamitedash.entity.powerup;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.mob.Mob.Direction;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.tile.Tile;
// Adds a box in front of the player
public class Box extends Powerup {
	static String path = "res/textures/sprites/boxicon.png";
	static Sprite sprite = Sprite.boxPowerup;
	int playerX, playerY;
	Direction dir; 
	 
	public Box(int x, int y){	 
		super(x, y, path, sprite);
	}
	
	public void update() {
		super.update();
	}
	
	public void effect(Player player) { // Adds a box in front of the player
		playerX = player.getX();
		playerY = player.getY();
		
		dir = player.dir;
		if(dir == Direction.UP)level.setTile(playerX, playerY - 16, Tile.box);
		else if(dir == Direction.DOWN)level.setTile(playerX, playerY + 16, Tile.box);
		else if(dir == Direction.RIGHT)level.setTile(playerX + 16, playerY, Tile.box);
		else if(dir == Direction.LEFT)level.setTile(playerX - 16, playerY, Tile.box);
		else level.setTile(playerX, playerY, Tile.box);
	}
	

	public void render(Screen screen) {
		super.render(screen);
	}
}
