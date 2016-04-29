package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public interface TreeVisitor<R> {
	
	public R visit(final InputOperation operation);
	public R visit(final JoinOperation operation);
	public R visit(final ProductionOperation operation);
	
}
