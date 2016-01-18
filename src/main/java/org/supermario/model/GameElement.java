package org.supermario.model;

import java.util.Observable;

public abstract class GameElement extends Observable {
	public abstract void move();
	public abstract void undoMove();

	public abstract void addToGame(Game game);

	public abstract Vector2D getPosition();
	
	public abstract Rectangle[] getBorderShapes();

	public boolean checkCollision(GameElement targetObj) {
		for (Rectangle r1 : this.getBorderShapes()) {
			for (Rectangle r2: targetObj.getBorderShapes()) {
				if (r1.collideWith(r2))
					return true;
			}
		}
		return false;
	}

	public void resolveCollisionWith(GameElement rightObj) {
		this.undoMove();
	}
}
