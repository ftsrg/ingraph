package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public class StatisticsVisitor implements AlgebraTreeVisitor {

	public long visit(final ProductionOperation operation) {
		return operation.getParent().accept(this);
	}

	public long visit(final JoinOperation operation) {
		final long leftTuples = operation.getLeftParent().accept(this);
		final long rightTuples = operation.getRightParent().accept(this);
		final long tuples = Math.round(leftTuples * rightTuples * operation.getDensity());

		return tuples;
	}

	public long visit(InputOperation operation) {
		return operation.getTuples();
	}

}
