package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideWithSpider extends Command {

	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public CollideWithSpider(GameWorld target)
	{
		super("Collide With Spider");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls collideWithSpider.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.collideWithSpider();
	}
	
}
