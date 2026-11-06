package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface IDrawable {

	// pCmpRelPrnt - point object relative to parent
	// gm - graphics obj
	public void draw(Graphics g, Point pCmpRelPrnt);
	
}
