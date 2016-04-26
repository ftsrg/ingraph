package hu.bme.mit.ingraph.algebra.operations;

import hu.bme.mit.ingraph.algebra.operations.visitors.Visitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class InputOperation extends AbstractOperation {

	@Getter protected String name;

	public long accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
