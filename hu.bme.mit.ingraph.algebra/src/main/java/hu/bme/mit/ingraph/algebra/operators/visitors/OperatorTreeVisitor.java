package hu.bme.mit.ingraph.algebra.operators.visitors;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;

public interface OperatorTreeVisitor<R> {
	
	public R visit(final InputOperator operator);
	public R visit(final JoinOperator operator);
	public R visit(final ProductionOperator operator);
	
}
