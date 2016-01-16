package org.supermario.model;

public class Block extends GameElement {
	private Vector2D position;
	private Rectangle[] borders;

	public Block(int x, int y) {
		this.position = new Vector2D(x, y);
		int w = GameConstants.BLOCK_SIDE_SIZE - GameConstants.BOUNDARIES_TOLERANCE;
		int h = GameConstants.BLOCK_SIDE_SIZE - GameConstants.BOUNDARIES_TOLERANCE;
		this.borders = new Rectangle[]{new Rectangle(position, w, h)};
	}

	@Override
	public void move() {
		//Do nothing for static blocks
	}

	@Override
	public void undoMove() {
		//Do nothing for static blocks
	}

	@Override
	public void addToGame(Game game) {
		//Do nothing
	}

	public Vector2D getPosition() {
		return this.position;
	}

	@Override
	public Rectangle[] getBorderShapes() {
		return this.borders;
	}
}
