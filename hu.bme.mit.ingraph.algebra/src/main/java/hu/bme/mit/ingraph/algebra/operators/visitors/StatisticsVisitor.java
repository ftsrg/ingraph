package hu.bme.mit.ingraph.algebra.operators.visitors;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;

public class StatisticsVisitor implements OperatorTreeVisitor<Long> {

	protected StatisticsVisitor() {

	}

	public static StatisticsVisitor create() {
		return new StatisticsVisitor();
	}

	public Long visit(final ProductionOperator operation) {
		return operation.getParent().accept(this);
	}

	public Long visit(final JoinOperator operation) {
		final long leftTuples = operation.getLeftParent().accept(this);
		final long rightTuples = operation.getRightParent().accept(this);
		final long tuples = Math.round(leftTuples * rightTuples * operation.getDensity());

		return tuples;
	}

	public Long visit(InputOperator operation) {
		return Long.valueOf(operation.getTuples());
	}

}
