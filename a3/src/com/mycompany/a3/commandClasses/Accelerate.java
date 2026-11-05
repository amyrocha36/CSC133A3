package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
//

public class Accelerate extends Command {
	
	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public Accelerate(GameWorld target)
	{
		super("Accelerate");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls accelerate.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.accelerate();
	}
}
