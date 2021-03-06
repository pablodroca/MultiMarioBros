package org.supermario.model.elements;

import java.util.Observable;

import org.supermario.common.Direction;
import org.supermario.common.Rectangle;
import org.supermario.common.Vector2D;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElementVisitor;

public abstract class GameElement extends Observable {
	private Vector2D position;
	private Vector2D velocity;
	private Vector2D acceleration;
	
	public GameElement(int x, int y) {
		this.position = new Vector2D(x,y);
		this.velocity = new Vector2D(0,0);
		this.acceleration = new Vector2D(0, 0);
	}

	public abstract void addToGame(Game game);
	public abstract void accept(GameElementVisitor visitor);

	public boolean inCollisionWith(GameElement targetObj) {
		for (Rectangle r1 : this.getBoundaries()) {
			for (Rectangle r2: targetObj.getBoundaries()) {
				if (r1.inCollisionWith(r2))
					return true;
			}
		}
		return false;
	}

	public void collideWith(Koopa koopa) {
		//do nothing by default
	}

	public void collideWith(Player player) {
		//do nothing by default
	}

	public void collideWith(Block block) {
		//do nothing by default
	}

	public abstract Rectangle[] getBoundaries() ;
	protected abstract void changeBoundariesPosition(Vector2D newPosition);

	public abstract void resolveCollisionWith(GameElement rightObj);

	public void move() {
		Vector2D newPosition = this.position.sum(this.velocity).sum(this.acceleration.scale(.5));
		this.velocity = this.velocity.sum(this.acceleration);
		changePosition(newPosition);
	}
	public void undoMove() {
		this.velocity = this.velocity.substract(this.acceleration);
		Vector2D newPosition = this.position.substract(this.velocity);
		changePosition(newPosition);
	}

	private void changePosition(Vector2D newPosition) {
		if (! this.position.equals(newPosition)) {			
			this.changeBoundariesPosition(newPosition);
			this.position = newPosition;
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	protected void changeAcceleration(Vector2D acceleration) {
		this.acceleration = acceleration;
	}
	
	protected void changeVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	protected void addToAcceleration(Vector2D delta) {
		this.acceleration = this.acceleration.sum(delta);
	}

	protected void addToVelocity(Vector2D delta) {
		this.velocity = this.velocity.sum(delta);
	}
	
	public Direction getDirection() {
		if (velocity.getX() < 0)
			return Direction.LEFT;
		else
			return Direction.RIGHT;
	}

	public Vector2D getPosition() {
		return this.position;
	}
	
	public Vector2D getVelocity() {
		return this.velocity;
	}

	public boolean isAbove(GameElement element) {
		for (Rectangle boundary : this.getBoundaries()) {
			for (Rectangle elementBoundary : element.getBoundaries()) {
				if (! boundary.isAbove(elementBoundary, GameConstants.BOUNDARIES_RELATIVE_POSITION_TOLERANCE)) {
					//With only one boundary not above the other element,
					//we can ensure the current object isn't above the one given by parameter
					return false;
				}
			}
		}
		return true;
	}
}
