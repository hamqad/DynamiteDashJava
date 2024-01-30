package com.syntx.dynamitedash.entity.mob;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.entity.powerup.Box;
import com.syntx.dynamitedash.entity.powerup.Clone;
import com.syntx.dynamitedash.entity.powerup.ExtraBomb;
import com.syntx.dynamitedash.entity.powerup.FastPlace;
import com.syntx.dynamitedash.entity.powerup.Ghost;
import com.syntx.dynamitedash.entity.powerup.HealthPotion;
import com.syntx.dynamitedash.entity.powerup.MegaBomb;
import com.syntx.dynamitedash.entity.powerup.Powerup;
import com.syntx.dynamitedash.entity.powerup.SpeedBoost;
import com.syntx.dynamitedash.entity.projectile.FireBall;
import com.syntx.dynamitedash.entity.projectile.Projectile;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.graphics.UI.UIActionListener;
import com.syntx.dynamitedash.graphics.UI.UIButton;
import com.syntx.dynamitedash.graphics.UI.UILabel;
import com.syntx.dynamitedash.graphics.UI.UIManager;
import com.syntx.dynamitedash.graphics.UI.UIPanel;
import com.syntx.dynamitedash.graphics.UI.UIProgressBar;
import com.syntx.dynamitedash.util.Vector2i;

public class Player extends Mob {
	
	protected Sprite sprite;
	boolean walking = false; // True if player is walking
	
	protected int time = 0;
	
	protected boolean reset = false;

	private UIPanel panel;
	
	// Player attributes:
	public int health = 100;
	public double speed = 1;
	protected int fireRate = 10;
	public boolean invisibility = false;
	public String id;
	
	protected double xa, ya;
	
	// Dynamite attributes:
	public int range = 2; // The radius of the dynamite explosions
	public int dynamiteNumber = 1; // How many dynamite can be placed at the same time
	public int placeRate = 5; // How fast multiple dynamite can be placed
	protected int currentPlace = placeRate; // Tracks current place rate
	public int detonateTime = 2; // How long it takes before the dynamite detonates
	
	// Powerup attributes:
	Powerup currentPowerup = null; // Holds the current powerup available
	public boolean used = false;
	public boolean onMenu = false;
	
	// Initializes the User interface variables:
	protected UIManager ui; // Creates a UI manager
	protected UIProgressBar uiHealthBar; // Creates a health bar component
	protected UIButton exit; // Creates a exit button
	protected UIButton powerupIcon; // Creates the power-up icon
    BufferedImage image = null;
    BufferedImage plain = null;
	BufferedImage box = null;
	BufferedImage clone = null;
	BufferedImage extraBomb = null;
	BufferedImage fastPlace = null;
	BufferedImage ghost = null;
	BufferedImage healthPotion = null;
	BufferedImage megaBomb = null;
	BufferedImage speedBoost = null;
	
    
	public Player(String id, int x, int y, UIPanel panel) {   // The x and y values are the coordinates of the player 		
		// Sets up the User interface for the player:
		ui = Game.getUIManager(); // Sets the UI manager 
		this.panel = panel;
		ui.addPanel(panel); // Adds the panel to the UI manager
		
		// Sets up the player ID label:
		UILabel idLabel = new UILabel(new Vector2i(8 * 3, 13 * 3), id); // The label for the player ID 
		idLabel.setColour(0xffe74c3c); // Sets the colour of the ID label
		idLabel.setFont (new Font("Verdana", Font.BOLD, 25));  // Sets the font of the ID label
		idLabel.dropShadow = false; // Adds a drop shadow to the ID label
		panel.addComponent(idLabel);  // Adds the ID label to the panel which displays "Player 1"
		
		// Sets up the health progress bar:
		uiHealthBar = new UIProgressBar(new Vector2i(53 * 3, 7 * 3), new Vector2i(80 * 3 - 20, 20));
		uiHealthBar.setColour(0x6a6a6a);
		uiHealthBar.setForegroundColour(0xee3030);
		panel.addComponent(uiHealthBar); 
		
		// Sets up the HP label on the health bar:
		UILabel hpLabel = new UILabel(new Vector2i (uiHealthBar.position).add(new Vector2i(100, 17)), "HP");
		hpLabel.setColour(0xFFFFFF);
		hpLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel.addComponent(hpLabel);
		
		// Sets up the exit button:
		UIPanel topRight = (UIPanel) new UIPanel(new Vector2i(0,  0), new Vector2i(20*3, 16*3), false).setColour(0xff636e72);
		exit = new UIButton(new Vector2i(0,0), new Vector2i(20 * 3, 16 * 3), new UIActionListener() {
			public void perform() { // Exits the game when pressed 
				Game.state = Game.STATE.SINGLEPLAYER;
				Game.spawned = false;
			}
		}, true);
		ui.addPanel(topRight);
		exit.setText("Exit");
		topRight.addComponent(exit);
				
		// Sets up the powerup icons:
		try {
			plain = ImageIO.read(new File("res/textures/sprites/plainicon.png"));
			box = ImageIO.read(new File("res/textures/sprites/boxicon.png"));
			clone = ImageIO.read(new File("res/textures/sprites/cloneicon.png"));
			extraBomb = ImageIO.read(new File("res/textures/sprites/extraicon.png"));
			fastPlace = ImageIO.read(new File("res/textures/sprites/placerateicon.png"));
			ghost = ImageIO.read(new File("res/textures/sprites/ghosticon.png"));
			healthPotion = ImageIO.read(new File("res/textures/sprites/potionicon.png"));
			megaBomb = ImageIO.read(new File("res/textures/sprites/megaicon.png"));
			speedBoost = ImageIO.read(new File("res/textures/sprites/speedicon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		powerupIcon = new UIButton(new Vector2i(140 * 3, 0 * 3), plain);
		panel.addComponent(powerupIcon);
		
		// Default attributes for the player:
		this.id = id;
		this.x = x;
		this.y = y;
		fireRate = FireBall.FIRE_RATE;
	}
	
	public void update() {  // Updates players attributes
		if(used) powerupIcon.setImage(plain);
		used = false;
		time++;
		uiHealthBar.setProgress(health / 100.0); // Sets the health progress bar to the health of the player
		if(health <= 0 && Game.state == Game.STATE.GAME) {
			Game.state = Game.STATE.GAMEOVER;
		}
		if(fireRate > 0) fireRate--; 
		if(currentPlace > 0) currentPlace --;
		// Updates players position and the animation depending on which key is pressed :
		clear(); 
		updateInvisibilty();
		//Sets up the power-up icon:
		if(currentPowerup != null && onMenu) { // If the player has picked up a powerup 
			if(currentPowerup instanceof Box) powerupIcon.setImage(box);
			if(currentPowerup instanceof Clone) powerupIcon.setImage(clone);
			if(currentPowerup instanceof ExtraBomb) powerupIcon.setImage(extraBomb);
			if(currentPowerup instanceof FastPlace) powerupIcon.setImage(fastPlace);
			if(currentPowerup instanceof Ghost) powerupIcon.setImage(ghost);
			if(currentPowerup instanceof HealthPotion) powerupIcon.setImage(healthPotion);
			if(currentPowerup instanceof MegaBomb) powerupIcon.setImage(megaBomb);
			if(currentPowerup instanceof SpeedBoost) powerupIcon.setImage(speedBoost);
		} else if(image != null || !onMenu) { // If the powerup has been used
			if(currentPowerup instanceof Box) powerupIcon.setImage(plain); // Change the powerup area on the UI back to the normal colour
		}
		
	} 
		
	public void updateInvisibilty() { // Updates the players invisibility
		if (invisibility) {
			if (time % 300 == 0) {
				invisibility = false;
			}
		}
	}
		
	
	private void clear() {  // Clears all removed projectiles
		for(int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPowerup(Powerup powerup) { // Sets the current powerup to thegive one
		currentPowerup = powerup;
	}
		
	public void deductPlayerHealth(int x , int y) {
		
	}
	
	public void addHealth(int h) {  // Adds health to the players current health 
		for(int i = h; i >= 0; i--) {
			if(!((health + i) > 100)) { 
				health += i;
				return;
			}
			continue;
		}
	}
	
	public void damage(int i) {
		
	}
	
	public void render(Screen screen) {  // Renders the player 
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
