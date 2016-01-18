package org.supermario.view;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javafx.scene.canvas.GraphicsContext;

import org.supermario.model.Block;
import org.supermario.model.Direction;
import org.supermario.model.GameConstants;
import org.supermario.model.Player;
import org.supermario.model.Vector2D;

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
