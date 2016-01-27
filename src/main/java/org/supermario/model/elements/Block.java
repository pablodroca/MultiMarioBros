package org.supermario.model.elements;

import org.supermario.common.Rectangle;
import org.supermario.common.Vector2D;
import org.supermario.model.Game;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElementVisitor;

public class Block extends GameElement {
	private Rectangle boundaries;

	public Block(int x, int y) {
		super(x, y);
		int w = GameConstants.BLOCK_SIDE_SIZE - GameConstants.BOUNDARIES_TOLERANCE;
		int h = GameConstants.BLOCK_SIDE_SIZE - GameConstants.BOUNDARIES_TOLERANCE;
		this.boundaries = new Rectangle(this.getPosition(), w, h);
	}
	
	@Override
	public void accept(GameElementVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addToGame(Game game) {
		//Do nothing
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
