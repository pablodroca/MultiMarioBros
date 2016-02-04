package org.supermario.model;

import org.supermario.model.elements.Block;
import org.supermario.model.elements.GameElement;
import org.supermario.model.elements.Koopa;
import org.supermario.model.elements.Player;

public class CollisionsResolverVisitor implements GameElementVisitor {
	private GameElement element;
	
	public CollisionsResolverVisitor(GameElement element) {
		this.element = element;
	}
	
	@Override
	public void visit(Koopa koopa) {
		this.element.resolveCollisionWith(koopa);
	}

	@Override
	public void visit(Player player) {
		this.element.resolveCollisionWith(player);
	}

	@Override
	public void visit(Block block) {
		this.element.resolveCollisionWith(block);
	}

}
