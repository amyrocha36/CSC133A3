package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

public class MyButton extends Button {

	// attributes
	
	
	
	// methods
	public MyButton(Command cmd)
	{
		super(cmd);
		
		// always styles
		getAllStyles().setBgTransparency(255);
		getAllStyles().setPadding(Component.TOP, 5);
		getAllStyles().setPadding(Component.BOTTOM, 5);
		getAllStyles().setPadding(Component.LEFT, 2);
		getAllStyles().setPadding(Component.RIGHT, 2);


		
		// unselected style
		getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		// pressed style
		getPressedStyle().setBgColor(ColorUtil.CYAN);
		getPressedStyle().setFgColor(ColorUtil.BLACK);
		getUnselectedStyle().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
	}
	
}
