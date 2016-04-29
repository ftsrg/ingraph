package hu.bme.mit.ingraph.rete;

import hu.bme.mit.ingraph.algebra.operators.WorkerOperator;

public abstract class WorkerNode<TWorkerOperator extends WorkerOperator> extends AbstractNode<TWorkerOperator> {

	public WorkerNode(final TWorkerOperator operator) {
		super(operator);
	}

}
