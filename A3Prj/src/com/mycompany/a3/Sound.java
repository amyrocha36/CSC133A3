package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {

	// attributes
	private Media m;
	
	// methods
	public Sound(String fileName, String audioType)
	{
		if (Display.getInstance().getCurrent() == null)
			{
				System.out.println("Error: Create sound objects after calling show()");
				System.exit(0);
			}
		
		while(m == null) {
		
		try 
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), 
					"/"+fileName);
		
			m = MediaManager.createMedia(is,  audioType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}
		
	}
	
	
	/** plays the sound from the beginning once
	 *  @return N/A
	 *  @preconditions N/A */
	public void play()
	{
		m.setTime(0);
		m.play();
	}
	
}
