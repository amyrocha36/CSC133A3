package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ToggleSound extends Command {
	
	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public ToggleSound(GameWorld target)
	{
		super("Sound ON/OFF");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls setSound.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		if (target.getSound())
		{
			target.setSound(false);
			return;
		}
		
		target.setSound(true);
	}
}
