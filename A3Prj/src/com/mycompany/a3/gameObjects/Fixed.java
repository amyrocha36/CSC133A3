package com.mycompany.a3.gameObjects;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.ISelectable;
//


public abstract class Fixed extends GameObject implements ISelectable{
	
	// methods
	
	// Constructor
	
	Fixed(int size, Point location, int color, int maxX, int maxY, GameWorld gw)
	{
		super(size, location, color,  maxX,  maxY, gw);
	}
	
	public abstract void setSelected(boolean b);
	
	public abstract boolean isSelected();
	
	public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
}
