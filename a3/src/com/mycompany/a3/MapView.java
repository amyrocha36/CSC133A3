package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.gameObjects.GameObject;
//

public class MapView extends Container implements Observer{

	// attributes
	private GameWorld reference;
	
	
	// methods
	// constructor
	public MapView(GameWorld owner)
	{
		getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.rgb(255, 0, 0)));
		reference = owner;
	}
	
	
	/** Updates the MapView based on activity in GameWorld
	 *  @return N/A
	 *  @preconditions a game world must be declared and initialized */
	@Override
	public void update(Observable o, Object arg)
	{
		((GameWorld) o).map();
		this.repaint();
	}
	
	
	/** Overrides paint to loop through and draw all game world objects
	 *  @return N/A
	 *  @preconditions a game world must be declared and initialized */
	@Override
	public void paint(Graphics g)
	{
		IIterator collection = reference.getIterator();
		GameObject target;
		Point pnt = new Point(this.getX(), this.getY());
		
		while(collection.hasNext()) 
		{
			target  = collection.getNext();
			target.draw(g, pnt);
		}
	}
}
