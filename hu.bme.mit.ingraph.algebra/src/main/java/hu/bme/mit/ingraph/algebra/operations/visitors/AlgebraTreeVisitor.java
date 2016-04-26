package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public interface AlgebraTreeVisitor {
	
	public long visit(InputOperation operation);
	public long visit(JoinOperation operation);
	public long visit(ProductionOperation operation);
	
}
