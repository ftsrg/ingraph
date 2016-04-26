package hu.bme.mit.ingraph.algebra.operations.visitors;

import hu.bme.mit.ingraph.algebra.operations.InputOperation;
import hu.bme.mit.ingraph.algebra.operations.JoinOperation;
import hu.bme.mit.ingraph.algebra.operations.ProductionOperation;

public interface Visitor {
	
	public long visit(InputOperation node);
	public long visit(JoinOperation node);
	public long visit(ProductionOperation node);
	
}
