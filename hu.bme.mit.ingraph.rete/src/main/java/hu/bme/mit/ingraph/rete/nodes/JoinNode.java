package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.JoinOperator;

public class JoinNode extends BetaNode<JoinOperator> {

	public JoinNode(final JoinOperator operator) {
		super(operator);
	}	

}
