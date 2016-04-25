package hu.bme.mit.ingraph.algebra.visitor;

import hu.bme.mit.ingraph.algebra.InputNode;
import hu.bme.mit.ingraph.algebra.JoinNode;
import hu.bme.mit.ingraph.algebra.ProductionNode;

public interface Visitor {
	
	public long visit(InputNode node);
	public long visit(JoinNode node);
	public long visit(ProductionNode node);
	
}
