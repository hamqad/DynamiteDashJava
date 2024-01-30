package com.syntx.dynamitedash.graphics.UI;

public class UIButtonListener {

	public void entered(UIButton button) { //When the mouse has entered the given bounds
		button.setColour(0xcdcdcd);
	}
	
	public void exited(UIButton button) { // When the mouse has left the given bounds
		button.setColour(0xaaaaaa);
	}
	
	public void pressed(UIButton button) { // When the mouse clicks the given bounds
		button.setColour(0xBA1F33);
	}
	
	public void released(UIButton button) { // When the mouse is released after clicking the given bounds
		button.setColour(0xcdcdcd);
	}
	
	
}
