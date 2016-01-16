package org.supermario.view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	private GraphicsContext graphics;
	private List<Animation> animations;
	private int animationsIndex;
	
	public Sprite(GraphicsContext graphics, String... imagePaths) {
		this.graphics = graphics;
		this.animations = new ArrayList<Animation>();
		Animation defaultAnimation = new Animation(imagePaths);
		this.animations.add(defaultAnimation);
		this.animationsIndex = 0;
	}
	
	public Animation addAnimation(String... imagePaths) {
		Animation animation = new Animation(imagePaths);
		this.animations.add(animation);
		return animation;
	}
	
	public void render(final int x, final int y, final int xMax, final int yMax) {
		Platform.runLater(new Runnable(){
			public void run() {
				animations.get(animationsIndex).animateFrame(graphics, x, y, xMax, yMax);
			}	
		});
	}
	
	public void changeCurrentAnimation(Animation animation) {
		this.animationsIndex = this.animations.indexOf(animation);
	}
	
	public void setDefaultAnimation() {
		this.animationsIndex = 0;
	}
}
