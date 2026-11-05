package com.mycompany.a3.commandClasses;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
//

public class CollideWithFlag extends Command {

	// attributes
		private GameWorld target = null;
		
		//methods
		//constructor
		public CollideWithFlag(GameWorld target)
		{
			super("Collide With Flag");
			
			this.target = target;
		}
		
		
		/** overwrite of action performed. calls collidewithFlag.
		 *  @return N/A
		 *  @preconditions N/A */
		@Override
		public void actionPerformed(ActionEvent evt)
		{
			target.collideWithFlag();
		}
	
}
