package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public class StatisticsVisitor implements TreeVisitor<Long> {

	public Long visit(final ProductionOperation operation) {
		return operation.getParent().accept(this);
	}

	public Long visit(final JoinOperation operation) {
		final long leftTuples = operation.getLeftParent().accept(this);
		final long rightTuples = operation.getRightParent().accept(this);
		final long tuples = Math.round(leftTuples * rightTuples * operation.getDensity());

		return tuples;
	}

	public Long visit(InputOperation operation) {
		return Long.valueOf(operation.getTuples());
	}

}
