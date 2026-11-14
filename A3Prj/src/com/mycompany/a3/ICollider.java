package com.mycompany.a3;

import com.mycompany.a3.gameObjects.GameObject;

public interface ICollider {
	
	public Boolean collidesWith(GameObject otherObject);
	public void handleCollision (GameObject otherObject);

}
