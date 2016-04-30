package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import hu.bme.mit.ingraph.rete.visitors.ReteVisitable;
import lombok.Getter;

public abstract class AbstractNode<TOperator extends AbstractOperator> implements ReteVisitable {

	@Getter	protected TOperator operator;

	public AbstractNode(TOperator operator) {
		this.operator = operator;
	}

}
