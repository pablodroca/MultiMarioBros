package org.supermario.model;

import java.util.Observable;

public class Player extends GameElement {
	private final static Vector2D diffVelocityLeft = new Vector2D(-GameConstants.MARIO_WALK_STEP, 0);
	private final static Vector2D diffVelocityRight = new Vector2D(GameConstants.MARIO_WALK_STEP, 0);
	private final static Vector2D diffVelocityJump = new Vector2D(0, -2 * GameConstants.MARIO_WALK_STEP);
	private final static Vector2D diffGravityAcceleration = new Vector2D(0, GameConstants.GRAVITY_ACCELERATION);
	
	
	private Vector2D position;
	private Vector2D velocity;
	private Vector2D acceleration;
	private Rectangle[] boundaries;

	public Player(int x, int y) {
		this.position = new Vector2D(x, y);
		this.velocity = new Vector2D(0,0);
		this.acceleration = new Vector2D(0,0);
		int w = GameConstants.MARIO_WIDTH - GameConstants.BOUNDARIES_TOLERANCE;
		int h = GameConstants.MARIO_HEIGHT - GameConstants.BOUNDARIES_TOLERANCE;
		this.boundaries = new Rectangle[]{new Rectangle(position, w, h)};
    }
	
	public void move(){
		Vector2D newPosition = this.position.sum(this.velocity);
		this.velocity = this.velocity.sum(this.acceleration);
		applyNewPosition(newPosition);
	}

	public void undoMove(){
		this.velocity = this.velocity.substract(this.acceleration);
		Vector2D newPosition = this.position.substract(this.velocity);
		applyNewPosition(newPosition);
	}
	public void resolveCollisionWith(GameElement rightObj) {
		super.resolveCollisionWith(rightObj);
		this.acceleration = new Vector2D(0, 0);
		this.velocity = new Vector2D(this.velocity.getX(), 0);
	}

	private void applyNewPosition(Vector2D newPosition) {
		if (! this.position.equals(newPosition)) {			
			this.position = newPosition;
			this.boundaries[0].moveCenter(newPosition);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public Direction getDirection() {
		if (velocity.getX() < 0)
			return Direction.LEFT;
		else
			return Direction.RIGHT;
	}

	public void goLeft() {
		this.velocity = this.velocity.sum(diffVelocityLeft);
	}
	
	public void goRight() {
		this.velocity = this.velocity.sum(diffVelocityRight);
	}

	public void jump() {
		this.velocity = this.velocity.sum(diffVelocityJump);
		this.acceleration = this.acceleration.sum(diffGravityAcceleration);
	}

	public void stopLeft() {
		this.velocity = this.velocity.substract(diffVelocityLeft);
	}
	
	public void stopRight() {
		this.velocity = this.velocity.substract(diffVelocityRight);
	}

	@Override
	public Vector2D getPosition() {
		return this.position;
	}

	@Override
	public void addToGame(Game game) {
		game.setPlayer(this);
	}

	@Override
	public Rectangle[] getBorderShapes() {
		return this.boundaries;
	}
}
