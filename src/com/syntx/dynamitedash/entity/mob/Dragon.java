package com.syntx.dynamitedash.entity.mob;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.level.Level;

public class Dragon extends Mob {
	
	Random random = new Random();
	
	// Holds the images of the three sprites of the dragon:
	private BufferedImage wingUp = null; 
	private BufferedImage wingDown = null;
	private BufferedImage shoot = null;
	
	
	private BufferedImage currentSprite = wingUp; // The current image
	
	int health = 300; // Dragons health
	int time = 0; // Time tracker
	private int fireRate = 5; // Fire rate of the dragon
	private int fireTracker = fireRate; // This tracks the current value of the fire delay
	private int shots; // How many shots the dragon shoots
	boolean fire = false; // If the dragon has fired its shots
	boolean move = false; // If the dragon still needs to move
	boolean hurt = false; // If the dragon has been hurt
	boolean firstHit = false, secondHit = false, thirdHit = false; // Holds weather the dragon has been hit and how many times
	int tileX; // The x tile position of the  dragon 
	int playerX, playerY; // The players positions in tiles
	// Each tile that the player will be hurt when the player enters:
	int x1 = 53, x2 = 54, x3 = 55; 
	int y1 = 16, y2 = 17, y3 = 18;
	// The position of the dragons rendering:
	int xx = 1040;
	int yy = 272;
	
	
	public Dragon(int x, int y) { // Spawns the dragon at the postions
		this.x = x << 4;
		this.y = y << 4;
		try {
			this.wingUp = ImageIO.read(new File("res/textures/sheets/wingUp.png"));
			this.wingDown = ImageIO.read(new File("res/textures/sheets/wingDown.png"));
			this.shoot = ImageIO.read(new File("res/textures/sheets/shoot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = wingUp; // Default dragon image
	}
	public void update() {
		time++;
		// Handles the health:
		if(health <= 0) removed = true;
		if(!removed) {
			Player p = Level.getClientPLayer();
			playerX = (int)Math.ceil(p.getX() / 16); // The players current x tile coordinate
			playerY = (int)Math.ceil(p.getY() / 16); // The players current y tile coordinate
			if((playerX == x1 || playerX == x1 || playerX == x1) && (playerY == y1 || playerY == y2 || playerY == y3)) p.damage(25); // Damages the player if it enters gate area
		}
		// Handles the animation:
		if (time % 30 == 0 && shots == 0) {
			if (currentSprite == wingUp) { 
				currentSprite = wingDown;
			} else currentSprite = wingUp;
		}
		if(hurt) {
			if (time % 60 != 0) {
				if(time % 5 == 0) {
					currentSprite = wingUp;
				}else currentSprite = wingDown;
			} else hurt = false;
		}
		//Handles the movement:
		if(time % 600 == 0) {
			fire = true;
			move = true;
		}
		tileX = x / 16;
		double xa = 0;
		if(move && tileX != 52) {
			xa--;
			move(xa, 0);
		}
		// Handles the firing:
		if(fire) {
			if (fireTracker > 0) fireTracker --; // Decreases the fire rate every update cycle
			if(fireTracker <= 0) {
				currentSprite = shoot; 
				shoot(x, y, random.nextInt(360) + 1, this);
				shots++;
				fireTracker = fireRate;
			}
		}
		if(shots >= 50) {
			currentSprite = wingUp;
			fire = false;
			shots = 0;
		}
		if (fire == false && time % 200 == 0 && move) {
			fire = true;
		} 
	}

	public void render(Screen screen) {
	}

	public void damage(int i) { // What happens when the dragon is damaged
		health -= i;
		hurt = true;
		// Spawns mobs based  on how many times its been hit
		if (!firstHit) {
			firstHit = true;
			level.add(new Dummy(59, 14));
			level.add(new Dummy(59, 15));
			level.add(new Dummy(59, 16));
			level.add(new Dummy(59, 17));
			level.add(new Dummy(59, 18));
			level.add(new Dummy(59, 19));
			level.add(new Dummy(59, 20));
		} else if(!secondHit) {
			secondHit = true;
			level.add(new ShooterProLeft(60, 18));
			level.add(new ShooterProLeft(60, 16));
		} else {
			level.add(new Zombie(57, 22));
			level.add(new Zombie(51, 22));
			level.add(new ChaserPro(62, 35));
		}
	}
	
	public void render(Graphics g) { // Renders the dragon
		System.out.println(x);
		System.out.println(y);
		g.drawImage(currentSprite, x + 765, y + 200, null);
		
	}

}
