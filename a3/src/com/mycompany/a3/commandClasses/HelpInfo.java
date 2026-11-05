package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class HelpInfo extends Command {

	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public HelpInfo(GameWorld target)
	{
		super("Help");
		
		this.target = target;
	}
	
	
	/** override of action performed. Displays dialog with keys user can use in game.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.HelpInfo();
	}
}
