package com.mycompany.a3.gameObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
//


public class Ant extends Movable implements IFoodie {

	// constants
	private final static int MAXFOODLEVEL = 100;
	private final static int CONSUMPTIONMAX = 10; // food consumption
	private final static int CONSUMPTIONMIN = 0;
	private final static int MAXFLAG = 9;
	private final static int MAXHEALTH = 10;
	private static final int ANTSIZE = 75;
	private final static int MAXIMUMSPEED = 10;

	
	// attributes
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private Ant instance = null;


	
	// methods
	/** Constructor */
	public Ant(int maxX, int maxY)
	{
				super(ANTSIZE, new Point((maxX/2),(maxY/2)), ColorUtil.rgb(190, 0, 0),  maxX,  maxY);
		healthLevel = MAXHEALTH;
		setHeading(0);
		setSpeed(1);
		setFoodLevel(MAXFOODLEVEL);
		foodConsumptionRate = 2;
		lastFlagReached = 1;
	}
	
	
	/** gets the singular instance of Ant
	 *  @return instance of Ant
	 *  @preconditions none */
	public Ant getInstance(int maxX, int maxY)
	{
		if (instance == null)
		{
			instance = new Ant(maxX, maxY);
		}
		
		return instance;
	}
	
	
	/** Changes the variable foodConsumptionRate to the given value
	 *  @return N/A
	 *  @preconditions consumption rate must be a non-zero value */
	public void setFoodConsumption(int consumptionRate) 
	{
		int proposed = consumptionRate;
		// if value is negative set value to old consumption plus one
		 if (proposed < CONSUMPTIONMIN)
		 {
			 proposed = foodConsumptionRate + 1;
		 }
		 
		 if (proposed > CONSUMPTIONMAX)
		 {
			 foodConsumptionRate = CONSUMPTIONMAX;
			 return;
		 }
		
		foodConsumptionRate = proposed;
	}
	
	/** Returns food consumption rate
	 *  @return food consumption rate
	 *  @preconditions food consumption rate must have a value */
	public int getFoodConsumption() 
	{
		return foodConsumptionRate;
	}
	
	/** Changes the variable healthLevel to the given value
	 *  @return N/A
	 *  @preconditions health must be a positive integer */
	public void setHealthLevel(int health) 
	{
		// make sure value is between 1 and 10
		if (health < 0 || health > MAXHEALTH)
		 {
			 throw new IllegalArgumentException("PROGRAM ERROR: Health must be between 0 and " + MAXHEALTH + ".");
		 }
		
		this.healthLevel = health;
		
		if ((getSpeed()) > health)
		{
			setSpeed(health);
		}
	}
	
	/** Returns current health value
	 *  @return healthLevel
	 *  @preconditions health level must have a value */
	public int getHealthLevel() 
	{
		return healthLevel;
	}
	
	/** Changes the number of the last flag the ant collided with.
	 *  @return N/A
	 *  @preconditions the flag number must be between 1-9, it must also be more than
	 *  the previous lastFlagReached value */
	public void setLastFlagReached(int flagNumber) 
	{
		if (flagNumber < 1 || flagNumber > MAXFLAG)
		 {
			 throw new IllegalArgumentException("PROGRAM ERROR: Number must be between 1 and " + MAXFLAG + ".");
		 }
		lastFlagReached = flagNumber;
	}
	
	/** Returns the number of the last flag reached.
	 *  @return the number of the last flag reached
	 *  @preconditions the variable lastFlagReached must have a value */
	public int getLastFlagReached() 
	{
		return lastFlagReached;
	}
	
	/** Returns the maximum speed of the ant.
	 *  @return the maximum speed of the ant.
	 *  @preconditions maximum speed must have a value */
	public int getMaximumSpeed() 
	{
		return MAXIMUMSPEED;
	}
	
	/** Changes the variable speed to the given value.
	 *  @return N/A
	 *  @preconditions value given must be a positive integer between 0 and 
	 *  the maximum speed value, or a positive integer between 0 and the current
	 *  health value */
	@Override
	public void setSpeed(int speed) 
	{
		if (speed < 0 || speed > MAXIMUMSPEED || speed > (healthLevel))
		{
			throw new IllegalArgumentException("PROGRAM ERROR: Speed must be between 0 and " + healthLevel + " .");
		}
		
		super.setSpeed(speed);
	}
	
	/** increases the speed of the ant
	 *  @return N/A
	 *  @preconditions ant speed must be less than maximum speed */
	public void accelerate() 
	{
		if (getFoodLevel() == 0)
		{
			setSpeed(0);
			return;
		}
		
		if (getSpeed() + 1 >= MAXIMUMSPEED || getSpeed() + 1>= healthLevel)
		{
			setSpeed(healthLevel);
			return;
		}

		setSpeed(getSpeed() + 1);
		}
	
	/** decreases the speed of the ant
	 *  @return N/A
	 *  @preconditions speed of the ant must be greater than 0 */
	public void brake() 
	{
		if (getSpeed() - 1 <=0)
		{
			 setSpeed(0);
			return;
		}
		setSpeed(getSpeed() - 1);
	}
	
	/** decreases the heading of the ant by 5 degrees
	 *  @return N/A
	 *  @preconditions N/A */
	public void turnLeft() 
	{
		int proposed = getHeading()-5;
		
		if (proposed < 0)
		{
			setHeading(360 + proposed);
		}
		else
		{
			setHeading(proposed);
		}
	}
	
	/** increases the heading of the ant by 5 degrees
	 *  @return N/A
	 *  @preconditions N/A */
	public void turnRight() 
	{
		int proposed = getHeading()+5;		
		
		if (proposed > 359)
		{
			setHeading(proposed - 360);
		}
		else
		{
			setHeading(proposed);
		}
	}
	
	/** Returns a string of information about the ant object
	 *  @return String of information about the ant object
	 *  @preconditions N/A */
	public String toString() 
	{
		return "Ant: loc=" + getLocation().getX() + "," + getLocation().getY() + " color=[" + ColorUtil.red(getColor()) + "," +
				ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "] heading=" + getHeading() + " speed=" + 
				getSpeed() + " size=" + getSize() + " maxSpeed=" + MAXIMUMSPEED + " foodConsumptionRate=" + foodConsumptionRate+ "\n";
	}
	
	
	/** draws the ant object as a filled circle
	 *  @return void
	 *  @preconditions ant color and size must be set */
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		g.setColor(this.getColor());
		
		// location relative to parent
		int x = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2)));
		int y = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2)));
		
		g.fillArc(x, y, this.getSize(), this.getSize(), 0, 360);
	}
	
}
