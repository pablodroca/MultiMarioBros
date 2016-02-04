package org.supermario.controller;

import org.supermario.model.GameElementVisitor;
import org.supermario.model.elements.Block;
import org.supermario.model.elements.Player;
import org.supermario.model.elements.Koopa;
import org.supermario.view.GameView;

public class ViewsBuilderVisitor implements GameElementVisitor {

	private GameView view;

	public ViewsBuilderVisitor(GameView view) {
		this.view = view;
	}

	@Override
	public void visit(Koopa koopa) {
		this.view.register(koopa);
	}

	@Override
	public void visit(Player player) {
		this.view.register(player);
	}

	@Override
	public void visit(Block block) {
		this.view.register(block);
	}

}
