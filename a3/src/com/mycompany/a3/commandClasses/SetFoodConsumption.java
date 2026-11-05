package com.mycompany.a3.commandClasses;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
//

public class SetFoodConsumption extends Command {
	
	// attributes
	private GameWorld target = null;
	
	//methods
	//constructor
	public SetFoodConsumption(GameWorld target)
	{
		super("Set Food Consumption Rate");
		
		this.target = target;
	}
	
	
	/** override of action performed. calls changeFoodConsumptionRate.
	 *  @return N/A
	 *  @preconditions N/A */
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		target.changeFoodConsumptionRate();
	}
}
