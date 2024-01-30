package com.syntx.dynamitedash.level;

import com.syntx.dynamitedash.util.Vector2i;

// Handles the nodes for the A* algorithm
public class Node {
	
	public Vector2i tile; // This holds the vector coordiantes of the tile
	public Node parent; // This value holds the node that came preceeds the current node. It will be used to trace back the path so the mob can follow the fastest path
	public double fCost, gCost, hCost; // These are the costs of travel. gCost is the total node to node cost from the start, hCost is a heuristic cost and fCost is a combination of the two.
	
	public Node(Vector2i tile, Node parent, double gCost, double hCost) { // Creates a node at the given tile coordinate, the parent node and g and h costs
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost; 
	}
	
}
