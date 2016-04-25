package hu.bme.mit.ingraph.algebra;

import hu.bme.mit.ingraph.algebra.visitor.Visitable;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractNode implements Visitable {

	@Getter	@Setter	protected int tuples;

}
