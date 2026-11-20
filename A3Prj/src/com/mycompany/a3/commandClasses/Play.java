package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class Play extends Command {

	// attributes
	private Game target = null;
	
	//methods
	//constructor
	public Play(Game target)
	{
		super("Play");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls turnRight.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.play();
	}
}
