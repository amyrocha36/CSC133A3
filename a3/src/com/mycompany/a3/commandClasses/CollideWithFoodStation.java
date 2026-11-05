package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideWithFoodStation extends Command {

	// attributes
	private GameWorld target = null;
	
	// methods
	// constructor
	public CollideWithFoodStation (GameWorld target)
	{
		super("Collide With Food Station");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls collideWithFoodStation.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.collideWithFoodStation();
	}
	
}
