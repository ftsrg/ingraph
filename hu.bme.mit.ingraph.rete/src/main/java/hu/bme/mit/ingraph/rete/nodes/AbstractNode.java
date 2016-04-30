package hu.bme.mit.ingraph.rete.nodes;

import org.apache.commons.lang.StringUtils;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import lombok.Getter;

public abstract class AbstractNode<TOperator extends AbstractOperator> {

	@Getter protected TOperator operator;

	public AbstractNode(TOperator operator) {
		this.operator = operator;
	}

	public abstract String prettyPrint(final int indentation);
	
	protected String indent(int indentation) {
		return StringUtils.repeat(" ", indentation);
	}

}
