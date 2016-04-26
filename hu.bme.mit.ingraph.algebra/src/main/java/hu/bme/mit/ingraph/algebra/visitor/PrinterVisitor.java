package hu.bme.mit.ingraph.algebra.visitor;

import hu.bme.mit.ingraph.algebra.InputNode;
import hu.bme.mit.ingraph.algebra.JoinNode;
import hu.bme.mit.ingraph.algebra.ProductionNode;

public class PrinterVisitor implements Visitor {

	public long visit(final ProductionNode node) {
		System.out.println("Production node");
		node.getParent().accept(this);
		return 0;
	}

	public long visit(final JoinNode node) {
		System.out.println("Join node: " + node.getLms() + " " + node.getRms());
		node.getLeftParent().accept(this);
		node.getRightParent().accept(this);
		return 0;
	}

	public long visit(InputNode node) {
		System.out.println("Input node: " + node.getName());
		return 0;
	}

}
