package com.mycompany.a3.gameObjects;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {

	// attributes
	private int heading;
	private int speed;
	private int foodLevel;
	
	// constants
	private static final int MAXSPEED = 10;
	private static final int MAXFOODLEVEL = 100;

	
	//Constructor
	Movable(int size, Point location, int color, int maxX, int maxY)
	{
		super(size, location, color, maxX, maxY);
	}
	
	// methods
	/** Allows object to move in the direction of heading at speed.
	 *  @return N/A
	 *  @preconditions heading, foodLevel, and speed must have non-zero values */
	public void move() 
	{
		//NOTE: 0,0 IS BOTTOM LEFT HAND CORNER
		//NOTE: 0 DEGREES IS ACTUALLY RIGHT SO SHIFT BY 90
		double headingRadians = Math.toRadians((360-heading)+90);
		double deltaX = Math.cos(headingRadians) * speed;
		double deltaY = Math.sin(headingRadians)* speed;
		int newX = (int)getLocation().getX() + (int)deltaX;
		int newY = (int)getLocation().getY() + (int)deltaY;
		
		if (newX < getMaxX() && newX > 0 )
		{
			getLocation().setX(Math.round(newX));

		}
		if (newY < getMaxY() && newY > 0)
		{
			getLocation().setY(Math.round(newY));
		}
	}
	
	/** Returns current food levels.
	 *  @return current value of food levels (integer)
	 *  @preconditions food level must have a value */
	public int getFoodLevel() 
	{ 
		return foodLevel;
	}
	
	/** Changes the variable foodLevel to the given value.
	 *  @return N/A
	 *  @preconditions value entered must be non-zero value */
	public void setFoodLevel(int level) 
	{
		if (level > MAXFOODLEVEL || level < 0)
		{
			 throw new IllegalArgumentException("PROGRAM ERROR: Food level must be between 0 and " + MAXFOODLEVEL + ".");
		}
		
		foodLevel = level;
		
		if (foodLevel == 0)
		{
			setSpeed(0);
		}
	}
	
	/** Returns current speed.
	 *  @return current speed (integer)
	 *  @preconditions speed must have a value */
	public int getSpeed() 
	{
		return speed;
	}
	
	/** Changes the variable speed to the given value.
	 *  @return N/A
	 *  @preconditions value given must be a positive integer */
	public void setSpeed(int speed) 
	{
		if (speed > MAXSPEED || speed < 0)
		{
			 throw new IllegalArgumentException("PROGRAM ERROR: Speed must be between 0 and " + MAXSPEED + ".");
		}
		
		this.speed = speed;
	}
	
	/** Returns current heading.
	 *  @return current heading using degrees from 0 to 359 (integer)
	 *  @preconditions heading must have a value */
	public int getHeading() 
	{
		return heading;
	}
	
	/** Changes the variable heading to the given value.
	 *  @return N/A
	 *  @preconditions heading must be between 0 and 359 degrees */
	public void setHeading(int heading) 
	{
		if (heading < 0 || heading > 359)
		{
			throw new IllegalArgumentException("PROGRAM ERROR: Heading must be between 0 and 359.");
		}
		
		this.heading = heading;
	}
	
}
