package hu.bme.mit.ingraph.rete;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import hu.bme.mit.ingraph.algebra.operators.BetaOperator;
import lombok.Getter;

public class BetaNode<TBetaOperator extends BetaOperator> extends WorkerNode<TBetaOperator> {

	@Getter AbstractNode<? extends AbstractOperator> leftParent;
	@Getter AbstractNode<? extends AbstractOperator> rightParent;
	
	public BetaNode(TBetaOperator operator) {
		super(operator);
	}

	public void subscribe(final AbstractNode<? extends AbstractOperator> leftParent, final AbstractNode<? extends AbstractOperator> rightParent) {
		this.leftParent = leftParent;
		this.rightParent = rightParent;
	}

}
