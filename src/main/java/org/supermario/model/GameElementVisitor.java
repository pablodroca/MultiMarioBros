package org.supermario.model;

import org.supermario.model.elements.Block;
import org.supermario.model.elements.Player;
import org.supermario.model.elements.Koopa;

public interface GameElementVisitor {
	public void visit(Koopa koopa);	
	public void visit(Player player);
	public void visit(Block block);
}
