package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.AlphaOperator;

public abstract class AlphaNode<TAlphaOperator extends AlphaOperator> extends WorkerNode<TAlphaOperator> {

	public AlphaNode(TAlphaOperator operator) {
		super(operator);
	}

}
