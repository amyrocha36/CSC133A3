package com.mycompany.a2;

import com.mycompany.a2.gameObjects.GameObject;

public interface ICollection {

	public void add(GameObject subject);
	public IIterator getIterator();
	
}
