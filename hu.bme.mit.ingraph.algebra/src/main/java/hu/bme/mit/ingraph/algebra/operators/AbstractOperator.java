package hu.bme.mit.ingraph.algebra.operators;

import hu.bme.mit.ingraph.algebra.operators.visitors.Visitable;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractOperator implements Visitable {

	@Getter	@Setter	protected int tuples;
	
}
