package hu.bme.mit.ingraph.algebra.operations;

import hu.bme.mit.ingraph.algebra.operations.visitors.AlgebraTreeVisitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductionOperation extends AbstractOperation {

	@Getter protected WorkerOperation parent;

	public long accept(AlgebraTreeVisitor visitor) {
		return visitor.visit(this);
	}

}
