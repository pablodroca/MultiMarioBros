package org.supermario.controller;

import org.supermario.model.GameElementVisitor;
import org.supermario.model.elements.Block;
import org.supermario.model.elements.Koopa;
import org.supermario.model.elements.Player;
import org.supermario.view.GameView;
import org.supermario.view.elementViews.BlockView;
import org.supermario.view.elementViews.KoopaView;
import org.supermario.view.elementViews.PlayerView;

public class ViewsBuilderVisitor implements GameElementVisitor {

	private GameView view;

	public ViewsBuilderVisitor(GameView view) {
		this.view = view;
	}

	@Override
	public void visit(Koopa koopa) {
		KoopaView view = new KoopaView(koopa, this.view.getGraphicsContext2D());
		this.view.registerView(koopa, view);
	}

	@Override
	public void visit(Player player) {
		PlayerView view = new PlayerView(player, this.view.getGraphicsContext2D());
		this.view.registerView(player, view);
	}

	@Override
	public void visit(Block block) {
		BlockView view = new BlockView(block, this.view.getGraphicsContext2D());
		this.view.registerView(block, view);
	}

}
