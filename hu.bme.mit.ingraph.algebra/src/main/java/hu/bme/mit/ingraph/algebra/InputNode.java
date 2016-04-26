package hu.bme.mit.ingraph.algebra;

import hu.bme.mit.ingraph.algebra.visitor.Visitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class InputNode extends AbstractNode {

	@Getter protected String name;

	public long accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
