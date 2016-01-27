package org.supermario.model.elements;

import org.supermario.common.Rectangle;
import org.supermario.common.Vector2D;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElementVisitor;


public class Player extends GameElement {
	private final static Vector2D diffVelocityLeft = new Vector2D(-GameConstants.MARIO_WALK_STEP, 0);
	private final static Vector2D diffVelocityRight = new Vector2D(GameConstants.MARIO_WALK_STEP, 0);
	private final static Vector2D diffVelocityJump = new Vector2D(0, -2 * GameConstants.MARIO_WALK_STEP);
	private final static Vector2D diffGravityAcceleration = new Vector2D(0, GameConstants.GRAVITY_ACCELERATION);

	private Rectangle boundaries;

	public Player(int x, int y) {
		super(x,y);
		int w = GameConstants.MARIO_WIDTH - GameConstants.BOUNDARIES_TOLERANCE;
		int h = GameConstants.MARIO_HEIGHT - GameConstants.BOUNDARIES_TOLERANCE;
		this.boundaries = new Rectangle(this.getPosition(), w, h);
    }
	
	@Override
	public void accept(GameElementVisitor visitor) {
		visitor.visit(this);
	}
	
	public void resolveCollisionWith(GameElement rightObj) {
		super.resolveCollisionWith(rightObj);
		this.changeAcceleration(new Vector2D(0, 0));
		this.changeVelocity(new Vector2D(this.getVelocity().getX(), 0));
	}

	public void goLeft() {
		this.addToVelocity(diffVelocityLeft);
	}
	
	public void goRight() {
		this.addToVelocity(diffVelocityRight);
	}

	public void jump() {
		this.addToVelocity(diffVelocityJump);
		this.addToAcceleration(diffGravityAcceleration);
	}

	public void stopLeft() {
		this.addToVelocity(diffVelocityLeft.negative());
	}
	
	public void stopRight() {
		this.addToVelocity(diffVelocityRight.negative());
	}

	@Override
	public void addToGame(Game game) {
		game.setPlayer(this);
	}

	@Override
	public Rectangle[] getBoundaries() {
		return new Rectangle[] {this.boundaries};
	}

	@Override
	protected void changeBoundariesPosition(Vector2D newPosition) {
		this.boundaries.moveAbsolute(newPosition);
	}
}
