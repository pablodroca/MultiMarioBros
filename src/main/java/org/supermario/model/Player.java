package org.supermario.model;

import java.util.Observable;

public class Player extends GameElement {
	private Vector2D position;
	private Vector2D velocity;
	private Rectangle[] boundaries;

	public Player(int x, int y) {
		this.position = new Vector2D(x, y);
		this.velocity = new Vector2D(0,0);
		int w = GameConstants.MARIO_WIDTH - GameConstants.BOUNDARIES_TOLERANCE;
		int h = GameConstants.MARIO_HEIGHT - GameConstants.BOUNDARIES_TOLERANCE;
		this.boundaries = new Rectangle[]{new Rectangle(position, w, h)};
    }
	
	public void move(){
		Vector2D newPosition = this.position.sum(this.velocity);
		applyNewPosition(newPosition);
	}

	public void undoMove(){
		Vector2D newPosition = this.position.substract(this.velocity);
		applyNewPosition(newPosition);
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
		this.velocity = new Vector2D(-GameConstants.MARIO_WALK_STEP, 0);
	}
	
	public void goRight() {
		this.velocity = new Vector2D(GameConstants.MARIO_WALK_STEP, 0);
	}

	public void goUp() {
		this.velocity = new Vector2D(0, -GameConstants.MARIO_WALK_STEP);
	}
	
	public void goDown() {
		this.velocity = new Vector2D(0, GameConstants.MARIO_WALK_STEP);
	}

	public void stop() {
		this.velocity = new Vector2D(0,0);
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
