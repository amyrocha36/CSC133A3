package com.mycompany.a3;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.mycompany.a3.gameObjects.*;
import com.codename1.charts.models.Point;
//


public class GameWorld extends Observable {
	
	// constants
	private static final int MAXFOODLEVEL = 100;
	
	// attributes
	private int clock;
	private int lives;
	private boolean sound = false;
	private GameObjectCollection gameObjects;
	private static Random generator = new Random();
	private int totalFoodStations = 3;
	private int totalSpiders = 3;
	private int totalFlags = 6;
	
	private int width = 1000;		//EDIT LATER TO MATCH MAP VIEW
	private int height = 1000;

	
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
	public int getTime() 
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
				gameObjects.add(new Ant(width, height));
				
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
				gameObjects.add(new Flag(1, new Point(antLocation.getX(), antLocation.getY()), width, height)); // flag 1 location = ant
				gameObjects.add(new Flag(2, new Point((int)(width*0.55),(int)(height*0.55)), width, height));
				gameObjects.add(new Flag(3, new Point((int)(width*0.47),(int)(height*0.63)), width, height));
				gameObjects.add(new Flag(4, new Point((int)(width*0.34), (int)(height*0.50)), width, height));
				gameObjects.add(new Flag(5, new Point((int) (width*0.55), (int)(height*0.29)), width, height));
				gameObjects.add(new Flag(6, new Point((int)(width*0.89), (int)(height*0.63)), width, height));
				
				// spawn 3 spiders (index 7-9)
				for (int i=0; i < totalSpiders; i++)
				{
					gameObjects.add(new Spider(width, height));

				}
				
				// spawn 3 food stations (index 10-12)
				for (int i=0; i < totalFoodStations; i++)
				{
					gameObjects.add(new FoodStation(width, height));

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
		System.out.println("Accelerated to " + subject.getSpeed() + " blocks per tick\n");

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
		System.out.println("Decelerated to " + subject.getSpeed() + " blocks per tick \n");

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
		
		System.out.println("New food consumption rate is " + 
				subject.getFoodConsumption() + "\n");
		
		setChanged();
		notifyObservers();
		
	}
	
	/** Simulates interaction of ant colliding with flag.
	 *  @return N/A
	 *  @preconditions n/a */
	public void collideWithFlag() 
	{
		// get ant
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
		
		
		
		int number;
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[] {cOk, cCancel};
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter a flag number:", myTF, cmds);
		if (c == cOk)
		{
			try
			{
				number = Integer.parseInt(myTF.getText());			
						
				if (number <= totalFlags)
				{
					iter = gameObjects.getIterator();
					Flag hit = null;
					
					while (iter.hasNext())
					{
						GameObject temp = iter.getNext();
						
						if (temp instanceof Flag)
						{
							hit = (Flag)temp;
							break;
						}
					}
		
					subject.setLocation(hit.getLocation());
					System.out.println("Collided with flag " + (number) + "\n");
			
					if (subject.getLastFlagReached() == number-1)
					{
						subject.setLastFlagReached(number);
						System.out.println("New highest Flag is " + subject.getLastFlagReached() + "\n");
				
						// win condition
						if (number == totalFlags)
						{
							System.out.println("Game over, you win! \n");
							System.out.println("Total Time: " + clock + "\n");
							System.exit(0);
						}
					}
					else
					{
						System.out.println("To advance, reach flag " + (subject.getLastFlagReached()+1) +"\n");
					}
			
				}
				else
				{
					System.out.println("Flag " + (number) + " does not exist. Largest flag number spawned is 6. \n");
				}
			} catch (NumberFormatException e) 
			{
				Dialog.show("Invalid Input", "Flag number must be a valid integer between 1 and 9.", "OK", null);					
			}
		}
		
		setChanged();
		notifyObservers();
		
	}
	
	/** Simulates interaction of ant colliding with food station.
	 *  @return N/A
	 *  @preconditions N/A */
	public void collideWithFoodStation() 
	{
		// set up
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
				
		iter = gameObjects.getIterator();
		FoodStation hit = null;
		
		while (iter.hasNext())
		{
			GameObject temp = iter.getNext();
			
			if (temp instanceof FoodStation)
			{
				hit = (FoodStation)temp;
				
				if (hit.getCapacity() != 0) {break;}
			}
		}		
		// get food station with food, simulate collision
			subject.setLocation(hit.getLocation());
			
		// check prevent going over max food level
		if (subject.getFoodLevel() + hit.getCapacity() > MAXFOODLEVEL)
		{
			subject.setFoodLevel(MAXFOODLEVEL);
		}
		else
		{
			subject.setFoodLevel(subject.getFoodLevel() + hit.getCapacity());
		}
		
		// phase out food station
		hit.setCapacity(0);
		hit.setColor(ColorUtil.rgb(186, 210, 161));
		
		// make new food station and add to list
		FoodStation newStation = new FoodStation(width, height);
		gameObjects.add(newStation);
		totalFoodStations = totalFoodStations+1;
		
		System.out.println("Collided with food station. Ant food level is now " + subject.getFoodLevel() + "\n");
		
		setChanged();
		notifyObservers();
		
	}
	
	/** Simulates interaction of ant colliding with spider.
	 *  @return true if ant is now dead, false if ant still alive.
	 *  @preconditions N/A */
	public void collideWithSpider() 
	{
		// set up
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
		
		iter = gameObjects.getIterator();
		int index = generator.nextInt(totalSpiders-1);
		Spider hit = null;
		
		while (iter.hasNext())
		{
			GameObject temp = iter.getNext();
			
			if (temp instanceof Spider)
			{
				hit = (Spider)temp;
				break;
			}
		}		
		
		
		for (int i=1; i<index; i++)
		{
			if (!iter.hasNext())
			{
				System.exit(1);
			}
			else
			{
				hit = (Spider)iter.getNext();
			}
		}
		
		subject.setLocation(hit.getLocation());
		

		// announce ant death if relevant
		if (subject.getHealthLevel() == 1)
		{
			subject.setHealthLevel(subject.getHealthLevel()-1);
			subject.setColor(ColorUtil.rgb(190, 190, 190));
			lives--;
			System.out.println("Ant is now dead. You have " +lives+ " remaining.\n");
			
			if (lives <=0)
			{
				System.out.println("Game over, you failed!");
				System.exit(0);
			}
			
			System.out.println("Resetting game...\n");
			init();
			System.out.println("Game reset!\n");
			return;
		}
		else
		{
			subject.setHealthLevel(subject.getHealthLevel()-1);
			subject.setColor(ColorUtil.rgb(ColorUtil.red(subject.getColor()), ColorUtil.green(subject.getColor())+19, ColorUtil.blue(subject.getColor())+19));
			System.out.println("Ant has been damaged! Ant health is now " + subject.getHealthLevel() + "\n");
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
		
		System.out.println(display);
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
		
		
		// print map to screen
		System.out.println(total);
	}
	
	
	/** Increments the clock by one tick. Updates spider heading, movable object
	 * position, and the food level of the ant.
	 *  @return N/A
	 *  @preconditions N/A */
	public void tick() 
	{

		IIterator iter = gameObjects.getIterator();
		
		
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
				move.move();

			}
		}
	
		
		// reduce ant food level
		int proposed = player.getFoodLevel() - player.getFoodConsumption();
		if (proposed <= 0)
		{
			player.setFoodLevel(0);
			
			// PATCH: should minus a life and reset when run out of food
			player.setHealthLevel(player.getHealthLevel()-1);
			player.setColor(ColorUtil.rgb(190, 190, 190));
			lives--;
			System.out.println("Ant is now dead. You have " +lives+ " remaining.\n");
			
			if (lives <=0)
			{
				System.out.println("Game over, you failed!");
				System.exit(0);
			}
			
			System.out.println("Resetting game...\n");
			init();
			System.out.println("Game reset!\n");
			return;
			
		}
		else
		{
			player.setFoodLevel(proposed);
		}
		
		// increment game clock
		clock++;
		
		System.out.println("Time has passed. Clock is now " + clock + ".\n");
		
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
		System.out.println("Turned left. New heading is " + subject.getHeading() + "\n");
		
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
		System.out.println("Turned right. New heading is " + subject.getHeading() + "\n");
		
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
	
	
	/** implementation of notifyObservers
	 *  @return N/A
	 *  @preconditions N/A */
	

}
