package com.mycompany.a3;

import com.mycompany.a3.gameObjects.GameObject;
//

public interface ICollection {

	public void add(GameObject subject);
	public IIterator getIterator();
	
}
