package com.mycompany.a3.gameObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
//

public class Flag extends Fixed {
	// attributes
		 private final int sequenceNumber;
		 
	// constants
			private static final int MAXFLAG = 9;
			private static final int FLAGSIZE = 75;
			
		 // methods
		 /** Constructor 
		  * @return N/A
		  * @preconditions number must be between 1 and 9, location must be between 0,0 and 1000,1000*/
		 public Flag(int number, Point location, int maxX, int maxY)
		 {
			 super(FLAGSIZE, location, ColorUtil.rgb(255, 255, 0), maxX, maxY);
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
		 
		 
			/** draws the flag object as a filled triangle plus a label with the number of the flag overlaid
			 *  @return void
			 *  @preconditions flag color, flag number, and size must be set */
			public void draw(Graphics g, Point pCmpRelPrnt)
			{
				// set color before draw
				g.setColor(this.getColor());
								
				// get triangle corners, 0 is bottom left, 1 is bottom right, 2 is top
				int[] xValues = {0,0,0};
				int[] yValues = {0,0,0};
				
				xValues[0]= (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2))); 
				yValues[0]= (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2))); 
				
				xValues[1]= (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() + (this.getSize()/2))); 
				yValues[1]= (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2))); 
				
				xValues[2]= (int)(pCmpRelPrnt.getX() + this.getLocation().getX()); 
				yValues[2]= (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() + (this.getSize()/2))); 

				
				g.fillPolygon(xValues, yValues, 3);
				
				
				// set string settings and write (text should be small and black)
				g.setColor(ColorUtil.BLACK);
				Font small = Font.createSystemFont(g.getFont().getStyle(), Font.STYLE_PLAIN, Font.SIZE_SMALL);
				
				String text = "" + getSequenceNumber();
				
				int x = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX()));
				int y = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY()));
				
				x = x - (small.stringWidth(text));
				y = y - (small.getHeight());
				
				System.out.println("test string width" + small.stringWidth(text) + "\n");
				
				g.drawString((text), x, y);
			}
}
