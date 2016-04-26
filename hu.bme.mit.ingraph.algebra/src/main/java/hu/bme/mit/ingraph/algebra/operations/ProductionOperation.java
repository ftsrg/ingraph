package hu.bme.mit.ingraph.algebra.operations;

import hu.bme.mit.ingraph.algebra.operations.visitors.Visitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductionOperation extends AbstractOperation {

	@Getter protected WorkerOperation parent;

	public long accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
