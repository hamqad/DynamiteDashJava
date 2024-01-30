package com.syntx.dynamitedash.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener{

	private static int mouseX = -1; // X location of the mouse, default coordinate is -1
	private static int mouseY = -1; // Y location of the mouse, default coordinate is -1
	private static int mouseB = -1;  // Holds the mouse button
	
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getButton() {
		return mouseB;
	}
	
	
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX(); // Sets mouses x variable to the position the mouse is dragged in the x plane
		mouseY = e.getY(); // Sets mouses x variable to the position the mouse is dragged in the y plane
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();  // Sets mouses x variable to the position the mouse is moved in the x plane
		mouseY = e.getY(); // Sets mouses y variable to the position the mouse is moved in the y plane
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton(); // Sets the mouses button value to the button pressed
	}

	public void mouseReleased(MouseEvent e) {
		mouseB =MouseEvent.NOBUTTON ; // When the mouse button is released, the variable is set to 0 
	}

}
