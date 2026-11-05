package com.mycompany.a2.gameObjects;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;
import com.codename1.charts.models.Point;


public class Spider extends Movable {
	 
	// constants
	private static final int MAXFOODLEVEL = 100;
	private static final int MINX = 0;
	private static final int MINY = 0;
	
	// attributes
	private static Random generator = new Random();


	
	// methods
	 /** Constructor */
	 public Spider(int maxX, int maxY)
	 {
		 // size is between 10 and 50
		super( (10 + generator.nextInt(40)) , (new Point(generator.nextInt(maxX),generator.nextInt(maxY))) , ColorUtil.BLACK,  maxX,  maxY); //chosen randomly on creation
		setHeading(generator.nextInt(359)); //random value
		
		setSpeed(generator.nextInt(5) + 5); // random between 5 and 10
		super.setFoodLevel(MAXFOODLEVEL);
	 }
	
	 
	 /** Overrides the function SetColor held by GameObject so color cannot be changed.
		 *  @return N/A
		 *  @preconditions N/A */
	 @Override
	 public void setColor(int color) 
	 {
		// force quit if called
		 throw new UnsupportedOperationException("setColor is currently disabled.");
	}
	 
	 /** Overrides the function setFoodLevel held by movable to make foodlevel
	  * unchangeable.
		 *  @return N/A
		 *  @preconditions N/A */
	 @Override
	 public void setFoodLevel(int fullness) 
	 {
		// force quit if called
				 throw new UnsupportedOperationException("setFoodLevel is currently disabled.");
	 }
	 
	 /** Overrides the move function held by movable to cause random movement
		 *  @return N/A
		 *  @preconditions N/A */
	 @Override
	 public void move() 
	 {
		 //NOTE: 0,0 IS BOTTOM LEFT HAND CORNER
		 //NOTE: 0 DEGREES IS ACTUALLY RIGHT SO SHIFT BY 90
		double headingRadians = Math.toRadians((360-getHeading())+90);
		double deltaX = Math.cos(headingRadians) * getSpeed();
		double deltaY = Math.sin(headingRadians)* getSpeed();
		int newX = (int)getLocation().getX() + (int)deltaX;
		int newY = (int)getLocation().getY() + (int)deltaY;
		Random generator = new Random();
		
		// prohibit from moving out of bounds
		int randomInt = generator.nextInt(180);
		
		if (newX >= getMaxX())
		{
			getLocation().setX(Math.round(getMaxX()));
			
			// up-right (180-270)
			if (newY >= getMaxY())
			{
				getLocation().setY(Math.round(getMaxY()));
				setHeading(180 + (randomInt/2));
			}
			// down-right (270-360)
			else if (newY <=MINY)
			{
				getLocation().setY(Math.round(MINY));
				setHeading(270 + (randomInt/2));
			}
			else
			{
				setHeading(180 + randomInt);
			}
		}
		else if (newX <= MINX)
		{
			getLocation().setX(Math.round(MINX));
		
			if (newY >= MINY)
			{
				getLocation().setY(Math.round(getMaxY()));
				setHeading(90 + (randomInt/2));
			}
			// down-left (0-90)
			else if (newY <= MINY)
			{
				getLocation().setY(Math.round(MINY));
				setHeading(0 + (randomInt/2));
			}
			else
			{
				setHeading(0 + randomInt);
			}
		}
		// up
		else if (newY >= getMaxY())
		{
			getLocation().setY(Math.round(getMaxY()));
			setHeading(90 + randomInt);
		}
		// down
		else if (newY <= MINY)
		{
			getLocation().setY(Math.round(MINY));
			int newHeading = ((270 + randomInt) > 359) ? 270 + randomInt - 360 : 270 + randomInt;
			setHeading(newHeading);
		}
		else
		{
			randomInt = generator.nextInt(20);
			getLocation().setX(Math.round(newX));
			getLocation().setY(Math.round(newY));
			int proposed = getHeading() + (randomInt - 10);
			
			if (proposed > 359)
			{
				proposed = proposed - 360;
			}
			if (proposed < 0)
			{
				proposed = 360 + proposed;
			}
			setHeading(proposed);	
		}
	 }
	 
	 /** Returns a string of information about the ant object 
		 *  @return string of information about the ant object
		 *  @preconditions N/A */
	 public String toString() 
	 {
		 return "Spider: loc=" + getLocation().getX() + "," + getLocation().getY() + " color=[" + ColorUtil.red(getColor()) +
				 "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "] heading=" + getHeading() + " speed=" + getSpeed()
				 + " size=" + getSize() + "\n";
		 }
}
