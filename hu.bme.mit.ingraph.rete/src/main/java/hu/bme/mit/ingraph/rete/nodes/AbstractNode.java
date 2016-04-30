package hu.bme.mit.ingraph.rete.nodes;

import org.apache.commons.lang.StringUtils;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import lombok.Getter;

public abstract class AbstractNode<TOperator extends AbstractOperator> {

	@Getter protected TOperator operator;

	public AbstractNode(TOperator operator) {
		this.operator = operator;
	}
	
	// TODO convert these to a visitor
	public abstract String prettyPrint(final int indentation, int indentationStep);

	protected String indent(int indentation) {
		return StringUtils.repeat(" ", indentation);
	}

}
