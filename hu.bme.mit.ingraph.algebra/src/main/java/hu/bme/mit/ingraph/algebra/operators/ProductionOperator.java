package hu.bme.mit.ingraph.algebra.operators;

import hu.bme.mit.ingraph.algebra.operators.visitors.OperatorTreeVisitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductionOperator extends AbstractOperator {

	@Getter protected WorkerOperator parent;

	public <R> R accept(OperatorTreeVisitor<? extends R> visitor) {
		return visitor.visit(this);
	}
	
}
