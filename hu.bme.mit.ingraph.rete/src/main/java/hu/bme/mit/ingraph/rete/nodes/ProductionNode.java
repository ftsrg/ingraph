package hu.bme.mit.ingraph.rete.nodes;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;
import hu.bme.mit.ingraph.rete.visitors.ReteVisitor;
import lombok.Getter;

public class ProductionNode extends AbstractNode<ProductionOperator> {

	@Getter private AbstractNode<? extends AbstractOperator> parent;

	public ProductionNode(ProductionOperator operator) {
		super(operator);
	}

	public void subscribe(AbstractNode<? extends AbstractOperator> parent) {
		this.parent = parent; 
	}

	@Override
	public <R> R accept(ReteVisitor<? extends R> visitor) {
		return visitor.visit(this);
	}
	
}
