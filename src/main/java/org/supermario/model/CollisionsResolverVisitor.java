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
		// TODO resolve collision with koopa
		this.element.resolveCollision();
	}

	@Override
	public void visit(Player player) {
		// TODO resolve collision with player
		this.element.resolveCollision();
	}

	@Override
	public void visit(Block block) {
		// TODO resolve collision with block
		this.element.resolveCollision();
	}

}
