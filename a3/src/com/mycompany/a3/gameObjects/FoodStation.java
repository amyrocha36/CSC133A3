package com.mycompany.a3.gameObjects;
import com.codename1.charts.util.ColorUtil;

import java.util.Random;

import com.codename1.charts.models.Point;
//

public class FoodStation extends Fixed {

	// attribute 
	private int capacity;
	private static Random generator = new Random();

	
	// constants
	private static final int MAXFOODLEVEL = 10;
	
	// methods
	/** Constructor */
	public FoodStation(int maxX, int maxY)
	{ 
		 // size is between 10 and 50
		super((generator.nextInt(40) + 10),new Point(generator.nextInt(maxX),generator.nextInt(maxY)), ColorUtil.rgb(124, 252, 0),  maxX,  maxY); // random in range 10 to 50
		
		
		// color is grass green (more like lime green)
		capacity = getSize();
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
	 
	 /** Returns a string of information about a food station object
		 *  @return string of information about a food station object
		 *  @preconditions N/A */
	 public String toString() 
	 {
		 return "FoodStation: loc=" + getLocation().getX() + "," + getLocation().getY() + 
				 " color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor())+
				 "," + ColorUtil.blue(getColor()) + "] size=" + getSize() + " capacity=" + capacity + "\n";
	 }
	
}
