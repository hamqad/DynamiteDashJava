package com.syntx.dynamitedash;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.entity.mob.PlayerOne;
import com.syntx.dynamitedash.entity.mob.PlayerThree;
import com.syntx.dynamitedash.entity.mob.PlayerTwo;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.graphics.UI.UIManager;
import com.syntx.dynamitedash.graphics.gamestates.GameOverScreen;
import com.syntx.dynamitedash.graphics.gamestates.HelpScreen;
import com.syntx.dynamitedash.graphics.gamestates.HomeScreen;
import com.syntx.dynamitedash.graphics.gamestates.LoadingScreen;
import com.syntx.dynamitedash.graphics.gamestates.MPLevelSelectScreen;
import com.syntx.dynamitedash.graphics.gamestates.MPPlayerSelectScreen;
import com.syntx.dynamitedash.graphics.gamestates.SinglePlayerScreen;
import com.syntx.dynamitedash.graphics.gamestates.WinScreen;
import com.syntx.dynamitedash.input.Keyboard;
import com.syntx.dynamitedash.input.Mouse;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	public enum STATE{
		LOADING,
		HOME,
		SINGLEPLAYER,
		MPPLAYERSELECT,
		MPLEVELSELECT,
		HELP,
		GAME,
		MPGAME,
		WIN,
		GAMEOVER
	};
	
	public static STATE state = STATE.LOADING;
	
	private static final long serialVersionUID = 1L;

	private static int width = 1000; // The width of the game window
	private static int height = width / 16 * 9; // The height of the game window
	private static int scale = 3; // What the game will be scaled by to cause retro effect
	public static String title = "Dynamite Dash"; // Title of the game window
	public static int playerCount = 1; // How many players need to be spawned

	// Creates all objects
	private Thread thread;
	private JFrame frame; // The window
	private Keyboard key1; // The keyboard listener for the first player
	public static Level level; // The current level
	
	private PlayerOne player1; // Main player
	private PlayerTwo player2; // Second player
	private PlayerThree player3; // Third player
	public static Player winner = null; // The winner in a multiplayer match
	public static boolean oneAlive, twoAlive, threeAlive; // Weather each of the players are currently alive or not
	public static boolean spawned = false; // Weather the level adn players have been spawned
	private boolean running = false; // Holds the value of weather the game is running or not

	static UIManager uiManager; // The UI Manager of the main game
	public static UIManager homeScreenManager; // The UI Manager of the home screen
	public static UIManager singlePlayerManager; // The UI Manager of the single player screen
	public static UIManager mpPlayerSelectManager; // The UI Manager of the multiplayer player select screen
	public static UIManager mpLevelSelectManager; // The UI Manager of the multiplayer level select screen 
	public static UIManager helpManager; // The UI Manager of the helps screen
	public static UIManager gameOverManager; // The UI Manager of the game over screen
	public static UIManager winManager; // The UI Manager of the win screen
	


	private Screen screen; // The main screen
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Holds the actual image that can be manipulated
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // The array of pixels of the buffered image

	public Game() { // Code here will only be called once
		Dimension size = new Dimension(width * scale, height * scale); // Sets the dimension for the game window
		setPreferredSize(size); // Sets size of window to the dimension

		// Instantiating all objects:
		screen = new Screen(width, height); // Instantiates the screen with the width and height required
		
		uiManager = new UIManager();
		homeScreenManager = new UIManager();
		singlePlayerManager = new UIManager();
		mpPlayerSelectManager = new UIManager();
		mpLevelSelectManager = new UIManager();
		helpManager = new UIManager();
		gameOverManager = new UIManager();
		winManager = new UIManager();
		
		frame = new JFrame();  // Instantiates the Jframe
		key1 = new Keyboard(); // Instantiates the keyboard class
		
		// Instantiates the mouse class and sets it up:
		Mouse mouse = new Mouse(); 
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() { // Returns the game windows width
		return width * scale;
	}

	public static int getWindowHeight() { // Returns the game windows height
		return height * scale;
	}
	
	public static UIManager getUIManager() { // Returns the games UI manager
		return uiManager;
	}

	public synchronized void start() { // starts the thread
		running = true;
		thread = new Thread(this, "Display"); // instantiates a new thread for the game object
		thread.start();
	}

	public synchronized void stop() { // stops the thread
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() { // The game loop. This code will be run whilst the game is running. It will be run when the thread is started
		long lastTime = System.nanoTime(); // Used for calculations
		long timer = System.currentTimeMillis(); // The current time , used for calculations
		final double ns = 1000000000.0 / 60.0; // 60 times a second
		double delta = 0;
		int frames = 0; // Holds the amount of frames per second
		int updates = 0; // Holds the amount of updates per second
		requestFocus(); // Focuses the game window as soon as the game is run
		while (running) { // This code is executed whilst the game is running
			// The following code assures that the update method is called 60 times a second for consistency as all instances of this game will be updated at the same speed regardless of the computer running it
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) { // Occurs every 1000 nano seconds
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps"); // Adds the FPS and UPS on the title of the game
				updates = 0; // Resets updates
				frames = 0; // Resets frames
			}
		}
		stop(); // Stops the thread when the game stops running
	}

	public void update() { // Updates each of the game object
		if(state == STATE.GAME || state == STATE.MPGAME) { // Sets up the game
			uiManager.update(); 
			if (!spawned) { // Spawns the level and players
				Level.reset(); // Resets previous level
				Level.init(); // Initialises new level
				TileCoordinate player1Spawn = Level.getPlayerOneCoordinate(); // Coordinates of the players spawn location
				player1 = new PlayerOne("Player 1", player1Spawn.x(), player1Spawn.y(), key1); // Spawns a player at the spawn position
				oneAlive = true; 
				level.add(player1); // Adds the player to the levels players list
				addKeyListener(key1);
				if (playerCount == 2 && state == STATE.MPGAME) { // Spawns the second player
					TileCoordinate player2Spawn = Level.getPlayerTwoCoordinate(); // Coordinates of the players spawn location
					player2 = new PlayerTwo("Player 2", player2Spawn.x(), player2Spawn.y(), key1); // Spawns second player
					twoAlive = true;
					level.add(player2);
				}
				if (playerCount == 3 && state == STATE.MPGAME) { // Spawns the third player
					TileCoordinate player2Spawn = Level.getPlayerThreeCoordinate(); // Coordinates of the players spawn location
					player2 = new PlayerTwo("Player 2", player2Spawn.x(), player2Spawn.y(), key1); // Spawns second player
					twoAlive = true;
					level.add(player2);
					
					TileCoordinate player3Spawn = level.player3Spawn; // Coordinates of the players spawn location
					player3 = new PlayerThree("Player 3", player3Spawn.x(), player3Spawn.y()); // Spawns second player
					threeAlive = true;
					level.add(player3);
				}
				spawned = true;
			}
			if(state == STATE.MPGAME) {
				// If there is only one player alive, it sets him to the winner:
				if(oneAlive && !twoAlive && !threeAlive) winner = player1; 
				if(!oneAlive && twoAlive && !threeAlive) winner = player2;
				if(!oneAlive && !twoAlive && threeAlive) winner = player3;
				// If a winner is selected, goes to the win screen:
				if(winner != null) {
					state = STATE.WIN;
				}
			}
			key1.update();
			level.update();
		}
		// Updates the UI manager depending on what screen is loaded:
		if(state == STATE.LOADING) {
			LoadingScreen.update();
		}
		if(state == STATE.HOME) {
			homeScreenManager.update();
		}
		if(state == STATE.SINGLEPLAYER) {
			singlePlayerManager.update();
		}
		if(state == STATE.MPPLAYERSELECT) {
			mpPlayerSelectManager.update();
		}
		if(state == STATE.MPLEVELSELECT) {
			mpLevelSelectManager.update();
		}
		if(state == STATE.HELP) {
			helpManager.update();
		}
		if(state == STATE.GAMEOVER) {
			gameOverManager.update();
		}
		if(state == STATE.WIN) {
			winManager.update();
		}
		
	}

	public void render() { // Handles the rendering of the game 
		BufferStrategy bs = getBufferStrategy();  // Retrieves the buffer strategy of the canvas class
		if (bs == null) { // If there is no buffer strategy, a buffer strategy will be created to hold three calculated screens in memory for speed improvement 
			createBufferStrategy(3);  
			return;
		}
		Graphics g = bs.getDrawGraphics(); // Used to draw graphics onto the buffer strategy
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Fills the screen with the contents of the buffer with the width and height of the window
		
		screen.clear();   // Clears the screen every time the next image is being rendered 
		double xScroll = 0;  // player.get()x - screen.width / 2;
		double yScroll = 0; // player.get()y - screen.width / 2;
		if ((state == STATE.MPGAME || state == STATE.GAME) && level != null) {
			level.render((int)xScroll, (int)yScroll, screen, g); 
		//	screen.renderSheet(50, 50, SpriteSheet.dragon, true);
		//	screen.renderSheet(500, 50, SpriteSheet.tiles, true);
			
		}
		for(int i = 0; i < pixels.length; i++) {  // The Buffered image screen pixels will be set to the pixels set in the screen class
			pixels[i] = screen.pixels[i];
		}
		
		// Renders the UI manager depending on which screen is loaded:
		if(state == STATE.GAME || state == STATE.MPGAME) {
			uiManager.render(g);
		}
		if(state == STATE.LOADING) {
			LoadingScreen.setUp();
			LoadingScreen.render(g);
		}
		if(state == STATE.HOME) {
			HomeScreen.setUp();
			homeScreenManager.render(g);
			HomeScreen.render(g);
		}
		if(state == STATE.SINGLEPLAYER) {
			SinglePlayerScreen.setUp();
			SinglePlayerScreen.render(g);
			singlePlayerManager.render(g); 
		}
		if(state == STATE.MPPLAYERSELECT) {
			MPPlayerSelectScreen.setUp();
			MPPlayerSelectScreen.render(g);
			mpPlayerSelectManager.render(g);
		}
		if(state == STATE.MPLEVELSELECT) {
			MPLevelSelectScreen.setUp();
			MPLevelSelectScreen.render(g);
			mpLevelSelectManager.render(g);
		}
		if(state == STATE.HELP) {
			HelpScreen.setUp();
			HelpScreen.render(g);
			helpManager.render(g);
		}
		if(state == STATE.GAMEOVER) {
			GameOverScreen.setUp();
			GameOverScreen.render(g);
			gameOverManager.render(g);
		}
		if(state == STATE.WIN) {
			WinScreen.setUp();
			WinScreen.render(g);
			winManager.render(g);
		}
		g.dispose();  // Disposes the current graphics on the buffer
		bs.show(); // Shows the calculated buffer onto the screen
	}

	public static void main(String[] args) { // The Start of program
		Game game = new Game();
		game.frame.setResizable(false); // Makes sure the window of the game cannot be resized to avoid graphical issues
		game.frame.setTitle("Dynamite Dash"); // Sets title of window
		game.frame.add(game); // Fills the window with the game class (canvas)
		game.frame.pack(); // Sets the size of the frame according to the components resolution
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates application when the X button is pressed
		game.frame.setLocationRelativeTo(null); // Centers the game window on the screen
		game.frame.setVisible(true); // Shows the window
		game.start();
		

	}

}
