package org.supermario.model;

public interface GameElementVisitor {
	public void visit(Koopa koopa);	
	public void visit(Player player);
	public void visit(Block block);
}
