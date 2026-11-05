package com.mycompany.a2.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class LeftTurn extends Command {

	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public LeftTurn(GameWorld target)
	{
		super("Left");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls turnLeft.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.turnLeft();
	}
}
