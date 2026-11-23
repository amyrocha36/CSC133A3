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
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.commandClasses.*;
import com.mycompany.a3.gameObjects.Flag;
import com.mycompany.a3.gameObjects.FoodStation;
import com.mycompany.a3.gameObjects.GameObject;
import com.codename1.ui.Toolbar;
//

public class Game extends Form implements Runnable{

	// attributes
	private GameWorld gw; 
	private MapView center;
	private ScoreView north;
	private Container south;
	private Container east;
	private Container west;
	private UITimer timer = new UITimer(this);
	private Toolbar myBar;
	private boolean playState = true;
	
	// buttons
	private MyButton accelerate;
	private MyButton leftTurn;
	private MyButton brakeButton;
	private MyButton rightTurn;
	private MyButton pauseButton;
	private MyButton playButton;
	private MyButton positionButton;
	
	// commands with keys or menu items
	private Accelerate acc;
	private Brake brake;
	private RightTurn right;
	private SetFoodConsumption setConsumpt;
	private LeftTurn left;
	private CheckBox soundCheck;
	private AboutInfo about;
	private Exit exit;
	
	// methods
	public Game() {
		gw = new GameWorld();
		center = new MapView(gw);
		north  = new ScoreView();
		
		gw.addObserver(north);
		gw.addObserver(center);
		
		// create command objects
		about = new AboutInfo(gw);
		acc = new Accelerate(gw);
		brake = new Brake(gw);
		exit = new Exit(gw);
		HelpInfo help = new HelpInfo(gw);
		left = new LeftTurn(gw);
		right = new RightTurn(gw);
		setConsumpt = new SetFoodConsumption(gw);
		Tick tick = new Tick(gw);
		ToggleSound sound = new ToggleSound(gw);
		Pause pause = new Pause(this);
		Play play = new Play(this);
		Position pos = new Position(center);
		
		// create buttons
		pauseButton = new MyButton(pause);
		playButton = new MyButton(play);
		accelerate = new MyButton(acc);
		leftTurn = new MyButton(left);
		brakeButton = new MyButton(brake);
		rightTurn = new MyButton(right);
		positionButton = new MyButton(pos);
		
		
		// set up GUI components
		setLayout(new BorderLayout());
		
		north.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		west = new Container();
		west.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		west.getAllStyles().setMargin(100,0,0,0);
		west.add(accelerate);
		west.add(leftTurn);
		
		east = new Container();
		east.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		east.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		east.getAllStyles().setMargin(100,0,0,0);
		east.add(brakeButton);
		east.add(rightTurn);
		
		south = new Container();
		south.setLayout(new FlowLayout(Component.CENTER));
		south.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.BLACK));
		south.add(positionButton);
		positionButton.setEnabled(false);
		south.add(pauseButton);

		
		add(BorderLayout.NORTH, north);
		add(BorderLayout.SOUTH, south);
		add(BorderLayout.EAST, east);
		add(BorderLayout.WEST, west);
		add(BorderLayout.CENTER, center);
		
		// set up tool bar
		myBar = new Toolbar();
		setToolBar(myBar);
		myBar.setTitle("UnderControl Game");

		
		myBar.addCommandToSideMenu(acc);
		soundCheck = new CheckBox("Sound");
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
		addKeyListener('l', left);

		
		
		this.show();
		
		
		gw.setWorldSize(center.getWidth(), center.getHeight());
		
		System.out.print("Width " + center.getWidth() + " Height:" + center.getHeight() + "\n");
		gw.init();
		revalidate();
		
		gw.createSounds();
		revalidate();
		
		timer.schedule(20, true, this);
	} 
	
	
	public void run()
	{
		gw.tick();
	}
	
	
	/** Pauses game and activated positioning mode
	 *  @return N/A
	 *  @preconditions N/A */
	public void pause()
	{
		south.replace(pauseButton, playButton, null, null, 0);
		timer.cancel();
		
		// disable relevant commands
		
		// disable key bindings
		removeKeyListener('a', acc);
		removeKeyListener('b', brake);
		removeKeyListener('r', right);
		removeKeyListener('c', setConsumpt);
		removeKeyListener('l', left);
		
		// disable the buttons
		accelerate.setEnabled(false);
		leftTurn.setEnabled(false);
		brakeButton.setEnabled(false);
		rightTurn.setEnabled(false);
		positionButton.setEnabled(true);
		
		// disable commands on tool bar
		acc.setEnabled(false);
		myBar.removeCommand(acc);
		myBar.removeCommand(exit);
		myBar.removeComponent(soundCheck);
		myBar.removeCommand(about);
		
		myBar.addCommandToSideMenu(acc);
		myBar.addComponentToSideMenu(soundCheck);
		myBar.addCommandToSideMenu(about);
		myBar.addCommandToSideMenu(exit);
		
		center.setPlay(false);
		gw.setPlay(false);
		
		revalidate();
		
	}
	
	
	/** Plays the game including sound and animation
	 *  @return N/A
	 *  @preconditions N/A */
	public void play()
	{
		south.replace(playButton, pauseButton, null, null, 0);
		timer.schedule(20, true, this);
		
		// enable key bindings
		addKeyListener('a', acc);
		addKeyListener('b', brake);
		addKeyListener('r', right);
		addKeyListener('c', setConsumpt);
		addKeyListener('l', left);
		
		// enable the buttons
		accelerate.setEnabled(true);
		leftTurn.setEnabled(true);
		brakeButton.setEnabled(true);
		rightTurn.setEnabled(true);
		positionButton.setEnabled(false);
		
		// enable commands on tool bar
		acc.setEnabled(true);
		myBar.removeCommand(acc);
		myBar.removeCommand(exit);
		myBar.removeComponent(soundCheck);
		myBar.removeCommand(about);
		
		myBar.addCommandToSideMenu(acc);
		myBar.addComponentToSideMenu(soundCheck);
		myBar.addCommandToSideMenu(about);
		myBar.addCommandToSideMenu(exit);
		
		center.setPlay(true);
		gw.setPlay(true);
		
		// unselect all during play
	
		IIterator iter = gw.getIterator();

		while (iter.hasNext())
		{
			GameObject temp = iter.getNext();
			if (temp instanceof Flag)
			{
				Flag target = (Flag)temp;
				target.setSelected(false);
			}
						
			if (temp instanceof FoodStation)
			{
				FoodStation target = (FoodStation)temp;
				target.setSelected(false);
			}
		}
		
		revalidate();
		
	}
}
