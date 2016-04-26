package hu.bme.mit.ingraph.algebra.operations;

import hu.bme.mit.ingraph.algebra.operations.visitors.AlgebraTreeVisitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class InputOperation extends AbstractOperation {

	@Getter protected String name;

	public long accept(AlgebraTreeVisitor visitor) {
		return visitor.visit(this);
	}

}
