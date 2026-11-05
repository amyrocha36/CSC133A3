package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer{

	
	// statics
	private static final String livesText = "Lives: ";
	private static final String clockText = "Clock: ";
	private static final String flagText = "Last Flag Reached: ";
	private static final String hungerText = "Ant Food Level: ";
	private static final String healthText = "Ant Health: ";
	private static final String soundText = "Sound: ";

	
	// attributes
	private Label lives;
	private Label clock;
	private Label flag;
	private Label hunger;
	private Label health;
	private Label sound;
	
	
	
	// methods
	// constructor
	public ScoreView()
	{
		
		setLayout(new FlowLayout(Component.CENTER));
		
		
		// clock label
		clock = new Label(clockText + "000");
		clock.getAllStyles().setPadding(Component.RIGHT,2);
		clock.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(clock);
		
		
		// lives label
		lives = new Label(livesText + "3");
		lives.getAllStyles().setPadding(Component.RIGHT,5);
		lives.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(lives);

		
		// flag label
		flag = new Label(flagText + "1");
		flag.getAllStyles().setPadding(Component.RIGHT,2);
		flag.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(flag);

		
		// hunger label
		hunger = new Label(hungerText + "000");
		hunger.getAllStyles().setPadding(Component.RIGHT,2);
		hunger.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(hunger);

		
		// health label
		health = new Label(healthText + "100");
		health.getAllStyles().setPadding(Component.RIGHT,2);
		health.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(health);


		// sound label
		sound = new Label(soundText + "OFF");
		sound.getAllStyles().setPadding(Component.RIGHT,2);
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
		add(sound);

	}
	
	
	/** Updates the ScoreView based on activity from game world
	 *  @return N/A
	 *  @preconditions a game world must be declared and initialized */
	@Override
	public void update(Observable o, Object arg)
	{
		GameWorld subject = (GameWorld) o;
		String temp = "";
		
		//set lives label
		temp = Integer.toString(subject.getLives());
		lives.setText(livesText + temp);
		
		
		// set clock label
		temp = Integer.toString(subject.getTime());		
		clock.setText(clockText + temp);
		
		// set flag label
		temp = Integer.toString(subject.getLastFlag());	
		flag.setText(flagText + temp);
		
		// set hunger text
		temp = Integer.toString(subject.getAntHunger());	
		hunger.setText(hungerText + temp);
		
		//set health text
		temp = Integer.toString(subject.getAntHealth());	
		health.setText(healthText + temp);
		
		
		// set sound label
		if (subject.getSound()) 
		{
			sound.setText(soundText + "ON");
		}
		else
		{
			sound.setText(soundText + "OFF");
		}
		
	}
}
