package hu.bme.mit.ingraph.rete.visitors;

import hu.bme.mit.ingraph.rete.nodes.InputNode;
import hu.bme.mit.ingraph.rete.nodes.JoinNode;
import hu.bme.mit.ingraph.rete.nodes.ProductionNode;

public interface ReteVisitor<R> {
	
	public R visit(final JoinNode node);
	public R visit(final InputNode node);
	public R visit(final ProductionNode node);
	
}
