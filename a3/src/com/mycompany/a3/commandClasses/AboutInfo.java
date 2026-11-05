package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class AboutInfo extends Command {
	
	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public AboutInfo(GameWorld target)
	{
		super("About");
		
		this.target = target;
	}
	
	
	/** override of action performed. Displays about info.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.AboutInfo();				
	}
}
