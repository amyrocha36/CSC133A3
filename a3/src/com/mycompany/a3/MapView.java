package com.mycompany.a2;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{

	// attributes
	
	
	// methods
	// constructor
	public MapView()
	{
		getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255, 0, 0)));
	}
	
	
	/** Updates the MapView based on activity in GameWorld
	 *  @return N/A
	 *  @preconditions a game world must be declared and initialized */
	@Override
	public void update(Observable o, Object arg)
	{
		((GameWorld) o).map();
	}
}
