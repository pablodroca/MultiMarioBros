package org.supermario.view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.canvas.GraphicsContext;

import org.supermario.common.Vector2D;
import org.supermario.model.GameConstants;
import org.supermario.view.drawing.Sprite;

public abstract class GameElementView implements Observer {
	private Sprite sprite;
	private double positionScale;

	public GameElementView(GraphicsContext gc, String... imagePaths) {
		super();
		this.sprite = new Sprite(gc, imagePaths);
	}

	public void scaleTo(double width, double height) {
		this.positionScale = width / (GameConstants.MARIO_WIDTH * ViewConstants.QTY_MARIO_FIT_IN_SCREEN);
	}

	public void draw() {
		Vector2D position = this.getElementPosition();
		int x = (int) (position.getX() * this.positionScale);
		int y = (int) (position.getY() * this.positionScale);
		int halfWidth = (int)(0.5 * this.positionScale * this.getElementWidth());
		int halfHeight = (int)(0.5 * this.positionScale * this.getElementHeight());
		this.sprite.render( x - halfWidth, y - halfHeight, 
							x + halfWidth, y + halfHeight);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//do nothing
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}

	public abstract int getElementHeight();	
	public abstract int getElementWidth();
	public abstract Vector2D getElementPosition();
 }