package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;


public class BGSound implements Runnable {

	
	// attributes
	private Media m;
	
	
	// methods
	public BGSound(String fileName, String audioType)
	{
		if (Display.getInstance().getCurrent() == null)
		{
			System.out.println("Error: Create sound objects after calling show() \n");
			System.exit(0);
		}
		
		while(m == null) {

		try
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			m = MediaManager.createMedia(is, audioType, this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}
	}
	
	
	/** Pauses the sound
	 *  @return N/A
	 *  @preconditions N/A */
	public void pause()
	{
		m.pause();
	}
	
	/** Plays the sound
	 *  @return N/A
	 *  @preconditions N/A */
	public void play()
	{
		m.play();
	}
	
	
	/** Starts the sound from the beginning
	 *  @return N/A
	 *  @preconditions N/A */
	public void run()
	{
		m.setTime(0);
		m.play();
	}
}
