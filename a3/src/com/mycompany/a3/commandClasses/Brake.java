package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
//

public class Brake extends Command {

	// attributes
		private GameWorld target = null;
		
		//methods
		//constructor
		public Brake(GameWorld target)
		{
			super("Break");
			
			this.target = target;
		}
		
		
		/** overwrite of action performed. calls brake.
		 *  @return N/A
		 *  @preconditions N/A */
		@Override
		public void actionPerformed(ActionEvent evt)
		{
			target.brake();
		}
	
}
