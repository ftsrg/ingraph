package hu.bme.mit.ingraph.algebra.visitor;

import hu.bme.mit.ingraph.algebra.InputNode;
import hu.bme.mit.ingraph.algebra.JoinNode;
import hu.bme.mit.ingraph.algebra.ProductionNode;

public class StatisticsVisitor implements Visitor {

	public long visit(final ProductionNode node) {
		return node.getParent().accept(this);
	}

	public long visit(final JoinNode node) {
		final long leftTuples = node.getLeftParent().accept(this);
		final long rightTuples = node.getRightParent().accept(this);
		final long tuples = Math.round(leftTuples * rightTuples * node.getDensity());
		
		return tuples;
	}

	public long visit(InputNode node) {
		return node.getTuples();		
	}


}
