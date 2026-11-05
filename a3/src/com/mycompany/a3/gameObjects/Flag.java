package com.mycompany.a2.gameObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

public class Flag extends Fixed {
	// attributes
		 private final int sequenceNumber;
		 
	// constants
			private static final int MAXFLAG = 9;
			private static final int FLAGSIZE = 10;
			
		 // methods
		 /** Constructor 
		  * @return N/A
		  * @preconditions number must be between 1 and 9, location must be between 0,0 and 1000,1000*/
		 public Flag(int number, Point location, int maxX, int maxY)
		 {
			 super(FLAGSIZE, location, ColorUtil.GREEN, maxX, maxY);
			 // input validation
			 if (number < 1 || number > MAXFLAG)
			 {
				 throw new IllegalArgumentException("PROGRAM ERROR: Flag number must be between 1 and " + MAXFLAG + ".");
			 }
			 
			 // attribute assignment
			 sequenceNumber = number; //1-9
		 }
		 
		 /** Overrides the set color function in Game Object to be unable to change the
		  * color.
			 *  @return N/A
			 *  @preconditions DO NOT CALL */
		 @Override
		 public void setColor(int color) 
		 {
			 // force quit if called
			 throw new UnsupportedOperationException("setColor is currently disabled.");
		 }
		 
		 /** Returns the sequence number of the flag
			 *  @return sequence number of the flag
			 *  @preconditions sequence number must have a value from 1-9 */
		 public int getSequenceNumber() 
		 {
			 return sequenceNumber;
		 }
		 
		 /** Returns a string with all information about a flag object.
			 *  @return string of information about a flag object.
			 *  @preconditions N/A */
		 public String toString() 
		 {
			 return "Flag: loc=" + getLocation().getX() + "," + getLocation().getY()+ " color=[" + ColorUtil.red(getColor()) + "," 
					 + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
					 "] size=" + getSize() + " seqNum=" + sequenceNumber + "\n";
		 }
}
