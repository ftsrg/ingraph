package hu.bme.mit.ingraph.rete.builder;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;
import hu.bme.mit.ingraph.algebra.operations.visitors.TreeVisitor;
import hu.bme.mit.ingraph.rete.InputNode;
import hu.bme.mit.ingraph.rete.JoinNode;
import hu.bme.mit.ingraph.rete.ProductionNode;

public class ReteBuilder implements TreeVisitor<Void> {

	public Void visit(final InputOperation operation) {
		final InputNode inputNode = new InputNode(operation);
		return null;
	}

	public Void visit(final JoinOperation operation) {
		final JoinNode joinNode = new JoinNode(operation);
		return null;
	}

	public Void visit(final ProductionOperation operation) {
		final ProductionNode productionNode = new ProductionNode(operation);
		operation.getParent().accept(this);
		return null;
	}
	
}
