package org.supermario.model.elements;

import org.supermario.common.Rectangle;
import org.supermario.common.Vector2D;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElementVisitor;


public class Player extends GameElement {
	private final static Vector2D diffVelocityLeft = new Vector2D(-GameConstants.MARIO_WALK_STEP, 0);
	private final static Vector2D diffVelocityRight = new Vector2D(GameConstants.MARIO_WALK_STEP, 0);
	private final static Vector2D diffVelocityJump = new Vector2D(0, -GameConstants.MARIO_JUMP_STEP);
	private final static Vector2D diffGravityAcceleration = new Vector2D(0, GameConstants.GRAVITY_ACCELERATION);

	private Rectangle boundaries;

	public Player(int x, int y) {
		super(x,y);

		this.changeAcceleration(new Vector2D(0, GameConstants.GRAVITY_ACCELERATION));

		int w = GameConstants.MARIO_WIDTH - GameConstants.BOUNDARIES_COLLISION_TOLERANCE;
		int h = GameConstants.MARIO_HEIGHT - GameConstants.BOUNDARIES_COLLISION_TOLERANCE;
		this.boundaries = new Rectangle(this.getPosition(), w, h);
    }
	
	@Override
	public void accept(GameElementVisitor visitor) {
		visitor.visit(this);
	}
	
	public void resolveCollisionWith(GameElement obj) {
		obj.collideWith(this);
	}

	public void collideWith(Koopa koopa) {
		if (this.isAbove(koopa)) {
			koopa.smash();
			this.smallJump();
		}
	}


	public void collideWith(GameElement player) {
		//do nothing
	}

	public void collideWith(Block block) {
		if (block.isAbove(this))
			block.destroy();
		else {
			this.undoMove();
			if (this.isAbove(block)) {
				this.changeAcceleration(new Vector2D(0, 0));
				this.changeVelocity(new Vector2D(this.getVelocity().getX(), 0));
			}
		}
	}

	public void goLeft() {
		this.addToVelocity(diffVelocityLeft);
	}

	public void goRight() {
		this.addToVelocity(diffVelocityRight);
	}

	private void smallJump() {
		if (! this.isJumping()) {
			this.addToVelocity(diffVelocityJump);
			this.changeAcceleration(diffGravityAcceleration);
		}
	}

	public void jump() {
		if (! this.isJumping()) {
			this.addToVelocity(diffVelocityJump);
			this.changeAcceleration(diffGravityAcceleration);
		}
	}

	public void stopLeft() {
		this.addToVelocity(diffVelocityLeft.negative());
	}
	
	public void stopRight() {
		this.addToVelocity(diffVelocityRight.negative());
	}

	public boolean isJumping() {
		return this.getVelocity().getY() < 0;
	}

	public boolean isFalling() {
		return this.getVelocity().getY() > 0;
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

	public void hit() {
	}
}
