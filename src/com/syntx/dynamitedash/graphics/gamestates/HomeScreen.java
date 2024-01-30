package com.syntx.dynamitedash.graphics.gamestates;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.graphics.UI.UIActionListener;
import com.syntx.dynamitedash.graphics.UI.UIButton;
import com.syntx.dynamitedash.graphics.UI.UIManager;
import com.syntx.dynamitedash.input.Mouse;
import com.syntx.dynamitedash.util.Vector2i;

public class HomeScreen {
	
	static BufferedImage screen = null;
	static UIButton singlePlayer, multiPlayer, help, exit;
	private static UIManager ui = Game.homeScreenManager;
	
	public static void setUp() {
		try {
			screen = ImageIO.read(new File("res/gamescreens/home.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	// Home:
			singlePlayer = new UIButton(new Vector2i(715, 558), new Vector2i(490, 97), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.SINGLEPLAYER;
				//ui.clear();
			}
		}, false);
		
		multiPlayer = new UIButton(new Vector2i(715, 692), new Vector2i(490, 97), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.MPPLAYERSELECT;
				//ui.clear();
			}
		}, false);
		
		help = new UIButton(new Vector2i(715, 826), new Vector2i(490, 97), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.HELP;
				//ui.clear();
			}
		}, false);
		
		exit = new UIButton(new Vector2i(49, 34), new Vector2i(54, 54), new UIActionListener() {
			public void perform() {
				System.exit(0);
			}
		}, false); 
		
	// College:
	/*	singlePlayer = new UIButton(new Vector2i(605, 472), new Vector2i(415, 82), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.SINGLEPLAYER;
				//ui.clear();
			}
		}, false);
		
		multiPlayer = new UIButton(new Vector2i(605, 585), new Vector2i(415, 82), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.MPPLAYERSELECT;
				//ui.clear();
			}
		}, false);
		
		help = new UIButton(new Vector2i(605, 699), new Vector2i(415, 82), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.HELP;
				//ui.clear();
			}
		}, false);
		
		exit = new UIButton(new Vector2i(41, 29), new Vector2i(46, 46), new UIActionListener() {
			public void perform() {
				System.exit(0);
			}
		}, false); */
		
		
		ui.addButton(singlePlayer);
		ui.addButton(multiPlayer);
		ui.addButton(help);
		ui.addButton(exit);
	}
	public static void render(Graphics g) {			
		g.drawImage(screen, 0, 0, null);
		ui.render(g);
	}
}

