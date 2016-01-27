package org.supermario.view;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javafx.scene.canvas.GraphicsContext;

import org.supermario.model.Direction;
import org.supermario.model.GameConstants;
import org.supermario.model.GameElement;
import org.supermario.model.Vector2D;

public class PlayerView extends GameElementView {
	private GameElement player;
	private Map<Direction, Animation> animationsMap;
	
	public PlayerView(GameElement player, GraphicsContext gc) {
		super(gc, "img/Mario_Stand.png");
		this.player = player;

		Animation left = this.getSprite().addAnimation("img/Mario_Left1.png", "img/Mario_Left2.png", "img/Mario_Left3.png");
		Animation right = this.getSprite().addAnimation("img/Mario_Right1.png", "img/Mario_Right2.png", "img/Mario_Right3.png");
		
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
		Direction direction = this.player.getDirection();
		if (this.animationsMap.containsKey(direction)) {
			Animation animation = this.animationsMap.get(direction);
			this.getSprite().changeCurrentAnimation(animation);
		}
		else
			this.getSprite().setDefaultAnimation();
	}

	@Override
	public Vector2D getElementPosition() {
		return this.player.getPosition();
	}

	@Override
	public int getElementHeight() {
		return GameConstants.MARIO_HEIGHT;
	}
	
	@Override
	public int getElementWidth() {
		return GameConstants.MARIO_WIDTH;
	}
	
}
