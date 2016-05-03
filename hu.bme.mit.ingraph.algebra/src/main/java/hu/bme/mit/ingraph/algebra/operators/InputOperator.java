package hu.bme.mit.ingraph.algebra.operators;

import hu.bme.mit.ingraph.algebra.operators.visitors.OperatorTreeVisitor;
import lombok.Builder;
import lombok.Getter;

@Builder
public class InputOperator extends AbstractOperator {

	@Getter protected String name;
	
	@Override
	public <R> R accept(final OperatorTreeVisitor<? extends R> visitor) {
		return visitor.visit(this);
	}
	
}
