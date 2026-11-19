package com.mycompany.a3.gameObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;

import java.util.Random;

import com.codename1.charts.models.Point;
//

public class FoodStation extends Fixed {

	// attribute 
	private int capacity;
	private static Random generator = new Random();
	private boolean dead = false;

	
	// constants
	private static final int MAXFOODLEVEL = 50;
	
	// methods
	/** Constructor */
	public FoodStation(int maxX, int maxY, GameWorld gw)
	{ 
		 // size is between 60 and 100
		super((generator.nextInt(40) + 60),new Point(50 + generator.nextInt(maxX-100), 50 + generator.nextInt(maxY-100)), ColorUtil.rgb(124, 252, 0),  maxX,  maxY, gw); // random in range 10 to 50
		
		
		// color is grass green (more like lime green)
		capacity = getSize()-50;
	}
	
	/** Returns the current capacity
	 *  @return capacity as an integer
	 *  @preconditions capacity must have a value */
	 public int getCapacity() 
	 {
		 return capacity;
	}
	 
	 /** Changes the variable capacity to the given value
		 *  @return N/A
		 *  @preconditions capacity must be a positive integer between 0 and 100 */
	 public void setCapacity(int capacity) 
	 {
		 if (capacity < 0 || capacity > MAXFOODLEVEL)
		 {
			 throw new IllegalArgumentException("PROGRAM ERROR: Capacity must be between 0 and " + MAXFOODLEVEL + ".");
		 }
		 
		 this.capacity = capacity;
	 }
	 
	 
	 /** returns if food station has already been declared "dead"
		 *  @return boolean of dead or not
		 *  @preconditions N/A */
	 public boolean getStatus()
	 {
		 return dead;
	 }
	 
	 
	 /** sets food station status to dead so it won't trigger part of the collision interaction that makes a replacement
		 *  @return N/a
		 *  @preconditions N/A */
	 public void setStatus(boolean status)
	 {
		 dead = status;
	 }
	 
	 
	 /** Returns a string of information about a food station object
		 *  @return string of information about a food station object
		 *  @preconditions N/A */
	 public String toString() 
	 {
		 return "FoodStation: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				 " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor())+
				 "," + ColorUtil.blue(getColor()) + "] size=" + getSize() + " capacity=" + capacity + "\n";
	 }
	 
	 
		/** draws food station as filled squares with their capacity printed on them
		 *  @return void
		 *  @preconditions food station color, capacity, and size must be set */
		public void draw(Graphics g, Point pCmpRelPrnt)
		{
			g.setColor(this.getColor());
			
			// location relative to parent
			int x = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2)));
			int y = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2)));
			
			g.fillRect(x, y, this.getSize(), this.getSize());
			
			g.setColor(ColorUtil.BLACK);
			Font small = Font.createSystemFont(g.getFont().getStyle(), Font.STYLE_PLAIN, Font.SIZE_SMALL);
			
			String text = "" + capacity;
			
			x = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX()));
			y = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY()));
			
			x = x - (small.stringWidth(text));
			y = y - (small.getHeight());
			
			g.drawString(text, x, y);
		}
		
		
		/** Food station only changes capacity and such when an ant collides with it
		 *  @return N/A
		 *  @preconditions N/A */
		@Override
		public void handleCollision(GameObject otherObject)
		{
			if (otherObject instanceof Ant)
			{
				getgw().collideWithFoodStation((Ant)otherObject, this);
			}
		}
	
}
