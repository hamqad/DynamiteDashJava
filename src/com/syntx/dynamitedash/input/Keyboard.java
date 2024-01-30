package com.syntx.dynamitedash.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean[120]; // An array for each keyboard input, True will mean when the key is pressed and False will mean the key is released
	public boolean oneUp, oneDown, oneLeft, oneRight, onePlace, oneActivate;  // Keep tracks of weather the keys set to a direction is pressed or not for the first player
	public boolean twoUp, twoDown, twoLeft, twoRight, twoPlace, twoActivate;  // Keep tracks of weather the keys set to a direction is pressed or not for the second player
	
	public void update() { 
		oneUp =  keys[KeyEvent.VK_W];  // sets the boolean up to true if the W key is pressed
		oneDown = keys[KeyEvent.VK_S]; // sets the boolean down to true if the S key is pressed
		oneLeft =  keys[KeyEvent.VK_A]; // sets the boolean up to true if the A key is pressed
		oneRight =  keys[KeyEvent.VK_D]; // sets the boolean up to true if the D key is pressed
		onePlace = keys[KeyEvent.VK_SPACE]; // sets the boolean place to true if the Spacebar is pressed
		oneActivate = keys[KeyEvent.VK_SHIFT]; // sets the boolean activate to true if left shift is pressed
		twoUp = keys[KeyEvent.VK_UP] ;  // sets the boolean up to true if the UP key is pressed
		twoDown = keys[KeyEvent.VK_DOWN]; // sets the boolean down to true if the DOWN arrow key is pressed
		twoLeft = keys[KeyEvent.VK_LEFT]; // sets the boolean up to true if the LEFT arrow key is pressed
		twoRight = keys[KeyEvent.VK_RIGHT]; // sets the boolean up to true if the RIGHT arrow key is pressed
		twoPlace = keys[KeyEvent.VK_NUMPAD0]; // sets the boolean place to true if the shift key is pressed
		twoActivate = keys[KeyEvent.VK_ENTER]; // sets the boolean activate to true if the control key is pressed 
	}
	
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true; // Whenever a key is pressed the key will be put into the keys array using the keys id as the index and it will be set to true  
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;  // Whenever a key is released, the key id in the keys array will be set to false
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
