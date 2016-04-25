package hu.bme.mit.ingraph.algebra;

import hu.bme.mit.ingraph.algebra.visitor.Visitor;
import lombok.Builder;

@Builder
public class InputNode extends AbstractNode {

	protected String name;

	public long accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
