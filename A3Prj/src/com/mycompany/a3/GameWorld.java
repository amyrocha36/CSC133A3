package com.mycompany.a3;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextField;
import com.mycompany.a3.gameObjects.*;
import com.codename1.charts.models.Point;
//


public class GameWorld extends Observable {
	
	// constants
	private static final int MAXFOODLEVEL = 100;
	
	// attributes
	private int clock;
	private int clockMili =0;
	private int lives;
	private boolean sound = false;
	private GameObjectCollection gameObjects;
	private static Random generator = new Random();
	private int totalFoodStations = 3;
	private int totalSpiders = 3;
	private int totalFlags = 6;
	private boolean damaged = false;
	private boolean consumed = false;
	private boolean playState = true;
	
	private int width = 1000;		
	private int height = 1000;
	
	
	// sounds
	private Sound dmg;
	private Sound consume;
	private Sound goal;
	private BGSound bg;


	
	// methods
	/** constructor
	 * @return N/A
	 * @preconditions N/A
	 */
	public GameWorld()
	{
		clock = 0;
		lives = 3;	
	}
	
	
	/** gets an iterator for the game objects collection
	 *  @return iterator
	 *  @preconditions n/A */
	public IIterator getIterator()
	{
		return gameObjects.getIterator();
	}
	
	
	
	/** creates game sounds
	 *  @return N/A
	 *  @preconditions N/A */
	public void createSounds()
	{
		System.out.print("Sounds creation started\n");
		
		bg = new BGSound("alarm.wav", "audio/wav");
		System.out.print("background sound added\n");
		
		dmg = new Sound("alarm.wav", "audio/wav");
		System.out.print("dmg sound added\n");
		
		goal = new Sound("alarm.wav", "audio/wav");
		System.out.print("goal sound added\n");

		consume = new Sound("alarm.wav", "audio/wav");
		System.out.print("consume sound added\n");
		
		System.out.print("Sounds created\n");
	}
	
	
	public void setPlay(boolean b)
	{
		playState = b;
		
		if (playState == false)
		{
			bg.pause();
		}
		else
		{
			if (sound == true)
			{
				bg.play();
			}
		}
	}
	
	
	/** gets the number of the last flag
	 *  @return integer number of the last flag
	 *  @preconditions n/A */
	public int getLastFlag()
	{
		// get the ant object
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		
		
		return subject.getLastFlagReached();
	}
	
	
	/** gets the number of the ants hunger level
	 *  @return integer number of the ants hunger level
	 *  @preconditions n/A */
	public int getAntHunger()
	{
		// get the ant object
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		
		
		return subject.getFoodLevel();
	}
	
	public int getAntHealth()
	{
		// get the ant object
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		
		
		return subject.getHealthLevel();
	}
	
	
	/** sets the world size
	 *  @return N/A
	 *  @preconditions must pass positive values */
	public void setWorldSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	
	/** gets the world width
	 *  @return integer that's the worlds width in pixels
	 *  @preconditions N/A*/
	public int getWorldWidth()
	{
		return width;
	}
	
	
	/** gets the world height
	 *  @return integer that's the worlds width in pixels
	 *  @preconditions N/A */
	public int getWorldHeight()
	{
		return height;
	}
	
	
	/** sets the status of the sound
	 *  @return N/A
	 *  @preconditions N/A */
	public void setSound(boolean offOn)
	{
		sound = offOn;
		
		if ((sound == true) && (playState == true))
		{
			bg.play();
		}
		
		setChanged();
		notifyObservers();
	}
	
	
	/** Gets the status of the sound
	 *  @return returns status of sound as boolean
	 *  @preconditions N/A */
	public boolean getSound()
	{
		return sound;
	}
	
	/** Returns number of lives left.
	 *  @return the number of lives left.
	 *  @preconditions lives must have a value */
	public int getLives() 
	{
		return lives;
	}
	
	/** Returns the number of ticks that has passed on the clock.
	 *  @return time in "ticks"
	 *  @preconditions clock must have a value */
	public float getTime() 
	{
		return clock;
	}
	
	
	/** Initializes game by setting up objects and sets up the first state
	 *  @return N/A
	 *  @preconditions N/A */
	public void init()
	{
		gameObjects = new GameObjectCollection();
		
		// spawn ant
				Ant tempObj  = Ant.getInstance(width, height, this);
				gameObjects.add(tempObj);
				
				IIterator temp = gameObjects.getIterator();
				
				Point antLocation = null;
				
				if (temp.hasNext())
				{
					antLocation = temp.getNext().getLocation();
				}
				else
				{
					System.exit(0);
				}
				
				// spawn flags 1-6 (index 1-6)
				gameObjects.add(new Flag(1, new Point(antLocation.getX(), antLocation.getY()), width, height, this)); // flag 1 location = ant
				gameObjects.add(new Flag(2, new Point((int)(width*0.55),(int)(height*0.55)), width, height, this));
				gameObjects.add(new Flag(3, new Point((int)(width*0.47),(int)(height*0.63)), width, height, this));
				gameObjects.add(new Flag(4, new Point((int)(width*0.34), (int)(height*0.50)), width, height, this));
				gameObjects.add(new Flag(5, new Point((int) (width*0.55), (int)(height*0.29)), width, height, this));
				gameObjects.add(new Flag(6, new Point((int)(width*0.89), (int)(height*0.63)), width, height, this));
				
				// spawn 3 spiders (index 7-9)
				for (int i=0; i < totalSpiders; i++)
				{
					gameObjects.add(new Spider(width, height, this));

				}
				
				// spawn 3 food stations (index 10-12)
				for (int i=0; i < totalFoodStations; i++)
				{
					gameObjects.add(new FoodStation(width, height, this));

				}
				
				// update the two views
				setChanged();
				notifyObservers();					
				
	}
	
	
	/** Displays basic info about the game
	 *  @return N/A
	 *  @preconditions n/a */
	public void AboutInfo()
	{
		Dialog.show("About Program", "Creator: Amy Rocha\n Course: CSC 133", "OK", null);	
	}
	
	
	/** Increases the speed of ant.
	 *  @return N/A
	 *  @preconditions n/a */
	public void accelerate() 
	{
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		subject.accelerate();
		setChanged();
		notifyObservers();
		
	}
	
	
	/** Decreases the speed of ant.
	 *  @return N/A
	 *  @preconditions n/a */
	public void brake() 
	{
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		subject.brake();

		setChanged();
		notifyObservers();
		
	}
	
	
	/** Changes the food consumption rate of the ant to the previous food consumption rate plus addedValue
	 *  @return N/A
	 *  @preconditions addedValue must be non zero */
	public void changeFoodConsumptionRate() 
	{
		// get previous rate
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		
		int currentRate = subject.getFoodConsumption();
		int addedValue = generator.nextInt(40)-20; // between -20 and +20
		
		subject.setFoodConsumption(currentRate + addedValue);
				
		setChanged();
		notifyObservers();
		
	}
	
	/** Simulates interaction of ant colliding with flag.
	 *  @return N/A
	 *  @preconditions n/a */
	public void collideWithFlag(Ant player, Flag target) 
	{

		if (player.getLastFlagReached() == target.getSequenceNumber()-1)
			{
				if(sound == true) {goal.play();}
				
				
				player.setLastFlagReached(target.getSequenceNumber());
				
				// win condition
				if (target.getSequenceNumber() == totalFlags)
					{
						System.out.println("Game over, you win! \n");
						System.out.println("Total Time: " + clock + "\n");
						System.exit(0);
					}
			}
		
		setChanged();
		notifyObservers();
		
	}
	
	/** Simulates interaction of ant colliding with food station.
	 *  @return N/A
	 *  @preconditions N/A */
	public void collideWithFoodStation(Ant player, FoodStation target) 
	{
		
		int change = 0;

		// modify capacity if not dead and check if dead
		if (target.getStatus() == false)
		{
			// play sound on hit
			if(sound == true) {consume.play();}
			
		// check prevent going over max food level
		if (player.getFoodLevel() + target.getCapacity() > MAXFOODLEVEL)
		{
			change = MAXFOODLEVEL - player.getFoodLevel();
			
			player.setFoodLevel(MAXFOODLEVEL);
		}
		else
		{
			player.setFoodLevel(player.getFoodLevel() + target.getCapacity());
			
			change = target.getCapacity();
		}
		
		
			target.setCapacity(target.getCapacity() - change);
			
			if (target.getCapacity() <= 0)
			{
				target.setColor(ColorUtil.rgb(186, 210, 161));
				target.setStatus(true);
		
				// make new food station and add to list
				FoodStation newStation = new FoodStation(width, height, this);
				gameObjects.add(newStation);
				totalFoodStations = totalFoodStations+1;
			}
		}
		
		
		setChanged();
		notifyObservers();
		
	}
	
	/** Simulates interaction of ant colliding with spider.
	 *  @return true if ant is now dead, false if ant still alive.
	 *  @preconditions N/A */
	public void collideWithSpider(Ant player, Spider target) 
	{
		// play sound on hit
		if(sound == true) {dmg.play();}
		
		// announce ant death if relevant
		if (player.getHealthLevel() == 1)
		{
			player.setHealthLevel(player.getHealthLevel()-1);
			player.setColor(ColorUtil.rgb(190, 190, 190));
			lives--;
			
			if (lives <=0)
			{
				System.out.println("Game over, you failed!");
				System.exit(0);
			}
			
			init();
			return;
		}
		else
		{
			player.setHealthLevel(player.getHealthLevel()-1);
			player.setColor(ColorUtil.rgb(ColorUtil.red(player.getColor()), ColorUtil.green(player.getColor())+19, ColorUtil.blue(player.getColor())+19));
		}
		
		setChanged();
		notifyObservers();
		
	}
	
	
	/** Displays the number of lives left, the elapsed time, the highest flag number 
	 * reached, the ants current food level, and the ants health level in a readable
	 * format.
	 *  @return N/A
	 *  @preconditions N/A */
	public void display() 
	{
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		
		String display = "Lives: " + lives + " Time: " + clock + " Highest Flag: " + subject.getLastFlagReached()
		+ " Food Level: " + subject.getFoodLevel() + " Health: " + subject.getHealthLevel() + "\n";
		}
	
	
	/** Displays basic directions for the game including key bindings.
	 *  @return N/A
	 *  @preconditions N/A */
	public void HelpInfo()
	{
		// build string
				String text = 
						"Key Bindings: \n"+
						"a - Accelerate\n"+
						"b - Brake\n"+
						"l - Left Turn\n"+
						"r - Right Turn\n"+
						"c - Set Food Consumption Rate\n"+
						"f - Collide With Food Station\n"+
						"g - Collide With Spider\n"+
						"t - Tick";
				
				
				Dialog.show("Help Info", text, "OK", null);
	}
	
	
	
	/** Outputs a map of the current world, aka where all the objects are located.
	 *  @return N/A
	 *  @preconditions N/A */
	public void map() 
	{
		String total = "";
		
		// gather the strings
		IIterator iter = gameObjects.getIterator();
		
		while (iter.hasNext()) 
		{
			GameObject next = iter.getNext();
			total = total + next.toString();
		}
		
			}
	
	
	/** Increments the clock by one tick. Updates spider heading, movable object
	 * position, and the food level of the ant.
	 *  @return N/A
	 *  @preconditions N/A */
	public void tick() 
	{

		IIterator iter = gameObjects.getIterator();
		// increment game clock
		clockMili = clockMili + 20;
		
		if ((clockMili % 500) == 0)
		{
			clock++;
			damaged =  false;
			consumed =  false;
		}
		
		
		Ant player = null;
		
		if (iter.hasNext())
		{
			player = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}		
		

		// heading is updated with spider move
		// call move for spiders and ants
		iter = gameObjects.getIterator();
		while (iter.hasNext())
		{
			GameObject temp = iter.getNext();
			
			if (temp instanceof Movable)
			{
				Movable move = (Movable)temp;
				move.move(20);

			}
		}
		
		// collision detection
		iter = gameObjects.getIterator();
		while (iter.hasNext())
		{
			GameObject curObj = iter.getNext();
			
			IIterator iter2 = gameObjects.getIterator();
			while(iter2.hasNext())
			{
				GameObject otherObj = iter2.getNext();
				
				if (otherObj != curObj)
				{
					if (curObj.collidesWith(otherObj))
					{
							curObj.handleCollision(otherObj);
					}
				}
			}
		}
	
		// reduce ant food level if time is a full second
		if ((clockMili % 500) == 0)
		{
			// reset all collision vectors
			iter = gameObjects.getIterator();
			while (iter.hasNext()) 
			{
				GameObject curObj = iter.getNext();
				curObj.resetCollisionVector();
			}
			
			int proposed = player.getFoodLevel() - player.getFoodConsumption();
			if (proposed <= 0)
			{
				player.setFoodLevel(0);
			
				// PATCH: should minus a life and reset when run out of food
				player.setHealthLevel(player.getHealthLevel()-1);
				player.setColor(ColorUtil.rgb(190, 190, 190));
				lives--;
			
				if (lives <=0)
				{
					System.out.println("Game over, you failed!");
					System.exit(0);
				}
			
				init();
				return;
			}
			else
			{
				player.setFoodLevel(proposed);
			}
		}
				
		setChanged();
		notifyObservers();
	}
	
	
	
	/** Changes the heading of the object -5 degrees.
	 *  @return N/A
	 *  @preconditions N/A */
	public void turnLeft() 
	{
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		subject.turnLeft();
		
		setChanged();
		notifyObservers();
	}
	
	/** Changes the heading of the object +5 degrees
	 *  @return N/A
	 *  @preconditions N/A */
	public void turnRight() 
	{
		IIterator iter = gameObjects.getIterator();
		
		
		Ant subject = null;
		
		if (iter.hasNext())
		{
			subject = (Ant)iter.getNext();
		}
		else
		{
			System.exit(0);
		}
		subject.turnRight();
		
		setChanged();
		notifyObservers();


	}
	
	
	
	/** Exits the game.
	 *  @return N/A
	 *  @preconditions N/A */
	public void exit() 
	{
		boolean confirmation = Dialog.show("Exit Confirmation", "Do you want to quit the game?", "yes", "no");					

		if (confirmation)
		{
			System.exit(0);
		}
	}
	
}