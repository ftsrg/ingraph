package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public class StatisticsVisitor implements Visitor {

	public long visit(final ProductionOperation node) {
		return node.getParent().accept(this);
	}

	public long visit(final JoinOperation node) {
		final long leftTuples = node.getLeftParent().accept(this);
		final long rightTuples = node.getRightParent().accept(this);
		final long tuples = Math.round(leftTuples * rightTuples * node.getDensity());

		return tuples;
	}

	public long visit(InputOperation node) {
		return node.getTuples();
	}

}
