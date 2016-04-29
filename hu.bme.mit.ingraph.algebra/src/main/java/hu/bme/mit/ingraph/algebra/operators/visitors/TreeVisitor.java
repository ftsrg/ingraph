package hu.bme.mit.ingraph.algebra.operators.visitors;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;

public interface TreeVisitor<R> {
	
	public R visit(final InputOperator operation);
	public R visit(final JoinOperator operation);
	public R visit(final ProductionOperator operation);
	
}
