package hu.bme.mit.ingraph.algebra.operations;

import hu.bme.mit.ingraph.algebra.operations.visitors.Visitable;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractOperation implements Visitable {

	@Getter	@Setter	protected int tuples;

}
