package org.supermario.view;

import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import javafx.scene.canvas.GraphicsContext;

import org.supermario.common.Direction;
import org.supermario.common.Vector2D;
import org.supermario.model.GameConstants;
import org.supermario.model.elements.GameElement;
import org.supermario.view.drawing.Animation;

public class KoopaView extends GameElementView {
	private GameElement koopa;
	private Map<Direction, Animation> animationsMap;
	
	public KoopaView(GameElement koopa, GraphicsContext gc) {
		super(gc, "img/Koopa_Left1.png");
		this.koopa = koopa;

		Animation left = this.getSprite().addAnimation("img/Koopa_Left1.png", "img/Koopa_Left2.png");
		Animation right = this.getSprite().addAnimation("img/Koopa_Right1.png", "img/Koopa_Right2.png");
		
		this.animationsMap = new TreeMap<Direction, Animation>();
		this.animationsMap.put(Direction.LEFT, left);
		this.animationsMap.put(Direction.RIGHT, right);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.defineAnimationForDirection();
		super.update(arg0, arg1);
	}

	private void defineAnimationForDirection() {
		Direction direction = this.koopa.getDirection();
		if (this.animationsMap.containsKey(direction)) {
			Animation animation = this.animationsMap.get(direction);
			this.getSprite().changeCurrentAnimation(animation);
		}
		else
			this.getSprite().setDefaultAnimation();
	}

	@Override
	public Vector2D getElementPosition() {
		return this.koopa.getPosition();
	}

	@Override
	public int getElementHeight() {
		return GameConstants.KOOPA_HEIGHT;
	}
	
	@Override
	public int getElementWidth() {
		return GameConstants.KOOPA_WIDTH;
	}
	
}
