package com.mycompany.a3.gameObjects;
import com.codename1.charts.models.Point;


public abstract class Fixed extends GameObject {
	
	// methods
	
	// Constructor
	
	Fixed(int size, Point location, int color, int maxX, int maxY)
	{
		super(size, location, color,  maxX,  maxY);
	}
	
	/** Overriding the setLocation function in GameObject to prohibit changing the 
	 * location of the object.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void setLocation(Point location) 
	{
		 throw new UnsupportedOperationException("setLocation is currently disabled.");
	}
}
