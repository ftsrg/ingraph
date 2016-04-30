package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;
import lombok.Getter;

public class ProductionNode extends AbstractNode<ProductionOperator> {

	@Getter private AbstractNode<? extends AbstractOperator> parentNode;

	public ProductionNode(ProductionOperator operator) {
		super(operator);
	}

	public void subscribe(AbstractNode<? extends AbstractOperator> parentNode) {
		this.parentNode = parentNode; 
	}
	
}
