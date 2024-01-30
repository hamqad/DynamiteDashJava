package com.syntx.dynamitedash.graphics.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.syntx.dynamitedash.input.Mouse;
import com.syntx.dynamitedash.util.Vector2i;

public class UIButton extends UIComponent {

	public UILabel label; // The label of the button
	private UIButtonListener buttonListener; // Checks for the users actions towards the button
	private UIActionListener actionListener; // Performs an action based on an event  
	
	private Image image; // The image of the button
	
	private boolean render = true;
	private boolean inside = false; // Whether the mouse is inside the button or not
	private boolean pressed = false; // Weather the button has been pressed or not 
	private boolean ignorePressed = false; // Weather the pressing of the mouse button should be ignored or not
	
	
	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener, boolean render) { // Creates a button of a specified size, position and an action listener
		super(position, size);
		this.actionListener = actionListener;
		Vector2i lp = new Vector2i(position); // The position of the label
		// Adjusts the position of the label text to be in the center of the button:
		lp.x += 4; 
		lp.y += size.y - 10;
		label = new UILabel(lp, ""); // Sets the label to blank as default;
		label.setColour(0x666666); // Sets the colour of the label to grey by default
		label.active = false; // Sets the label to non-active by default
		this.render = render;
		init(); // Initializes the button		
	}
	
	public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener) { // Creates a button with an image, position and an action listener
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.actionListener = actionListener;
		setImage(image); // Sets the given image to the buttons image
		init(); // Initializes the button 
	}
	
	public UIButton(Vector2i position, BufferedImage image) { // Creates a button with an image and position
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		setImage(image); // Sets the given image to the buttons image
		init(); // Initializes the button 
	}
	
	private void init() { // Initializes the button
		setColour(0xAAAAAA); // Sets the colour of the button by default
		buttonListener = new UIButtonListener(); // Instantiates the button listener for the button
	}
	
	void init(UIPanel panel) { // Initializes the button in the panel
		super.init(panel);
		if(label != null)
			panel.addComponent(label);
	}
	
	public void setText(String text) { // Sets the text of the buttons label
		if(text == "") // If the text is blank set the labels active variable to false
			label.active = false;
		else
			label.text = text; // Else set the text to the text specified
	}
	
	public void setButtonListener(UIButtonListener buttonlistener) { // Sets the button listener
		this.buttonListener = buttonListener;
	}

	public void setImage(Image image) { // Sets the image of the button
		this.image = image;
	}
	
	public void update() { // Checks buttons activity
		Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y); // Creates the bounds for the button
		boolean leftMouseDown = Mouse.getButton() == MouseEvent.BUTTON1; // If true, the left mouse button has been pressed
		if(rect.contains(new Point(Mouse.getX(), Mouse.getY()))) { // Checks weather the mouse is within the bounds
			// Entered:
			if(!inside) { // If the mouse is not already inside the bounds
				if(leftMouseDown)  // And if it is  already clicked
					ignorePressed = true; // ignore the pressing of the button 
				else
					ignorePressed = false; // if its not already pressed don't ignore it and carry on 
				buttonListener.entered(this); // Call the entered method
			}
			inside = true; // Set inside to true to avoid repeat calling
			// Pressed:
			if(!pressed && !ignorePressed && leftMouseDown){ // If the button is clicked
				pressed = true; // Sets pressed to true
				buttonListener.pressed(this); // Calls the pressed method
			// Released:
			}else if(Mouse.getButton() == MouseEvent.NOBUTTON) { // If the mouse has been released from the button
				if(pressed) { // ff the mouse was pressed
				buttonListener.released(this); // Call the released method
				if(actionListener != null) actionListener.perform(); // Perform the buttons action 
				pressed = false; // Set pressed to false
				}
				ignorePressed = false; // Sets ignore pressed to true
			}
			// Exited:
		} else { // If the mouse isn't within the bounds 
			if(inside) { // And if the mouse was previously inside the bounds
				buttonListener.exited(this); // Call the exited method
				pressed = false; // sets pressed to false so that the button isnt pressed when the mouse leaves the bounds
			}
			inside = false; // Sets inside to false		
		}
	}
			
	
	public void render(Graphics g) { // Renders the button onto the screen
		if(render) {
			int x = position.x + offset.x; // The x position of the button
			int y = position.y + offset.y; // The y position of the button
			// Renders the button with an image if it is not null:
			if(image != null) { 
				g.drawImage(image, x, y, null);
			} else  {
			// Renders the button with a rectangle:
				g.setColor(colour); 
				g.fillRect(x, y, size.x, size.y);
			// Renders the button with text if it is not null:
				if(label != null) 
				label.render(g); 
			
			}
		}
	}
}



