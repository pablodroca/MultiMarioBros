package org.supermario.model.elements;

import org.supermario.common.Rectangle;
import org.supermario.common.Vector2D;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElementVisitor;

public class Koopa extends GameElement {
	private Rectangle[] boundaries;
	
	public Koopa(int x, int y) {
		super(x, y);
		
		this.changeVelocity(new Vector2D(- GameConstants.KOOPA_WALK_STEP, 0));
		
		int w = GameConstants.KOOPA_WIDTH - GameConstants.BOUNDARIES_TOLERANCE;
		int h = GameConstants.KOOPA_HEIGHT - GameConstants.BOUNDARIES_TOLERANCE;
		int headWidth = (int) (0.30 * w);
		int headHeight = (int) (0.30 * h);
		int bodyWidth = w;
		int bodyHeight = h - headHeight;
		Vector2D headCenter = this.getPosition().substract(new Vector2D((w+headWidth)/2, (h+headHeight)/2));
		Vector2D bodyCenter = this.getPosition().substract(new Vector2D(0,(bodyHeight-h)/2));
		this.boundaries = new Rectangle[] {
								new Rectangle(headCenter, headWidth, headHeight),
								new Rectangle(bodyCenter, bodyWidth, bodyHeight)			
							};
	}

	@Override
	public void accept(GameElementVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addToGame(Game game) {
		// Do nothing
	}

	@Override
	public Rectangle[] getBoundaries() {
		return this.boundaries;
	}

	@Override
	protected void changeBoundariesPosition(Vector2D newPosition) {
		Vector2D movement = newPosition.substract(this.getPosition());
		for (Rectangle rectangle : boundaries) {
			rectangle.moveRelative(movement);
		}
	}

	@Override
	public void resolveCollisionWith(GameElement rightObj) {
		super.resolveCollisionWith(rightObj);
		this.changeVelocity(this.getVelocity().negative());
	}

}
