package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form; 
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.commandClasses.*;
import com.codename1.ui.Toolbar;


public class Game extends Form{

	// attributes
	private GameWorld gw; 
	private MapView center;
	private ScoreView north;
	
	// methods
	public Game() {
		gw = new GameWorld();
		center = new MapView();
		north  = new ScoreView();
		
		gw.addObserver(north);
		gw.addObserver(center);
		
		// create command objects
		AboutInfo about = new AboutInfo(gw);
		Accelerate acc = new Accelerate(gw);
		Brake brake = new Brake(gw);
		CollideWithFlag colFlag = new CollideWithFlag(gw);
		CollideWithFoodStation colFood = new CollideWithFoodStation(gw);
		CollideWithSpider colSpider = new CollideWithSpider(gw);
		Exit exit = new Exit(gw);
		HelpInfo help = new HelpInfo(gw);
		LeftTurn left = new LeftTurn(gw);
		RightTurn right = new RightTurn(gw);
		SetFoodConsumption setConsumpt = new SetFoodConsumption(gw);
		Tick tick = new Tick(gw);
		ToggleSound sound = new ToggleSound(gw);
		
		
		// set up GUI components
		setLayout(new BorderLayout());
		
		north.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		Container west = new Container();
		west.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		west.getAllStyles().setMargin(100,0,0,0);
		west.add(new MyButton(acc));
		west.add(new MyButton(left));
		
		Container east = new Container();
		east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		east.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		east.getAllStyles().setMargin(100,0,0,0);
		east.add(new MyButton(brake));
		east.add(new MyButton(right));
		
		Container south = new Container();
		south.setLayout(new FlowLayout(Component.CENTER));
		south.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		south.add(new MyButton(colFlag));
		south.add(new MyButton(colSpider));
		south.add(new MyButton(colFood));
		south.add(new MyButton(tick));
		
		add(BorderLayout.NORTH, north);
		add(BorderLayout.SOUTH, south);
		add(BorderLayout.EAST, east);
		add(BorderLayout.WEST, west);
		add(BorderLayout.CENTER, center);
		
		// set up tool bar
		Toolbar myBar = new Toolbar();
		setToolBar(myBar);
		myBar.setTitle("UnderControl Game");

		
		myBar.addCommandToSideMenu(acc);
		CheckBox soundCheck = new CheckBox("Sound");
		soundCheck.getAllStyles().setFgColor(ColorUtil.WHITE);
		soundCheck.setCommand(sound);
		myBar.addComponentToSideMenu(soundCheck);
		myBar.addCommandToSideMenu(about);
		myBar.addCommandToSideMenu(exit);
		myBar.addCommandToRightBar(help);


		// set up key bindings
		addKeyListener('a', acc);
		addKeyListener('b', brake);
		addKeyListener('r', right);
		addKeyListener('c', setConsumpt);
		addKeyListener('f', colFood);
		addKeyListener('g', colSpider);
		addKeyListener('t', tick);
		addKeyListener('l', left);

		
		
		this.show();
		
		
		gw.setWorldSize(center.getWidth(), center.getHeight());
		
		System.out.print("Widht " +center.getWidth() + " Height:" + center.getHeight() + "\n");
		gw.init();
	} 
}
