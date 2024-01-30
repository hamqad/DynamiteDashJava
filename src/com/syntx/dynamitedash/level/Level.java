package com.syntx.dynamitedash.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.dynamite.Dynamite;
import com.syntx.dynamitedash.entity.explosion.Explosion;
import com.syntx.dynamitedash.entity.gate.Gate;
import com.syntx.dynamitedash.entity.mob.Mob;
import com.syntx.dynamitedash.entity.mob.Player;
import com.syntx.dynamitedash.entity.particle.Particle;
import com.syntx.dynamitedash.entity.powerup.Powerup;
import com.syntx.dynamitedash.entity.projectile.Projectile;
import com.syntx.dynamitedash.entity.spawner.PowerupSpawner;
import com.syntx.dynamitedash.graphics.Screen;
import com.syntx.dynamitedash.level.tile.Tile;
import com.syntx.dynamitedash.util.Vector2i;

public class Level {
	
	public enum LEVEL{
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		TEN,
		MPONE,
		MPTWO,
		MPTHREE
	};

	public static LEVEL level;
	static boolean progressed = false;
	
	public int width, height; // Dimension of level
	protected int[] tilesInt; // Array of the specific tiles the level contains
	public int[] tiles; // Array of pixels of the map
	protected int tile_size;
	public TileCoordinate player1Spawn;
	public TileCoordinate player2Spawn;
	public TileCoordinate player3Spawn;
	private static List<Entity> entities = new ArrayList<Entity>(); // Holds a list of entities on the level
	private static List<Projectile> projectiles = new ArrayList<Projectile>(); // Holds a list of projectiles on the level
	private static List<Particle> particles = new ArrayList<Particle>(); // Holds a list of particles on the level
	private static List<Dynamite> dynamite = new ArrayList<Dynamite>(); // Holds a list of dynamite on the level
	private static List<Explosion> explosions = new ArrayList<Explosion>(); // Holds a list of dynamite on the level
	public static List<Mob> enemies = new ArrayList<Mob>(); // Holds a list of enemies on the level
	private static List<Powerup> powerups = new ArrayList<Powerup>(); // Holds a list of powerups on the level
	private static List<Gate> gates = new ArrayList<Gate>(); // Holds a list of gates on the level
	

	private boolean changed = false;

	public static List<Player> players = new ArrayList<Player>(); // Holds a list of players on the level
	
	private Comparator<Node> nodeSorter = new Comparator<Node>() { // Compares and sorts two nodes using their total cost
		public int compare(Node n0, Node n1) {
			if(n1.fCost < n0.fCost) return +1; // if the first node's cost is smaller than the second node, return +1 which will move it up in the node list by 1
			if(n1.fCost > n0.fCost) return -1; // if the first node's cost is larger than the second node, return -1 which will move it down in the node list by 1
			return 0; // Returns 0 if the nodes cost are the same 
		}
		
	};	
	
	public Level(int width, int height) { // Creates random level

		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	public Level(String path) { // Creates a level using an image
		generateLevel();
		loadLevel(path);
		new PowerupSpawner(this);
//		for (int i = 0; i < entities.size(); i++) { // Loops through each entity on the level and updates it
//			System.out.println(entities.get(i));
//		}
//		for (int i = 0; i < projectiles.size(); i++) { // Loops through each projectile on the level and updates it
//			System.out.println(projectiles.get(i));
//		}
//		for (int i = 0; i < particles.size(); i++) { // Loops through each particle on the level and updates it
//			System.out.println(particles.get(i));
//		}
//		for (int i = 0; i < players.size(); i++) { // Loops through each player on the level and updates it
//			System.out.println(players.get(i));
//		}
//		for (int i = 0; i < dynamite.size(); i++) { // Loops through each dynamite on the level and updates it
//			System.out.println(dynamite.get(i));
//		}
//		for (int i = 0; i < explosions.size(); i++) { // Loops through each explosion on the level and updates it
//			System.out.println(explosions.get(i));
//		}
//		for (int i = 0; i < enemies.size(); i++) { // Loops through each enemy on the level and updates it
//			System.out.println(enemies.get(i));
//		}
//		for (int i = 0; i < powerups.size(); i++) { // Loops through each powerup on the level and updates it
//			System.out.println(powerups.get(i));
//		}
//		for (int i = 0; i < gates.size(); i++) { // Loops through each gate on the level and updates it
//			System.out.println(gates.get(i));
//		}

	}
	
	public static void init() {
		if(level == LEVEL.ONE) {
			Game.level = new LevelOne("/levels/levelone.png");
		}else if(level == LEVEL.TWO) {
			Game.level = new LevelTwo("/levels/leveltwo.png");
		}else if(level == LEVEL.THREE) {
			Game.level = new LevelThree("/levels/levelthree.png");
		}else if(level == LEVEL.FOUR) {
			Game.level = new LevelFour("/levels/levelfour.png");
		}else if(level == LEVEL.FIVE) {
			Game.level = new LevelFive("/levels/levelfive.png");	
		}else if(level == LEVEL.SIX) {
			Game.level = new LevelSix("/levels/levelsix.png");
		}else if(level == LEVEL.SEVEN) {
			Game.level = new LevelSeven("/levels/levelseven.png");
		}else if(level == LEVEL.EIGHT) {
			Game.level = new LevelEight("/levels/leveleight.png");
		}else if(level == LEVEL.NINE){
			Game.level = new LevelNine("/levels/levelnine.png");
		}else if(level == LEVEL.TEN){
			Game.level = new LevelTen("/levels/levelten.png");
		}else if(level == LEVEL.MPONE){
			Game.level = new MPLevelOne("/levels/mplevelone.png");
		}else if(level == LEVEL.MPTWO){
			Game.level = new MPLevelTwo("/levels/mpleveltwo.png");
		}else if(level == LEVEL.MPTHREE){
			Game.level = new MPLevelThree("/levels/mplevelthree.png");
		}
	}
	
	protected void generateLevel() { // generates the level
		
	}
	
	public static TileCoordinate getPlayerOneCoordinate() {
		if(Game.level instanceof LevelOne) {
			return new TileCoordinate(24, 16);
		}else if(Game.level instanceof LevelTwo) {
			return new TileCoordinate(24, 16);
		}else if(Game.level instanceof LevelThree) {
			return new TileCoordinate(24, 16);
		}else if(Game.level instanceof LevelFour) {
			return new TileCoordinate(24, 11);
		}else if(Game.level instanceof LevelFive) {
			return new TileCoordinate(21, 18);
		}else if(Game.level instanceof LevelSix) {
			return new TileCoordinate(19, 17);
		}else if(Game.level instanceof LevelSeven) {
			return new TileCoordinate(17, 17);
		}else if(Game.level instanceof LevelEight) {
			return new TileCoordinate(16, 4);
		}else if(Game.level instanceof LevelNine){
			return new TileCoordinate(14, 3);
		}else if(Game.level instanceof LevelTen){
			return new TileCoordinate(4, 17);
		}else if(Game.level instanceof MPLevelOne){
			return new TileCoordinate(30, 15);
		}else if(Game.level instanceof MPLevelTwo){
			return new TileCoordinate(1, 1);
		}else if(Game.level instanceof MPLevelThree){
			return new TileCoordinate(24, 16);
		} else {
			return new TileCoordinate(17, 17);
		}
	}
	
	public static TileCoordinate getPlayerTwoCoordinate() {
		if(Game.level instanceof MPLevelOne) {
			return new TileCoordinate(38, 21);
		}else if(Game.level instanceof MPLevelTwo) {
			return new TileCoordinate(1, 16);
		}else {
			return new TileCoordinate(24, 16);
		}
	}
	
	public static TileCoordinate getPlayerThreeCoordinate() {
		if(Game.level instanceof MPLevelOne) {
			return new TileCoordinate(12, 9);
		}else if(Game.level instanceof MPLevelTwo) {
			return new TileCoordinate(1, 11);
		}else {
			return new TileCoordinate(24, 16);
		}
	}

	protected void loadLevel(String path) { // loads the level through an image specified via the path

	}

	public void update() { // Updates the level
		for (int i = 0; i < entities.size(); i++) { // Loops through each entity on the level and updates it
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) { // Loops through each projectile on the level and updates it
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) { // Loops through each particle on the level and updates it
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) { // Loops through each player on the level and updates it
			players.get(i).update();
		}
		for (int i = 0; i < dynamite.size(); i++) { // Loops through each dynamite on the level and updates it
			dynamite.get(i).update();
		}
		for (int i = 0; i < explosions.size(); i++) { // Loops through each explosion on the level and updates it
			explosions.get(i).update();
		}
		for (int i = 0; i < enemies.size(); i++) { // Loops through each enemy on the level and updates it
			enemies.get(i).update();
		}
		for (int i = 0; i < powerups.size(); i++) { // Loops through each powerup on the level and updates it
			powerups.get(i).update();
		}
		for (int i = 0; i < gates.size(); i++) { // Loops through each gate on the level and updates it
			gates.get(i).update();
		}
		remove();
	}
	
	public void remove() {
		for (int i = 0; i < entities.size(); i++) { // Loops through each entity on the level and removes it if needed
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) { // Loops through each projectile on the level and removes it if needed
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) { // Loops through each particle on the level and removes it if needed
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) { // Loops through each player on the level and removes it if needed
			if(players.get(i).isRemoved()) players.remove(i);
		}
		for (int i = 0; i < dynamite.size(); i++) { // Loops through each dynamite on the level and removes it if needed
			if(dynamite.get(i).isRemoved()) dynamite.remove(i);
		}
		for (int i = 0; i < explosions.size(); i++) { // Loops through each explosion on the level and removes it if needed
			if(explosions.get(i).isRemoved()) explosions.remove(i);
		}
		for (int i = 0; i < enemies.size(); i++) { // Loops through each enemy on the level and removes it if needed
			if(enemies.get(i).isRemoved()) enemies.remove(i);
		}
		for (int i = 0; i < powerups.size(); i++) { // Loops through each powerup on the level and removes it if needed
			if(powerups.get(i).isRemoved()) powerups.remove(i);
		}
		for (int i = 0; i < gates.size(); i++) { // Loops through each gate on the level and removes it if needed
			if(gates.get(i).isRemoved()) gates.remove(i);
		}
	}
	
	public static void reset() {
		for (int i = 0; i < entities.size(); i++) { // Loops through each entity on the level and removes it if needed
			entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) { // Loops through each projectile on the level and removes it if needed
			projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) { // Loops through each particle on the level and removes it if needed
			particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) { // Loops through each player on the level and removes it if needed
			players.remove(i);
		}
		for (int i = 0; i < dynamite.size(); i++) { // Loops through each dynamite on the level and removes it if needed
			dynamite.remove(i);
		}
		for (int i = 0; i < explosions.size(); i++) { // Loops through each explosion on the level and removes it if needed
			explosions.remove(i);
		}
		for (int i = 0; i < enemies.size(); i++) { // Loops through each enemy on the level and removes it if needed
			enemies.remove(i);
		}
		for (int i = 0; i < powerups.size(); i++) { // Loops through each powerup on the level and removes it if needed
			powerups.remove(i);
		}
		for (int i = 0; i < gates.size(); i++) { // Loops through each gate on the level and removes it if needed
			gates.remove(i);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) { // handles the collisions of entities with tiles by taking in the x and y positions of the entity, the direction its traveling in and its size
		boolean solid = false; // Default collision is set to false
		for (int c = 0; c < 4; c++) { // Loops through each corner of the tile for corner precision collision detection
			int xt = (x - c % 2 * size + xOffset) >> 4; // Sets the area of the tile on the horizontal plane which is colliding with the player to the exact size of the tile
			int yt = (y - c / 2 * size  + yOffset) >> 4; // Sets the area of the tile on the vertical plane which is colliding with the player to the exact size of the tile
			if (getTile(xt, yt).solid()) solid = true;  // Checks weather the tile ahead of the sprite is solid or not, if it is the player wont be able to move in that direction
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen, Graphics g) { // Renders the screen
		screen.setOffset(xScroll, yScroll);
		// sets 4 corner pins:
		int x0 = xScroll >> 4; // Left side of screen. Set to the offset of screen / 16 to find which tile is located in the corner
		int x1 = (xScroll + screen.width + 16) >> 4; // Right side of screen
		int y0 = yScroll >> 4; // Top plane of the screen
		int y1 = (yScroll + screen.height + 16) >> 4; // Bottom plane of screen
		for (int y = y0; y < y1; y++) { // cycles through every pixel from the top left corner of the screen to the bottom left
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen); // Renders the tile in that position of the screen
			}
		}
		
		for (int i = 0; i < entities.size(); i++) { // Loops through each entity on the level and renders it
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) { // Loops through each projectile on the level and renders it
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) { // Loops through each particle on the level and renders it
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) { // Loops through each player on the level and renders it
			players.get(i).render(screen);
		}
		for (int i = 0; i < dynamite.size(); i++) { // Loops through each dynamite on the level and renders it
			dynamite.get(i).render(screen);
		}
		for (int i = 0; i < explosions.size(); i++) { // Loops through each explosion on the level and renders it
			explosions.get(i).render(screen);
		}
		for (int i = 0; i < enemies.size(); i++) { // Loops through each enemy on the level and renders it
			enemies.get(i).render(screen);
		}
		for (int i = 0; i < enemies.size(); i++) { // Loops through each enemy on the level and renders it
			enemies.get(i).render(g);
		}
		for (int i = 0; i < powerups.size(); i++) { // Loops through each powerup on the level and renders it
			powerups.get(i).render(screen);
		}
		for (int i = 0; i < gates.size(); i++) { // Loops through each gate on the level and renders it
			gates.get(i).render(screen);
		}
		
	}

	public void add(Entity e) { // Adds the entity to the entity list
		e.init(this); // Adds the entity to the level
		if (e instanceof Particle) { // If the entity being added is a particle, the particle is added to the particle list
			particles.add((Particle) e);
		} else if (e instanceof Projectile) { // If the entity being added is a projectile, the projectile is added to the projectile list
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) { // If the entity being added is a player, the player is added to the player list
			players.add((Player) e);
		} else if (e instanceof Dynamite){ // If the entity being added is a dynamite, the dynamite is added to the dynamite list
			dynamite.add((Dynamite) e);
		} else if (e instanceof Explosion){ // If the entity being added is an explosion, the explosion is added to the explosion list
			explosions.add((Explosion) e);
		} else if(e instanceof Mob){ // If the entity being added is a mob, the mob is added to the mob list
			enemies.add((Mob) e);
		}else if(e instanceof Powerup){ // If the entity being added is a powerup, the powerup is added to the powerup list
			powerups.add((Powerup) e);
		}else if(e instanceof Gate){ // If the entity being added is a gate, the gate is added to the gate list
			gates.add((Gate) e);
		}else {
			entities.add(e);
		}
	}
		
	
	public static List<Player> getPlayers() { // Returns the list of players
		 return players;
	}
	
	public static Player getPlayerAt(int index) { // Returns the player at a specific index
		 return players.get(index);
	}
	
	public static Player getClientPLayer() { //Returns the player playing the game
		return players.get(0);
	}
	
	 public List<Node> findPath(Vector2i start, Vector2i goal){ // Outputs a list of nodes that are the fastest way to get from the start coordinate to the goal coordinate (A* algorithm)
		List<Node> openList = new ArrayList<Node>(); // List of nodes that are adjacent to the current node 
		List<Node> closedList = new ArrayList<Node>(); // List of nodes that have been disregarded from the open list
		Node current = new Node(start, null, 0, getDistance(start, goal)); // The current node being traversed which is by default the node the start position is currently on
		openList.add(current); // Adds the current node into the openList
		while (openList.size() > 0) { // Checks for nodes to reach the goal as long as the list of available nodes is not empty0
			Collections.sort(openList, nodeSorter); // Sorts the open list using the node sorter so that node with the shortest cost will be at the first index in the list 
			current = openList.get(0); // Sets the current node as the first node in the list of available nodes which will be the closest
			if(current.tile.equals(goal)) { // Stops looking for a path if the goal has been reached and creates a list of nodes which represent the closest path and returns the list
				List<Node> path = new ArrayList<Node>(); // The list of nodes that represent shortest path from start to goal  
				while (current.parent != null) { // Adds all nodes to the path list until start is reached
					path.add(current); // Adds the current node to the path
					current = current.parent; // Sets current as the node preceding it 
				}
				openList.clear(); // Clears the open list to save memory
				closedList.clear();// Clears the closed list to save memory
				return path; // Returns the shortest path
			}
			openList.remove(current); // Removes the current node from the open list if the goal has not been reached...
			closedList.add(current); // ...and adds it to the closed list
			for(int i = 0; i < 9; i++) { // Loops through each adjacent node of the current node
				if (i==4) continue; // If the node thats being checked is the node in the middle, the loop is continued as this isn't a possibility
				int x = current.tile.getX(); // The current nodes x position 
				int y = current.tile.getY(); // The current nodes y position
				int xi = (i % 3) -1; // The  relative x coordinate of the tile relative to the current tile 
				int yi = (i / 3) -1; // The  relative y coordinate of the tiles relative to the current tile
				Tile at = getTile(x + xi, y + yi); // The current tile being considered in the loop
				if (at == null) continue; // Moves on to the next tile if the current tile is non-existent
				if(at.solid()) continue; // If the tile being considered is solid, disregard it and continue
				Vector2i a = new Vector2i(x + xi, y + yi); // The current tile in vector form
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95); // Sets the g cost to the g cost of the current tile + the distance from the current tile to the tile being considered 
				double hCost = getDistance(a, goal); // The heuristic cost is set to the distance from the tile being considered to the goal
				Node node = new Node(a, current, gCost, hCost); // Creates a node at the position of the tile being considered with the parent of the current node and both costs
				if(vecInList(closedList, a) && gCost >= node.gCost) continue; // Skips the node if the vector is already in the closed list and the cost of traversal is larger or equal to the current nodes cost
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node); // Adds the node to the open list if its not already in it or the cost is greater than the nodes current cost		
			}
		}
		closedList.clear(); // Clears the list for memory efficiency
		return null; // Returns null if no path is found 
	} 
	
	private boolean vecInList(List<Node> list, Vector2i vector) { // Checks if a vector is in a list
		for(Node n : list) { // Loops through each node in the list and checks if the nodes tile is equal to the vector
			if(n.tile.equals(vector)) return true;
		}
		return false;
	}
	
	private double getDistance(Vector2i tile, Vector2i goal) { // Gets the distance between a tile and a goal
		double dx = tile.getX() - goal.getX(); // Distance between the tiles and goals x component 
		double dy = tile.getY() - goal.getY(); // Distance between the tiles and goals y component
		double distance =  Math.sqrt(dx * dy); // Distance from the tile to goal, calculated using Pythag theorem 
		return distance == 1 ? 1 : 0.95; // Returns the distance
	
	}
	
	public List<Entity> getEntities(Entity e, int radius){ // Returns an list of entities that are within a specific radius from an enitiy
		List<Entity> result = new ArrayList<Entity>(); // The list of entities that are within the range 
		int ex = (int)e.getX();  // X coordinate of current entity
		int ey = (int)e.getY();	// X coordinate of current entity
		for(int i = 0; i < entities.size(); i ++) { // Loops through each entity on the level
			Entity entity = entities.get(i);  
			int x = (int)entity.getX(); // X coordinate of the foreign entity in the list
			int y = (int)entity.getY(); // Y coordinate of the foreign entity in the list
			
			
			int dx = Math.abs(x - ex); // Calculates the distance between the foreign entity and the current entity on the x axis
			int dy = Math.abs(y - ey); // Calculates the distance between the foreign entity and the current entity on the y axis
			double distance = Math.sqrt((dx * dx) + (dy * dy)); // uses Pythagoras' theorem to calculate the distance between the two entities 
			if(distance <= radius) result.add(entity); // Adds the entity to the list if the distance is smaller than or equal to the range specified
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius){ // Returns an list of players that are within a specific radius from an enitiy
		List<Player> result = new ArrayList<Player>();  // List of players that are within the range
		int ex = (int)e.getX();  // X coordinate of current entity
		int ey = (int)e.getY();	// X coordinate of current entity
		for (int i = 0; i < players.size(); i++) { // Loops through each player in the level
			Player player = players.get(i);   
			int x = (int)player.getX(); // X coordinate of the  player in the list
			int y = (int)player.getY(); // Y coordinate of the  player in the list
		
			int dx = Math.abs(x - ex); // Calculates the distance between the player and the current entity on the x axis
			int dy = Math.abs(y - ey); // Calculates the distance between the player and the current entity on the y axis
			double distance = Math.sqrt((dx * dx) + (dy * dy)); // Uses Pythagoras' theorem to calculate the distance between the entity and player 
			if(distance <= radius) result.add(player); // Adds the player to the list if the distance is smaller than or equal to the range specified
		}
		return result;
	}
	
	public Player getClosestPlayer(Entity e){ // Returns the closest player to an enitiy
		double[] distances = new double[3];
		distances[0] = 10000.00;
		distances[1] = 10000.00;
		distances[2] = 10000.00;
		int ex = (int)e.getX();  // X coordinate of current entity
		int ey = (int)e.getY();	// X coordinate of current entity
		for (int i = 0; i < players.size(); i++) { // Loops through each player in the level
			Player player = players.get(i);   
			int x = (int)player.getX(); // X coordinate of the  player in the list
			int y = (int)player.getY(); // Y coordinate of the  player in the list
		
			int dx = Math.abs(x - ex); // Calculates the distance between the player and the current entity on the x axis
			int dy = Math.abs(y - ey); // Calculates the distance between the player and the current entity on the y axis
			double distance = Math.sqrt((dx * dx) + (dy * dy)); // Uses Pythagoras' theorem to calculate the distance between the entity and player
			distances[i] = distance;
		}
		if(distances[1] > distances[2]){
			double temp = distances[1];
			distances[1] = distances[2];
			distances[2] = temp;	
			if(distances[0] > distances [1]) {
				temp = distances[0];
				distances[0] = distances[1];
				distances[1] = temp;
				return players.get(2);
			} else {
				return players.get(0);
			}
		}
		if(distances[0] > distances [1]) {
			double temp = distances[0];
			distances[0] = distances[1];
			distances[1] = temp;
			return players.get(1);
		} else {
			return players.get(0);
		}
	}
	
	public static void nextLevel() {
		if(level == LEVEL.ONE) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.TWO;
			Game.spawned = false;
		}else if(level == LEVEL.TWO) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.THREE;
			Game.spawned = false;
		}else if(level == LEVEL.THREE) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.FOUR;
			Game.spawned = false;
		}else if(level == LEVEL.FOUR) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.FIVE;
			Game.spawned = false;
		}else if(level == LEVEL.FIVE) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.SIX;
			Game.spawned = false;
		}else if(level == LEVEL.SIX) {
			reset();
			reset();
			reset();	
			reset();
			level = LEVEL.SEVEN;
			Game.spawned = false;
		}else if(level == LEVEL.SEVEN) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.EIGHT;
			Game.spawned = false;
		}else if(level == LEVEL.EIGHT) {
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.NINE;
			Game.spawned = false;	
		}else if(level == LEVEL.NINE){
			reset();
			reset();
			reset();
			reset();
			level = LEVEL.TEN;
			Game.spawned = false;
		} else {
			reset();
			reset();
			reset();
			reset();
			Game.state = Game.STATE.HOME;
		}
	}

	// Grass = 0x4B8B2A
	// Box = 0xAF7B36
	// Stone wall = 0x898782
	// Stone floor = 0xA09E98
	public Tile getTile(int x, int y) { // Gets specific tile
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_level1_grass)
			return Tile.level1_grass; // If the colour is 0xff4B8B2A, the tile will be set to grass
		if (tiles[x + y * width] == Tile.col_level1_box)
			return Tile.level1_box; // If the colour is 0xffAF7B36, the tile will be set to box
		if (tiles[x + y * width] == Tile.col_level1_stone_wall)
			return Tile.level1_stone_wall; // If the colour is 0xff898782, the tile will be set to a stone wall
		if (tiles[x + y * width] == Tile.col_level1_stone_floor)
			return Tile.level1_stone_floor; // If the colour is 0xffA09E98, the tile will be set to a stone floor
		if (tiles[x + y * width] == Tile.col_level1_door)
			return Tile.level1_door; // If the colour is 0xff4F3F27, the tile will be set to a door
		return Tile.voidTile;
	}
	
	public void setTile(int x, int y, Tile tile) { // Sets the tile at a given position to the one required
		int yy = (int)Math.floor(y / 16); // Turns the y coordinate from pixel precision into tile precision
		int xx = (int)Math.floor(x / 16); // Turns the x coordinate from pixel precision into tile precision		
		tiles[xx + yy * width] = tile.getCol(tile); // Sets the colour of the tile at the given position to the required tiles colour  
	}

}
