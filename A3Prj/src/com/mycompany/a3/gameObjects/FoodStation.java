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
	private boolean isSelected = false;

	
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
		
		
		/** sets isSelected
		 *  @return N/A
		 *  @preconditions N/A */
		@Override
		public void setSelected(boolean b)
		{
			isSelected = b;
		}
		
		
		/** gets isSelected
		 *  @return true if shape is selected
		 *  @preconditions N/A */
		@Override
		public boolean isSelected()
		{
			return isSelected;
		}
		
		
		/** checks if the pointer is contained in the object
		 *  @return true if pointer contained false if otherwise
		 *  @preconditions N/A */
		@Override
		public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
		{
			float px = pPtrRelPrnt.getX();
			float py = pPtrRelPrnt.getY();
			float xLoc = (pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2.0f)));
			float yLoc = (pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2.0f)));
			
			if ((px >= xLoc) && (px <= xLoc + getSize()) && (py >= yLoc) && (py <= yLoc + getSize()))
			{
				return true;
			}
			
			return false;
		}
		
		
		/** draws object depending on if it's selected or not, draws food station as squares with their capacity printed on them
		 *  @return N/A
		 *  @preconditions food station color, capacity, and size must be set*/
		@Override
		public void draw(Graphics g, Point pCmpRelPrnt)
		{
			
			// upper left location relative to parent
			int x = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2.0f)));
			int y = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2.0f)));
			
			// get center relative to parent shifted for text
			int Centerx = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX()));
			int Centery = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY()));
			
			Font small = Font.createSystemFont(g.getFont().getStyle(), Font.STYLE_PLAIN, Font.SIZE_SMALL);
			String text = "" + capacity;
			
			Centerx = Centerx - (small.stringWidth(text));
			Centery = Centery - (small.getHeight());
			
			g.setColor(this.getColor());

			
			if(isSelected())
			{
				g.drawRect(x, y, this.getSize(), this.getSize());
			}
			else
			{
				g.fillRect(x, y, this.getSize(), this.getSize());
			}
			
			
			g.setColor(ColorUtil.BLACK);
			
			g.drawString(text, Centerx, Centery);
		}
		
	
}
