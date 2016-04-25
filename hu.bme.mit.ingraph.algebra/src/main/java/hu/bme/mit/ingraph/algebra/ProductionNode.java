package hu.bme.mit.ingraph.algebra;

import hu.bme.mit.ingraph.algebra.visitor.Visitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductionNode extends AbstractNode {

	@Getter protected WorkerNode parent;

	public long accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
