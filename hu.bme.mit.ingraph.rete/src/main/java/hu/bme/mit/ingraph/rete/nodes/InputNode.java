package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.rete.visitors.ReteVisitor;

public class InputNode extends AbstractNode<InputOperator> {

	public InputNode(InputOperator operator) {
		super(operator);
	}

	@Override
	public <R> R accept(ReteVisitor<? extends R> visitor) {
		return visitor.visit(this);
	}

}
