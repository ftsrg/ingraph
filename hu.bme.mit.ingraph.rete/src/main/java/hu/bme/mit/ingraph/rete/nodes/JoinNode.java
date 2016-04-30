package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.rete.visitors.ReteVisitor;

public class JoinNode extends BetaNode<JoinOperator> {

	public JoinNode(final JoinOperator operator) {
		super(operator);
	}

	@Override
	public <R> R accept(ReteVisitor<? extends R> visitor) {
		return visitor.visit(this);
	}

}
