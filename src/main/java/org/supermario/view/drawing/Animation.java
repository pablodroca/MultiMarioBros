package org.supermario.view.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Animation {
	private Image[] images;
	private int imageIndex;
	
	public Animation(String... imagePaths) {
		this.images = new Image[imagePaths.length];
		int i = 0;
		for (String imagePath : imagePaths) {
			images[i++] = new Image(imagePath);
		}
		this.imageIndex = 0;
	}

	public void animateFrame(GraphicsContext graphics, int x, int y, int xMax, int yMax) {
		graphics.drawImage(images[imageIndex], x, y, xMax - x, yMax - y);
		imageIndex = (imageIndex + 1) % images.length;
	}
}
