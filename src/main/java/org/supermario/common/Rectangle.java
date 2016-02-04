package org.supermario.common;

public class Rectangle {
	private Vector2D topLeft;
	private Vector2D bottomRight;
	private int width;
	private int height;
	
	public Rectangle(Vector2D centerPosition, int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.computeBoundaries(centerPosition);
	}

	public void moveAbsolute(Vector2D centerPosition) {
		this.computeBoundaries(centerPosition);
	}
	
	public boolean inCollisionWith(Rectangle target) {
		return ! ( bottomRight.getX() < target.topLeft.getX() 
				|| bottomRight.getY() < target.topLeft.getY()
				|| topLeft.getX() > target.bottomRight.getX()
				|| topLeft.getY() > target.bottomRight.getY()); 
	}
	
	private void computeBoundaries(Vector2D centerPosition) {
		int x = centerPosition.getX() - (width/2);
		int y = centerPosition.getY() - (height/2);
		this.topLeft = new Vector2D(x, y);
		this.bottomRight = new Vector2D(x + width, y + height);		
	}

	public void moveRelative(Vector2D movement) {
		Vector2D center = this.topLeft.sum(new Vector2D(width/2, height/2));
		this.computeBoundaries(center.sum(movement));
	}

	public boolean isAbove(Rectangle other, int tolerance) {
		return bottomRight.getY() < other.topLeft.getY() + tolerance;
	}

	public boolean isBelow(Rectangle other, int tolerance) {
		return other.isAbove(this, tolerance);
	}
}
