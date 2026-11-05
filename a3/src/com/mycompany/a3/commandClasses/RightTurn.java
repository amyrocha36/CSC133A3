package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
//

public class RightTurn extends Command {
	
	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public RightTurn(GameWorld target)
	{
		super("Right");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls turnRight.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.turnRight();
	}
}
