package org.supermario.common;

public class Vector2D {
	private int x;
	private int y;

	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Vector2D sum(Vector2D other) {
		return new Vector2D(this.x + other.x, this.y + other.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		Vector2D toCompare = (Vector2D)obj;
		return this.x == toCompare.x && this.y == toCompare.y;
	}

	public Vector2D substract(Vector2D other) {
		return new Vector2D(this.x - other.x, this.y - other.y);
	}

	public Vector2D negative() {
		return new Vector2D(-this.x, -this.y);
	}
}
