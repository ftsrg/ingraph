package hu.bme.mit.ingraph.algebra.operators;

import hu.bme.mit.ingraph.algebra.operators.visitors.OperatorTreeVisitable;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractOperator implements OperatorTreeVisitable {

	@Getter	@Setter	protected int tuples;
	
}
