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
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.util.Vector2i;

public class MPLevelSelectScreen {
	
	static BufferedImage screen = null;
	static UIButton one, two, three, four, five, six, seven, eight, nine, ten, back;
	private static UIManager ui = Game.mpLevelSelectManager;
	
	public static void setUp() {
		try {
			screen = ImageIO.read(new File("res/gamescreens/multiplayerlevel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Home: 
		one = new UIButton(new Vector2i(240, 466), new Vector2i(213, 213), new UIActionListener() {
			public void perform() {
				Level.reset();
				Level.level = Level.LEVEL.MPONE;
				Game.state = Game.STATE.MPGAME;
				//ui.clear();
			}
		}, false);
		
		
		two = new UIButton(new Vector2i(546, 466), new Vector2i(213, 213), new UIActionListener() {
			public void perform() {
				Level.reset();
				Level.level = Level.LEVEL.MPTWO;
				Game.state = Game.STATE.MPGAME;
				//ui.clear();
			}
		}, false);
		
		
		three = new UIButton(new Vector2i(853, 466), new Vector2i(213, 213), new UIActionListener() {
			public void perform() {
				Level.reset();
				Level.level = Level.LEVEL.MPTHREE;
				Game.state = Game.STATE.MPGAME;
				//ui.clear();
			}
		}, false);
		
		four = new UIButton(new Vector2i(1160, 466), new Vector2i(213, 213), null, true);
		five = new UIButton(new Vector2i(1466, 466), new Vector2i(213, 213), null, true);
		six = new UIButton(new Vector2i(240, 752), new Vector2i(213, 213), null, true);	
		seven = new UIButton(new Vector2i(546, 752), new Vector2i(213, 213), null, true);
		eight = new UIButton(new Vector2i(853, 752), new Vector2i(213, 213), null, true);
		nine = new UIButton(new Vector2i(1160, 752), new Vector2i(213, 213), null, true);
		ten = new UIButton(new Vector2i(1466, 752), new Vector2i(213, 213), null, true);
		
		
		back = new UIButton(new Vector2i(49, 34), new Vector2i(54, 54), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.MPPLAYERSELECT;
			}
		}, false); 
		
	// College:
	/*	 one = new UIButton(new Vector2i(203, 394), new Vector2i(180, 180), new UIActionListener() {
			public void perform() {
				Game.level = Level.mp_one;
				Game.state = Game.STATE.GAME;
				//ui.clear();
			}
		}, false);
		
		
		two = new UIButton(new Vector2i(462, 394), new Vector2i(180, 180), new UIActionListener() {
			public void perform() {
				Game.level = Level.mp_two;
				Game.state = Game.STATE.GAME;
				//ui.clear();
			}
		}, false);
		
		
		three = new UIButton(new Vector2i(722, 394), new Vector2i(180, 180), new UIActionListener() {
			public void perform() {
				Game.level = Level.mp_three;
				Game.state = Game.STATE.GAME;
				//ui.clear();
			}
		}, false);
		
		four = new UIButton(new Vector2i(981, 394), new Vector2i(180, 180), null, true);
		five = new UIButton(new Vector2i(1240, 394), new Vector2i(180, 180), null, true);
		six = new UIButton(new Vector2i(203, 636), new Vector2i(180, 180), null, true);	
		seven = new UIButton(new Vector2i(462, 636), new Vector2i(180, 180), null, true);
		eight = new UIButton(new Vector2i(722, 636), new Vector2i(180, 180), null, true);
		nine = new UIButton(new Vector2i(981, 636), new Vector2i(180, 180), null, true);
		ten = new UIButton(new Vector2i(1240, 636), new Vector2i(180, 180), null, true);
		
		
		back = new UIButton(new Vector2i(41, 29), new Vector2i(46, 46), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.MPPLAYERSELECT;
			}
		}, false); */

		
		ui.addButton(one);
		ui.addButton(two);
		ui.addButton(three);
		ui.addButton(four);
		ui.addButton(five);
		ui.addButton(six);
		ui.addButton(seven);
		ui.addButton(eight);
		ui.addButton(nine);
		ui.addButton(ten);
		ui.addButton(back);
	}
	
	public static void render(Graphics g) {
		g.drawImage(screen, 0, 0, null);
		ui.render(g);

	}
	
}
