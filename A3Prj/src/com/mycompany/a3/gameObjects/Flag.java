package com.mycompany.a3.gameObjects;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.codename1.charts.models.Point;
//

public class Flag extends Fixed {
	// attributes
		 private final int sequenceNumber;
		 
	// constants
			private static final int MAXFLAG = 9;
			private static final int FLAGSIZE = 75;
			private boolean isSelected = false;

		 // methods
		 /** Constructor 
		  * @return N/A
		  * @preconditions number must be between 1 and 9, location must be between 0,0 and 1000,1000*/
		 public Flag(int number, Point location, int maxX, int maxY, GameWorld gw)
		 {
			 super(FLAGSIZE, location, ColorUtil.rgb(255, 255, 0), maxX, maxY, gw);
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
		 
		 
		
			
			
			/** responds appropriately when collision with ant
			 *  @return N/A
			 *  @preconditions N/A */
			@Override
			public void handleCollision(GameObject otherObject)
			{
				if (otherObject instanceof Ant)
				{
					getgw().collideWithFlag((Ant)otherObject, this);
				}
			}
			
			
			/** sets isSelected
			 *  @return N/A
			 *  @preconditions N/A */
			@Override
			public void setSelected(boolean b)
			{
				isSelected = b;
			}
			
			
			/** gets isSelected
			 *  @return true if shape is selected
			 *  @preconditions N/A */
			@Override
			public boolean isSelected()
			{
				return isSelected;
			}
			
			
			/** checks if the pointer is contained in the object
			 *  @return true if pointer contained false if otherwise
			 *  @preconditions N/A */
			@Override
			public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt)
			{
				float px = pPtrRelPrnt.getX();
				float py = pPtrRelPrnt.getY();
				float xLoc = (pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2.0f)));
				float yLoc = (pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2.0f)));
				
				if ((px >= xLoc) && (px <= xLoc + getSize()) && (py >= yLoc) && (py <= yLoc + getSize()))
				{
					return true;
				}
				
				return false;
			}
			
			
			/** draws the flag object as a triangle, filled or unfilled depending on if selected, plus a label with the number of the flag overlaid
			 *  @return void
			 *  @preconditions flag color, flag number, and size must be set */
			public void draw(Graphics g, Point pCmpRelPrnt)
			{
				// get triangle corners, 0 is bottom left, 1 is bottom right, 2 is top
				int[] xValues = {0,0,0};
				int[] yValues = {0,0,0};
				
				xValues[0]= (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() - (this.getSize()/2))); 
				yValues[0]= (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2))); 
				
				xValues[1]= (int)(pCmpRelPrnt.getX() + (this.getLocation().getX() + (this.getSize()/2))); 
				yValues[1]= (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() - (this.getSize()/2))); 
				
				xValues[2]= (int)(pCmpRelPrnt.getX() + this.getLocation().getX()); 
				yValues[2]= (int)(pCmpRelPrnt.getY() + (this.getLocation().getY() + (this.getSize()/2))); 
				
				// get text position
				Font small = Font.createSystemFont(g.getFont().getStyle(), Font.STYLE_PLAIN, Font.SIZE_SMALL);
				String text = "" + getSequenceNumber();
				
				int x = (int)(pCmpRelPrnt.getX() + (this.getLocation().getX()));
				int y = (int)(pCmpRelPrnt.getY() + (this.getLocation().getY()));
				
				x = x - (small.stringWidth(text));
				y = y - (small.getHeight());
				
				
				// set color before draw
				g.setColor(this.getColor());
				
				if (isSelected())
				{
					g.drawPolygon(xValues, yValues, 3);
				}
				else
				{
					g.fillPolygon(xValues, yValues, 3);	
				}
				
				// set string settings and write (text should be small and black)
				g.setColor(ColorUtil.BLACK);
				g.drawString((text), x, y);
			}
}
