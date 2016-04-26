package hu.bme.mit.ingraph.rete;

import hu.bme.mit.ingraph.algebra.operations.AbstractOperation;
import lombok.Getter;

public abstract class AbstractNode<TOperation extends AbstractOperation> {

	@Getter protected TOperation operation;
	
}
