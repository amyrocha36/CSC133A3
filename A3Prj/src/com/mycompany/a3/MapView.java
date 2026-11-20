package com.mycompany.a3;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.gameObjects.Fixed;
import com.mycompany.a3.gameObjects.Flag;
import com.mycompany.a3.gameObjects.FoodStation;
import com.mycompany.a3.gameObjects.GameObject;
//

public class MapView extends Container implements Observer{

	// attributes
	private GameWorld reference;
	private boolean play = true;
	private boolean placing = false;
	
	
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
	
	
	public void setPlay(boolean b)
	{
		play = b;
	}
	
	
	/** Overrides pointerPressed to select fixed shapes during pause
	 *  @return N/A
	 *  @preconditions a game world must be declared and initialized */
	@Override
	public void pointerPressed(int x, int y) {
		if(play == false)
		{
			IIterator iter = reference.getIterator();
			
			
			
			if(placing == false)
			{
				//make pointer location relative to parent’s origin
				x = x - getParent().getAbsoluteX();
				y = y - getParent().getAbsoluteY();
				
				Point pPtrRelPrnt = new Point(x, y);
				Point pCmpRelPrnt = new Point(getX(), getY());
				
				while (iter.hasNext())
				{
					GameObject temp = iter.getNext();
					if (temp instanceof ISelectable)
					{
						ISelectable target = (ISelectable)temp;
				
						if (target.contains(pPtrRelPrnt, pCmpRelPrnt)) 
						{
							target.setSelected(true);
						} 
						else 
					{
						target.setSelected(false);
					}
					}
				}
			}
			else
			{
				while (iter.hasNext())
				{
					GameObject temp = iter.getNext();
					if (temp instanceof ISelectable)
					{
						Fixed target = (Fixed)temp;
				
						if (target.isSelected())
						{
							x = x - getAbsoluteX();
							y = y - getAbsoluteY();
							
							place(new Point(x,y), target);
							setPlace(false);
						}
					}
				}
				
			}
		repaint();
		}
	}
	
	
	/** changes the location to the pointer on press
	 *  @return N/A
	 *  @preconditions position button must be pressed, an object must be selected, game must be paused */
	public void place(Point location, Fixed target)
	{
		target.setLocation(location);
		repaint();
	}
	
	
	/** sets placing mode on or off
	 *  @return N/A
	 *  @preconditions position button must be pressed, an object must be selected, game must be paused */
	public void setPlace(boolean b)
	{
		boolean objectSelected = false;
		
		IIterator iter = reference.getIterator();
		while (iter.hasNext())
		{
			GameObject temp = iter.getNext();
			if (temp instanceof ISelectable)
			{
				Fixed target = (Fixed)temp;
		
				if (target.isSelected())
				{
					objectSelected = true;
				}
			}
		}
		
		placing = (objectSelected)? b : false;
	}
}
