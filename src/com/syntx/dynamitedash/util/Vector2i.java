package com.syntx.dynamitedash.util;

// Class to handle two vectors of integers
public class Vector2i { 
	
	public int x, y;
	
	public Vector2i() { // Creates a new vector with a location of 0,0
		set(0, 0);
	}
	
	public Vector2i(Vector2i vector) { // Creates a vector from an existing vector
		set(vector.x, vector.y);
	}
	
	public Vector2i(int x, int y) { // Creates a new vector with the x and y coordinates
		set(x,y);
	}	
	
	public void set(int x,int y) { // Sets the parameters as the coordinates of the vector
		this.x = x;
		this.y = y;
	}
	
	public int getX() { // Getter for the vectors x component
		return x;
	}
	
	public int getY() { // Getter for the vectors y component
		return y;
	}
	
	public Vector2i add(Vector2i vector) { // Adds a given vectors coordinates onto the current vector
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	public Vector2i add(int value) { // Adds a given value onto the current vector
		this.x += value;
		this.y += value;
		return this;
	}
	
	public Vector2i subtract(Vector2i vector) { // Subtracts a given vectors coordinates onto the current vector
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
		
	}
	
	public Vector2i setX(int x) { // Setter for the vectors x component
		this.x = x;
		return this;
	}
	
	
	public Vector2i setY(int y) { // Setter for the vectors y component
		this.y = y;
		return this;
	}
	
	public double getDistance(Vector2i v0, Vector2i v1) { // Returns the distance between two vectors
		double x = v0.getX() - v1.getX(); // The difference between the two vectors x positions
		double y = v0.getY() - v1.getY(); // The difference between the two vectors y positions
		return Math.sqrt(x * x + y * y); // The resultant difference between the two vectors
	}
	
	public boolean equals(Object object) { // Checks if two vectors are equal
		if(!(object instanceof Vector2i)) return false; // Returns false if the object is not an instance of a vector
		Vector2i vec = (Vector2i) object; // Casts the given object into a vector
		if (vec.getX() == this.getX() && vec.getY() == this.getY()) return true; // Returns true if the given vector is identical to the current vector
		return false;
	} 

}
