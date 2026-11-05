package com.mycompany.a2.gameObjects;
import com.codename1.charts.models.Point;


public abstract class GameObject {

	// attributes
	private int size; // size of the object, length fo square
	private Point location; // location of object where 0,0 is left bottom corner
	private int color; // color of object using colorUtil
	
	// constants
	private int MAXX = 1000;
	private int MAXY = 1000;
	
	// methods
	
	GameObject(int size, Point location, int color, int maxX, int maxY)
	{
		this.size = size;
		this.location = location;
		this.color = color;
		MAXX = maxX;
		MAXY = maxY;
	}
	
	/** Returns size of object (width of hit box.
	 *  @return size of object aka the width of the hit box
	 *  @preconditions size must have a value */
	public int getSize() 
	{
		return size;
	}
	
	/** Returns location of object (X,Y coordinate point of the middle).
	 *  @return X,Y coordinate point of the middle of the object
	 *  @preconditions location must have a value */
	public Point getLocation() 
	{
		return location;
	}
	
	
	/** Returns location of object (X,Y coordinate point of the middle).
	 *  @return X,Y coordinate point of the middle of the object
	 *  @preconditions location must have a value */
	public int getMaxX() 
	{
		return MAXX;
	}
	
	
	/** Returns location of object (X,Y coordinate point of the middle).
	 *  @return X,Y coordinate point of the middle of the object
	 *  @preconditions location must have a value */
	public int getMaxY() 
	{
		return MAXY;
	}
	
	
	/** Changes location variable to the given value.
	 *  @return N/A
	 *  @preconditions given point must not be out of bounds of the map */
	public void setLocation(Point location) 
	{
		if (location.getX() < 0 || location.getX() > MAXX || location.getY() < 0 || location.getY() > MAXY)
		{
			 throw new IllegalArgumentException("PROGRAM ERROR: Location point must be between (0,0) and (" + MAXX + "," + MAXY + "). Was " + location.getX() +" " + location.getY());
		}
		
		this.location = location;
	}
	
	/** Returns color of the object.
	 *  @return color of the object
	 *  @preconditions color must have a value */
	public int getColor() 
	{
		return color;
	}
	
	/** Changes the variable color to the given value.
	 *  @return N/A
	 *  @preconditions given value must be in the hex format provided by rgb(). */
	public void setColor(int color) 
	{	
		this.color = color;
	}
	
}
