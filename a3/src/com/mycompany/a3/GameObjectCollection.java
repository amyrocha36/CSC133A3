package com.mycompany.a2;

import java.util.Vector;

import com.mycompany.a2.gameObjects.GameObject;


public class GameObjectCollection implements ICollection {
	
	// attributes
	private Vector<GameObject> collection;
	
	// inner class
	private class Iterator implements IIterator{
		// attributes
		private int position;
		
		
		// methods
		public Iterator()
		{
			position = -1;
		}
		
		
		/** returns true if there are more items in the collection
		 *  @return true or false 
		 *  @preconditions N/A */
		public boolean hasNext()
		{
			if (collection.size() <= 0)
			{
				return false;
			}
			if (position == collection.size()-1)
			{
				return false;
			}
			
			return true; //if collection isn't empty and next item isn't after end of collection
		}
		
		
		/** Returns the value of the next object in the collection.
		 *  @return a Game Object from the collection
		 *  @preconditions next is not null */
		public GameObject getNext()
		{
			position++;
			return(collection.get(position));
		}
	}
	
	// methods
	
	public GameObjectCollection() 
	{
		collection = new Vector<GameObject>();
	}
	
	/** adds a game object to the collection
	 *  @return nothing
	 *  @preconditions n/a */
	public void add(GameObject subject)
	{
		collection.add(subject);
	}
	
	
	/** returns an iterator for this collection
	 *  @return iterator initialized to start of list
	 *  @preconditions lives must have a value */
	public IIterator getIterator()
	{
		return new Iterator();
	}
}
