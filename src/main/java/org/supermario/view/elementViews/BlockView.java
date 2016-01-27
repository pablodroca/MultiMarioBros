package org.supermario.view.elementViews;

import java.util.Observer;

import javafx.scene.canvas.GraphicsContext;

import org.supermario.common.Vector2D;
import org.supermario.model.GameConstants;
import org.supermario.model.elements.Block;

public class BlockView extends GameElementView implements Observer {
	private Block block;
	
	public BlockView(Block block, GraphicsContext gc) {
		super(gc, "img/Block.png");
		this.block = block;
	}

	@Override
	public int getElementHeight() {
		return GameConstants.BLOCK_SIDE_SIZE;
	}

	@Override
	public int getElementWidth() {
		return GameConstants.BLOCK_SIDE_SIZE;
	}

	@Override
	public Vector2D getElementPosition() {
		return this.block.getPosition();
	}

}
