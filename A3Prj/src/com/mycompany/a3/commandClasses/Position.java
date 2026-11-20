package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.MapView;

public class Position extends Command {

	
	// attributes
	private MapView target = null;
	
	//methods
	//constructor
	public Position(MapView target)
	{
		super("Position");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls turnRight.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.setPlace(true);
	}
	
}
