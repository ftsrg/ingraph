package hu.bme.mit.ingraph.rete;

import hu.bme.mit.ingraph.algebra.operations.JoinOperation;

public class JoinNode extends WorkerNode<JoinOperation> {

	public JoinNode(JoinOperation operation) {
		super(operation);
	}	

}
