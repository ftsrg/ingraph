package hu.bme.mit.ingraph.rete.builder;

import hu.bme.mit.ingraph.algebra.operators.AbstractOperator;
import hu.bme.mit.ingraph.algebra.operators.InputOperator;
import hu.bme.mit.ingraph.algebra.operators.JoinOperator;
import hu.bme.mit.ingraph.algebra.operators.ProductionOperator;
import hu.bme.mit.ingraph.algebra.operators.visitors.TreeVisitor;
import hu.bme.mit.ingraph.rete.nodes.AbstractNode;
import hu.bme.mit.ingraph.rete.nodes.InputNode;
import hu.bme.mit.ingraph.rete.nodes.JoinNode;
import hu.bme.mit.ingraph.rete.nodes.ProductionNode;

public class ReteBuilder implements TreeVisitor<AbstractNode<? extends AbstractOperator>> {

	public AbstractNode<? extends AbstractOperator> visit(final InputOperator operator) {
		final InputNode inputNode = new InputNode(operator);
		
		return inputNode;
	}

	public AbstractNode<? extends AbstractOperator> visit(final JoinOperator operator) {
		final JoinNode joinNode = new JoinNode(operator);
		
		final AbstractNode<? extends AbstractOperator> leftParentNode = operator.getLeftParent().accept(this);
		final AbstractNode<? extends AbstractOperator> rightParentNode = operator.getRightParent().accept(this);
		joinNode.subscribe(leftParentNode, rightParentNode);
		
		return joinNode;
	}

	public AbstractNode<? extends AbstractOperator> visit(final ProductionOperator operator) {
		final ProductionNode productionNode = new ProductionNode(operator);
		
		final AbstractNode<? extends AbstractOperator> parentNode = operator.getParent().accept(this);
		productionNode.subscribe(parentNode);
		
		return productionNode;
	}

}
