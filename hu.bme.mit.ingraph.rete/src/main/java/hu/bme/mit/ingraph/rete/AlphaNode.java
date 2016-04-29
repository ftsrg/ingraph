package hu.bme.mit.ingraph.rete;

import hu.bme.mit.ingraph.algebra.operators.AlphaOperator;

public class AlphaNode<TAlphaOperator extends AlphaOperator> extends WorkerNode<TAlphaOperator> {

	public AlphaNode(TAlphaOperator operator) {
		super(operator);
	}

}
