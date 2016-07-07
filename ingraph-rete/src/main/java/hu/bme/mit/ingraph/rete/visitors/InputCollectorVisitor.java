package hu.bme.mit.ingraph.rete.visitors;

import java.util.Collection;

import org.eclipse.collections.impl.factory.Lists;

import hu.bme.mit.ingraph.rete.nodes.InputNode;
import hu.bme.mit.ingraph.rete.nodes.JoinNode;
import hu.bme.mit.ingraph.rete.nodes.ProductionNode;
import lombok.Getter;

public class InputCollectorVisitor implements ReteVisitor<Void> {

	@Getter protected Collection<InputNode> inputNodes = Lists.mutable.of();
	
	protected InputCollectorVisitor() {

	}

	public static InputCollectorVisitor create() {
		return new InputCollectorVisitor();
	}

	@Override
	public Void visit(InputNode node) {
		inputNodes.add(node);
		return null;
	}

	@Override
	public Void visit(JoinNode node) {
		node.getLeftParent().accept(this);
		node.getRightParent().accept(this);
		return null;
	}

	@Override
	public Void visit(ProductionNode node) {
		node.getParent().accept(this);
		return null;
	}

}
