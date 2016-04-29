package hu.bme.mit.ingraph.rete;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import lombok.Getter;

public abstract class AbstractNode<TOperator extends AbstractOperator> {

	@Getter protected TOperator operator;

	public AbstractNode(TOperator operator) {
		this.operator = operator;
	}
	
}
